package hw;

import java.util.ArrayList;
import java.util.List;

/**
 * Recursion All Subsets I Given a set of characters represented by a String,
 * return a list containing all subsets of the characters.
 * 
 * Assumptions
 * 
 * There are no duplicate characters in the original set. ​Examples
 * 
 * Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
 * Set = "", all the subsets are [""] Set = null, all the subsets are []
 * 
 * @author
 *
 */
public class AllSubsetsI {
	public List<String> subSets(String set) {
		ArrayList<String> res = new ArrayList<String>();
		if (set == null) { // || set.length() == 0 should not be here, empty string will have [""] result
			return res;
		}
		StringBuffer sb = new StringBuffer();
		char[] chars = set.toCharArray();
		helper(chars, sb, 0, res);
		return res;
	}

	private void helper1(char[] chars, StringBuffer sb, int idx, ArrayList<String> res) {
		if (idx == chars.length) {
			res.add(sb.toString());
			return;
		}
		helper(chars, sb.append(chars[idx]), idx + 1, res);
		sb.deleteCharAt(sb.length() - 1);  // !!important, remember to remove, otherwise sb will keep all chars
		helper(chars, sb, idx + 1, res);
	}
	
	private void helper(char[] chars, StringBuffer sb, int idx, ArrayList<String> res) {
		
		res.add(sb.toString());
		for (int i = idx; i < chars.length; i++){
		  helper(chars, sb.append(chars[i]), i + 1, res); //!!! should be i + 1, not idx + 1 here
		  sb.deleteCharAt(sb.length() - 1);
		}
	}
}

class PracticeClassSolution3 {
	// pure recursion, does not need root to leaf pass value down(top-down path info),
	// only built upon the value from bottom-up.
	// 1. subproblem: n - 1 lelemtns's all subsets.
	// 2. recursion rule: for each element in sublist, add or does not add current one
	// 3. base case: 0 elements's all subsets [""]
	public List<String> subSets(String input) {
		List<String> result = new ArrayList<>();
		if (input == null) {
			return result;
		}
		helper(input, 0, result);
		return result;
	}
	
	private void helper(String input, int idx, List<String> result) {
		// base case here
		if (idx == input.length()) {
			result.add("");
			return;
		}
		
		// recursion rule, add or not add current idx pos char, upon the results of (idx+1 to end of input)
		helper(input, idx + 1, result);
		int size = result.size();
		for (int i = 0; i < size; i++) {
			// original result is without current char
			// now for each result already there, add current char
			result.add(result.get(i) + input.charAt(idx));
		}
	}
}

class PracticeClassSolution3II {
	// pure recursion, does not need root to leaf pass value down(top-down path info),
	// only built upon the value from bottom-up.
	// 1. subproblem: n - 1 lelemtns's all subsets.
	// 2. recursion rule: for each element in sublist, add or does not add current one
	// 3. base case: 0 elements's all subsets [""]
	public List<String> subSets(String input) {
		List<String> result = new ArrayList<>();
		if (input == null) {
			return result;
		}
		helper(input, result);
		return result;
	}
	
	// "abc"
	private void helper(String problem, List<String> result) {
		// base case here
		if (problem.isEmpty()) {
			result.add("");
			return;
		}
		
		// recursion rule, add or not add current idx pos char, upon the results of (idx+1 to end of input)
		helper(problem.substring(1), result);
		// ["", "b", "c", "bc" | "a", "ab", "ac", "abc"]
		int size = result.size();
		for (int i = 0; i < size; i++) {
			// original result is without current char
			// now for each result already there, add current char
			result.add(result.get(i) + problem.charAt(0));
		}
	}
}
