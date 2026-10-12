/**
 * 1277. Count Square Submatrices with All Ones
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Matrix
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 *
 * Pattern: Dynamic Programming (2D Grid, Maximal Square)
 *
 * Key insight: dp[i][j] is the side length of the largest all-ones square whose
 * bottom-right corner is (i,j), computed as 1 + min(top-left, top, left) when
 * matrix[i][j] == 1, because a square of side s requires three overlapping
 * squares of side s-1. Summing every dp value counts each square exactly once
 * at its bottom-right corner.
 *
 * Time Complexity: O(M * N) - Each cell is computed once and then summed once.
 *
 * Space Complexity: O(M * N) - A DP table matching the input matrix dimensions.
 *
 * Edge Cases Handled: single-row and single-column matrices (the first
 * row/column is copied directly and inner loops are skipped), all-ones matrix,
 * all-zeros matrix, and a 1x1 matrix; only safe for non-empty rectangular input
 * since matrix[0] is accessed.
 */
class CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = matrix[0][i];
        }

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = matrix[i][0];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        int sum = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                sum += dp[i][j];
            }
        }

        return sum;
    }
}
