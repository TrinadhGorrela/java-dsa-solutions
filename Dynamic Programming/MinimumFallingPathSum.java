/**
 * 931. Minimum Falling Path Sum
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Matrix
 * https://leetcode.com/problems/minimum-falling-path-sum/
 *
 * Pattern: Dynamic Programming (2D Grid / Matrix)
 *
 * Key insight: The minimum falling path into any cell depends only on the three
 * cells directly above it (up-left, up, up-right), so processing rows top to
 * bottom lets each cell reuse already-computed minima in O(1). The extra spacer
 * row (index 1 holds row 0) keeps the previous row uniformly addressable.
 *
 * Time Complexity: O(M * N) - Every cell is computed once with constant work
 * for its three parent cells.
 *
 * Space Complexity: O(M * N) - A DP table with one extra row beyond the input
 * matrix.
 *
 * Edge Cases Handled: single-row matrix (first row copied directly and simply
 * minimized), single-column matrix (out-of-bounds diagonals are skipped by the
 * bounds check), 1x1 matrix, non-square matrices, and negative values
 * (Integer.MAX_VALUE is only the running seed, never added to a cell); only
 * safe for non-empty input since matrix[0] is accessed.
 */
class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length + 1][matrix[0].length];

        for (int i = 0; i < matrix[0].length; i++) {
            dp[1][i] = matrix[0][i];
        }

        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = matrix[i - 1][j] + getMin(dp, i, j);
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < dp[i].length; i++) {
            min = Math.min(min, dp[matrix.length][i]);
        }

        return min;
    }

    private int getMin(int[][] dp, int row, int col) {
        int min = Integer.MAX_VALUE;
        int[][] dirs = {
                { -1, -1 }, { -1, 0 }, { -1, 1 }
        };

        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < dp.length && newCol >= 0 && newCol < dp[0].length) {
                min = Math.min(min, dp[newRow][newCol]);
            }
        }

        return min;
    }
}
