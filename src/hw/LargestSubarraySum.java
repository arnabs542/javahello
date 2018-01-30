package hw;

/**
 * DP Largest SubArray Sum
 * 
 * Given an unsorted integer array, find the subarray that has the greatest sum.
 * Return the sum.
 * 
 * Assumptions
 * 
 * The given array is not null and has length of at least 1. Examples
 * 
 * {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5
 * 
 * {-2, -1, -3}, the largest subarray sum is -1
 * 
 * @author
 *
 */
public class LargestSubarraySum {

	public int largestSum_self_AC(int[] arr) {
		int result = arr[0];
		int dp = arr[0];
		for (int i = 1; i < arr.length; i++) {
			dp = Math.max(dp, 0) + arr[i];
			result = Math.max(result, dp);
		}
		return result;
	}

	public int largestSum(int[] arr) {
		// Assumptions: array != null && length >= 1.
		// The subarray must contain at least one element.
		int result = arr[0];
		int cur = arr[0];
		// dp[i] means the largest sum of subarray ending at index i.
		// dp[i] = arr[i] if dp[i-1] <= 0
		// = dp[i-1] + array[i] if dp[i-1] > 0
		// so that we can reduce the space consumption by
		// recording the latest largest sum.
		for (int i = 1; i < arr.length; i++) {
			cur = Math.max(cur + arr[i], arr[i]);
			result = Math.max(result, cur);
		}
		return result;
	}
}
