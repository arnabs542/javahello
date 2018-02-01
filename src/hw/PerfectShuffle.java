package hw;

/**
 * 
 * Given an array of integers (without any duplicates), shuffle the array such
 * that all permutations are equally likely to be generated.
 * 
 * Assumptions
 * 
 * The given array is not null Medium probability sampling-and-randomization
 * 
 * @author
 *
 */
public class PerfectShuffle {
	public void shuffle??(int[] array) {
		// Assumption: array is not null.
		if (array.length <= 1) {
			return;
		}
		// from right to left,
		// for index i - 1, randomly pick one of the first elements.
		for (int i = array.length; i >= 1; i--) {
			int idx = (int) (Math.random() * i);
			swap(array, i - 1, idx);
		}
	}
	
	private void swap(int[] array, int left, int right) {
		int tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}
	
	
	public void shuffle_self??(int[] array) {
		// Assumption: array is not null.
		if (array.length <= 1) {
			return;
		}
		for (int i = 0; i < array.length; i++) {
			int idx = (int) (Math.random() * (i+1));
			swap(array, 1, idx);
		}
	}
}
