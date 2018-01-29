package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Recursion All Permutations I Given a string with no duplicate characters,
 * return a list with all permutations of the characters.
 * 
 * Examples
 * 
 * Set = "abc", all permutations are ["abc", "acb", "bac", "bca", "cab", "cba"]
 * Set = "", all permutations are [""] Set = null, all permutations are []
 * 
 * @author
 *
 */
public class AllPermutationsI {

	public List<String> permutations1(String set) {
		ArrayList<String> res = new ArrayList<String>();
		if (set == null) {
			return res;
		}
		char[] chars = set.toCharArray();
		boolean[] used = new boolean[chars.length];
		StringBuilder perm = new StringBuilder();
		helper(chars, used, perm, res);
		return res;
	}

	private void helper(char[] chars, boolean[] used, StringBuilder perm, ArrayList<String> res) {
		if (perm.length() == chars.length) {
			res.add(perm.toString());
			return;
		}
		for (int i = 0; i < chars.length; i++) {
			if (used[i]) {
			  continue;
			}
			used[i] = true;
			perm.append(chars[i]);
			helper(chars, used, perm, res);
			perm.deleteCharAt(perm.length() - 1);
			used[i] = false;
		}
	}	
	
	// method 1, use DFS with swap method
	public List<String> permutations11(String set) {
		ArrayList<String> res = new ArrayList<String>();
		if (set == null) {
			return res;
		}
		char[] chars = set.toCharArray();
		helper(chars, 0, res);
		return res;
	}

	private void helper(char[] chars, int idx, ArrayList<String> res) {
		if (idx >= chars.length) {
			res.add(new String(chars));
			return;
		}
		for (int i = idx; i < chars.length; i++) {
			swap(chars, idx, i);
			helper(chars, idx + 1, res);
			swap(chars, idx, i);
		}
	}
	
	// self ref, actually no need to use stringbuilder
	public List<String> permutations_self(String set) {
		ArrayList<String> res = new ArrayList<String>();
		if (set == null) {
			return res;
		}
		char[] chars = set.toCharArray();
		StringBuffer sb = new StringBuffer();
		helperSelf(chars, sb, 0, res);
		return res;
	}

	private void helperSelf(char[] chars, StringBuffer sb, int idx, ArrayList<String> res) {
		if (idx >= chars.length) {
			res.add(sb.toString());
			return;
		}
		for (int i = idx; i < chars.length; i++) {
			swap(chars, idx, i);
			sb.append(chars[idx]);
			helperSelf(chars, sb, idx + 1, res);
			sb.deleteCharAt(sb.length() - 1); // !!! important, don't forget this.
			swap(chars, idx, i);
		}
	}

	private void swap(char[] chars, int a, int b) {
		char tmp = chars[a];
		chars[a] = chars[b];
		chars[b] = tmp;
	}
	
	
	// Method 2. main to the order of all the permutations.
	public List<String> permutations(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) {
			return new ArrayList<String>();
		}
		char[] chars = set.toCharArray();
		Arrays.sort(chars);
		// record which index has been used in the original chars.
		boolean[] used = new boolean[chars.length];
		StringBuilder cur = new StringBuilder();
		helperWithOrder(chars, used, cur, result);
		return result;
	}
	
	private void helperWithOrder(char[] chars, boolean[] used, StringBuilder cur, List<String> result) {
		// termination condition:
		// when the permutation contains all the characters in the original array.
		if (cur.length() == chars.length) {
			result.add(cur.toString());
			return;
		}
		// when picking the next char, always according to the order.
		for (int i = 0; i < chars.length; i++) {
			// if a char is already used, we can not pick it for a second time.
			if (!used[i]) {
				used[i] = true;
				cur.append(chars[i]);
				helperWithOrder(chars, used, cur, result);
				used[i] = false;
				// cur.deleteCharAt(i); !!! this is wrong, cur's length is very different from i
				cur.deleteCharAt(cur.length() - 1);
			}
		}
	}
}

class PracticeClassAllPermutation {
	// 1. subproblem: n - 1 chars all per
	// 2. recursion rule: for every perm, insert current char into all different pos, 
	// 3. base case: 0 char,  ""-> "" ; or 1 char,  "a" -> "a"
	public List<String> permutations(String input) {
		List<String> results = new ArrayList<>();
		if (input == null) {
			return results;
		}
		List<String> res = helper(input, 0, results);
		return res;
	}
	
	// here idx is to optimize the substring operation, not for pass top-down info
	private List<String> helper(String input, int idx, List<String> results) {
		if (idx == input.length()) {
			results.add("");
			return results;
		}
		List<String> tmp = helper(input, idx + 1, results);
		
		// ["bc", "cb"]
		
		results = new ArrayList<>();
		for (String s : tmp) {
			// i is the pos to insert, all len + 1 positions
			// here stringbuilder won't help, insert in the middle, sb only good for append.
			for (int i = 0; i <= s.length(); i++) {
				results.add(s.substring(0, i) + input.charAt(idx) + s.substring(i));
			}
		}
		return results;
	}
	
}
