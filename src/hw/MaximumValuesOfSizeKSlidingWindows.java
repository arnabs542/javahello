package hw;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Maximum Values Of Size K Sliding Windows
 * 
 * Description
 * 
 * Given an integer array A and a sliding window of size K, find the maximum
 * value of each window as it slides from left to right.
 * 
 * Assumptions
 * 
 * The given array is not null and is not empty
 * 
 * K >= 1, K <= A.length
 * 
 * Examples
 * 
 * A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4},
 * {2,4,2}, {4,2,1}},
 * 
 * and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
 * 
 * Medium Deque
 * 
 * @author
 *
 */
public class MaximumValuesOfSizeKSlidingWindows {
	public List<Integer> maxWindows(int[] array, int k) {
		// Assumptions: array is not null or not empty,
		// k >= 1 and k <= a.length.
		List<Integer> max = new ArrayList<>();
		// use a descending deque to solve this problem,
		// we store the index instead of the actual value in the deque,
		// and we make sure:
		// 1. the deque only contains index in the current sliding window.
		// 2. for any index, the previous index with smaller value is 
		// discarded from the deque.
		Deque<Integer> deque = new LinkedList<Integer>();
		for (int i = 0; i < array.length; i++) {
			// discard any index with smaller value than index i.
			while (!deque.isEmpty() && array[deque.peekLast()] <= array[i]) {
				deque.pollLast();
			}
			// it is possible the head element is out of the current
			// sliding window so we might need to discard it as well.
			if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
				deque.pollFirst();
			} 
			deque.offerLast(i);
			if (i >= k - 1) {
				max.add(array[deque.peekFirst()]);
			}
		}
		return max;
		
	}
}
