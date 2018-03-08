package hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Find the longest common substring of two given strings.
 * 
 * Assumptions
 * 
 * The two given strings are not null Examples
 * 
 * S = ¡°abcde¡±, T = ¡°cdf¡±, the longest common substring of S and T is ¡°cd¡±
 * 
 * 
 * 
 * 
 * 
 * Medium 2 D Array String
 * 
 * @author
 *
 */
public class LongestCommonSubstring {

	// self, ac
	public String longestCommon(String s, String t) {
		// Assumptions: s, t are not null.
		if (s.length() == 0 || t.length() == 0) {
			return new String();
		}
		HashMap<Character, List<Integer>> map = new HashMap<>();
		if (s.length() > t.length()) {
			String tmp = t;
			t = s;
			s = tmp;
		}
		// pre-process map
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				map.get(s.charAt(i)).add(i);
			} else {
				ArrayList<Integer> arr = new ArrayList<>();
				arr.add(i);
				map.put(s.charAt(i), arr);
			}
		}
		// find longest
		String[] result = new String[] { "" };
		char[] chars = t.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!map.containsKey(chars[i])) {
				continue;
			}
			List<Integer> sIdxs = map.get(chars[i]);
			for (int sidx : sIdxs) {
				checkSubstr(s, chars, sidx, i, result);
			}
		}
		return result[0];
	}

	private void checkSubstr(String s, char[] tchars, int sidx, int tidx, String[] result) {
		int shift = 0;
		while (sidx + shift + 1 < s.length() && tidx + shift + 1 < tchars.length) {
			if (s.charAt(sidx + shift + 1) == tchars[tidx + shift + 1]) {
				shift++;
			} else {
				break;
			}
		}
		if (shift + 1 > result[0].length()) {
			result[0] = new String(tchars, tidx, shift + 1);
		}
	}

	// did not consider the string has duplication case
	public String longestCommon_wrong(String s, String t) {
		// Assumptions: s, t are not null.
		if (s.length() == 0 || t.length() == 0) {
			return "";
		}
		HashMap<Character, Integer> map = new HashMap<>();
		if (s.length() > t.length()) {
			String tmp = t;
			t = s;
			s = tmp;
		}
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), i);
		}
		int start = 0;
		String result = "";
		char[] chars = t.toCharArray();
		while (start < chars.length) {
			if (map.containsKey(chars[start])) {
				int end = start;
				while (end + 1 < chars.length && map.containsKey(chars[end + 1])
						&& map.get(chars[end + 1]) == end + 1) { // !!! need to check boundary
					end += 1;
				}
				if (end - start + 1 > result.length()) {
					result = new String(chars, start, end + 1); // to check
				}
				start = end + 1;
			} else {
				start++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String[] arr = new String[] { "abccddefffghhh", "bdhghhf" };
		String[] arr1 = new String[] { "aaaaaa", "bbb" };
		String s = arr[0];
		String t = arr[1];

		LongestCommonSubstring sol = new LongestCommonSubstring();
		String result = sol.longestCommon(s, t);
		System.out.println(result);
	}
}
