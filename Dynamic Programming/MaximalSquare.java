/**
 * 221. Maximal Square
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Matrix
 * https://leetcode.com/problems/maximal-square/
 *
 * Pattern: Dynamic Programming (2D Grid)
 *
 * Key insight: dp[i][j] holds the side length of the largest all-'1' square
 * ending at (i, j); it is 1 + min(up, left, diagonal) because a square can only
 * grow if all three smaller squares exist. The answer is the largest side
 * length squared.
 *
 * Time Complexity: O(M * N) - Every cell is visited exactly once.
 *
 * Space Complexity: O(M * N) - A full DP table the size of the matrix is
 * allocated.
 *
 * Edge Cases Handled: single row or single column (first row/column seeded
 * directly), all-zero matrix (returns 0), squares touching the borders, matrix
 * containing only '1's; empty matrix is NOT handled (matrix[0] would throw)
 */
class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = matrix[0][i] == '0' ? 0 : 1;
            max = Math.max(max, dp[0][i]);
        }

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = matrix[i][0] == '0' ? 0 : 1;
            max = Math.max(max, dp[i][0]);
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }

                max = Math.max(max, dp[i][j]);
            }
        }

        return max * max;
    }
}
