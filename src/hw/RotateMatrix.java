package hw;

/**
 * Rotate Matrix
 * 
 * Rotate an N * N matrix clockwise 90 degrees.
 * 
 * Assumptions
 * 
 * The matrix is not null and N >= 0 Examples
 * 
 * { {1, 2, 3}
 * 
 * {8, 9, 4},
 * 
 * {7, 6, 5} }
 * 
 * after rotation is
 * 
 * { {7, 8, 1}
 * 
 * {6, 9, 2},
 * 
 * {5, 4, 3} }
 * 
 * Medium 2 D Array
 * 
 * @author
 *
 */
public class RotateMatrix {
	// Method 1: Split into circles and for each circle split it into 
	// four partitions
	public void rotate(int[][] matrix) {
		// Assumptions: matrix is not null and has size of N * N, N >= 0.
		int n = matrix.length;
		if (n <= 1) {
			return;
		}
		// only split into at most n/2 circles
		int round = n / 2;
		for (int level = 0; level < round; level++) {
			// !!! find the range for each level
			int left = level;
			int right = n - 2 - level;
			for (int i = left; i <= right; i++) {
				// find the 'offset' to do rotation in current level.
				int tmp = matrix[left][i];
				matrix[left][i] = matrix[n-1-i][left];
				// matrix[n-1-i][left] = matrix[right][n-1-i];
				matrix[n-1-i][left] = matrix[n-1-left][n-1-i];
				matrix[n-1-left][n-1-i] = matrix[i][n-1-left];
				matrix[i][n-1-left] = tmp;
			}
		}
	}
	
	// Method 2: Rotate a point by 90 degree clockwise ==
	// 1. Mirror the point according to y axis, then
	// 2. Mirror the point according to the line of y = x.
	public void rotateII(int[][] matrix) {
		// Assumptions: matrix is not null and has size of N * N, N >= 0.
		int n = matrix.length;
		if (n <= 1) {
			return;
		}
		mirrorY(matrix, n);
		mirrorYEX(matrix, n);
	}
	
	private void mirrorY(int[][] matrix, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {
				swap(matrix, i, j, i, n-1-j);
			}
		}
	}
	
	private void mirrorYEX(int[][] matrix, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1 - i; j++) {  // can also be j < n - i
				swap(matrix, i, j , n - 1 - j, n - 1 - i);
			}
		}
	}
	
	private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
		int tmp = matrix[i1][j1];
		matrix[i1][j1] = matrix[i2][j2];
		matrix[i2][j2] = tmp;
	}
}
