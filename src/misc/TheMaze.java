package misc;

/**
 * 
 * 490. The Maze
 * 
 * There is a ball in a maze with empty spaces and walls. The ball can go
 * through empty spaces by rolling up, down, left or right, but it won't stop
 * rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 * 
 * Given the ball's start position, the destination and the maze, determine
 * whether the ball could stop at the destination.
 * 
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means
 * the empty space. You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column
 * indexes.
 * 
 * Example 1
 * 
 * Input 1: a maze represented by a 2D array
 * 
 * 0 0 1 0 0 0 0 0 0 0 0 0 0 1 0 1 1 0 1 1 0 0 0 0 0
 * 
 * Input 2: start coordinate (rowStart, colStart) = (0, 4) Input 3: destination
 * coordinate (rowDest, colDest) = (4, 4)
 * 
 * Output: true Explanation: One possible way is : left -> down -> left -> down
 * -> right -> down -> right.
 * 
 * Example 2
 * 
 * Input 1: a maze represented by a 2D array
 * 
 * 0 0 1 0 0 0 0 0 0 0 0 0 0 1 0 1 1 0 1 1 0 0 0 0 0
 * 
 * Input 2: start coordinate (rowStart, colStart) = (0, 4) Input 3: destination
 * coordinate (rowDest, colDest) = (3, 2)
 * 
 * Output: false Explanation: There is no way for the ball to stop at the
 * destination.
 * 
 * Note: There is only one ball and one destination in the maze. Both the ball
 * and the destination exist on an empty space, and they will not be at the same
 * position initially. The given maze does not contain border (like the red
 * rectangle in the example pictures), but you could assume the border of the
 * maze are all walls. The maze contains at least 2 empty spaces, and both the
 * width and height of the maze won't exceed 100.
 * 
 * 
 * @author
 *
 */

public class TheMaze {
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		// input sanity check
		if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0 || start == null
				|| start.length < 2 || destination == null || destination.length < 2) {
			return false;
		}
		int m = maze.length, n = maze[0].length;
		if (start[0] < 0 || start[0] >= m || start[1] < 0 || start[1] >= n || destination[0] < 0
				|| destination[1] >= n) {
			return false;
		}
		// mark start point visited
		maze[start[0]][start[1]] = -1;
		return helper(maze, start, destination, -1);
	}

	private static final int[][] DIRS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private boolean helper(int[][] maze, int[] start, int[] destination, int dirIdx) {
		// reach the destination
		if (start[0] == destination[0] && start[1] == destination[1]) {
			return true;
		}
		for (int i = 0; i < DIRS.length; i++) {
			if (i == dirIdx) {
				continue;
			}
			int[] nextStop = getNextStop(start, DIRS[i], maze);
			if (nextStop[0] != -1 && nextStop[1] != -1) {
				// mark new stop as visited
				maze[nextStop[0]][nextStop[1]] = -1;
				if (helper(maze, nextStop, destination, i)) {
					return true;
				}
			}
		}
		return false;
	}

	private int[] getNextStop(int[] start, int[] dir, int[][] maze) {
		int m = maze.length, n = maze[0].length;
		int nx = start[0], ny = start[1];
		// can pass the -1 visited, only stops when reach wall or border
		while (nx + dir[0] >= 0 && nx + dir[0] < m && ny + dir[1] >= 0 && ny + dir[1] < n && 
				maze[nx + dir[0]][ny + dir[1]] != 1) {
			nx += dir[0];
			ny += dir[1];
		}
		// not start, not visited ==> combined to not visited
		//if (nx != start[0] && ny != start[1] && maze[nx][ny] != -1 ) {  //!!!condition wrong
		//if (!(nx == start[0] && ny == start[1]) && maze[nx][ny] != -1 ) {
		if (maze[nx][ny] != -1) {
			return new int[]{nx, ny};
		} else {
			return new int[]{-1, -1};
		}
	}

	
	public static void main(String[] args) {
		TheMaze sol = new TheMaze();
		int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
		int[] start = {0, 4};
		int[] dest = {4, 4};
		boolean result = sol.hasPath(maze, start, dest);
		System.out.println(result);
		
	}
}
