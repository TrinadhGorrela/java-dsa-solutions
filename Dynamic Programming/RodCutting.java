/**
 * 703296. Rod Cutting
 * Difficulty: Medium | Tags: Arrays, Dynamic Programming
 * https://www.geeksforgeeks.org/problems/rod-cutting0840/1
 *
 * Pattern: Dynamic Programming (Unbounded Knapsack)
 *
 * Key insight: A cut of any length may be reused without limit, so the best
 * value for length j is dp[j] = max(dp[j], price[i-1] + dp[j-i]); iterating cut
 * lengths in the outer loop and capacities in increasing order lets the same
 * cut be combined with itself, which is exactly what unbounded knapsack
 * requires.
 *
 * Time Complexity: O(N^2) - Outer loop over the N cut lengths, inner loop over
 * up to N rod capacities.
 *
 * Space Complexity: O(N) - A single dp array indexed by rod length (n+1
 * entries).
 *
 * Edge Cases Handled: empty price array (returns dp[0] = 0), a single available
 * length, all-zero or all-equal prices, negative prices (dp is initialised to
 * 0, so a losing cut is never selected), full rod used uncut; null input is not
 * guarded and throws a NullPointerException.
 */
class RodCutting {
    public int cutRod(int[] price) {
        int[] dp = new int[price.length + 1];

        for (int i = 1; i <= price.length; i++) {
            for (int j = i; j < dp.length; j++) {
                dp[j] = Math.max(dp[j], price[i - 1] + dp[j - i]);
            }
        }

        return dp[price.length];
    }
}
