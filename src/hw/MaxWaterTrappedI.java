package hw;

/**
 * 
 * Max Water Trapped I
 * 
 * Description
 * 
 * Given a non-negative integer array representing the heights of a list of
 * adjacent bars. Suppose each bar has a width of 1. Find the largest amount of
 * water that can be trapped in the histogram.
 * 
 * Assumptions
 * 
 * The given array is not null Examples
 * 
 * { 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2 (at index
 * 1, 1 unit of water can be trapped and index 3, 1 unit of water can be
 * trapped)
 * 
 * 
 * 
 * Medium Array Two Pointers
 * 
 * @author
 *
 */
public class MaxWaterTrappedI {

	// REF
	public int maxTrapped(int[] array) {
		// Assumption: array is not null.
		if (array.length == 0) {
			return 0;
		}
		// --> [left, right]: unknown area to explore.
		int left = 0;
		int right = array.length - 1;
		int leftMax = array[left];
		int rightMax = array[right];
		int result = 0;
		//--> no need for left = right because one of them will be current taller bar,
		// the other will be moving towards it. (fanzheng: The taller bar if capable for storing water,
		// would be reached by either left/right, and will be calced the storage, and moved on, because
		// the leftMax/rightMax will be taller than it, and left/right will stop on one of left/right -->
		// the taller bar will NOT be the one that (left/right stops on AND capable to store water).
		// so no need for == case.
		while (left < right) { 
			if (array[left] <= array[right]) {
				result += Math.max(0, leftMax - array[left]);
				leftMax = Math.max(leftMax, array[left]);
				left++;
			} else {

				result += Math.max(0, rightMax - array[right]);
				rightMax = Math.max(rightMax, array[right]);
				right--;
			}

		}
		return result;
	}

	// self, AC
	public int maxTrapped_self(int[] array) {
		// Assumption: array is not null.
		if (array.length == 0) {
			return 0;
		}
		int leftMax = array[0];
		int rightMax = array[array.length - 1];
		int left = 1;
		int right = array.length - 2;
		int result = 0;
		while (left <= right) {
			// if (array[left] <= array[right]) { // wrong: should compare max (this is
			// wrong: should use the array[left/right] not left/rightMax, because the
			// higher --> if use array[left/right], then when )
			// boundary stops on left / right
			if (leftMax <= rightMax) {
				if (array[left] <= leftMax) { // !!! even if == ,should also increase left, otherwise may deadloop
					result += leftMax - array[left]; // here for sure rightMax > leftMax
					left++;
				} else {
					leftMax = array[left];
				}

			} else {
				if (array[right] <= rightMax) { // !!! same here, should include == case
					result += rightMax - array[right]; // here for sure rightMax < leftMax, no need to do
														// Math.min(leftMax, rightMax)
					right--;
				} else {
					rightMax = array[right];
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		MaxWaterTrappedI sol = new MaxWaterTrappedI();
		int[] bars1 = new int[] { 2, 1, 3, 2, 4 }; // expect 2
		int[] bars2 = new int[] { 5, 4, 2, 1, 3, 6 }; // expect 10
		int[] bars3 = new int[] { 5, 3, 2 }; // expect 0
		int[] bars4 = new int[] { 2, 8, 3, 6, 4, 5 }; // expect 4
		int[] bars = new int[] { 5, 1, 4, 2, 3, 6 }; // expect 10


		int result = sol.maxTrapped(bars);
		System.out.println(result);
	}
}
