package hw;

import java.util.Arrays;

import utils.ConsolePrinter;

/**
 * Array Deduplication III
 * 
 * Description
 * 
 * Given a sorted integer array, remove duplicate elements. For each group of
 * elements with the same value do not keep any of them. Do this in-place, using
 * the left side of the original array and and maintain the relative order of
 * the elements of the array. Return the array after deduplication.
 * 
 * Assumptions
 * 
 * The given array is not null Examples
 * 
 * {1, 2, 2, 3, 3, 3} -> {1}
 * 
 * Medium Array
 * 
 * 
 * @author
 *
 */
public class ArrayDeduplicationIII {

	public int[] dedup_REF(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int end = 0; 
		// use flag to see if there is any duplicates of array[end].
		boolean flag = false;
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[end]) {
				// if there is duplicate, set flag and do nothing.
				// --> this will continue the loop for i++ until encounter a different char
				// while end is on the repeated value char position.
				flag = true;
			} else if (flag == true) {
				// if array[i] != array[end], and flag is set,
				// array[end] should not be included in the valid subarray,
				// and we can just replace it with array[i] since next
				// we are going to check if there is any duplicate of array[i].
				// --> now the current end is the last repeated char
				// should be overwritten by the new char on [i], which is different from end
				array[end] = array[i];
				// reset flag to false since we are processing another element now.
				flag = false;
			} else {
				// if array[i] != array[end] and flag is not set,
				// it means there is no duplicate of array[end] and it should be 
				// included in the valid subarray.
				array[++end] = array[i];
			}
		}
		// --> note for the last char, still need to consider two cases
		return Arrays.copyOf(array, flag ? end: end + 1);
	}
	
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int slow = 0; // [0, slow) is good subarray
		int fast = 0;
		while (fast < array.length) {
			if (fast == array.length - 1 || array[fast] != array[fast + 1]) {
				array[slow++] = array[fast++];
			} else {
				// skip current repeated value until meet next value.
				while (fast + 1 < array.length && array[fast] == array[fast + 1]) { // should be ==, not !=
					fast++;
				}
				if (array[fast] == array[fast - 1]) {
					fast++;
				}
			}
		}
		return Arrays.copyOf(array, slow);
	}
	
	public static void main(String[] args) {
		ArrayDeduplicationIII sol = new ArrayDeduplicationIII();
		int[] arr1 = new int[] {1, 2, 2, 3, 3, 3}; // --> {1}
		int[] arr = new int[] {1,1,2,3,3,4,5,5,5}; // --> {2, 4}

		int[] result = sol.dedup(arr);
		ConsolePrinter.printIntArray(result);
	}
}
