/**
 * 1572. Matrix Diagonal Sum
 * Difficulty: Easy | Tags: Array, Matrix
 * https://leetcode.com/problems/matrix-diagonal-sum/
 *
 * Pattern: Dual-Diagonal Accumulation
 *
 * Key insight: A cell belongs to the primary diagonal (i == j) or the
 * anti-diagonal (i + j == n−1); on odd n, the center cell satisfies both
 * conditions and is counted only once thanks to the `||` (not double-add).
 *
 * Time Complexity: O(n²) - Double loop over the full matrix; could be O(n) with
 * direct diagonal indexing.
 *
 * Space Complexity: O(1) - Running sum only.
 *
 * Edge Cases Handled: 1×1 matrix (center counted once), odd n (shared center),
 * even n (no overlap)
 */
class MatrixDiagonalSum {
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (i == j || i + j == mat.length - 1) {
                    sum += mat[i][j];
                }
            }
        }
        return sum;
    }
}
