/**
 * 54. Spiral Matrix
 * Difficulty: Medium | Tags: Array, Matrix, Simulation
 * https://leetcode.com/problems/spiral-matrix/
 *
 * Pattern: Boundary Shrinking Spiral Traversal
 *
 * Key insight: Walk the four edges of the current bounds (top row → right col →
 * bottom row → left col), then shrink each boundary inward. Guard the bottom
 * and left passes with `rowBegin ≤ rowEnd` / `colBegin ≤ colEnd` to avoid
 * double-counting in single-row or single-column remnants.
 *
 * Time Complexity: O(m·n) - Each element visited exactly once.
 *
 * Space Complexity: O(m·n) - Output list holds all elements.
 *
 * Edge Cases Handled: single row, single column, 1×1 matrix, odd vs. even
 * dimensions (innermost layer may be a single row or column)
 */
class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;
            for (int j = rowBegin; j <= rowEnd; j++) {
                res.add(matrix[j][colEnd]);
            }
            colEnd--;
            if (rowBegin <= rowEnd) {
                for (int k = colEnd; k >= colBegin; k--) {
                    res.add(matrix[rowEnd][k]);
                }
            }
            rowEnd--;
            if (colBegin <= colEnd) {
                for (int row = rowEnd; row >= rowBegin; row--) {
                    res.add(matrix[row][colBegin]);
                }
            }
            colBegin++;
        }
        return res;
    }
}
