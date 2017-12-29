package hw;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Data Structure K Smallest In Unsorted Array Find the K smallest numbers in an
 * unsorted integer array A. The returned numbers should be in ascending order.
 * 
 * Assumptions
 * 
 * A is not null K is >= 0 and smaller than or equal to size of A Return
 * 
 * an array with size K containing the K smallest numbers in ascending order
 * Examples
 * 
 * A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
 * 
 * @author
 *
 */
public class KSmallestInUnsortedArray {
	public int[] kSmallest(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k == 0) {
			return new int[0];
		}
		int[] res = new int[k];
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++) {
			if (i < k) {
				maxHeap.offer(arr[i]);
			} else {
				if (arr[i] < maxHeap.peek()) {
					maxHeap.poll();
					maxHeap.offer(arr[i]);
				}
			}
		}
		// should do in reverse order idx in res, for polling from max heap
		for (int i = k - 1; i >= 0; i--) {
			res[i] = maxHeap.poll();
		}
		return res;
	}

	public int[] kSmallest_ref1(int[] arr, int k) {
		// Method 1: K sized max heap
		if (arr == null || arr.length == 0 || k == 0) {
			return new int[0];
		}
		
//		Comparator<Integer> myCom = new Comparator<Integer>() {
//			// @overide --> this is wrong, can't put overide here
//			public int compare(Integer o1, Integer o2) {
//				// do not use "==" here !!!
//				if (o1.equals(o2)) {
//					return 0;
//				} 
//				return o1 > o2 ? -1 : 1;
//			}
//		};
//		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, myCom);
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				// do not use "==" here !!!
				if (o1.equals(o2)) {
					return 0;
				} 
				return o1 > o2 ? -1 : 1;
			}
		});
		for (int i = 0; i < arr.length; i++) {
			if (i < k) {
				maxHeap.offer(arr[i]);
			} else if (arr[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.offer(arr[i]);
			}
		}
		// should do in reverse order idx in res, for polling from max heap
		int[] res = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			res[i] = maxHeap.poll();
		}
		return res;
	}	
	
	public int[] kSmallest_ref2(int[] arr, int k) {
		// Method 2: quick select
		if (arr == null || arr.length == 0 || k == 0) {
			return new int[0];
		}
		quickSelect(arr, 0, arr.length - 1, k);
		int[] res = Arrays.copyOf(arr, k);
		Arrays.sort(res);
		return res;
	}
	
	private void quickSelect(int[] arr, int left, int right, int target) {
		int idx = partition(arr, left, right);
		if (idx == target) {
			return;
		} else if (idx < target) {
			quickSelect(arr, idx + 1, right, target);
		} else {
			quickSelect(arr, left, idx - 1, target);
		}
	}
	
	private int partition(int[] arr, int left, int right) {
		if (left >= right) {
			return left;
		}
		int pivot = arr[right];
		int start = left, end = right - 1;
		while (start <= end) {
			if (arr[start] < pivot) {
				start++;
			} else if (arr[end] >= pivot) {
				end--;
			} else {
				swap(arr, start++, end--);
			}
		}
		swap(arr, start, right);
		return start;
	}
	
	private void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
}
