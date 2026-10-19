/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Greedy
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transactio
 * n-fee/
 *
 * Pattern: Space-Optimized DP (Hold/Not-Hold State Machine with Fee on Buy
 * Side)
 *
 * Key insight: Fold the fee into the buy transition (-price - fee), so every
 * completed round-trip pays it exactly once while the "hold" and "cash" states
 * collapse to just two rolling values.
 *
 * Time Complexity: O(N) - Single backward pass with constant work per day
 *
 * Space Complexity: O(1) - Only two length-2 arrays (ahead/curr) are retained
 *
 * Edge Cases Handled: empty or single-day prices (returns 0), fee >= price
 * spread (best action is do-nothing, profit 0), flat or declining prices, fee
 * attached to the buy leg so it is never charged twice for one round trip
 */
class BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int[] ahead = new int[2];

        for (int i = prices.length - 1; i >= 0; i--) {
            int[] curr = new int[2];
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1) {
                    curr[buy] = Math.max(-prices[i] - fee + ahead[0], ahead[1]);
                } else {
                    curr[buy] = Math.max(prices[i] + ahead[1], ahead[0]);
                }
            }

            ahead = curr;
        }

        return ahead[1];
    }
}
