/**
 * 518. Coin Change II
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Knapsack Problem,
 * Complete Knapsack
 * https://leetcode.com/problems/coin-change-ii/
 *
 * Pattern: 1D Complete Knapsack (combination counting)
 *
 * Key insight: Outer-loop on coins and inner-loop on amounts ensures each
 * combination is counted once (not permutations); dp[amt] accumulates ways to
 * form amt by adding the current coin to all previously reachable amounts.
 *
 * Time Complexity: O(coins * amount) - Each coin updates every reachable amount
 *
 * Space Complexity: O(amount) - Single 1D array of size amount+1
 *
 * Edge Cases Handled: amount=0 returns 1 (one way: choose nothing); coins
 * larger than current amount are naturally skipped by the loop bound
 */
class CoinChangeII {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int amt = coins[i]; amt < dp.length; amt++) {
                int remaining = amt - coins[i];
                dp[amt] += dp[remaining];
            }
        }

        return dp[amount];
    }
}
