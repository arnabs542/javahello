package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Recursion All Permutations II
 * 
 * Given a string with possible duplicate characters, return a list with all
 * permutations of the characters.
 * 
 * Examples
 * 
 * Examples
 * 
 * Set = "abc", all permutations are ["abc", "acb", "bac", "bca", "cab", "cba"]
 * Set = "aba", all permutations are ["aab", "aba", "baa"] Set = "", all
 * permutations are [""] Set = null, all permutations are [] Set = null, all
 * permutations are []
 * 
 * @author
 *
 */
public class AllPermutationsII {
	// ref method
	public List<String> permutations(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) {
			return result;
		}
		char[] chars = set.toCharArray();
		helper(chars, 0, result);
		return result;
	}

	// index: at the level of index, we are to determine for current permutation, which element
	// is positioned at the index.
	private void helper(char[] chars, int idx, List<String> result) {
		// base case: when we have determined that for all the indices of the current permutations 
		// which element to choose.
		if (idx >= chars.length) {
			result.add(new String(chars));
			return;
		}
		// Notice: the rule is just for the current level, if a certain element is picked,
		// we can not pick any of its duplicates. Therefore we use a set to record
		// all the distinct elements.
		Set<Character> used = new HashSet<>();
		for (int i = idx; i < chars.length; i++) {
			// used.add() will return false if the value to add is already in the set.
			// false means can not be added, true means successfully added.
			if (!used.add(chars[i])) {
				continue;
			}
			//used.add(chars[i]); // no need to put here, last if statement already did that.
			swap(chars, i, idx);
			helper(chars, idx + 1, result);
			swap(chars, i, idx);
			//used.remove(chars[i]);  //!!!! can NOT add here, otherwise same as ignore the duplicates
		}
	}

	//////////////////////////////////////////////////////////////////////////

	// self method
	public List<String> permutations_self(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) {
			return result;
		}
		char[] chars = set.toCharArray();
		Arrays.sort(chars); // !!! DON'T FORGET, important
		helpers(chars, 0, result);
		return result;
	}

	// wrong first time,
	// case 'aabc', when 'c***', the char[1:] is 'aba', order is not sorted, will
	// cause repeated order
	private void helpers(char[] chars, int idx, List<String> result) {
		if (idx >= chars.length - 1) { // think about set="", this will miss the result as ""., either >= len-1 , or ==
										// len
			result.add(new String(chars));
			return;
		}
		// need to sort the [idx:] part, but remember to backup before and restore
		// after!!!
		char[] charsBak = Arrays.copyOfRange(chars, idx, chars.length);
		Arrays.sort(chars, idx, chars.length);
		for (int i = idx; i < chars.length; i++) {
			if (i > idx && chars[i] == chars[i - 1]) {
				continue;
			}
			swap(chars, idx, i);
			helpers(chars, idx + 1, result);
			swap(chars, idx, i);
		}
		restoreArray(chars, idx, charsBak);
	}

	private void restoreArray(char[] chars, int idx, char[] newChars) {
		for (int i = idx; i < chars.length; i++) {
			chars[i] = newChars[i - idx];
		}
	}

	private void swap(char[] chars, int a, int b) {
		char tmp = chars[a];
		chars[a] = chars[b];
		chars[b] = tmp;
	}
}
