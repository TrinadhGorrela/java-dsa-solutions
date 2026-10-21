/**
 * 701431. 0 - 1 Knapsack Problem
 * Difficulty: Medium | Tags: Dynamic Programming
 * https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
 *
 * Pattern: Dynamic Programming (2D 0-1 Knapsack)
 *
 * Key insight: dp[i][j] holds the best value from the first i items within
 * capacity j, and item i is either skipped (dp[i-1][j]) or taken (val[i-1] +
 * dp[i-1][j - wt[i-1]]); because the take-branch reads the PREVIOUS row, an
 * item can never be selected more than once.
 *
 * Time Complexity: O(N * W) - The (N+1) x (W+1) table is filled once, with N =
 * val.length items and W = capacity
 *
 * Space Complexity: O(N * W) - The full 2D table of item-count by capacity is
 * materialized
 *
 * Edge Cases Handled: capacity 0 (answer 0), items heavier than the capacity
 * (skipped via the wt > j branch), empty item array (the table collapses to a
 * single row and the answer is 0), each item usable at most once (guaranteed by
 * the previous-row transitions), an item whose weight exactly equals the
 * remaining capacity (taken in full), negative item values (never chosen, since
 * skipping is always an alternative).
 */
class ZeroOneKnapsackProblem {
    public int knapsack(int w, int val[], int wt[]) {
        int[][] dp = new int[val.length + 1][w + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i - 1][j - wt[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[val.length][w];
    }
}
