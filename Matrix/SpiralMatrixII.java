/**
 * 59. Spiral Matrix II
 * Difficulty: Medium | Tags: Array, Matrix, Simulation
 * https://leetcode.com/problems/spiral-matrix-ii/
 *
 * Pattern: Layer-by-Layer Spiral Fill
 *
 * Key insight: Maintain four boundaries (top, bottom, left, right); traverse
 * the four edges of the current outer layer filling values with an incrementing
 * counter, then shrink all four boundaries inward and repeat.
 *
 * Time Complexity: O(n²) - Each of the n² cells written exactly once.
 *
 * Space Complexity: O(n²) - The output matrix itself.
 *
 * Edge Cases Handled: n = 1 (single cell), odd n (innermost layer is a single
 * row/col), boundary collapse after last layer
 */
class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        int rowBegin = 0;
        int rowEnd = n - 1;
        int colBegin = 0;
        int colEnd = n - 1;
        int counter = 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {

            for (int i = colBegin; i <= colEnd; i++) {
                res[rowBegin][i] = counter++;
            }
            rowBegin++;

            for (int j = rowBegin; j <= rowEnd; j++) {
                res[j][colEnd] = counter++;
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                for (int k = colEnd; k >= colBegin; k--) {
                    res[rowEnd][k] = counter++;
                }
                rowEnd--;
            }

            if (colBegin <= colEnd) {
                for (int s = rowEnd; s >= rowBegin; s--) {
                    res[s][colBegin] = counter++;
                }
                colBegin++;
            }
        }
        return res;
    }
}
