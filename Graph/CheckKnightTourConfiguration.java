/**
 * 2596. Check Knight Tour Configuration
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Matrix, Simulation
 * https://leetcode.com/problems/check-knight-tour-configuration/
 *
 * Pattern: DFS Path Validation (All 8 Knight Moves)
 *
 * Key insight: Starting from cell 0 at (0,0), recursively explore all 8
 * possible knight moves; at each step verify the target cell is in-bounds and
 * holds the expected consecutive value (expval + 1). The tour is valid only if
 * every cell from 0 to n²−1 is visited in order.
 *
 * Time Complexity: O(n²) - Each cell visited at most once; 8 branches pruned by
 * value check.
 *
 * Space Complexity: O(n²) - Recursion depth equals total cells in the path.
 *
 * Edge Cases Handled: grid[0][0] ≠ 0 (immediate false), 1×1 grid (trivially
 * true), dead-end branches pruned early by value mismatch
 */
class CheckKnightTourConfiguration {
    public boolean checkValidGrid(int[][] grid) {
         if (grid[0][0] != 0) 
         return false;
         
       return isValid( grid, 0, 0, grid.length, 0);
    }

    boolean isValid(int[][] grid, int r, int c, int n, int expectedValue) {
        if (r < 0 || c < 0 || r >=n || c >=n || grid[r][c] != expectedValue)
            return false;

        if (expectedValue == n * n - 1)
            return true;

        boolean ans1 = isValid(grid, r - 2, c + 1, n, expectedValue + 1);
        boolean ans2 = isValid(grid, r - 1, c + 2, n, expectedValue + 1);
        boolean ans3 = isValid(grid, r + 1, c + 2, n, expectedValue + 1);
        boolean ans4 = isValid(grid, r + 2, c + 1, n, expectedValue + 1);
        boolean ans5 = isValid(grid, r + 2, c - 1, n, expectedValue + 1);
        boolean ans6 = isValid(grid, r + 1, c - 2, n, expectedValue + 1);
        boolean ans7 = isValid(grid, r - 1, c - 2, n, expectedValue + 1);
        boolean ans8 = isValid(grid, r - 2, c - 1, n, expectedValue + 1);

        return ans1 || ans2 || ans3 || ans4 || ans5 || ans6 || ans7 || ans8;
    }
}
