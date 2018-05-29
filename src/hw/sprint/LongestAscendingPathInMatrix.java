package hw.sprint;

/**
 * 
 * 
 * Longest Ascending Path In Matrix Description Given an integer matrix, find
 * the length of the longest ascending path.
 * 
 * From a cell, there are 4 directions to reach a neighbor(up, down, left,
 * right).
 * 
 * Examples:
 * 
 * { {1, 2, 3},
 * 
 * {4, 2, 6},
 * 
 * {7, 1, 9} }
 * 
 * The longest ascending path is 1 -> 2 -> 3 -> 6 -> 9, length is 5
 * 
 * Medium Depth First Search Dynamic Programming
 * 
 * 
 * @author
 *
 */

public class LongestAscendingPathInMatrix {
	public int longest(int[][] matrix) {
		// dp[i][j]: longest path starting from (i, j)
		// at first think dp[i][j] for ENDING at (i, j) , then comes the problem of
		// how to init and calculate and update dp[][]? May go several round.
		// ===> should be STARTING
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return -1;
		}
		int globalMax = -1;
		int m = matrix.length, n = matrix[0].length;
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// dp[i][j] = Math.max(dp[i][j], helper(matrix, i, j, dp));
				helper(matrix, i, j, dp);
				globalMax = Math.max(dp[i][j], globalMax);
			}
		}
		return globalMax;
	}

	final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private int helper(int[][] matrix, int x, int y, int[][] dp) {
		// for memorized result
		if (dp[x][y] > 0) {
			return dp[x][y];
		}
		dp[x][y] = 1; // init as 1 for min value in neighbors on matrix[i][j]
		// !!! should init as 1, not 0
		for (int i = 0; i < DIRS.length; i++) {
			int nx = x + DIRS[i][0], ny = y + DIRS[i][1];
			if (0 <= nx && nx < matrix.length && 0 <= ny && ny < matrix[0].length && matrix[nx][ny] > matrix[x][y]) {
				dp[x][y] = Math.max(dp[x][y], helper(matrix, nx, ny, dp) + 1);
			}
		}
		return dp[x][y];
	}
}

