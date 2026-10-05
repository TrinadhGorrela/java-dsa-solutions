/**
 * 474. Ones and Zeroes
 * Difficulty: Medium | Tags: Array, String, Dynamic Programming, Knapsack
 * Problem, 0-1 Knapsack
 * https://leetcode.com/problems/ones-and-zeroes/
 *
 * Pattern: 2D 0-1 Knapsack (Space-Optimized)
 *
 * Key insight: Treat zeros as weight-1 and ones as weight-2 in a
 * two-dimensional knapsack. Iterating strings in the outer loop and capacities
 * in reverse prevents reuse of the same string, achieving the 0-1 property with
 * a single 2D table.
 *
 * Time Complexity: O(L * m * n) - For each of L strings, iterate over all
 * (m+1)*(n+1) DP states.
 *
 * Space Complexity: O(m * n) - Single 2D dp table of size (m+1) x (n+1).
 *
 * Edge Cases Handled: empty string array (dp stays all-zero, returns 0), m or n
 * is 0 (no capacity, returns 0), strings longer than capacity on both axes
 * (skipped by loop bounds), all-zeros or all-ones strings (consumes only one
 * dimension).
 */
class OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int zeros = 0;
            int ones = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
