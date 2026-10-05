/**
 * 304. Range Sum Query 2D - Immutable
 * Difficulty: Medium | Tags: Array, Design, Matrix, Prefix Sum
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 *
 * Pattern: 2D Prefix Sum (Precomputation)
 *
 * Key insight: Build a prefix-sum matrix where prefix[i][j] stores the
 * cumulative sum of the rectangle from (0,0) to (i-1, j-1). The sum of any
 * sub-rectangle is then recoverable in O(1) using
 * inclusion-exclusion: the four-corner formula cancels over-counted regions,
 * which is exact because each prefix value is a full rectangle sum.
 *
 * Time Complexity: O(M*N) to precompute the prefix matrix; O(1) per sumRegion
 * query.
 *
 * Space Complexity: O(M*N) - The prefix matrix has one extra row and one extra
 * column beyond the original M x N grid.
 *
 * Edge Cases Handled: single-cell queries (row1 == row2, col1 == col2, works
 * via inclusion-exclusion); full-matrix query (0,0) to (M-1, N-1); single-row
 * or single-column sub-rectangles.
 */
class NumMatrix {
    int[][] prefix;

    public NumMatrix(int[][] matrix) {
        this.prefix = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                prefix[i][j] = matrix[i - 1][j - 1] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefix[row2 + 1][col2 + 1] - prefix[row1][col2 + 1] - prefix[row2 + 1][col1] + prefix[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
