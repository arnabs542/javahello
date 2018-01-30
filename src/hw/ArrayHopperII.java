package hw;

/**
 * 
 * DP
 * 
 * Array Hopper II
 * 
 * Given an array A of non-negative integers, you are initially positioned at
 * index 0 of the array. A[i] means the maximum jump distance from index i (you
 * can only jump towards the end of the array). Determine the minimum number of
 * jumps you need to reach the end of array. If you can not reach the end of the
 * array, return -1.
 * 
 * Assumptions
 * 
 * The given array is not null and has length of at least 1. Examples
 * 
 * {3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the
 * end of array)
 * 
 * {2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in
 * this case.
 * 
 * @author
 *
 */
public class ArrayHopperII {
	// Method 1: DP.
	public int minJump1(int[] array) {
		// Assumptions: array is not null and is not empty.
		int length = array.length;
		// minJump record the min number of jumps from 0 to each of the indices.
		int[] minJump = new int[length];
		// we do not need to jump for index 0l
		minJump[0] = 0;
		for (int i = 1; i < length; i++) {
			minJump[i] = -1; // initialized as unreachable.
			for (int j = i - 1; j >= 0; j--) {
				if (j + array[j] >= i && minJump[j] != -1) {
					if (minJump[i] == -1 || minJump[i] > minJump[j] + 1) {
						minJump[i] = minJump[j] + 1;
					}
				}
			}
		}
		return minJump[length - 1];
	}

	// Method 2: Greedy solution. --> ???
	public int minJumpII(int[] array) {
		if (array.length == 1) {
			return 0;
		}
		// # of jumps currently.
		int jump = 0;
		// max index by current # of jumps.
		int cur = 0;
		// max index by current + 1 # of jumps.
		int next = 0;
		for (int i = 0; i < array.length; i++) {
			if (i > cur) {
				// if index i can not be reached by current # of jumps,
				// we need jump one more step.
				jump++;
				// if there is no progress by jumping one more step,
				// means it is unreachable.
				if (cur == next) {
					return -1;
				}
				cur = next;
			}
			next = Math.max(next, array[i] + i);
		}
		return jump;
	}

	// self, first wrong, add reach>=length-1 case then correct
	public int minJump(int[] array) {
		// Assumptions: array is not null and is not empty.
		int length = array.length;
		int jump = 0;
		int reach = 0;
		int nextReach = reach; 
		for (int i = 0; i < length; i++) {
		  // should first check this >= case for reach, otherwise wrong for case3
		  if (reach >= length - 1) {
				return jump;
			}
		  if (reach < i) {
		    if (nextReach >= i) {
		      jump++;
		      reach = nextReach;
		    } else {
		      return -1;
		    }
		  } 
		  nextReach = Math.max(nextReach, array[i] + i);
		  if (nextReach >= length - 1) {
		    return jump + 1;
		  }
		}
		return -1;
	}

}
