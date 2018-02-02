package hw;

/**
 * DP Longest Ascending SubArray Given an unsorted array, find the length of the
 * longest subarray in which the numbers are in ascending order.
 * 
 * Assumptions
 * 
 * The given array is not null Examples
 * 
 * {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length
 * is 4.
 * 
 * {1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.
 * 
 * 
 * @author
 *
 */
public class LongestAscendingSubArray {
	public int longest(int[] array) {
		// assumptions: the gien array is not null.
		if (array.length == 0) {
			return 0;
		}
		// dp[i] = 1 (array[i] <= array[i-1]
		// = dp[i-1] + 1 (array[i] > array[i-1])
		// So we can make the space consumption more efficient by
		// only recording the latest dp[i]
		// ---> no need to use the extra space, cur to record
		// on current pos the length of the ascending array ending on it
		int cur = 1;
		int result = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[i - 1]) {
				cur++;
				result = Math.max(result, cur);
			} else {
				cur = 1;
			}
		}
		return result;
	}

	public int longest_self_afterClass_AC2(int[] array) {
		// Basic idea: optimize space upon the first way.-->
		// DP of 1d, use dp[i] to record the longest
		// ascending subarray ends on position i.
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int maxLen = 1;
		int cur = 1;
		for (int i = 1; i < n; i++) {
			if (array[i] > array[i - 1]) {
				cur += 1;
				maxLen = Math.max(maxLen, cur);
			} else {
				cur = 1;
			}
		}
		return maxLen;
	}

	public int longest_self_afterClass_AC(int[] array) {
		// Basic idea: DP of 1d, use dp[i] to record the longest
		// ascending subarray ends on position i.
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int maxLen = 1;
		int[] dp = new int[n];
		dp[0] = 1;
		for (int i = 1; i < n; i++) {
			if (array[i] > array[i - 1]) {
				dp[i] = dp[i - 1] + 1;
				maxLen = Math.max(maxLen, dp[i]);
			} else {
				dp[i] = 1;
			}
		}
		return maxLen;
	}

}
