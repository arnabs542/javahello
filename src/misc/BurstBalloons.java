package misc;

/**
 * 
 * 312. Burst Balloons 
 * DescriptionHintsSubmissionsDiscussSolution 
 * 
 * Given n
 * balloons, indexed from 0 to n-1. Each balloon is painted with a number on it
 * represented by array nums. You are asked to burst all the balloons. If the
 * you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and
 * right then becomes adjacent.
 * 
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * 
 * Note:
 * 
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can
 * not burst them. 0 ¡Ü n ¡Ü 500, 0 ¡Ü nums[i] ¡Ü 100 Example:
 * 
 * Input: [3,1,5,8] Output: 167 Explanation: nums = [3,1,5,8] --> [3,5,8] -->
 * [3,8] --> [8] --> [] coins = 3*1*5 + 3*5*8 + 1*3*8 + 1*8*1 = 167 Seen this
 * question in a real interview before? Difficulty:Hard Total Accepted:41.3K
 * Total Submissions:94.1K Contributor:dietpepsi Companies
 * 
 * Related Topics
 * 
 * 
 * 
 * 
 * @author
 *
 */
public class BurstBalloons {
    /**
    bp[i][j]: max coins you can get from bursting balloons [i] ~ [j]
    bp[i][j] = max(bp[i][j], bp[i-1] * nums[k] * bp[j+1] + bp[i][k-1] + bp[k+1][j])
    bp[i][i] = balloons[i];
    
    Time: O(n ^ 3),
    Space: O(n ^ 2)
    
    */
    public int maxCoins(int[] nums) {
        // input sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // padding two ends of input with 1 for easier processing
        int n = nums.length + 2;
        int[] balloons = new int[n];
        balloons[0] = 1;
        balloons[n - 1] = 1;
        for (int i = 1; i < n - 1; i++) {
            balloons[i] = nums[i - 1];
        }
        // init dp matrix
        int[][] dp = new int[n][n];
        
        helper(balloons, 1, n - 2, dp); // !!! n-2, not n - 1
        return dp[1][n - 2];
    }
    
    private int helper(int[] balloons, int left, int right, int[][] dp) {
        if (left > right) {
            return 0;
        }
        // !!! if cached result, direct return
        if (dp[left][right] > 0) {
            return dp[left][right];
        }
        for (int k = left; k <= right; k++) {
            int newValue = balloons[left - 1] * balloons[k] * balloons[right + 1] 
                + helper(balloons, left, k - 1, dp) 
                + helper(balloons, k + 1, right, dp);
            dp[left][right] = Math.max(dp[left][right], newValue);
        }
        
        return dp[left][right];
    }
}
