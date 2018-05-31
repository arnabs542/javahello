package misc;

import java.util.Arrays;

/**
 * 322. Coin Change
 * 
 * DescriptionHintsSubmissionsDiscussSolution
 * 
 * You are given coins of different denominations and a total amount of money
 * amount. Write a function to compute the fewest number of coins that you need
 * to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 * 
 * Example 1:
 * 
 * Input: coins = [1, 2, 5], amount = 11 Output: 3 Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * 
 * Input: coins = [2], amount = 3 Output: -1 Note: You may assume that you have
 * an infinite number of each kind of coin.
 * 
 * Difficulty:Medium Total Accepted:98.7K Total Submissions:370.4K
 * Contributor:jianchao.li.fighter Related Topics
 * 
 * 
 * Sol 1 -- naive dp 
 * 
 * n: number of types of coins
 * m: amount
 * 
 * dp[i][j]: the min number of first i types of coins to make amount j
 * 
 * dp[i][j] = min(dp[i-1][j - coins[i-1] * k] + k), where j-coins[i-1] * k >= 0
 * 
 * init: dp[0][j] = Integer.MAX_VALUE, dp[i][0] = 0 
 * 
 * 
 * @author
 *
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
    	/**
    	 * Sol 3, optimize time and space
    	 * Time: O(n * m), 
    	 * Space: O(m)
    	 */
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i < n + 1; i++) {
        	for (int j = 1; j <= amount; j++) {
        		dp[j] = dp[j];
        		if (j - coins[i - 1] >= 0 ) {
        			int prev = dp[j - coins[i - 1]];
        			if (prev < Integer.MAX_VALUE) {
        				dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
        			}
        		}
        	}
        }
        return (dp[amount] < Integer.MAX_VALUE) ? dp[amount] : -1;
    }
	
	
    public int coinChange_sol2(int[] coins, int amount) {
    	/**
    	 * Sol 2, optimize time
    	 * Time: O(n * m), 
    	 * Space: O(mn)
    	 */
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;  // only [0][0] is necessary, no need to loop, by default 0

        for (int i = 1; i < n + 1; i++) {
        	for (int j = 1; j <= amount; j++) {
        		dp[i][j] = dp[i - 1][j];
        		if (j - coins[i - 1] >= 0 ) {
        			// dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - coins[i - 1]] + 1); 
        			// !!! check for max_value first otherwise + 1 will be min negative value -2147483648
        			// int prev = dp[i - 1][j - coins[i - 1]]; !!!! dp[i][j - coins[i - 1]]
        			int prev = dp[i][j - coins[i - 1]];
        			if (prev < Integer.MAX_VALUE) {
        				dp[i][j] = Math.min(dp[i][j], prev + 1);
        			}
        		}
        	}
        }
        return (dp[n][amount] < Integer.MAX_VALUE) ? dp[n][amount] : -1;
    }
	
	
    public int coinChange_sol1(int[] coins, int amount) {
    	/**
    	 * Time: O(n * m ^ 2), 
    	 * Space: O(mn)
    	 */
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;  // only [0][0] is necessary, no need to loop, by default 0
//        for (int i = 0; i < n + 1; i++) {
//        	dp[i][0] = 0;
//        }
        for (int i = 1; i < n + 1; i++) {
        	for (int j = 1; j <= amount; j++) {
        		// dp[i][j] = Integer.MAX_VALUE;  
        		// !!!not max, but dp[i-1][j], because that's the best result 
        		// when without current new type of coin
        		dp[i][j] = dp[i - 1][j];
        		int kCoins = j / coins[i - 1];
        		for (int k = 1; k <= kCoins; k++) {
        			int prev = dp[i - 1][j - k * coins[i - 1]];
        			if (prev < Integer.MAX_VALUE) {
            			dp[i][j] = Math.min(dp[i][j], prev + k);
        			}
        		}
        	}
        }
        return (dp[n][amount] < Integer.MAX_VALUE) ? dp[n][amount] : -1;
    }
    
    public static void main(String[] args) {
    	int[] coins = {1, 2, 5};
    	int amount = 11;
    	CoinChange sol = new CoinChange();
    	System.out.println(sol.coinChange(coins, amount));
    	
    }
}
