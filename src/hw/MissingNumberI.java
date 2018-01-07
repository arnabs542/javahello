package hw;

import java.util.HashSet;

/**
 * None Missing Number I 
 * 
 * Given an integer array of size N - 1, containing all
 * the numbers from 1 to N except one, find the missing number.
 * 
 * Assumptions
 * 
 * The given array is not null, and N >= 1 Examples
 * 
 * A = {2, 1, 4}, the missing number is 3 
 * A = {1, 2, 3}, the missing number is 4
 * A = {}, the missing number is 1
 * 
 * @author
 *
 */
public class MissingNumberI {
	// Method 1: use HashSet.
	// Assumption: array is not null.
	public int missing(int[] arr) {
		int n = arr.length + 1;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int number: arr) {
			set.add(number);
		}
		for (int i = 1; i< n; i++) {
			if (!set.contains(i)) {
				return i;
			}
		}
		return n;
	}
	
	// Method 2: use sum.
	public int missingII(int[] arr) {
		int n = arr.length + 1;
		long targetSum = (n + 0L) * (n + 1) / 2;
		long actualSum = 0L;
		for (int num : arr) {
			actualSum += num;
		}
		return (int)(targetSum - actualSum);
	}
	
	// Method 3: bit operation. - O(n)
	public int missingIII(int[] arr) {
		int n = arr.length + 1;
		int xor = 0;
		// xor 1 to n;
		for (int i = 1; i <= n; i++) {
			xor ^= i;
		}
		// after this operation, all the numbers from 1 to n are pair xor'ed
		// except for the missing number.
		// since x ^ x = 0, remaining number is the result.
		for (int num : arr) {
			xor ^= num;
		}
		return xor;
	}
	
	// Method 4: swap to the original position. - O(n)
	public int missingIV(int[] arr) {
		// try to swap the numbers to its corresponding position.
		// for the number x, the corresponding position is x - 1.
		for (int i = 0; i< arr.length; i++) {
			// while arr[i] is not i + 1, swap arr[i] to its correct position if possible.
			while (arr[i] != i + 1 && arr[i] != arr.length + 1) {
				swap(arr, i, arr[i] - 1);
			}
		}
		// if the missing number is in range of [1, n-1], 
		// then we can find it by checking the index i where arr[i] != i + 1
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != i + 1) {
				return i + 1;
			}
		}
		// if all the numbers of [1, n-1] are in position,
		// the missing number is n
		return arr.length + 1;
		
	}
	
	private void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
