package hw;

/**
 * Given a matrix that contains integers, find the submatrix with the largest
 * sum.
 * 
 * Return the sum of the submatrix.
 * 
 * Assumptions
 * 
 * The given matrix is not null and has size of M * N, where M >= 1 and N >= 1
 * Examples
 * 
 * { {1, -2, -1, 4},
 * 
 * {1, -1, 1, 1},
 * 
 * {0, -1, -1, 1},
 * 
 * {0, 0, 1, 1} }
 * 
 * the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
 * 
 * @author
 *
 */
public class LargestSubMatrixSum {
	public int largest(int[][] matrix) {
		// Assumptions: matrix is not null, and not empty, size m * n, m, n >= 1
		int m = matrix.length;
		int n = matrix[0].length;
		// preprocess the column-wise prefix sum;
		int[][] colSum = new int[m+1][n+1]; 
		// colSum[i][j] means the sum of matrix[0][j-1] to matrix[i-1][j-1], 
		// i.e. column j-1, rows[0, i-1];
		// colSum[0][0 ~ n] and colSum[0 ~ m][0] are initialized as 0s.
		preProcessColSum(matrix, colSum);
		int[] result = new int[]{Integer.MIN_VALUE};
		// iter the top bottom rows, and for each pair of fixed rows, 
		// find the max sub matrix, using the colSum
		for (int top = 0; top < m; top++) {
			for (int bot = top; bot < m; bot++) {
				int left = 0;
				int col = 0;
				//int subSum = colSum[bot + 1][col + 1] - colSum[top][col + 1]; // if do this here, then need to col++!!!
				int subSum = 0; // should start with 0, otherwise col start from 0, then col 0 is repeated!!
				while (col < n) {
					subSum = colSum[bot + 1][col + 1] - colSum[top][col + 1] + Math.max(0, subSum);
					result[0] = Math.max(result[0], subSum);
					col++;
				}
			}
		}
		return result[0];
	}
	
	private void preProcessColSum(int[][] matrix, int[][] colSum) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				colSum[i + 1][j + 1] = colSum[i][j + 1] + matrix[i][j];
			}
		}
	}

	////////////////////////////////////////////////////////////////////


	public int largest_REF(int[][] matrix) {
		// Assumptions: matrix is not null, has size N * M and N, M >= 1.
		int N = matrix.length;
		int M = matrix[0].length;
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int[] cur = new int[M];
			for (int j = i; j < N; j++) {
				// for each row i, we add the rows one by one for row j,
				// to make sure each time we only introduce O(n) time.
				add(cur, matrix[j]);
				// for each possible pair of rows i, j,
				// we would like to know what is the largest submatrix sum
				// using top row as row i and bottom row as row j.
				// we "flatten" these area to a one dimentional array.
				result = Math.max(result, max(cur));
			}
		}
		return result;
	}
	private void add(int[] cur, int[] add) {
		for (int i = 0; i < cur.length; i++) {
			cur[i] += add[i];
		}
	}
	
	// largest subarray sum.
	private int max(int[] cur) {
		int result = cur[0];
		int tmp = cur[0];
		for (int i = 1; i < cur.length; i++) {
			tmp = Math.max(tmp + cur[i], cur[i]);
			result = Math.max(result, tmp);
		}
		return result;
	}
	
	public static void main(String[] args) {
		LargestSubMatrixSum sol = new LargestSubMatrixSum();
		int[][] matrix1 = new int[][] { 
			{1, -2, -1, 4},
			{1, -1, 1, 1},			  
			{0, -1, -1, 1},
			{0, 0, 1, 1} };
		int answer1 = 7;
		
		int[][] matrix = new int[][] { 
			{2,-1, 2, 1,-3},
			{0,-2,-1, 2, 1},
			{3, 2, 1,-3,-2}
		};
		int answer = 6;
		
		int result = sol.largest(matrix);
		int ref = sol.largest_REF(matrix);
		System.out.println(result);
		System.out.println(ref);	
	}
}
