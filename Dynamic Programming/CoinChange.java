/**
 * 322. Coin Change
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Breadth-First Search,
 * Knapsack Problem, Complete Knapsack
 * https://leetcode.com/problems/coin-change/
 *
 * Pattern: Bottom-up 1D DP, Complete Knapsack variant
 *
 * Key insight: Initialize dp[0]=0 and everything else to infinity; for each
 * amount, greedily try every coin as a last coin used, keeping the minimum
 * across all choices.
 *
 * Time Complexity: O(amount * coins) - For each amount, iterate all coins
 *
 * Space Complexity: O(amount) - Single 1D array of size amount+1
 *
 * Edge Cases Handled: amount=0 returns 0; unreachable amounts stay at MAX_VALUE
 * and return -1; coins larger than current amount are skipped
 */
class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int amt = 1; amt <= amount; amt++) {
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] <= amt) {
                    int remaining = amt - coins[i];

                    if (dp[remaining] != Integer.MAX_VALUE) {
                        dp[amt] = Math.min(dp[amt], dp[remaining] + 1);
                    }
                }
            }
        }
        
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }
}
