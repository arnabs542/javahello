package misc;

import java.util.Arrays;

/**
 * 
 * 518. Coin Change 2 
 * 
 * DescriptionHintsSubmissionsDiscussSolution 
 * 
 * You are given
 * coins of different denominations and a total amount of money. Write a
 * function to compute the number of combinations that make up that amount. You
 * may assume that you have infinite number of each kind of coin.
 * 
 * Note: You can assume that
 * 
 * 0 <= amount <= 5000 
 * 1 <= coin <= 5000 
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer 
 * 
 * Example 1:
 * 
 * Input: amount = 5, coins = [1, 2, 5] 
 * Output: 4 
 * Explanation: there are four
 * ways to make up the amount: 5=5 5=2+2+1 5=2+1+1+1 5=1+1+1+1+1 
 * 
 * Example 2:
 * 
 * Input: amount = 3, coins = [2] 
 * Output: 0 
 * Explanation: the amount of 3 cannot
 * be made up just with coins of 2. 
 * 
 * Example 3:
 * 
 * Input: amount = 10, coins = [10] 
 * Output: 1
 * 
 * 
 * 
 * @author
 *
 */
public class CoinChangeII {
	/**
	 * Assumption: inputs are valid
	 * 
	 * dp[i][j] : the number of ways to make amount j with [0 : i-1] types of coins
	 * dp[i][j] = dp[i - 1][j] + sum (dp[i-1][j - coins[i - 1] * k] for k = 1 ~ j / coins[i - 1]) ==>
	 * dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]
	 * 
	 * @param amount
	 * @param coins
	 * @return
	 */
    public int change(int amount, int[] coins) {
    		int n = coins.length;
    		int[] dp = new int[amount + 1];
    		Arrays.fill(dp, 0);  // !!!!!
    		dp[0] = 1; // !!!!!!

    		for (int i = 1; i <= coins.length; i++) {
    			for (int j = 1; j <= amount; j++) {
    				if (j - coins[i - 1] >= 0) {
    					int prev = dp[j - coins[i - 1]];
    					dp[j] += prev;
    					
    				}
    			}
    		}
    		return dp[amount];
    }
    
    public static void main(String[] args) {
    		int[] coins = {1, 2, 5};
    		int amount = 5;  // expect 4
    		CoinChangeII sol = new CoinChangeII();
    		System.out.println(sol.change(amount, coins));
    }
}
