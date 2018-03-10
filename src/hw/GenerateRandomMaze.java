package hw;
/**
 * 
 * Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and
 * wall��s width are both 1 cell. For each pair of cells on the corridor, there
 * must exist one and only one path between them. (Randomly means that the
 * solution is generated randomly, and whenever the program is executed, the
 * solution can be different.). The wall is denoted by 1 in the matrix and
 * corridor is denoted by 0.
 * 
 * Assumptions
 * 
 * N = 2K + 1 and K >= 0 
 * the top left corner must be corridor 
 * there should be as many corridor cells as possible 
 * for each pair of cells on the corridor, there
 * must exist one and only one path between them 
 * 
 * Examples
 * 
 * N = 5, one possible maze generated is
 * 
 * 0 0 0 1 0
 * 
 * 1 1 0 1 0
 * 
 * 0 1 0 0 0
 * 
 * 0 1 1 1 0
 * 
 * 0 0 0 0 0
 * 
 * 0 - path
 * 1 - wall
 * 
 * Hard Depth First Search
 * 
 * @author
 *
 */
public class GenerateRandomMaze {
	//REF
	public int[][] maze(int n) {
		// Assumptions: n = 2 * k + 1, where k >= 0.
		int[][] maze = new int[n][n];
		// initialize the matrix as only (0, 0) is corridor,
		// other cells are all walls at the beginning.
		// later we are trying to break the walls to form corridors.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				maze[i][j] = 1;
			}
		}
		maze[0][0] = 0;
		generate(maze, 0, 0);
		return maze;
	}
	
	private void generate(int[][] maze, int x, int y) {
		// get a random shuffle of all the possible directions,
		// and follow the shuffled order to do DFS & backtrack.
		Dir[] dirs = Dir.values();
		shuffle(dirs);
		for (Dir dir : dirs) {
			// advance by two steps.
			// --> why 2 steps? --> problem require widths of corridors and walls are always 1
			// odd points are 1 --> why? --> no 2 * 2 submatrix that contains all 0s
			// event points are 0 --> hwy? no 2 * 2 submatrix that contains all 1s
			int nextX = dir.moveX(x, 2);
			int nextY = dir.moveY(y, 2);
			// DFS stops when there is no valid wall for all 4 directions from current position.
			if (isValidWall(maze, nextX, nextY)) {
				// only if the cell is a wall(meaning we have not visited before),
				// we break the walls through to make it corridors.
				maze[dir.moveX(x, 1)][dir.moveY(y, 1)] = 0; // the path from current to the next pos will be corridor 0
				maze[nextX][nextY] = 0; // the next pos will be corridor 0
				generate(maze, nextX, nextY);
			}
		}
	}
	
	// Get a random order of the directions.
	private void shuffle(Dir[] dirs) {
		for (int i = 0; i < dirs.length; i++) {
			int index = (int)(Math.random() * (dirs.length - i));
			Dir tmp = dirs[i];
			dirs[i] = dirs[i + index];
			dirs[i + index] = tmp;
		}
	}
	
	// check if the position (x, y) is within the maze and it is a wall.
	private boolean isValidWall (int[][] maze, int x , int y) {
		return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
	}
	
	// this is an example of using enum in Java,
	// basically enum is good and recommended way for representing 
	// as set of predefined sonstants.
	// for more details, please refer to the lab class :)
	enum Dir {
		NORTH(-1, 0),
		SOUTH(1, 0),
		EAST(0, 1),
		WEST(0, -1);
		
		int deltaX;
		int deltaY;
		
		Dir(int deltaX, int deltaY) {
			this.deltaX = deltaX;
			this.deltaY = deltaY;
		}
		
		// move certain times of deltaX.
		public int moveX(int x, int times) {
			return x + times * deltaX;
		}
		
		// move certain times of deltaY.
		public int moveY(int y, int times) {
			return y + times * deltaY;
		}
	}
	
}
