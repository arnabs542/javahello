package misc;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * 
 * Given an array of integers and an integer k, you need to find the total
 * number of continuous subarrays whose sum equals to k.
 * 
 * Example 1: Input:nums = [1,1,1], k = 2 Output: 2 Note: The length of the
 * array is in range [1, 20,000]. The range of numbers in the array is [-1000,
 * 1000] and the range of the integer k is [-1e7, 1e7].
 * 
 * Difficulty:Medium Total Accepted:39.8K Total Submissions:99.7K
 * Contributor:½ñ¤¬×î¸ß Companies
 * 
 * Related Topics
 * 
 * Similar Questions Two SumContinuous Subarray SumSubarray Product Less Than
 * KFind Pivot Index
 * 
 * @author
 *
 */
public class SubarraySumEqualsK {
	/**
	 * Basic idea: sum[i:j] = prefixSum[j] - prefixSum[i-1] == k
	 * 
	 * use prefixSum and HashMap to recored previous prefixSum, 
	 * for a new prefixSum[j], find if there is and how many (prefixSum[j] - k) in previous 
	 * scanned array indexes. Then accumulate and return the result.
	 * 
	 * Time: O(n)
	 * Space: O(n)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int subarraySum(int[] nums, int k) {
		// input sanity check
		if (nums == null || nums.length == 0) {
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1); // the the whole prefix sum == k scenario
		int prefixSum = 0;
		int result = 0;
		for (int ele : nums) {
			prefixSum += ele;
			// int count = map.getOrDefault(k - prefixSum, 0); //!!!WRONG, should be prefixSum - k
			int count = map.getOrDefault(prefixSum - k, 0);
			result += count;
			map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
		}
		return result;
	}
}
