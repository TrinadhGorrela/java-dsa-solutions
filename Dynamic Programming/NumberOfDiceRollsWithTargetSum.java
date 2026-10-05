/**
 * 1155. Number of Dice Rolls With Target Sum
 * Difficulty: Medium | Tags: Dynamic Programming
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 *
 * Pattern: Dynamic Programming (2D Count Accumulation)
 *
 * Key insight: dp[i][j] = number of ways to reach sum j using exactly i dice.
 * For each die, try every face value (1..k) and accumulate counts from the
 * previous die's state. This bottom-up build ensures each combination is
 * counted exactly once, with results taken modulo 10^9+7.
 *
 * Time Complexity: O(n × target × k) — three nested loops over dice, target
 * sums, and face values
 *
 * Space Complexity: O(n × target) — 2D DP array of dimensions (n+1) ×
 * (target+1)
 *
 * Edge Cases Handled: target = 0 with n > 0 (impossible, returns 0), target < n
 * (sum too small), target > n × k (sum too large, no combination works), single
 * die (n = 1)
 */
class NumberOfDiceRollsWithTargetSum {
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        int MOD = 1_000_000_007;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                for (int face = 1; face <= k; face++) {
                    if (face <= j) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - face]) % MOD;
                    }
                }
            }
        }

        return dp[n][target];
    }
}
