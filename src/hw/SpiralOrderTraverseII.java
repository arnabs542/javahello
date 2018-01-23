package hw;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * Data Structure
Spiral Order Traverse II
Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. 

Return the list of traversal sequence.

Assumptions

The 2D array is not null and has size of M * N where M, N >= 0
Examples

{ {1,  2,  3,  4},

  {5,  6,  7,  8},

  {9, 10, 11, 12} }

the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

 * @author 
 *
 */

public class SpiralOrderTraverseII {
	// self: Recursive traversal
	public List<Integer> spiral_self_AC(int[][] matrix) {
		// Assumptions: matrix is M * N, both M, N >= 0, matrix is not null
		List<Integer> list = new ArrayList<Integer>();
		// actually if the problem gives the assumption, do not need to check this. but OJ still requires.
		if (matrix.length == 0 || matrix[0].length == 0) {
			  return list;
		}
		int m = matrix.length, n = matrix[0].length;
		recursiveTraverse(matrix, 0, m, n, list);
		return list;
	}
	
	private void recursiveTraverse(int[][] matrix, int offset, int row, int col, List<Integer> result) {
		// the base case is when there is only 0 or 1 element left, or 1 row / column.
		if (row <= 0 || col <= 0) {
			return;
		}
		if (row == 1 && col == 1) {
			result.add(matrix[offset][offset]);
			return;
		} else if (row == 1) {  // need to special deal with 1 row / col, otherwise wrong
			for (int i = 0; i < col; i++) {
				result.add(matrix[offset][offset + i]);
			}
			return;
		} else if (col == 1) {
			for (int i = 0; i < row; i++) {
				result.add(matrix[offset + i][offset]);
			}
			return;
		}
		// --> note it's size - 1 here, then every direction will go [0, size-1) range, 
		// the size - 1 position of current direction will be the 0 pos of next direction 
		for (int i = 0; i < col - 1; i++) {
			result.add(matrix[offset][offset + i]);
		}
		for (int i = 0; i < row - 1; i++) {
			result.add(matrix[offset + i][offset + col - 1]);
		}
		for (int i = col - 1; i > 0; i--) {
			result.add(matrix[offset + row - 1][offset + i]);
		}
		for (int i = row - 1; i > 0; i--) {
			result.add(matrix[offset + i][offset]);
		}
		recursiveTraverse(matrix, offset + 1, row - 2, col - 2, result); 
	}
	
	// REF: Iterative traversal.
	public List<Integer> spiral(int[][] matrix) {
		// Assumptions: matrix not null,  matrix is N * N, where M, N >= 0
		List<Integer> list = new ArrayList<Integer>();
		int m = matrix.length;
		// need to handle this case since if m == 0, matrix[0].length will 
		// throw ArrayIndexOutofBoundException.
		if (m == 0) {
			return list;
		}
		int n = matrix[0].length;
		int left = 0, right = n - 1; // [left, right]
		int up = 0, down = m - 1; // [up, down]
		// the base case is a little complicated comparing to N * N matrix.
		// 1. there is nothing left.
		// 2. there is one row left.
		// 3. there is one column left.
		while (left < right && up < down) {
			for (int i = left; i <= right; i++) { // [left, right]
				list.add(matrix[up][i]);
			}
			for (int i = up + 1; i < down; i++) { // (up, down) !!! < down or <= down - 1, mind the boundary
				list.add(matrix[i][right]);
			}
			for (int i = right; i >= left; i--) { // [left, right]
				list.add(matrix[down][i]);
			}
			for (int i = down - 1; i > up; i--) { // (up, down)
				list.add(matrix[i][left]);
			}
			left++;
			right--;
			up++;
			down--;
		}
		// 1. if nothing left
		if (left > right || up > down) {
			return list;
		}
		// 2. if one row left
		if (up == down) {
			for (int i = left; i <= right; i++) {
				list.add(matrix[up][i]);
			}
		} else {
			// 3. if one column left
			for (int i = up; i <= down; i++) {
				list.add(matrix[i][left]);
			}
		}
		return list;
	}
}
