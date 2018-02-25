package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * All Subsequences of Sorted String (All Subsets II)
 * 
 * Given a set of characters represented by a String, return a list containing
 * all subsets of the characters.
 * 
 * Assumptions
 * 
 * There could be duplicate characters in the original set. ​Examples
 * 
 * Set = "abc", all the subsets are ["", "a", "ab", "abc", "ac", "b", "bc", "c"]
 * Set = "abb", all the subsets are ["", "a", "ab", "abb", "b", "bb"] Set = "",
 * all the subsets are [""] Set = null, all the subsets are [] Hard Depth First
 * Search
 * 
 * @author
 *
 */
public class AllSubsetsII {

	public List<String> subSets(String set) {
		List<String> result = new ArrayList<>();
	    // Assumption: set is not null --> laicode does not support
	    if (set == null) {
	    	return result;
	    }
		
		int idx = 0;
		// set.sort(); // check String sort; --> actually no string sort, to char[] and sort
		char[] chars = set.toCharArray();
		Arrays.sort(chars);
		StringBuilder buffer = new StringBuilder();
		helper(idx, buffer, chars, result);
		return result;
	}

	private void helper(int idx, StringBuilder buffer, char[] chars, List<String> result) {
		if (idx >= chars.length) {
			result.add(buffer.toString());
			return;
		}
		// case 1, take current char
		buffer.append(chars[idx]);
		helper(idx + 1, buffer, chars, result);
		buffer.deleteCharAt(buffer.length() - 1); //deleteCharAt, not remove

		// case 2, does not take current char
		// --> for repeated chars, say "abbbc", only need to cover [bbb, bb, b] cases for b in set.
		// --> use 'case 1: take char' branches to control how many 'b' taken, as idx grows.
		// --> for different idx, once current level does NOT take 'b', let idx skip all the remaining same chars,
		// --> and does not take any of them, until next idx + 1 loop.
		// x y b b b z..
		//     1 1 1
		//     1 1 0
		//     1 0 0 
		//     0 0 0 
		// --> x, y, z can take all possible 0 / 1 branches. but for b, recursively 
		while (idx + 1 < chars.length && chars[idx+1] == chars[idx]) {
			idx++;
		}
		// in this way, idx will keep the same physical meaning for repreat/nonrepeat chars
		helper(idx + 1, buffer, chars, result);
		
		/**
		// case 2, does not take current char
		// for repeated chars, skill all the remaining char recursion to the idx of diff char
		while (idx > 0 && chars[idx] == chars[idx - 1]) {
			idx++;
			if (idx == chars.length) {
				break;
			}
		}
		// !!! if repeat, idx here stops at next diff index, but if not repeated, idx is the same !!! --> infinite call
		// --> should distinguish between too different cases.
		if (idx < chars.length) {
			helper(idx, buffer, chars, result);
		}
		
		*/
		
	}
	
	/////////////////////////////////////////////////////////////
	// REF -->
	/////////////////////////////////////////////////////////////
	
	// Method 1: DFS I.
	public List<String> subSets_REF(String set) {
		List<String> result = new ArrayList<>();
	    // Assumption: set is not null --> laicode does not support
	    if (set == null) {
	    	return result;
	    }
		char[] chars = set.toCharArray();
		// Make sure the multi-set is sorted so that we can dedup.
		Arrays.sort(chars);
		StringBuilder buffer = new StringBuilder();
		helper_ref(0, buffer, chars, result);
		return result;
	}
	
	// index: at current level, determine if the element at "index" should be 
	// included in the subset or not.
	private void helper_ref(int idx, StringBuilder buffer, char[] chars, List<String> result) {
		if (idx == chars.length) {
			result.add(buffer.toString());
			return;
		}
		helper(idx + 1, buffer.append(chars[idx]), chars, result);
		buffer.deleteCharAt(buffer.length() - 1);
		// skip all the consecutive and duplicate elements.
		while (idx < chars.length - 1 && chars[idx] == chars[idx + 1]) {
			idx++;
		}
		helper(idx + 1, buffer, chars, result);
	}
	
	// Method 2: DFS II.
	// subSets method part the same as Method 1 DFS. Different method body for helper_ref
	// 
	// index: We pick the element by ascending order, "index" is the smallest index we can 
	// pick for the subset.
	// --> it's like a 'add char' way of thinking.
	private void helper_ref2(int idx, StringBuilder buffer, char[] chars, List<String> result) {
		result.add(buffer.toString());
		// For the consecutive duplicate elements, we only pick the first one.
		for (int i = idx; i < chars.length; i++) {
			if (i == idx || chars[i] != chars[i-1]) {
				buffer.append(chars[i]);
				helper(i + 1, buffer, chars, result); // note i + 1, not idx + 1
				buffer.deleteCharAt(buffer.length() - 1);
			}
		}
	}
	
	public static void main(String[] args) {
		String set1 = null;
		String set2 = "abc";
		String set = "abbbc";
		AllSubsetsII sol = new AllSubsetsII();
		List<String> result = sol.subSets(set);
		for (String line : result) {
			System.out.println(line);			
		}
	}
	
	/**
	 * "abbb"
	 * 
	 * Method 1 output:
	 * 
abbb
abb
ab
a
bbb
bb
b
""

	 * Method 2 output:
	 * 
""  
a
ab
abb
abbb
b
bb
bbb

	 * 
	 * 
	 */

}
