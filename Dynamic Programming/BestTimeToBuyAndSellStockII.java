/**
 * 122. Best Time to Buy and Sell Stock II
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Greedy
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * Pattern: Dynamic Programming (Space-Optimized State Machine)
 *
 * Key insight: At each day you are either holding a stock (buy=1) or not
 * (buy=0), and the optimal profit depends only on the next day's optimal states
 * — so we can iterate backwards keeping just two values (ahead/curr) per state.
 * Unlimited transactions simply mean every upward price movement is capturable,
 * which the max() recurrence naturally discovers.
 *
 * Time Complexity: O(N) - Single backward pass over the prices array with
 * constant work per day
 *
 * Space Complexity: O(1) - Only two length-2 arrays (ahead, curr) are
 * maintained regardless of input size
 *
 * Edge Cases Handled: empty/single-element prices array (loop never runs or
 * runs once, returns 0 correctly via initialized states); strictly decreasing
 * prices (never buys, profit stays 0); already monotonic increasing prices
 * (captures the full range); all-same prices (no profit); only safe for
 * non-negative prices as assumed by the problem
 */
class BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        int[] ahead = new int[2];
        int[] curr = new int[2];

        for (int i = prices.length - 1; i >= 0; i--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;

                if (buy == 1) {
                    profit = Math.max(-prices[i] + ahead[0], ahead[1]);
                } else {
                    profit = Math.max(prices[i] + ahead[1], ahead[0]);
                }

                curr[buy] = profit;
            }

            ahead = curr;
        }

        return ahead[1];
    }
}
