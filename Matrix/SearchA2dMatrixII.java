/**
 * 240. Search a 2D Matrix II
 * Difficulty: Medium | Tags: Array, Binary Search, Divide and Conquer, Matrix
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 *
 * Pattern: Staircase Search (Top-Right Start)
 *
 * Key insight: Start at the top-right corner: if the cell equals target, found;
 * if larger, eliminate the entire column (move left); if smaller, eliminate the
 * entire row (move down). Each step removes a full row or column.
 *
 * Time Complexity: O(m + n) - At most m downward moves + n leftward moves.
 *
 * Space Complexity: O(1) - Two indices: row, col.
 *
 * Edge Cases Handled: target absent (returns false), single row or column,
 * target at the starting top-right cell, target at bottom-left
 */
class SearchA2dMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
