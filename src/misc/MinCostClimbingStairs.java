package misc;

/**
 * 746. Min Cost Climbing Stairs 
 * 
 * DescriptionHintsSubmissionsDiscussSolution
 * 
 * DiscussPick One On a staircase, the i-th step has some non-negative cost
 * cost[i] assigned (0 indexed).
 * 
 * Once you pay the cost, you can either climb one or two steps. You need to
 * find minimum cost to reach the top of the floor, and you can either start
 * from the step with index 0, or the step with index 1.
 * 
 * Example 1: 
 * Input: cost = [10, 15, 20] 
 * Output: 15 
 * Explanation: Cheapest is
 * start on cost[1], pay that cost and go to the top. 
 * 
 * 
 * Example 2: 
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1] 
 * Output: 6 Explanation: Cheapest is start
 * on cost[0], and only step on 1s, skipping cost[3]. 
 * Note: cost will have a
 * length in the range [2, 1000]. Every cost[i] will be an integer in the range
 * [0, 999].
 * 
 * 
 * Difficulty:Easy Total Accepted:11.4K Total Submissions:26.2K
 * Contributor:TopCoder111 Subscribe to see which companies asked this question.
 * 
 * Related Topics ArrayDynamic Programming Similar Questions Climbing Stairs
 * 
 * @author
 *
 */
public class MinCostClimbingStairs {
	// time: O(n)
	// space: O(n) --> O(1)
	// Assumption: cost length >= 2
	public int minCostClimbingStairs(int[] cost) {
		// try to optimize the space, not using the whole array
		
		if (cost == null || cost.length <= 1) {
			return 0;
		}
		int n = cost.length;
		int pre1 = 0; // one step previous
		int pre2 = 0; // two steps previous
		int cur = 0;
		for (int i = 2; i < n + 1; i++) {
			cur = Math.min(pre1 + cost[i - 1], pre2 + cost[i - 2]);
			pre2 = pre1;
			pre1 = cur;
			 
		}
		return cur;
	}
	
	public int minCostClimbingStairs1(int[] cost) {
		// dp[i]: the min cost of reaching pos i

		if (cost == null || cost.length == 0) {
			return 0;
		}
		int n = cost.length;
		int[] dp = new int[n + 1];
		// according to the test case,
		// standing at pos i does not need to pay the cost,
		// if need to go further from pos i, then need to pay the cost!!!
		// one more note: top of the case means out of the array, finish the array
		// dp[0] = cost[0];
		// dp[1] = cost[1];
		dp[0] = 0;
		dp[1] = 0;
		for (int i = 2; i < n + 1; i++) {

			// dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
			dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
		}
		return dp[n];
	} 
}
