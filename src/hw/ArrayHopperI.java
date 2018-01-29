package hw;

/**
 * 
 * DP
 * 
 * Array Hopper I 
 * 
 * Given an array A of non-negative integers, you are initially positioned at
 * index 0 of the array. A[i] means the maximum jump distance from that position
 * (you can only jump towards the end of the array). Determine if you are able
 * to reach the last index.
 * 
 * Assumptions
 * 
 * The given array is not null and has length of at least 1. Examples
 * 
 * {1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then
 * reach the end of the array)
 * 
 * {2, 1, 1, 0, 2}, we are not able to reach the end of array
 * 
 * @author
 *
 */
public class ArrayHopperI {
	// Method 3: Greedy solution.
	
	
	// ???
	// Method 2: DP, canJump[i] means from index i,
	// can jump to index array.length - 1
	public boolean canJumpII(int[] array) {
		// Assumptions: array is not null and is not empty.
		if (array.length == 1) {
			return true;
		}
		boolean[] canJump = new boolean[array.length];
		for (int i = array.length - 2; i >= 0; i--) {
			// if from index i, it is already possible to jump
			// to the end of the array.
			if (i + array[i] >= array.length - 1) {
				canJump[i] = true;
			} else {
				// if any of the reachable indices from index i
				// is reachable to the end of the array.
				for (int j = array[i]; j >= 1; j--) {
					if (canJump[j + i]) {
						canJump[i] = true;
						break;
					}
				}
			}
		}
		return canJump[0];
	}
	
	
	// Method 1: DP, boolean canJump[i] means from index 0, can jump to index i.
	public boolean canJump(int[] array) {
		// Assumptions: array is not null and is not empty.
		boolean[] canJump = new boolean[array.length];
		canJump[0] = true;
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				if (canJump[j] && array[j] + j >= i) {
					canJump[i] = true;
					break;
				}
			}
		}
		return canJump[array.length - 1];
	}
}
