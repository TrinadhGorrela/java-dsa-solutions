/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * Difficulty: Medium | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * Pattern: Space-Optimized DP with Cooldown (Hold/Not-Hold State Machine +
 * Lookahead Row)
 *
 * Key insight: Selling today can only be followed by buying two days later, so
 * the sell branch reads the not-hold value from i+2 (farAhead) instead of i+1 -
 * that single lookahead slot is what encodes the one-day cooldown.
 *
 * Time Complexity: O(N) - One backward pass; each day does constant work over
 * the 2 states
 *
 * Space Complexity: O(1) - Only three length-2 arrays (curr, ahead, farAhead)
 * are kept
 *
 * Edge Cases Handled: empty or single-day prices (returns 0), monotone
 * declining prices (returns 0), cooldown strictly enforced between a sell and
 * the next buy (farAhead jump), no handling for negative prices (assumes normal
 * quotes)
 */
class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        int[] ahead = new int[2];
        int[] farAhead = new int[2];

        for (int i = prices.length - 1; i >= 0; i--) {
            int[] curr = new int[2];

            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1) {
                    curr[buy] = Math.max(-prices[i] + ahead[0], ahead[1]);
                } else {
                    curr[buy] = Math.max(prices[i] + farAhead[1], ahead[0]);
                }
            }

            farAhead = ahead;
            ahead = curr;
        }

        return ahead[1];
    }
}
