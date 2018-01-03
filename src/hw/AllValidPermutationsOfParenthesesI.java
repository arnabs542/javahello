package hw;

import java.util.ArrayList;
import java.util.List;

/**
 * Recursion All Valid Permutations Of Parentheses I Given N pairs of
 * parentheses ¡°()¡±, return a list with all the valid permutations.
 * 
 * Assumptions
 * 
 * N >= 0 Examples
 * 
 * N = 1, all valid permutations are ["()"] N = 3, all valid permutations are
 * ["((()))", "(()())", "(())()", "()(())", "()()()"] N = 0, all valid
 * permutations are [""]
 * 
 * @author
 *
 */
public class AllValidPermutationsOfParenthesesI {
	public List<String> validParentheses(int n) {
		ArrayList<String> res = new ArrayList<String>();
		if (n < 0) {
			return res;
		}
		char[] cur = new char[n * 2];
		helper(cur, n, n, 0, res);
		return res;
	}

	private void helper(char[] cur, int left, int right, int idx, ArrayList<String> res) {
		if (idx == cur.length) {
			res.add(new String(cur));
			return;
		}
		if (left > 0) {
			cur[idx] = '('; // !!! need to use single quote, double means string!!!
			helper(cur, left - 1, right, idx + 1, res);
		}
		if (right > left) {
			cur[idx] = ')';
			helper(cur, left, right - 1, idx + 1, res);
		}
	}
}
