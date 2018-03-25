package hw.testfinal;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given a string, we can insert at most one empty space between each pair of
 * adjacent letters. Print all permutations of strings after insertions of empty
 * spaces. (Note that the relative orders of letters in the input string do not
 * change)
 * 
 * Input: str = "ABC" Output: ABC AB_C A_BC A_B_C
 * 
 * @author
 *
 */

public class InsertSpacesInString {
	// basic idea: For a string of length n, there are n - 1 places to put // space.
	// Every place will be either ‘put’ or ‘not put’.

	public void printStringSpace(char[] chars) {
		// Assumptions: input is not null;

		List<String> output = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		dfs(chars, 0, sb, output);
		printStrings(output);
	}

	// idx: the current index in chars to process, add chars[idx], and
	// decided whether to insert space.
	private void dfs(char[] chars, int idx, StringBuilder buffer, List<String> result) {
		if (idx == chars.length - 1) {
			// base case, only add char,
			buffer.append(chars[idx]);
			result.add(buffer.toString());
			buffer.deleteCharAt(buffer.length() - 1); // !!! important, last one also need to delete
			return;
		}
		buffer.append(chars[idx]);
		// not add space
		dfs(chars, idx + 1, buffer, result);
		// add space
		buffer.append(' ');
		dfs(chars, idx + 1, buffer, result);
		buffer.deleteCharAt(buffer.length() - 1); // !!! length() not size()
		buffer.deleteCharAt(buffer.length()- 1); // !!! need to delete one more !!!, space and the char itself!!!
	}

	private void printStrings(List<String> output) {
		for (String line : output) {
			System.out.println(line);
		}
	}

	public static void main(String[] args) {
		InsertSpacesInString sol = new InsertSpacesInString();
		String str = "ABC"; // expect: ABC AB_C A_BC A_B_C
		sol.printStringSpace(str.toCharArray());

	}

}
