package hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * String All Anagrams Find all occurrence of anagrams of a given string s in a
 * given string l. Return the list of starting indices.
 *
 * Assumptions
 *
 * s is not null or empty. l is not null. Examples
 *
 * l = "abcbac", s = "ab", return [0, 3] since the substring with length 2
 * starting from index 0/3 are all anagrams of "ab" ("ab", "ba").
 */
public class AllAnagrams {
	public List<Integer> allAnagrams(String s, String l) {
		// Assumptions:
		// s is not null or empty; l is not null.
		List<Integer> result = new ArrayList<>();
		if (s.length() > l.length()) {
			return result;
		}
		Map<Character, Integer> wordCounts = getWordCounts(s);
		int charToMatchCnt = wordCounts.size();
		int right = 0; 
		int left = 0;
		while (right < l.length()) {
			char cur = l.charAt(right);
			if (wordCounts.containsKey(cur)) {
				int cnt = wordCounts.get(cur);
				wordCounts.put(cur, cnt - 1);
				if (cnt - 1 == 0) {
					charToMatchCnt -= 1;
				}
				if (cnt == 0) {   //!!! also need this part 
					charToMatchCnt += 1;
				}
			}
			left = right - s.length() + 1;
			if (left > 0) {
				cur = l.charAt(left - 1);  // !!! change to left - 1 from left
				if (wordCounts.containsKey(cur)) {
					int cnt = wordCounts.get(cur);
					wordCounts.put(cur, cnt + 1);
					if (cnt + 1 == 0) {
						charToMatchCnt -= 1;
					}
					if (cnt == 0) {
						charToMatchCnt += 1;
					}
				}
			}
			// !!! this should be outside of if (left > 0) check!!!, otherwise missing out  correct cases
			if (charToMatchCnt == 0) {
				result.add(left);
			}

			right++;
		}
		return result;

	}

	private Map<Character, Integer> getWordCounts(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char ch : s.toCharArray()) {
			Integer count = map.get(ch);
			if (count == null) {
				map.put(ch, 1);
			} else {
				map.put(ch, count + 1);
			}
		}
		return map;
	}

	//////////////////////////////////////////////////////////////////////////////////
	// ref
	public List<Integer> allAnagrams_ref(String s, String l) {
		// Find all anagrams of String s in String l, return all the starting indices.
		// Assumptions: s, l are not null, s is not empty.
		List<Integer> result = new ArrayList<>();
		if (l.length() == 0) {
			return result;
		}
		// when s is longer than l, there is no way any of the substrings of l
		// could be an anagram of s.
		if (s.length() > l.length()) {
			return result;
		}
		// This map records for each of the distinct characters in s,
		// how many characters are neede.
		// e.g. s = "abbc", map = {'a': 1, 'b': 2, 'c':1}
		// when we get an instance of 'a' in l, we let count of 'a' decremented by 1,
		// and only when the count is from 1 to 0, we have 'a' totally matched.
		Map<Character, Integer> map = countMap(s);
		// Record how many distinct characters have been matched.
		// only when all the distinct characters are matched, A.K.A.
		// match == map.size(), we find an anagram.
		int match = 0;
		// we have a sliding window of size s.ength(), and since the size is fixed,
		// we only need to record the end index of the sliding window.
		// Also, when move the sliding window by one step from left to right,
		// what we only need to change is
		// 1. remove the leftmost character at the previous sliding window.
		// 2. add the rightmost character at the current sliding window.
		for (int i = 0; i < l.length(); i++) {
			// handle the new added character (rightmost) at the current sliding window.
			char tmp = l.charAt(i);
			Integer count = map.get(tmp);
			if (count != null) {
				// the number of needed count should be --.
				// and only when the count is from 1 to 0, we find an additional match.
				map.put(tmp, count - 1);
				if (count == 1) {
					match++;
				}
			}
			// handle the leftmost character at the previous sliding window.
			if (i >= s.length()) {
				tmp = l.charAt(i - s.length());
				count = map.get(tmp);
				if (count != null) {
					// the number of needed count should be ++.
					// and only when the count is from 0 to 1, we are short for one match of
					// distinct character.
					map.put(tmp, count + 1);
					if (count == 0) {
						match--;
					}
				}
			}
			// for the current sliding window, if all the distinct characters are matched
			// (the count are all zero).
			if (match == map.size()) {
				result.add(i - s.length() + 1);
			}
		}
		return result;
	}

	private Map<Character, Integer> countMap(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char ch : s.toCharArray()) {
			Integer count = map.get(ch);
			if (count == null) {
				map.put(ch, 1);
			} else {
				map.put(ch, count + 1);
			}
		}
		return map;
	};

	class Solution {
		public List<Integer> allAnagrams(String s, String l) {
			// aa, aaa --> [0, 1]
			// abbc, abbcabcab, --> [0, 1, 2, 5]
			// note! abc, abcabccefgaegdaes --> [0, 1, 2, 3], l may have other chars
			//
			// Basic idea:
			// get the word count of s in hashmap, and the entry count of the map
			// go through l, keep the track of the hash map values and total counts
			// when map[char]-- --> == 0, increase a totalcount for match
			// everytime a 0 is generated, check totalCount
			// when all totalCount matches , record a index
			// when map[char] < 0 (=0 and then the char hit in l again),
			// move slow pointer to make map[char] == 0, also update totalCount, then check
			// map
			// till fast hit end

			// Assumptions: s is not null or empty, l is not null
			ArrayList<Integer> result = new ArrayList<>();
			if (s.length() > l.length()) {
				return result;
			}
			// Map<Character, Integer> wordCounts = getWordCounts(s);
			// int toMatch = wordCounts.size();
			int slow = 0;
			int fast = 0;
			while (fast < l.length()) {
				char cur = l.charAt(fast);
				// if (wordCounts.containsKey(cur) {
				// // deal with the correct one
				// } else {
				// // adjust slow, or start from fast + 1 ?
				// }
			}
			return result;

		}
	}

}
