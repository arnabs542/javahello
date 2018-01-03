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
