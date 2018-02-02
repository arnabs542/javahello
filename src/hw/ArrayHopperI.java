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

	public boolean canJump_self_AC(int[] array) {
		// Assumptions: array is not null and is not empty.
		if (array.length == 1) {
			return true;
		}
		int furthest = 0;
		for (int i = 0; i < array.length; i++) {
			if (furthest < i) {
				return false;
			}
			furthest = Math.max(furthest, i + array[i]);
			if (furthest >= array.length - 1) {
				return true;
			}
		}
		return true;
	}

	// Method 3: Greedy solution.
	// Keep the max index reachable by jumping x steps, and
	// the max index reachable by jumping x+1 steps.
	public boolean canJumpIII(int[] array) {
		// Assumptions: array is not null and is not empty.
		if (array.length == 1) {
			return true;
		}
		// the max index jumping current steps can reach.
		int cur = 0;
		// the max index jumping current + 1 steps can reach.
		int next = 0;
		for (int i = 0; i < array.length; i++) {
			if (i > cur) {
				// if i > cur, we can not use current steps to jump to i.
				if (cur == next) {
					// cur == next means there is no progress for
					// using current + 1 steps, if that is the case,
					// we can never reach end of array.
					return false;
				}
				cur = next;
			}
			next = Math.max(next, i + array[i]);
		}
		return true;
	}

	// --> after class understand
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
				// --> self
				// j is the distance you can jump from pos i,
				// from i onward, positions [i, i+j]
				// any position has canJump[] = true,
				// then canJump[i] is true 
				// <--
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
