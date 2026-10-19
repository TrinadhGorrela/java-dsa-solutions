/**
 * 123. Best Time to Buy and Sell Stock III
 * Difficulty: Hard | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * Pattern: Space-Optimized Bottom-Up DP (State Machine: Hold/Not-Hold +
 * Transaction Cap)
 *
 * Key insight: Track max profit from day i onward for each (holding?,
 * transactions left) state, rolling only two rows of a 2x3 table. Selling
 * consumes one cap, so capping the transaction dimension at 2 enforces the
 * at-most-two-transitions rule without any recursion.
 *
 * Time Complexity: O(N) - Single backward pass over prices; the 2x3 state table
 * is constant work per day
 *
 * Space Complexity: O(1) - Only two 2x3 rows are retained (ahead/curr),
 * independent of N
 *
 * Edge Cases Handled: empty or single-day prices array (returns 0), strictly
 * declining or flat prices (max-with-0 keeps profit non-negative), never
 * exceeding the 2-transaction cap (cap dimension hard-stops at index 1..2), no
 * handling for negative prices (assumes normal quotes)
 */
class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        int[][] ahead = new int[2][3];

        for (int i = prices.length - 1; i >= 0; i--) {
            int[][] curr = new int[2][3];

            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    int profit = 0;

                    if (buy == 1) {
                        profit = Math.max(-prices[i] + ahead[0][cap],
                                ahead[1][cap]);
                    } else {
                        profit = Math.max(prices[i] + ahead[1][cap - 1],
                                ahead[0][cap]);
                    }

                    curr[buy][cap] = profit;
                }
            }

            ahead = curr;
        }

        return ahead[1][2];
    }
}
