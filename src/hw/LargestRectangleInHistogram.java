package hw;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * Given a non-negative integer array representing the heights of a list of
 * adjacent bars. Suppose each bar has a width of 1. Find the largest
 * rectangular area that can be formed in the histogram.
 * 
 * Assumptions
 * 
 * The given array is not null or empty Examples
 * 
 * { 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from
 * index 2 and ending at index 4)
 * 
 * 
 * 
 * Hard Stack
 * 
 * @author
 *
 */
public class LargestRectangleInHistogram {

	// ref -->
	// 1. add the ending bar of height 0 to make the code cleaner, 
	// 2. in for loop, code is also cleaner for always offer.
	public int largest_ref(int[] array) {
		// Assumptions: given array is not null, not empty
		// all the values in the array are non-negative.
		Deque<Integer> stack = new LinkedList<>(); 
		// Note that stack contains the index for increasing bars, not the "value" of the array.
		int result = 0;
		for (int i = 0; i <= array.length; i++) {
			// we need a way of popping out all the elements in the stack
			// at last, so that we explicitly add a bar of height 0.
			int cur = (i == array.length ? 0 : array[i]);
			while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
				int height = array[stack.pollFirst()];
				// determine the lfet boundary of the largest rectangle
				// with height array[i].
				int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
				// determine the right boundary of the largest rectangle
				// with the height of the popped element.
				result = Math.max(result, height * (i - left));
			}
			stack.offerFirst(i);
		}
		return result;
	}
	
	
	// self, ac
	public int largest(int[] array) {
		// Assumptions: given array is not null, not empty
		Deque<Integer> stack = new LinkedList<>(); // keep the index for increasing bars
		int largest = 0;
		for (int i = 0; i < array.length; i++) {
			if (stack.isEmpty() || array[i] > array[stack.peekLast()]) {
				stack.offerLast(i);
			} else {
				// for equal height, can also pop current stack, the new equal height will
				// enqued and be calced when it gets popped.
				while (!stack.isEmpty() && array[stack.peekLast()] >= array[i]) {
					int idx = stack.pollLast();
					int left = stack.isEmpty() ? 0 : stack.peekLast() + 1;
					int area = array[idx] * (i - left);
					largest = Math.max(largest, area);
				}
				stack.offerLast(i);
			}
		}
		// reach the end of array, process the remaining in stack.
		while (!stack.isEmpty()) {
			int idx = stack.pollLast();
			int left = stack.isEmpty() ? 0 : stack.peekLast() + 1;
			int area = array[idx] * (array.length - left); // !!! length, not ()
			largest = Math.max(largest, area);
		}
		return largest;
	}
}
