package misc;

/**
 * 463. Island Perimeter
 * 
 * DescriptionHintsSubmissionsDiscussSolution You are given a map in form of a
 * two-dimensional integer grid where 1 represents land and 0 represents water.
 * Grid cells are connected horizontally/vertically (not diagonally). The grid
 * is completely surrounded by water, and there is exactly one island (i.e., one
 * or more connected land cells). The island doesn't have "lakes" (water inside
 * that isn't connected to the water around the island). One cell is a square
 * with side length 1. The grid is rectangular, width and height don't exceed
 * 100. Determine the perimeter of the island.
 * 
 * Example:
 * 
 * [[0,1,0,0], [1,1,1,0], [0,1,0,0], [1,1,0,0]]
 * 
 * Answer: 16 Explanation: The perimeter is the 16 yellow stripes in the image
 * below:
 * 
 * Difficulty:Easy Total Accepted:84.3K Total Submissions:144.9K
 * Contributor:amankaraj Companies
 * 
 * Related Topics
 * 
 * Similar Questions Max Area of IslandFlood Fill
 * 
 * @author
 *
 */
public class IslandPerimeter {

	/**
	 * Basic idea, DFS can cover the cells of island, when DFS, perimeters are
	 * composed of the edges that are water or border, which means when expanding to
	 * neighbors, if it's neighboring cell is water or out of bound, then that edge
	 * is a part of the perimeter.
	 * 
	 * Assume that the input can be changed, during dfs, use -1 to mark cells that
	 * are visited (1 -> -1).
	 * 
	 */
	public int islandPerimeter(int[][] grid) {
		if (grid == null || grid[0].length == 0) {
			return 0;
		}
		int[] result = { 0 };
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					dfs(grid, i, j, result); // since only one island, one dfs is enough
					break;
				}
			}
		}
		return result[0];
	}

	private static final int[][] DIRS = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	private void dfs(int[][] grid, int x, int y, int[] result) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
			result[0] += 1;
			return;
		}
		if (grid[x][y] == -1) {
			return;
		}
		grid[x][y] = -1; // mark visited
		for (int[] dir : DIRS) {
			int nx = x + dir[0], ny = y + dir[1];
			dfs(grid, nx, ny, result);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * 
 * Java 9 line solution, add 4 for each land and remove 2 for each internal edge
 * 13.2K VIEWS 53 Last Edit: May 2, 2018 9:52 PM
 * 

public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += 4;
                    if (i > 0 && grid[i-1][j] == 1) result -= 2;
                    if (j > 0 && grid[i][j-1] == 1) result -= 2;
                }
            }
        }
        return result;
    }


 * 
 */
