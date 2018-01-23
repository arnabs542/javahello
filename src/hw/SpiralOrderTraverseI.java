package hw;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Structure
Spiral Order Traverse I
Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

Assumptions

The 2D array is not null and has size of N * N where N >= 0
Examples

{ {1,  2,  3},

  {4,  5,  6},

  {7,  8,  9} }

the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]
 * @author 
 *
 */

public class SpiralOrderTraverseI {
	// Method 1: Recursive traversal
	public List<Integer> spiral(int[][] matrix) {
		// Assumptions: matrix is N * N, N >= 0, matrix is not null
		List<Integer> list = new ArrayList<Integer>();
		recursiveTraverse(matrix, 0, matrix.length, list);
		return list;
	}
	
	private void recursiveTraverse(int[][] matrix, int offset, int size, List<Integer> result) {
		// the base case is when there is only 0 or 1 element left.
		// --> not that it's a N * N matrix, then its always ending with 0 or 1 element.
		// --> incase it's N * M, then ending may be 0, or 1 cell, or 1 row, or 1 column.
		if (size == 0) {
			return;
		}
		if (size == 1) {
			result.add(matrix[offset][offset]);
			return;
		}
		// --> note it's size - 1 here, then every direction will go [0, size-1) range, 
		// the size - 1 position of current direction will be the 0 pos of next direction 
		for (int i = 0; i < size - 1; i++) {
			result.add(matrix[offset][offset + i]);
		}
		for (int i = 0; i < size - 1; i++) {
			result.add(matrix[offset + i][offset + size - 1]);
		}
		for (int i = size - 1; i > 0; i--) {
			result.add(matrix[offset + size - 1][offset + i]);
		}
		for (int i = size - 1; i > 0; i--) {
			result.add(matrix[offset + i][offset]);
		}
		recursiveTraverse(matrix, offset + 1, size - 2, result); //!!! note size - 2 not -1!!!
	}
	
	// Method 2: Iterative traversal.
	public List<Integer> spiral2(int[][] matrix) {
		// Assumptions: matrix is N * N, N >= 0, matrix is not null
		List<Integer> list = new ArrayList<Integer>();
		int n = matrix.length;
		int start = 0, end = n - 1; // [start, end]
		// the base case is when there is 0 or 1 element in the submatrix,
		// i.e. start < end or start == end.
		// for start < end, means still 4 directions to go
		// for start == end, only 1 cell left.
		while (start < end) {
			for (int i = start; i < end; i++) { // [start, end), so not end - 1
				list.add(matrix[start][i]);
			}
			for (int i = start; i < end - 1; i++) {
				list.add(matrix[i][end]);
			}
			for (int i = end; i > start; i--) { // not 0, should be start here
				list.add(matrix[end][i]);
			}
			for (int i = end; i > 0; i--) {
				list.add(matrix[i][start]);
			}
			start++;
			end--;
		}
		if (start == end) {
			list.add(matrix[start][start]);
		}
		return list;
	}
}
