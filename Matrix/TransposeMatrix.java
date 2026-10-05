/**
 * 867. Transpose Matrix
 * Difficulty: Easy | Tags: Array, Matrix, Simulation
 * https://leetcode.com/problems/transpose-matrix/
 *
 * Pattern: Index Swap Transposition
 *
 * Key insight: Create a result array with swapped dimensions (n×m) and write
 * matrix[i][j] into res[j][i] in a single nested loop.
 *
 * Time Complexity: O(m·n) - Every cell visited once.
 *
 * Space Complexity: O(m·n) - New n×m result matrix.
 *
 * Edge Cases Handled: non-square matrices (dimensions swap), single row, single
 * column, 1×1 matrix
 */
class TransposeMatrix {
    public int[][] transpose(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] res = new int[c][r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }
}
