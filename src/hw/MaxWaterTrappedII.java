package hw;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * Max Water Trapped II
 * 
 * Description
 * 
 * Given a non-negative integer 2D array representing the heights of bars in a
 * matrix. Suppose each bar has length and width of 1. Find the largest amount
 * of water that can be trapped in the matrix. The water can flow into a
 * neighboring bar if the neighboring bar's height is smaller than the water's
 * height. Each bar has 4 neighboring bars to the left, right, up and down side.
 * 
 * Assumptions
 * 
 * The given matrix is not null and has size of M * N, where M > 0 and N > 0,
 * all the values are non-negative integers in the matrix. Examples
 * 
 * { { 2, 3, 4, 2 },
 * 
 * { 3, 1, 2, 3 },
 * 
 * { 4, 3, 5, 4 } }
 * 
 * the amount of water can be trapped is 3,
 * 
 * at position (1, 1) there is 2 units of water trapped,
 * 
 * at position (1, 2) there is 1 unit of water trapped.
 * 
 * 
 * 
 * 
 * 
 * Hard Breadth First Search Depth First Search Heap
 * 
 * 
 * @author
 *
 */
public class MaxWaterTrappedII {
	public int maxTrapped(int[][] matrix) {
		// Assumptions: matrix is not null, has size of M * N,
		// M > 0 & N > 0, all the values are non-negative integers.
		int rows = matrix.length;
		int cols = matrix[0].length;
		if (rows < 3 || cols < 3) {
			return 0;
		}
		// Best-First-Search, minHeap maintains all the border cells
		// of the "closed area" and we always find the one with lowest
		// height to see if any of its neighbors can trap any water.
		PriorityQueue<Pair> minHeap = new PriorityQueue<>();
		boolean[][] visited = new boolean[rows][cols];
		// put all the border cells of the matrix into heap.
		processBorder(minHeap, visited, matrix, rows, cols);
		int result = 0;
		while (!minHeap.isEmpty()) {
			Pair cur = minHeap.poll();
			// get all possible neighbor cells, excluding already visited.
			List<Pair> neighbors = allNeighbors(cur, matrix, visited);
			for (Pair nei : neighbors) {
				visited[nei.x][nei.y] = true;
				// if current-water-height is height than nei's, able to store water
				result += Math.max(0, cur.height - nei.height);
				// if nei's height is smaller than current-water-height, set the height to water-height;
				nei.height = Math.max(nei.height, cur.height);
				// put nei to minHeap as seed points.
				minHeap.offer(nei);
			}
		}
		return result;
	}
	
	// put all the border cells into the min heap at the very beginning,
	// these are the start points of the whole BFS process.
	private void processBorder(PriorityQueue<Pair> minHeap, boolean[][] visited, int[][] matrix, int rows, int cols) {
		// for top and bottom rows, include two ends for each row
		for (int j = 0; j < cols; j++) {
			minHeap.offer(new Pair(0, j, matrix[0][j]));
			visited[0][j] = true;
			minHeap.offer(new Pair(rows - 1, j, matrix[rows - 1][j]));
			visited[rows - 1][j] = true;
		}
		// for left and right cols, EXCLUDE two ends for each col
		for (int i = 1; i < rows - 1; i++) {
			minHeap.offer(new Pair(i, 0, matrix[i][0]));
			visited[i][0] = true;
			minHeap.offer(new Pair(i, cols - 1, matrix[i][cols - 1]));
			visited[i][cols - 1] = true;
		}
	}
	
	private List<Pair> allNeighbors(Pair cur, int[][] matrix, boolean[][] visited) {
		List<Pair> neis = new ArrayList<>();
		int[] dx = new int[] {0, 0, -1, 1};
		int[] dy = new int[] {-1, 1, 0, 0};
		for (int i = 0; i < 4; i++) {
			int nx = cur.x + dx[i];
			int ny = cur.y + dy[i];
			if (0 <= nx && nx < matrix.length && 0 <= ny && ny < matrix[0].length && !visited[nx][ny]) {
				neis.add(new Pair(nx, ny, matrix[nx][ny]));
			}
		}
		return neis;
	}

	static class Pair implements Comparable<Pair> {
		int x; // row index.
		int y; // column index.
		int height; // height of the cell in the original matrix.

		Pair(int x, int y, int height) {
			this.x = x;
			this.y = y;
			this.height = height;
		}

		// !!! this is for comparator!!!
		// @Override
		// public int compare(Pair o1, Pair o2) {
		//
		// }

		@Override
		public int compareTo(Pair p1) {
			if (this.height == p1.height) {
				return 0;
			} else {
				return this.height < p1.height ? -1 : 1;
			}
		}
	}
}
