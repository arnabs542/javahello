package misc;

/**
 * 
 * 329. Longest Increasing Path in a Matrix
 * DescriptionHintsSubmissionsDiscussSolution 
 * 
 * Given an integer matrix, find the
 * length of the longest increasing path.
 * 
 * From each cell, you can either move to four directions: left, right, up or
 * down. You may NOT move diagonally or move outside of the boundary (i.e.
 * wrap-around is not allowed).
 * 
 * Example 1:
 * 
 * Input: nums = [ [9,9,4], [6,6,8], [2,1,1] ] Output: 4 Explanation: The
 * longest increasing path is [1, 2, 6, 9]. Example 2:
 * 
 * Input: nums = [ [3,4,5], [3,2,6], [2,2,1] ] Output: 4 Explanation: The
 * longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * 
 * Difficulty:Hard Total Accepted:55.2K Total Submissions:147K
 * Contributor:dietpepsi Companies
 * 
 * Related Topics
 * 
 * 
 * 
 * @author
 *
 */
public class LongestIncreasingPathInaMatrix {
	/**
	 * Basic Idea:
	 * DFS + DP (memorization)
	 * cache[x][y]: the max length for STARTING from [x][y]
	 * 
	 * Time: O(mn)
	 * Space: O(mn)
	 * 
	 * =========
	 * Another way is to model the matrix as a graph, where each cell is node,
	 * and only cell(smaller value) to cell(bigger value) has an edge.
	 * process the graph, and use BFS to find the longest path in the graph.
	 * THen the total STEPS to generate seeds into queue and traverse whole graph will 
	 * be the result.
	 * 
	 * Graph theory, Java solution, O(v^2), no DFS
	 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78336/Graph-theory-Java-solution-O(v2)-no-DFS
	 * 
	 * @param matrix
	 * @return
	 */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        	return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		int curMax = dfs(matrix, i, j, m, n, cache);
        		max = Math.max(max, curMax);
        	}
        }
        return max;
    }
    
	public static final int[][] DIRS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	
	private int dfs(int[][] matrix, int x, int y, int m, int n, int[][] cache) {
		if (cache[x][y] != 0) {
			return cache[x][y];
		}
		int max = 1;
		for (int[] dir: DIRS) {
			int nx = x + dir[0], ny = y + dir[1];
			if (nx < 0 || nx >= m || ny < 0 || ny >= n || matrix[nx][ny] <= matrix[x][y]) {
				continue;
			}
			int newLen = 1 + dfs(matrix, nx, ny, m, n, cache);
			max = Math.max(max, newLen);
		}	
		cache[x][y] = max;
		return max;
	}

    
}
