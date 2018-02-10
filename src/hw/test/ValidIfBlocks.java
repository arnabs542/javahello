package hw.test;

import java.util.ArrayList;
import java.util.List;

/**
 * --> print all possible ways of n pairs of "if{}" blocks.
 * 
 * 
 * Time complexity: O(2^n) ¡ú O (n * 2 ^ 2n )?? is processing the indentation
 * O(n) in each level? 
 * 
 * Space complexity: O(n)--> other student example O(n^2).
 * is my implementation O(n)? I¡¯m reusing the StringBuilder
 * 
 * @author
 *
 */

public class ValidIfBlocks {
	// Teacher Yan mention: another way to do is separate the {} generation logic and print logic.
	// 1. generate all results of n pair of {}.
	// 2. final step, for each way of result, do the print.
	// The good thing about this way is , print is easy to be modified. 
	// Separation of Concerns, good design.
	
	public void validIfBlocks(int n) {
		if (n <= 0) {
			return;
		}
		List<String> blocks = new ArrayList<>();
		helper_ref(blocks, n, n);
	}
	
	private void helper_ref(List<String> blocks, int left, int right) {
		if (left == 0 && right == 0) {
			print(blocks);
			return;
		}
		
		StringBuilder builder = new StringBuilder();
		if (left > 0) {
			for (int i = 0; i < right - left; i++) {
				builder.append("    ");
			}
			blocks.add(builder.append("if {").toString());
			helper_ref(blocks, left - 1, right);
			blocks.remove(blocks.size() - 1);
		}
		builder.setLength(0);
		if (right > left) {
			for (int i = 0; i < right - left - 1; i++) {
				builder.append("    ");
			}
			blocks.add(builder.append("}").toString());
			helper_ref(blocks, left, right - 1);
			blocks.remove(blocks.size() - 1);
		}
	}
	
	private void print(List<String> blocks) {
		for (String s : blocks) {
			System.out.println(s);
		}
		System.out.println("==================================================");
		
	}
	

	///////////////////////////////////////////////////////////////////////////////////////
	// writtern during mid term, looks good. logic is mainly right.
	// Assumption: n >= 0
	public void printNBlocks(int n) {
		// writtern during mid term, looks good. logic is mainly right.
		List<String> result = new ArrayList<String>();
		StringBuilder buffer = new StringBuilder();
		helper(result, n, n, 0, buffer);
		for (String s : result) {
			System.out.println("===========================================");
			System.out.println(s);
		}
	}

	private void helper(List<String> result, int left, int right, int open, StringBuilder buffer) {
		if (left == 0 && right == 0) {
			result.add(buffer.toString()); // list, not append, add
			return;
		}

		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < open; i++) {
			tmp.append("  ");
		}
		String indent = tmp.toString();
		// indent = " " * open;

		// case 1: add a {
		if (left > 0) {
			buffer.append(indent + "{");
			buffer.append("\r\n"); // should add this to make new lines.
			helper(result, left - 1, right, open + 1, buffer); // don't forget buffer
			// buffer.remove(indent + "{");
			buffer.delete(buffer.length() - indent.length() - 1, buffer.length());
			buffer.delete(buffer.length() - "\r\n".length(), buffer.length());
			// todo: check stringbuilder api...
		}

		// case 2: add a }

		if (right > 0 && open > 0) {
			// String rightIndent = indent - " "; // String can do + , can not do -
			String rightIndent = indent.substring(0, indent.length() - "  ".length());
			// should be moved into if, otherwise rightIndex substring will indexOutOfBounds
			buffer.append(rightIndent + "}");
			buffer.append("\r\n"); // should add this to make new lines.
			helper(result, left, right - 1, open - 1, buffer); // don't forget buffer
			// buffer.remove(rightIndent + "}");
			buffer.delete(buffer.length() - rightIndent.length() - 1, buffer.length());
			buffer.delete(buffer.length() - "\r\n".length(), buffer.length());

			// todo: check stringbuilder api...
		}
	}

	public static void main(String[] args) {
		ValidIfBlocks sol = new ValidIfBlocks();
		int n = 3;
		sol.printNBlocks(n);
		sol.validIfBlocks(n);
	}

}
