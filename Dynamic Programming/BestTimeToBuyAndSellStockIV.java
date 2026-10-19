/**
 * 188. Best Time to Buy and Sell Stock IV
 * Difficulty: Hard | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * Pattern: Space-Optimized Bottom-Up DP (State Machine: Hold/Not-Hold x k
 * Transaction Caps)
 *
 * Key insight: Generalizes Stock II by making the transaction dimension a
 * k-sized array - each sell decrements the cap index, so the DP naturally
 * enforces "at most k transactions" while only the next day's row is ever
 * needed.
 *
 * Time Complexity: O(N * k) - Backward pass over N days with a 2 x (k+1) state
 * table updated per day
 *
 * Space Complexity: O(k) - Only the ahead row of size 2 x (k+1) is kept between
 * iterations
 *
 * Edge Cases Handled: k = 0 (returns 0, cap loop never executes), empty or
 * single-day prices (loop skipped, returns 0), flat/declining prices (profit
 * stays 0), large k larger than N/2 (still correct, just not special-cased into
 * the O(N) greedy path)
 */
class BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        int[][] ahead = new int[2][k + 1];

        for (int i = prices.length - 1; i >= 0; i--) {
            int[][] curr = new int[2][k + 1];

            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (buy == 1) {
                        curr[buy][cap] = Math.max(-prices[i] + ahead[0][cap], ahead[1][cap]);
                    } else {
                        curr[buy][cap] = Math.max(prices[i] + ahead[1][cap - 1], ahead[0][cap]);
                    }
                }
            }

            ahead = curr;
        }

        return ahead[1][k];
    }
}
