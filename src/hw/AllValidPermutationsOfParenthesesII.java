package hw;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.
 * 
 * Assumptions
 * 
 * l, m, n >= 0 
 * 
 * Examples
 * 
 * l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]",
 * ""]
 * 
 * 
 * 
 * Hard Depth First Search Recursion
 * 
 * @author
 *
 */
public class AllValidPermutationsOfParenthesesII {
	private static final char[] PS = new char[] {'(', ')', '[', ']', '{', '}'};// static, final
	
	// Assumptions; l, m, n >= 0.
	public List<String> validParentheses(int l, int m, int n) {
		int[] remains = new int[] {l, l, m, m, n, n};
		int length = l * 2 + m * 2 + n * 2;
		List<String> result = new ArrayList<>();
		StringBuilder buffer = new StringBuilder();
		Deque<Character> stack = new LinkedList<>();
		helper(buffer, stack, remains, length, result);
		return result;
	}
	
	private void helper(StringBuilder buffer, Deque<Character> stack, int[] remains, int length, List<String> result) {
		if (buffer.length() == length) {
			// result.add(new String(buffer)); //This constructor is provided to ease migration to StringBuilder. 
			// Obtaining a string from a string builder via the toString method is likely to run faster and is generally preferred.
			result.add(buffer.toString()); 
		}
		// for left ones
		for (int i = 0; i < remains.length; i += 2 ) {
			if (remains[i] > 0) {
				buffer.append(PS[i]);
				remains[i]--;
				stack.offerLast(PS[i]);
				helper(buffer, stack, remains, length, result);
				buffer.deleteCharAt(buffer.length() - 1);
				remains[i]++; // !!! don't forget
				stack.pollLast(); // !! don't forget
			}
		}
		// for right ones
		for (int i = 1; i < remains.length; i += 2) {
			if (remains[i] > 0 && remains[i] - 1 >= remains[i-1] && PS[i-1] == stack.peekLast()) { //!! note >=, not <=
				buffer.append(PS[i]);
				remains[i]--;
				stack.pollLast();
				helper(buffer, stack, remains, length, result);
				buffer.deleteCharAt(buffer.length() - 1);
				remains[i]++; // !! don't forget
				stack.offerLast(PS[i-1]); // !! don't forget, and is i-1
			}
		}
	}
	
	
	private void helper_REF(StringBuilder buffer, Deque<Character> stack, int[] remains, int length, List<String> result) {
		if (buffer.length() == length) {
			// result.add(new String(buffer)); //This constructor is provided to ease migration to StringBuilder. 
			// Obtaining a string from a string builder via the toString method is likely to run faster and is generally preferred.
			result.add(buffer.toString()); 
		}
		
		for (int i = 0; i < remains.length; i++ ) {
			if (i % 2 == 0) {  // use this to distinguish between left and right
				if (remains[i] > 0) {
					buffer.append(PS[i]);
					remains[i]--;
					stack.offerLast(PS[i]);
					helper(buffer, stack, remains, length, result);
					buffer.deleteCharAt(buffer.length() - 1);
					remains[i]++; // !!! don't forget
					stack.pollLast(); // !! don't forget
				}				
			} else {
				//if (remains[i] > 0 && remains[i] - 1 >= remains[i-1] && PS[i-1] == stack.peekLast()) { //!! note >=, not <=
				if (!stack.isEmpty() && stack.peekLast() == PS[i-1]) { // only need to check stack
					buffer.append(PS[i]);
					remains[i]--;
					stack.pollLast();
					helper(buffer, stack, remains, length, result);
					buffer.deleteCharAt(buffer.length() - 1);
					remains[i]++; // !! don't forget
					stack.offerLast(PS[i-1]); // !! don't forget, and is i-1
				}
			}

		}
		
	}
	
	public static void main(String[] args) {
		AllValidPermutationsOfParenthesesII sol = new AllValidPermutationsOfParenthesesII();
		int l = 1, m = 1, n = 1;
		List<String> result = sol.validParentheses(l, m, n);
		for (String line : result) {
			System.out.println(line);
		}
	}

}
