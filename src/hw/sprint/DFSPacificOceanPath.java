package hw.sprint;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 417. Pacific Atlantic Water Flow
 * 
 * 
 * Given an m * n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific Ocean" touches the left and top edges of the matrix and the "Atlantic ocean" 
 * touches the right and bottom edges.
 * 
 * Water can only flow in four directions( up, down, left, or right) from a cell to another one with height 
 * equal or lower.
 * 
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * 
 * Pacific ~~~~~~~~
 * ~	1	2	2	3	5^	*
 * ~	3	2	3	4^	4^	*
 * ~	2	4	5^	3	1	*
 * ~	6^	7^	1	4	5	*
 * ~	5^	1	1	2	4	*
 * ~	*	*	*	*	*	Atlantic
 * 
 * Return:
 * [{0, 4}, {1, 3}, {1, 4}, [2, 2], [3, 0], [3, 1], [4, 0]]
 * (positions with ^ in above matrix)
 * 
 * @author 
 *
 */
public class DFSPacificOceanPath {
	public List<int[]> pacificAtlantic(int[][] matrix) {
		List<int[]> res = new ArrayList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return res;
		}
		int n = matrix.length, m = matrix[0].length;
		boolean[][] pacific = new boolean[n][m];
		boolean[][] atlantic = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
			dfs(matrix, atlantic, Integer.MIN_VALUE, i, m - 1);
		}
		for (int j = 0; j < m; j++) {
			dfs(matrix, pacific, Integer.MIN_VALUE, 0, j);
			dfs(matrix, atlantic, Integer.MIN_VALUE, n - 1, j);		
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					res.add(new int[] {i, j});
				}
			}
		}
		return res;
	}
	
	final int[][] DIRS = {
		{-1, 0}, {1, 0}, {0, 1}, {0, -1}	
	};
	
	private void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y) {
		int n = matrix.length, m = matrix[0].length;
		if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || matrix[x][y] < height) {
			return;
		}
		visited[x][y] = true;
		for (int[] dir : DIRS) {
			dfs(matrix, visited, matrix[x][y], x + dir[0], y + dir[1]);
		}
	}
}