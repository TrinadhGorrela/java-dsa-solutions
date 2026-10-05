/**
 * 63. Unique Paths II
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Matrix
 * https://leetcode.com/problems/unique-paths-ii/
 *
 * Pattern: 2D grid DP with obstacle nullification
 *
 * Key insight: dp[i][j] = 0 for any obstacle cell; otherwise dp[i][j] =
 * dp[i-1][j] + dp[i][j-1] (sum of paths from above and left), which naturally
 * kills path counts downstream of obstacles since zero propagates.
 *
 * Time Complexity: O(m*n) - Single pass filling the DP grid
 *
 * Space Complexity: O(m*n) - Full 2D table matching grid dimensions
 *
 * Edge Cases Handled: obstacle at (0,0) immediately returns 0; first row/column
 * propagate zeros after any obstacle blocks the edge
 */
class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
}
