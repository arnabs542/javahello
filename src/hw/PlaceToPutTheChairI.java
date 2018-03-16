package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import utils.ConsolePrinter;

/**
 * Place To Put The Chair I
 * 
 * Place To Put The Chair I
 * 
 * Description
 * 
 * Given a gym with k pieces of equipment and some obstacles. We bought a chair
 * and wanted to put this chair into the gym such that the sum of the shortest
 * path cost from the chair to the k pieces of equipment is minimal. The gym is
 * represented by a char matrix, ¡®E¡¯ denotes a cell with equipment, ¡®O¡¯ denotes
 * a cell with an obstacle, 'C' denotes a cell without any equipment or
 * obstacle. You can only move to neighboring cells (left, right, up, down) if
 * the neighboring cell is not an obstacle. The cost of moving from one cell to
 * its neighbor is 1. You can not put the chair on a cell with equipment or
 * obstacle.
 * 
 * Assumptions
 * 
 * 1) The cost from one cell to any of its neighbors(up/down/left/right) is 1.
 * 2) 'E' denotes equiment, 'O' denotes obstacle, 'C' denotes empty cell. 3) The
 * chair can not be put on 'E' or 'O'. 4) Each 'C' cell is reachable from all
 * 'E' cells.
 * 
 * There is at least one equipment in the gym
 * 
 * The given gym is represented by a char matrix of size M * N, where M >= 1 and
 * N >= 1, it is guaranteed to be not null It is guaranteed that each 'C' cell
 * is reachable from all 'E' cells.
 * 
 * If there does not exist such place to put the chair, just return null (Java)
 * empty vector (C++) Examples
 * 
 * { { 'E', 'O', 'C' },
 * 
 * { 'C', 'E', 'C' },
 * 
 * { 'C', 'C', 'C' } }
 * 
 * we should put the chair at (1, 0), so that the sum of cost from the chair to
 * the two equipment is 1 + 1 = 2, which is minimal.
 * 
 * 
 * 
 * Hard Breadth First Search
 * 
 * @author
 *
 */
public class PlaceToPutTheChairI {
	private static final char EQUIP = 'E';
	private static final char OB = 'O';

	public List<Integer> putChair(char[][] gym) {
		// Assumptions: gym is not null, has size M * N,
		// where M >= 1 and N >= 1,
		// return null if you can not put the chair anywhere.
		// there is at least one equipment in the gym.
		int M = gym.length;
		int N = gym[0].length;
		// use a matrix to record the sum of shortest path cost
		// from each cell to all the 'E' cells.
		int[][] cost = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (EQUIP == gym[i][j]) {
					// use BFS to calculate the shortest path cost from
					// each of the equipments to all the other reachable cells
					// and add the cost to each corresponding cell.
					// Note the return boolean value represents if there exists
					// another 'E' cell not reachable from the current one,
					// if so, there won't exists a cell to place the chair.
					// --> because if two 'E' are isolated by 'O's, then not possible
					// to find a point for 'C' that reach all 'E's.
					if (!addCost(cost, gym, i, j)) {
						return null;
					}
				}
			}
		}
		// find the cell with smallest sum of shortest path costs
		// to all the 'E' cells.
		List<Integer> result = null;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (EQUIP != gym[i][j] && OB != gym[i][j]) {
					if (result == null) {
						result = Arrays.asList(i, j);
					} else if (cost[i][j] < cost[result.get(0)][result.get(1)]) {
						result.set(0, i);
						result.set(1, j);
					}
				}
			}
		}
		// ConsolePrinter.printIntMatrix(cost);
		return result;
	}

	// --> find shortest pathes for 'E' at (i, j) and record to cost
	private boolean addCost(int[][] cost, char[][] gym, int i, int j) {
		// use a boolean matrix to make sure each cell will be visited
		// no more than once.
		boolean[][] visited = new boolean[gym.length][gym[0].length];
		// Bread-First-Search, record the current path cost.
		int pathCost = 1;
		Queue<Pair> queue = new LinkedList<>();
		visited[i][j] = true;
		queue.offer(new Pair(i, j));
		// --> expanding to neighbors from closest to farthest by using the queue
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int l = 0; l < size; l++) {
				Pair cur = queue.poll();
				List<Pair> neis = getNeis(cur, gym);
				for (Pair nei : neis) {
					if (!visited[nei.i][nei.j]) {
						visited[nei.i][nei.j] = true;
						cost[nei.i][nei.j] += pathCost;
						queue.offer(nei);
					}
				}
			}
			// advance the pathCost by 1 for each level.
			pathCost++;
		}
		

		// if there exists another 'E' cell not reachable from
		// the path start 'E' cell, we return false.
		for (int x = 0; x < gym.length; x++) {
			for (int y = 0; y < gym[0].length; y++) {
				if (!visited[x][y] && EQUIP == gym[x][y]) {
					return false;
				}
			}
		}
		return true;
	}

	
	private List<Pair> getNeis(Pair cur, char[][] gym) {
		int x = cur.i;
		int y = cur.j;
		int M = gym.length;
		int N = gym[0].length;
		List<Pair> neis = new ArrayList<>();
		int[] deltax = new int[] {0, 0, -1, 1};
		int[] deltay = new int[] {-1, 1, 0, 0};
		for (int i = 0; i < 4; i++) {
			int nx = x + deltax[i];
			int ny = y + deltay[i];
			if (nx < M && nx >= 0 && ny < N && ny >= 0 && OB != gym[nx][ny]) {
				neis.add(new Pair(nx, ny));
			}
		}
		return neis;
		
	}

	
	static class Pair {
		int i;
		int j;

		Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) {
		char E = 'E', C = 'C', O = 'O';
		char[][] gym = new char[][] { 
			{E, C, E, O, C}, 
			{E, O, C, C, E}, 
			{O, O, E, C, C}, 
			{C, O, C, E, E}, 
			{E, C, C, C, C}
		}; // expecting [1, 2]
		PlaceToPutTheChairI sol = new PlaceToPutTheChairI();
		List<Integer> result = sol.putChair(gym);
		System.out.println(result);
		
	}
}
