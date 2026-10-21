/**
 * 704512. Knapsack with Duplicate Items
 * Difficulty: Medium | Tags: Dynamic Programming
 * https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
 *
 * Pattern: Dynamic Programming (Unbounded / Complete Knapsack)
 *
 * Key insight: dp[i][j] is the best value obtainable from the first i item
 * types within capacity j, and when item i fits we either skip it (dp[i-1][j])
 * or take it and stay on the SAME row i (dp[i][j - wt[i-1]]); reading the
 * current row instead of the previous one is precisely what lets a single item
 * type be used an unlimited number of times.
 *
 * Time Complexity: O(N * C) - The (N+1) x (C+1) table is filled once, with N =
 * val.length item types and C = capacity
 *
 * Space Complexity: O(N * C) - The full 2D table of item-count by capacity is
 * materialized
 *
 * Edge Cases Handled: capacity 0 (nothing fits, answer 0), items heavier than
 * the capacity (skipped via the wt > j branch, carrying dp[i-1][j] forward),
 * empty item array (the table collapses to a single row and the answer is 0),
 * unlimited reuse of one item type (the same-row transition keeps adding the
 * item until the capacity is exhausted), negative item values (never chosen,
 * since skipping is always an alternative). Note: an item of weight 0 is
 * credited only once rather than unlimited times, and val and wt are assumed to
 * be parallel arrays of equal length.
 */
class KnapsackWithDuplicateItems {
    public int knapSack(int val[], int wt[], int capacity) {
        int[][] dp = new int[val.length + 1][capacity + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i][j - wt[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[val.length][capacity];
    }
}
