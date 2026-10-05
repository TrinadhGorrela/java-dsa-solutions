/**
 * 48. Rotate Image
 * Difficulty: Medium | Tags: Array, Math, Matrix
 * https://leetcode.com/problems/rotate-image/
 *
 * Pattern: Transpose + Row Reverse
 *
 * Key insight: A 90° clockwise rotation decomposes into two in-place
 * operations: (1) transpose the matrix by swapping matrix[i][j] with
 * matrix[j][i] for i < j, then (2) reverse each row with two pointers.
 *
 * Time Complexity: O(n²) - Transpose touches n²/2 cells, row reversal touches
 * n²/2 cells.
 *
 * Space Complexity: O(1) - In-place swaps only.
 *
 * Edge Cases Handled: 1×1 matrix (no swaps needed), 2×2 matrix, any square size
 */
class RotateImage {
    public void rotate(int[][] matrix) {
        int r = matrix.length;

        for (int i = 0; i < r; i++) {
            for (int j = i + 1; j < r; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < r; i++) {
            int start = 0;
            int end = matrix[i].length - 1;
            while (start < end) {
                int temp = matrix[i][start];
                matrix[i][start] = matrix[i][end];
                matrix[i][end] = temp;
                start++;
                end--;
            }

        }
    }
}
