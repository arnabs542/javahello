package misc;

/**
 * 
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * DescriptionHintsSubmissionsDiscussSolution Say you have an array for which
 * the ith element is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times) with the following restrictions:
 * 
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again). After you sell your stock, you cannot
 * buy stock on next day. (ie, cooldown 1 day) Example:
 * 
 * Input: [1,2,3,0,2] Output: 3 Explanation: transactions = [buy, sell,
 * cooldown, buy, sell]
 * 
 * 
 * Basic Idea: profit[i] = max(hold[i-1]+price[i], unhold[i-1])
 * 
 * 
 * @author
 *
 */
public class BestTimeToBuyAndSellStockWithCoolDown {
    /**
     * Time: O(n)
     * Space: O(n)
     * 
     * @param prices
     * @return
     */
	public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
        	return 0;
        }
        int n = prices.length;
        int[] hold = new int[n];
        int[] sell = new int[n];
        
        hold[0] = -prices[0];
        // sell[0] = 0; by default when init the sell, can comment out
        if (n >= 2) {
        	hold[1] = Math.max(hold[0], -prices[1]);  //!!!!!
        	sell[1] = Math.max(hold[0] + prices[1], sell[0]);
        }
        for (int i = 2; i < n; i++) {
        	hold[i] = Math.max(sell[i - 2] - prices[i], hold[i - 1]);
        	sell[i] = Math.max(hold[i - 1] + prices[i], sell[i - 1]);
        }
        return sell[n - 1];
    }
	
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int n = prices.length;
		// at day 0, status of 3 operations
		int curSell = 0;
		int preSell = 0;
		int hold = -prices[0];
		// starting from day 1, update the 3 status
		if (n >= 2) {
			int tmp = hold;
			hold = Math.max(-prices[0], -prices[1]);
			curSell = Math.max(tmp + prices[1], curSell);
		}
		for (int i = 2; i < n; i++) {
			int tmp = hold;
			hold = Math.max(preSell - prices[i], hold);
			preSell = curSell;
			curSell = Math.max(curSell, tmp + prices[i]);
		}
		return curSell;
	}
}
