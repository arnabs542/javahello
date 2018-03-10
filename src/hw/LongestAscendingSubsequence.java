package hw;

/**
 * Longest Ascending Subsequence
 * 
 * Description
 * 
 * Given an array A[0]...A[n-1] of integers, find out the length of the longest
 * ascending subsequence.
 * 
 * Assumptions
 * 
 * A is not null Examples Input: A = {5, 2, 6, 3, 4, 7, 5} Output: 4 Because [2,
 * 3, 4, 5] is the longest ascending subsequence.
 * 
 * Medium Array Binary Search
 * 
 * 
 * @author
 *
 */
public class LongestAscendingSubsequence {
	// Method 1: O(n^2)
	public int longestI(int[] array) {
		// Assumptions: array is not null.
		if (array.length == 0) {
			return 0;
		}
		// dp[i]: the length of longest ascending subsequence ending on index i;
		int[] longest = new int[array.length];
		int result = 1;
		for (int i = 0; i < longest.length; i++) {
			// init as 1, since the shortest one has length 1 -- array[i] itself.
			longest[i] = 1;
			for (int j = 0; j < i; j++) {
				// only when array[j] < array[i], it is possible to use the 
				// longest ascending subsequence ending at index j and array[i]
				// to form a new ascending subsequence.
				if (array[j] < array[i]) {
					longest[i] = Math.max(longest[i], longest[j] + 1);
				}
			}
			// possibly update the global longest one.
			result = Math.max(longest[i], result);
		}
		return result;
	}
	
	// Method 2: O(nlogn)
	public int longest(int[] array) {
		// Assumptions: array is not null.
		if (array.length == 0) {
			return 0;
		}
		// tbl[i]: the smallest ending value of all the ascending subsequences
		// with length i.
		int[] tbl = new int[array.length + 1]; // tbl[0] not used, tbl[i]: subsequence of length i
		// initialization: at the very beginning, the longest ascending subsequence we have
		// has length 1 (array[0] itself).
		// while we traversing the array, we will update existing tbl[i] and find
		// new longest ascending subsequence.
		// --> result is the actual length that tbl[] will be used. tbl can be at most len+1 long
		int result = 1;
		tbl[1] = array[0];
		for (int i = 1; i < array.length; i++) {
			// tbl is guaranteed to be in ascending order --- the key point.
			// from tbl, find the best (longest) ascending subsequence, which can 
			// concatenate array[i] to form a new one.
			// this is actually a binary search of the 'largest samller value'.
			// --> 
			int index = find(tbl, 1, result, array[i]);
			// two cases:
			// 1. if array[i] is larger than all values in tbl, (index == result)
			// we can possibly for a longer ascending subsequence than all we have before
			// 2. we may update tbl[index + 1] because we find a better ascending subsequence
			// that (a) has length of index + 1, and (b) use array[i] as ending element (array[i]
			// is smaller than or equal to original tbl[index+1] and larger than tbl[index]).
			if (index == result) {
				tbl[++result] = array[i];
			} else {
				tbl[index + 1] = array[i];
			}
		}
		return result;
	}
	
	// find the index of the "largest smaller value" to target in the tbl,
	// tbl is sorted in ascending order.
	private int find(int[] tbl, int left, int right, int target) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (tbl[mid] >= target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right; // right, think about exiting cases and it should be right
	}
}
