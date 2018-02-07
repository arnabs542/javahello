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
public class ArrayDeduplicationII {
	
	public int[] dedup_ref(int[] array) {
		// Assumptions: array is not null
		if (array.length <= 2) {
			return array;
		}
		// [0, end] is the valid area
		int end = 1;
		for (int i = 2; i < array.length; i++) {
			// Notice: we can do this because it is a sorted array, and 
			// array[i] == array[end - 1] implies that
			// array[end] == array[end] == array[i].
			// if it is not a sorted array we need to find other way out.
			if (array[i] != array[end - 1]) {
				array[++end] = array[i];
			}
		}
		return Arrays.copyOf(array, end + 1);
	}
	
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 2) {
			return array;
		}
		// [0, end] is the valid area
		int end = 1;
		for (int i = 2; i < array.length; i++) {
			// compare with end-1 because, end can = end - 1
			// make sure there is no more than 2 vals
			if (array[i] != array[end - 1]) {
				array[++end] = array[i];
			}
		}
		return Arrays.copyOf(array, end + 1);
	}
}
