/**
 * 62. Unique Paths
 * Difficulty: Medium | Tags: Math, Dynamic Programming, Combinatorics
 * https://leetcode.com/problems/unique-paths/
 *
 * Pattern: 2D grid DP, path counting from top-left
 *
 * Key insight: dp[i][j] = dp[i-1][j] + dp[i][j-1] because every path to (i,j)
 * must arrive from directly above or directly left; first row and first column
 * are all 1s since there's only one way along an edge.
 *
 * Time Complexity: O(m*n) - Fill every cell of the m x n grid
 *
 * Space Complexity: O(m*n) - Full 2D DP table
 *
 * Edge Cases Handled: 1x1 grid returns 1; single row or column returns 1 (only
 * one straight-line path)
 */
class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
