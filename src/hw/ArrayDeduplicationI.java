package hw;

import java.util.Arrays;

/**
 * Array Deduplication I
 * 
 * Description
 * 
 * Given a sorted integer array, remove duplicate elements. For each group of
 * elements with the same value keep only one of them. Do this in-place, using
 * the left side of the original array and maintain the relative order of the
 * elements of the array. Return the array after deduplication.
 * 
 * Assumptions
 * 
 * The array is not null Examples
 * 
 * {1, 2, 2, 3, 3, 3} → {1, 2, 3}
 * 
 * Easy Array
 * 
 * 
 * @author
 *
 */
public class ArrayDeduplicationI {
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		// [0, end] is the 'good' area
		int end = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[end]) {
				array[++end] = array[i];
			}
		}
		return Arrays.copyOf(array, end + 1);
	}
	
	public int[] dedup_ref(int[] array) {
		// Assumptions: array is not null
		if (array.length <= 1) {
			return array;
		}
		// slow pointer starts from 0 and array[0] is always valid.
		// slow pointer is "included" in the valid partition.
		int end = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[end]) {
				array[++end] = array[i];
			}
		}
		// Notice: usually you don't have to copy the subarray, instead,
		// you can just return the length of valid subarray after ddup
		// since the operation is done in place.
		// this is only useful for laicode to validate the result.
		return Arrays.copyOf(array, end + 1);
	}
}
