/**
 * 766. Toeplitz Matrix
 * Difficulty: Easy | Tags: Array, Matrix
 * https://leetcode.com/problems/toeplitz-matrix/
 *
 * Pattern: Neighbor Comparison (Diagonal Invariant)
 *
 * Key insight: Every diagonal (constant i−j) has identical values iff each cell
 * equals its lower-right neighbor matrix[i+1][j+1]; a single scan of interior
 * cells suffices.
 *
 * Time Complexity: O(m·n) - Interior cells only; loop bounds skip last row/col.
 *
 * Space Complexity: O(1) - No extra storage.
 *
 * Edge Cases Handled: 1×1 matrix (trivially true), single row or column (always
 * true), first mismatch returns false early
 */
class ToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1])
                    return false;
            }
        }
        return true;
    }
}
