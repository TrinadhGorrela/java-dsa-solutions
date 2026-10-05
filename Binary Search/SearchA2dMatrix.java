/**
 * 74. Search a 2D Matrix
 * Difficulty: Medium | Tags: Array, Binary Search, Matrix
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * Pattern: Binary Search on Virtual Flattened Array
 *
 * Key insight: Treat the m×n matrix as a sorted array of length m*n; map a flat
 * index to (row, col) via mid / n and mid % n, then run standard binary
 * search—no actual flattening needed.
 *
 * Time Complexity: O(log(m·n)) - Standard binary search over m*n elements.
 *
 * Space Complexity: O(1) - Flat index bounds and row/col derived via
 * division/modulo.
 *
 * Edge Cases Handled: target absent (returns false), single row or single
 * column, target at first or last cell
 */
class SearchA2dMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix[0].length * matrix.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / matrix[0].length;
            int col = mid % matrix[0].length;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
