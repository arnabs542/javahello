package hw;

import java.util.ArrayList;
import java.util.List;

/**
 * Recursion N Queens Get all valid ways of putting N Queens on an N * N
 * chessboard so that no two Queens threaten each other.
 * 
 * Assumptions
 * 
 * N > 0
 * 
 * Return
 * 
 * A list of ways of putting the N Queens Each way is represented by a list of
 * the Queen's y index for x indices of 0 to (N - 1)
 * 
 * Example
 * 
 * N = 4, there are two ways of putting 4 queens:
 * 
 * [1, 3, 0, 2] --> the Queen on the first row is at y index 1, the Queen on the
 * second row is at y index 3, the Queen on the third row is at y index 0 and
 * the Queen on the fourth row is at y index 2.
 * 
 * [2, 0, 3, 1] --> the Queen on the first row is at y index 2, the Queen on the
 * second row is at y index 0, the Queen on the third row is at y index 3 and
 * the Queen on the fourth row is at y index 1.
 * 
 * @author
 *
 */
public class NQueens {
	// Method 2: validate the queen position in O(1) each time
	public List<List<Integer>> nqueensII(int n) {
		// Assumptions: n > 0.
		List<List<Integer>> result = new ArrayList<>();
		// here use a lot of records for used positions, recording by index,
		// therefore compared to method 1, better to use int[] for cur,
		// and thus an index to record where in int[] cur.
		int[] cur = new int[n];
		boolean[] usedCols = new boolean[n]; // in Java by default as false values
		// there are 2 * n - 1 diagonals for n * n matrix (each diagonal is x + y =
		// constant in range[0, 2n-2])
		boolean[] usedDiags = new boolean[2 * n - 1];
		// also 2 * n - 1 reversed diagonals, (each diagonal is y - n = constant in
		// range [-n+1, n-1], can shift to +n-1 --> [0, 2n-2])
		boolean[] usedRevDiags = new boolean[2 * n - 1];
		helper(n, 0, cur, usedCols, usedDiags, usedRevDiags, result);
		return result;
	}

	private void helper(int n, int idx, int[] cur, boolean[] usedCols, boolean[] usedDiags, boolean[] usedRevDiags,
			List<List<Integer>> result) {
		if (idx == n) {
			result.add(toList(cur));
			return;
		}
		for (int i = 0; i < n; i++) {
			if (valid(idx, i, n, usedCols, usedDiags, usedRevDiags)) {
				cur[idx] = i;
				mark(idx, i, n, usedCols, usedDiags, usedRevDiags, true);
				helper(n, idx + 1, cur, usedCols, usedDiags, usedRevDiags, result);
				mark(idx, i, n, usedCols, usedDiags, usedRevDiags, false);
			}
		}
	}

	private boolean valid(int row, int col, int n, boolean[] usedCols, boolean[] usedDiags, boolean[] usedRevDiags) {
		if (usedCols[col] == true || usedDiags[row + col] == true || usedRevDiags[col - row + n - 1]) {
			return false;
		}
		return true;
	}

	private void mark(int row, int col, int n, boolean[] usedCols, boolean[] usedDiags, boolean[] usedRevDiags,
			boolean val) {
		usedCols[col] = val;
		usedDiags[row + col] = val;
		usedRevDiags[col - row + n - 1] = val;
	}

	private List<Integer> toList(int[] cur) {
		List<Integer> al = new ArrayList<>();
		for (int i : cur) {
			al.add(i);
		}
		return al;
	}

	// Method 1: validate the queen position in O(n) each time.
	public List<List<Integer>> nqueens(int n) {
		// Assumptions: n > 0.
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// cur will be a list of size n, and cur[i] is the column number
		// where the queen on row i positioned.
		List<Integer> cur = new ArrayList<Integer>();
		helper(n, cur, result);
		return result;
	}

	private void helper(int n, List<Integer> cur, List<List<Integer>> result) {
		// base case: when for all the rows we know where the queen is positioned.
		if (cur.size() == n) {
			result.add(new ArrayList<Integer>(cur));
			return;
		}
		for (int i = 0; i < n; i++) {
			// check if putting a queen at column i at current row is valid.
			if (valid(cur, i)) {
				cur.add(i);
				helper(n, cur, result);
				cur.remove(cur.size() - 1);
			}
		}
	}

	private boolean valid(List<Integer> cur, int column) {
		int row = cur.size(); // previous rows to check, [0, row - 1], column is to be added at [row] pos
		for (int i = 0; i < row; i++) {
			// int a = Math.abs(cur.get(i) - column);
			// int b = row - 1 - i;
			// check not on same column, and not on same diagnal
			if (cur.get(i) == column || Math.abs(cur.get(i) - column) == row - i) {
				return false;
			}
		}
		return true;
	}

	// for convert to leetcode output, and also for JUnit test
	public List<List<String>> convert(List<List<Integer>> input) {
		List<List<String>> res = new ArrayList<List<String>>();
		for (int i = 0; i < input.size(); i++) {
			List<String> strSolution = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			List<Integer> solution = input.get(i);

			for (int row = 0; row < solution.size(); row++) {
				int column = solution.get(row);
				for (int col = 0; col < column; col++) {
					sb.append(".");
				}
				sb.append("Q");
				for (int col = column + 1; col < solution.size(); col++) {
					sb.append(".");
				}
				strSolution.add(sb.toString());
				sb.setLength(0); // clear the stringbuilder
			}
			res.add(strSolution);
		}
		return res;
	}

	public static void main(String[] args) {
		NQueens sol = new NQueens();
		int n = 4;
		List<List<Integer>> res = sol.nqueens(n);
		List<List<String>> sres = sol.convert(res);
		System.out.println("done");
	}

	//////////////////////////////////////////////////////////////////////
	// written during midterm II

	public List<List<Integer>> nQueens(int n) {
		// Assumption: n > 0
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> cur = new ArrayList<>();
		// cur is one solution with n rows, cur[i] is column index of
		// row i
		helper_mt(n, cur, result);
		return result;
	}

	private void helper_mt(int n, List<Integer> cur, List<List<Integer>> result) {
		if (cur.size() == n) {
			result.add(new ArrayList<Integer>(cur));
			// result.append(Arrays.copyOf(cur));
			// todo: check copy later ¡ú add(new ArrayList<Integer>(cur));
			return;
		}
		// check
		for (int i = 0; i < n; i++) {
			if (isValid(i, result, cur)) {
				cur.add(i);
				helper_mt(n, cur, result);
				cur.remove(cur.size() - 1);
			}
		}
	}

	// parameter ¡®result¡¯ not used, can remove
	// cur is List, not array!!! change cur[i] --> cur.get(i)
	private boolean isValid(int col, List<List<Integer>> result, List<Integer> cur) {
		for (int i = 0; i < cur.size(); i++) {
			// not on same column, not on same diagonal lines.
			// if (cur[i] == col || Math.abs(col - cur[i]) == Math.abs(cur.size() - i)) {
			if (cur.get(i) == col || Math.abs(col - cur.get(i)) == Math.abs(cur.size() - i)) {
			return false;
			}
		}
		return true;
	}

}
