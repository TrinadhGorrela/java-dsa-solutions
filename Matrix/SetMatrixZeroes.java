/**
 * 73. Set Matrix Zeroes
 * Difficulty: Medium | Tags: Array, Hash Table, Matrix
 * https://leetcode.com/problems/set-matrix-zeroes/
 *
 * Pattern: Two-Pass Flag Arrays
 *
 * Key insight: First pass records which rows and columns contain a zero in
 * boolean[row] and boolean[col]; second pass zeroes out every cell whose row or
 * column was flagged. O(m+n) extra space instead of O(m·n).
 *
 * Time Complexity: O(m·n) - Two full passes over the matrix.
 *
 * Space Complexity: O(m + n) - Boolean arrays of length m and n.
 *
 * Edge Cases Handled: no zeros (matrix unchanged), single zero cell, zeros only
 * in first row/column, entire matrix is zeros
 */
class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (row[i]) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            if (col[j]) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
