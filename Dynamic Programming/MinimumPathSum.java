/**
 * 64. Minimum Path Sum
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Matrix
 * https://leetcode.com/problems/minimum-path-sum/
 *
 * Pattern: 2D grid DP, path accumulation from top-left to bottom-right
 *
 * Key insight: dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1]); each cell's
 * minimum cost path sum depends only on its top and left neighbors, with the
 * start cell seeded to grid[0][0].
 *
 * Time Complexity: O(m*n) - Fill every cell of the m x n grid
 *
 * Space Complexity: O(m*n) - Full 2D DP table (same size as input)
 *
 * Edge Cases Handled: single cell returns grid[0][0]; first row has only
 * leftward paths; first column has only downward paths
 */
class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + grid[i][j], dp[i - 1][j] + grid[i][j]);
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
