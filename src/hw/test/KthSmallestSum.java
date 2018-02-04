package hw.test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * Given two sorted arrays A and B, with their sizes to be m and n,
 * respectively. We can pick one element a from A and the other element b from
 * B, and their sum s is defined to be s = a + b. How to find k-th smallest s
 * from all possible values of s. 
 * 
 * Assumption: k < m * n. 
 * 
 * A[m] = {1, 4, 6, 8, 10......} 
 * B[n] = {1, 4, 5, 7, 8, ...} 
 * k = 4, 
 * 
 * return 6. 
 * (
 * smallest 1 + 1 = 2,
 * 2nd smallest 1 + 4 = 5, 
 * 3rd smallest 4 + 1 = 5, 
 * 4th smallest 1 + 5 = 6.
 * )
 * 
 * @author
 *
 */

public class KthSmallestSum {

	/**
	 * Assumptions: k < m * n. two input arrays are sorted. values in input array
	 * are unique. equal sums are counted separatedly, eg. 1+2 and 2+1 are two sums.
	 * 
	 * @param arr1
	 * @param arr2
	 * @param k
	 * @return
	 */
	public int kthSmallest(int[] arr1, int[] arr2, int k) {
		if (arr1 == null || arr1.length == 0) {
			return arr2[k - 1];
		}
		if (arr2 == null || arr2.length == 0) {
			return arr1[k - 1];
		}
		PriorityQueue<Cell> minHeap = new PriorityQueue(2, new Comparator<Cell>() {
			@Override
			public int compare(Cell o1, Cell o2) {
				int sum1 = arr1[o1.aIdx] + arr2[o1.bIdx];
				int sum2 = arr1[o2.aIdx] + arr2[o2.bIdx];
				return Integer.compare(sum1, sum2);
			}
		});
		boolean[][] visited = new boolean[arr1.length][arr2.length];
		minHeap.offer(new Cell(0, 0));
		visited[0][0] = true;
		for (int i = 0; i < k - 1; i++) {
			Cell tmp = minHeap.poll();
			if (tmp.aIdx + 1 < arr1.length && !visited[tmp.aIdx + 1][tmp.bIdx]) {
				visited[tmp.aIdx + 1][tmp.bIdx] = true;
				minHeap.offer(new Cell(tmp.aIdx + 1, tmp.bIdx));
			}
			if (tmp.bIdx + 1 < arr2.length && !visited[tmp.aIdx][tmp.bIdx + 1]) {
				visited[tmp.aIdx][tmp.bIdx + 1] = true;
				minHeap.offer(new Cell(tmp.aIdx, tmp.bIdx + 1));
			}
		}
		Cell res = minHeap.poll();
		return arr1[res.aIdx] + arr2[res.bIdx];
	}
}

class Cell {
	int aIdx;
	int bIdx;

	public Cell(int a, int b) {
		aIdx = a;
		bIdx = b;
	}
}
