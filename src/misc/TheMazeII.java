package misc;

import java.util.PriorityQueue;
import java.util.Queue;

public class TheMazeII {
	// AC, ~ 40% for point structure, similar for int[] structure
	class Point {
		int x, y, dist;

		public Point(int _x, int _y, int _dist) {
			x = _x;
			y = _y;
			dist = _dist;
		}
	}

	private static final int[][] DIRS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, {0, -1} };

	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		// assume all inputs are valid 
		int m = maze.length, n = maze[0].length;
		PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> (o1[2] - o2[2]));
		heap.offer(new int[] {start[0], start[1], 2});  // 0 -> 2 make distance starting from 2
		maze[start[0]][start[1]] = 2;
		while (!heap.isEmpty()) {
			int[] cur = heap.poll();
			if (cur[0] == destination[0] && cur[1] == destination[1]) {
				// found!! for sure the shortest path
				return cur[2] - 2;  // because init as 0
			}
			for (int[] dir : DIRS) {
				int nx = cur[0], ny = cur[1], move = 0;
				while (nx + dir[0] >= 0 && nx + dir[0] < m && ny + dir[1] >= 0 && ny + dir[1] < n
						&& maze[nx + dir[0]][ny + dir[1]] != 1) {  // since distances >= 2, 1 will only be walls
					nx += dir[0];
					ny += dir[1];
					move += 1;
				}
                //if ((maze[nx][ny] != 0 && cur.dist + move >= maze[nx][ny]) || (nx == start[0] && ny == start[1])) {
				//if ((nx == start[0] && ny == start[1]) || (maze[nx][ny] != 0 && cur.dist + move >= maze[nx][ny])) {
				if  (maze[nx][ny] != 0 && cur[2] + move >= maze[nx][ny]) continue;
				// may found destination first, but can not guarantee it's shortest path??

				int[] next = new int[] {nx, ny, cur[2] + move};
				heap.offer(next);
				maze[nx][ny] = cur[2] + move;
			} // for

		} // while
		return -1;
	}
	
	
	public int shortestDistance_POINT(int[][] maze, int[] start, int[] destination) {
		// assume all inputs are valid 
		int m = maze.length, n = maze[0].length;
		PriorityQueue<Point> heap = new PriorityQueue<>((o1, o2) -> (o1.dist - o2.dist));
		heap.offer(new Point(start[0], start[1], 2));  // 0 -> 2 make distance starting from 2
		maze[start[0]][start[1]] = 2;
		while (!heap.isEmpty()) {
			Point cur = heap.poll();
			if (cur.x == destination[0] && cur.y == destination[1]) {
				// found!! for sure the shortest path
				return cur.dist - 2;  // because init as 0
			}
			for (int[] dir : DIRS) {
				int nx = cur.x, ny = cur.y, move = 0;
				while (nx + dir[0] >= 0 && nx + dir[0] < m && ny + dir[1] >= 0 && ny + dir[1] < n
						&& maze[nx + dir[0]][ny + dir[1]] != 1) {  // since distances >= 2, 1 will only be walls
					nx += dir[0];
					ny += dir[1];
					move += 1;
				}
                //if ((maze[nx][ny] != 0 && cur.dist + move >= maze[nx][ny]) || (nx == start[0] && ny == start[1])) {
				//if ((nx == start[0] && ny == start[1]) || (maze[nx][ny] != 0 && cur.dist + move >= maze[nx][ny])) {
				if  (maze[nx][ny] != 0 && cur.dist + move >= maze[nx][ny]) continue;
				// may found destination first, but can not guarantee it's shortest path??

				Point next = new Point(nx, ny, cur.dist + move);
				heap.offer(next);
				maze[nx][ny] = cur.dist + move;
			} // for

		} // while
		return -1;
	}
	
	public static void main1(String[] args) {
		TheMazeII sol = new TheMazeII();
		int[][] maze = { { 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0 } };
		int[] start = { 0, 0 };
		int[] dest = { 8, 6 };
		int result = sol.shortestDistance(maze, start, dest); // expect 16
		System.out.println(result);
	}

	public static void main(String[] args) {
		TheMazeII sol = new TheMazeII();
		int[][] maze = { { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		int[] start = { 0, 4 };
		int[] dest = { 4, 4 };
		int result = sol.shortestDistance(maze, start, dest); // expect 12
		System.out.println(result);

	}

}

class TheMazeII_DFS20 {
	/*
	 * normal DFS will TLE, this one try DFS with recorded length at every
	 * stopPoint, prune early if new length is bigger than before visited path
	 * landing on repeated stopPoint AC, ~ 20%
	 */
	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		// input sanity check
		if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0 || start == null
				|| start.length < 2 || destination == null || destination.length < 2) {
			return -1;
		}
		int m = maze.length, n = maze[0].length;
		if (start[0] < 0 || start[0] >= m || start[1] < 0 || start[1] >= n || destination[0] < 0
				|| destination[1] >= n) {
			return -1;
		}
		// mark start point visited, real dist will be 0, but to distinguish from 0, use
		// 1, !!! WRONG
		// !!! use 1 will make it a wall and make the map WRONG
		// REMEMBER to -1 for final result;
		maze[start[0]][start[1]] = -1;
		helper(maze, start, destination, -1, 1);
		return maze[destination[0]][destination[1]] - 1; // if not reached, original 0 - 1 = -1

	}

	private static final int[][] DIRS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private void helper(int[][] maze, int[] start, int[] destination, int dirIdx, int curDist) {
		// System.out.println(start[0] + " " + start[1] + " " + curDist);
		// reach the destination
		if (start[0] == destination[0] && start[1] == destination[1]) {
			// System.out.println("curDist = " + Integer.toString(curDist));
			maze[start[0]][start[1]] = maze[start[0]][start[1]] == 0 ? curDist
					: Math.min(curDist, maze[start[0]][start[1]]);
			return;
		}
		int m = maze.length, n = maze[0].length;
		for (int i = 0; i < DIRS.length; i++) {
			if (i == dirIdx) {
				continue;
			}
			int nx = start[0], ny = start[1];
			int dx = DIRS[i][0], dy = DIRS[i][1], move = 0;
			while (nx + dx >= 0 && nx + dx < m && ny + dy >= 0 && ny + dy < n && (maze[nx + dx][ny + dy] != 1)) {
				// all walls 1, --> stop
				// all visited will be 2 and above, not visited 0 --> continue movin
				nx += dx;
				ny += dy;
				move += 1;
			}
			if (maze[nx][ny] == -1)
				continue;
			if (maze[nx][ny] == 0 || curDist + move < maze[nx][ny]) {
				// not visited or visited and this path is shorter
				maze[nx][ny] = curDist + move;
				helper(maze, new int[] { nx, ny }, destination, i, curDist + move);
			}
			// else {
			// System.out.println("PRUNE " + nx + " " + ny );
			// }
		}

	}

	public static void main(String[] args) {
		TheMazeII sol = new TheMazeII();
		int[][] maze = { { 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0 } };
		int[] start = { 0, 0 };
		int[] dest = { 8, 6 };
		int result = sol.shortestDistance(maze, start, dest); // expect 16
		System.out.println(result);
	}

	public static void main1(String[] args) {
		TheMazeII sol = new TheMazeII();
		int[][] maze = { { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		int[] start = { 0, 4 };
		int[] dest = { 4, 4 };
		int result = sol.shortestDistance(maze, start, dest); // expect 12
		System.out.println(result);

	}

}

class TheMazeII_TLE {

	public int shortestDistance_TLE27outOF28(int[][] maze, int[] start, int[] destination) {
		// input sanity check
		if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0 || start == null
				|| start.length < 2 || destination == null || destination.length < 2) {
			return -1;
		}
		int m = maze.length, n = maze[0].length;
		if (start[0] < 0 || start[0] >= m || start[1] < 0 || start[1] >= n || destination[0] < 0
				|| destination[1] >= n) {
			return -1;
		}
		int[] result = { Integer.MAX_VALUE };
		// mark start point visited
		maze[start[0]][start[1]] = -1;
		helper(maze, start, destination, -1, result, 0);
		return result[0] == Integer.MAX_VALUE ? -1 : result[0];
	}

	private static final int[][] DIRS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private void helper(int[][] maze, int[] start, int[] destination, int dirIdx, int[] result, int curDist) {
		System.out.println(start[0] + " " + start[1] + " " + curDist);
		// reach the destination
		if (start[0] == destination[0] && start[1] == destination[1]) {
			// System.out.println("curDist = " + Integer.toString(curDist));
			result[0] = Math.min(curDist, result[0]);
			return;
		}
		for (int i = 0; i < DIRS.length; i++) {
			if (i == dirIdx) {
				continue;
			}
			int[] nextStop = getNextStop(start, DIRS[i], maze);
			if (nextStop[0] != -1 && nextStop[1] != -1) {
				// mark new stop as visited
				maze[nextStop[0]][nextStop[1]] = -1;
				if (curDist + nextStop[2] < result[0]) {
					helper(maze, new int[] { nextStop[0], nextStop[1] }, destination, i, result, curDist + nextStop[2]);
				}
				// !!! unmark visited ??
				maze[nextStop[0]][nextStop[1]] = 0;
			}
		}

	}

	// returns new position in [0] and [1], and distance in [2]
	private int[] getNextStop(int[] start, int[] dir, int[][] maze) {
		int m = maze.length, n = maze[0].length;
		int nx = start[0], ny = start[1], dist = 0;
		// can pass the -1 visited, only stops when reach wall or border
		while (nx + dir[0] >= 0 && nx + dir[0] < m && ny + dir[1] >= 0 && ny + dir[1] < n
				&& maze[nx + dir[0]][ny + dir[1]] != 1) {
			nx += dir[0];
			ny += dir[1];
			dist += 1;
		}
		// if (!(nx == start[0] && ny == start[1])) {
		// !!! note diff than mazeI, example 1 [1, 3] has TWO ways to reach [1, 0] !!!
		if (maze[nx][ny] != -1) { // !!! can't skip when visited, otherwise missing other possible shorter way
			System.out.println(nx + " " + ny + " " + dist);
			return new int[] { nx, ny, dist };
		} else {
			return new int[] { -1, -1, 0 };
		}
	}

	public static void main(String[] args) {
		TheMazeII sol = new TheMazeII();
		int[][] maze = { { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		int[] start = { 0, 4 };
		int[] dest = { 4, 4 };
		int result = sol.shortestDistance(maze, start, dest); // expect 12
		System.out.println(result);

	}
}
