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
	public void shuffle_REF(int[] array) {
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

	public void shuffle_self(int[] array) {
		// Assumptions: array is not null;
		int n = array.length;
		for (int i = n - 1; i >= 0; i--) {
			// int idx = (int) Math.random() * (i + 1);  // !!! this way int will apply to random only!!!
			int idx = (int) (Math.random() * (i + 1));
			swap(array, idx, i);
		}
	}


}
