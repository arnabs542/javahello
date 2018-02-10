package hw;

import java.util.Arrays;

import utils.ConsolePrinter;

/**
 * Array Deduplication IV
 * 
 * Description
 * 
 * Given an unsorted integer array, remove adjacent duplicate elements
 * repeatedly, from left to right. For each group of elements with the same
 * value do not keep any of them.
 * 
 * Do this in-place, using the left side of the original array. Return the array
 * after deduplication.
 * 
 * Assumptions
 * 
 * The given array is not null Examples
 * 
 * {1, 2, 3, 3, 3, 2, 2} ¡ú {1, 2, 2, 2} ¡ú {1}, return {1}
 * 
 * Hard Array Stack
 * 
 * ?? what about {1, 2, 2, 3, 3, 2} ¡ú {1, 2} or {1} ??
 * 
 * @author
 *
 */
public class ArrayDeduplicationIV {

	public int[] dedup_REF(int[] array) {
		// Assumption: array is not null.
		int end = -1;
		for (int i = 0; i < array.length; i++) {
			// we are using the left part of the original array as a stack
			// and the top element's index is end. if the stack if empty (end==-1)
			// we just push the element into the stack, or if the element is not
			// the same as the top element of the stack, we can push the element into 
			// the stack as well.
			if (end == -1 || array[end] != array[i]) {
				array[++end] = array[i];
			} else {
				// otherwise, we ignore all consecutive duplicates and 
				// remove the top lement of the stack.
				while (i + 1 < array.length && array[i + 1] == array[end]) {
					i++;
				}
				end--;
			}
		}
		return Arrays.copyOf(array, end + 1);
	}
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int slow = 0; // [0, end] is the good area 
		int fast = 1;
		while (fast < array.length) {
			
			if (slow >= 0 && array[fast] == array[slow]) { // note slow may be -1, need to check, case 3
				slow--;
				// skip the repeated value until fast is on a different value pos.
				int repeatVal = array[fast];
				while (fast + 1 < array.length && array[fast + 1] == repeatVal) {
					fast++;
				}
				if (array[fast] == repeatVal) {
					fast++;
				}
			} else {
				array[++slow] = array[fast++];
			}
			
		}
		return Arrays.copyOf(array, slow + 1);
	}

	public static void main(String[] args) {
		ArrayDeduplicationIV sol = new ArrayDeduplicationIV();
		int[] arr1 = new int[] { 1, 2, 3, 3, 3, 2, 2 }; // --> {1}
		int[] arr2 = new int[] { 1, 2, 2, 3, 3, 2}; // --> {1, 2}
		int[] arr3 = new int[] { 1,1,2,3,3,3,2,1,6}; // --> {1, 6}
		// int[] result = sol.dedup_REF(arr3);
		int[] result = sol.dedup(arr2);
		ConsolePrinter.printIntArray(result);
	}
}
