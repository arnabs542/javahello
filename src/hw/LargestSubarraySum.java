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

	// quiz: follow up, return largest sum, left, right bounds
	public int[] largestSum_self_quiz1(int[] array) {
		// Assumptions: array is not null and length >= 1.
		int[] result = new int[3];
		int n = array.length;
		int[] dp = new int[n];
		// dp[i]: the biggest sum of subarray that ends on
		// index i.
		dp[0] = array[0];
		result[0] = array[0];
		int left = 0;
		result[1] = left;
		for (int i = 1; i < n; i++) {
			if (dp[i - 1] >= 0) {
				// extend the subarray
				dp[i] = dp[i - 1] + array[i];

			} else {
				// reset dp, left
				dp[i] = array[i];
				left = i;
			}
			// need to test for every i.
			// not only when extending subarray.
			// think about neg val array!!!
			if (dp[i] >= result[0]) {
				result[0] = dp[i];
				result[1] = left;
				result[2] = i;
			}
		}
		return result;
	}

	///////////////////////////////////////////

	// follow up 2: how to return the left , right bounds of the subarray
	public int[] largestSum_self_followup2(int[] arr) {
		// assume that arr not null and arr.length > = 1;
		int solLeft = 0;
		int solRight = 0;
		int curLeft = 0;
		int solMax = arr[0];
		int cur = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (cur < 0) {
				cur = arr[i];
				curLeft = i;
			} else {
				cur += arr[i];
			}
			if (cur > solMax) {
				solLeft = curLeft;
				solRight = i;
				solMax = cur; // !! important, don't forget
			}
		}
		return new int[] { solLeft, solRight };
	}

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

	public static void main(String[] args) {
		// int[] arr = new int[] { 2, -1, 4, -2, 1 }; // expect: {0, 2}
		int[] arr = new int[] { -2, -1, -3 }; // expect: {1, 1}
		LargestSubarraySum sol = new LargestSubarraySum();
		int[] bounds = sol.largestSum_self_followup2(arr);

		for (int i : bounds) {
			System.out.println(i);
		}

	}

}
