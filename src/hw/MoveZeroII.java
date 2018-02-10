package hw;

/**
 * 
 * Move 0s To The End II 
 * 
 * Description Given an array of integers, move all the 0s
 * to the right end of the array.
 * 
 * The relative order of the elements in the original array need to be
 * maintained.
 * 
 * Assumptions:
 * 
 * The given array is not null. Examples:
 * 
 * {1} --> {1} {1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} Easy Array
 * 
 * @author
 *
 */
public class MoveZeroII {
	public int[] moveZero(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int end = 0; // [0, end) is the good area with non-0s.
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				array[end++] = array[i];
			}
		}
		// process the [end, len) part to fill with 0s.
		for (int i = end; i < array.length; i++) {
			array[i] = 0;
		}
		return array;
	}
	
	public int[] moveZero_Ref(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		// the slow pointer is not included in the non-zero subarray.
		int end = 0; 
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				array[end++] = array[i];
			}
		}
		// fill in the right part with 0.
		for (int i = end; i < array.length; i++) {
			array[i] = 0;
		}
		return array;
	}
}
