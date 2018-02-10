package hw;

import utils.ConsolePrinter;

/**
 * Largest And Smallest
 * 
 * Description
 * 
 * 
 * Use the least number of comparisons to get the largest and smallest number in
 * the given integer array. Return the largest number and the smallest number.
 * 
 * Assumptions
 * 
 * The given array is not null and has length of at least 1 Examples
 * 
 * {2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5,
 * 1].
 * 
 * Medium Array
 * 
 * @author
 *
 */
public class LargestAndSmallest {
	//--> if use scan and keep tracking the min and max, --> 2 (n - 1) comparison
	// use def idea: 
	// 1. compare and swap into smaller / bigger half, (n - 1) / 2 comparison
	// 2. find max in bigger half and min in smaller half, (n / 2 - 1) + (n / 2 - 1) comparison
	
	public int[] largestAndSmallest(int[] array) {
		// Assumption: array is not null & array.length >= 1.
		int n = array.length;
		// indices (x, n - 1 - x) will be paired up, the larger numbers of 
		// each pair will be put on the left side, so after the comparisons,
		// The left half of the array are the larger values for each pairs,
		// the right half of the array are the smaller values for each pairs.
		// for (int i = 0; i < n / 2; i++) {
		 for (int i = 0; i < n / 2; i++) {
			if (array[i] < array[n - 1 - i]) {
				swap(array, i, n - 1 - i);
			}
		}
		// The largest value should be the largest of the left half,
		// The smallest value should be the smallest of the right half.
		// --> Note the boundary index, think about odd, even, and minimize comparison. 
		// boundaries are inclusive, need to cover the middle point.
		return new int[] {largest(array, 0, (n - 1) / 2), smallest(array, n / 2, n - 1)};
		
	}
	
	private int largest(int[] array, int left, int right) {
		int largest = array[left];
		for (int i = left + 1; i <= right; i++) {
			largest = Math.max(largest, array[i]);
		}
		return largest;
	}
	
	private int smallest(int[] array, int left, int right) {
		int smallest = array[left];
		for (int i = left + 1; i <= right; i++) {
			smallest = Math.min(smallest, array[i]);
		}
		return smallest;
	}
	
	private void swap(int[] array, int a, int b) {
		int tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
	
	public static void main(String[] args) {
		LargestAndSmallest sol = new LargestAndSmallest();
		int[] arr1 = new int[] {1,2,5,3,4};  // --> [5, 1]
		int[] arr = new int[] {3,2,1,3,4};  // --> [4, 1]
		int[] result = sol.largestAndSmallest(arr);
		ConsolePrinter.printIntArray(result);

	}
}
