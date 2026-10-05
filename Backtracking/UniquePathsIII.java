/**
 * 980. Unique Paths III
 * Difficulty: Hard | Tags: Array, Backtracking, Bit Manipulation, Matrix,
 * Hamiltonian Path
 * https://leetcode.com/problems/unique-paths-iii/
 *
 * Pattern: Backtracking (DFS Hamiltonian Path)
 *
 * Key insight: Every non-obstacle cell must be visited exactly once before
 * reaching the end cell (value 2). The algorithm explores all four directions
 * from the current cell, marks/unmarks visited cells, and at the end cell
 * verifies that every non-obstacle cell was used — enforcing the Hamiltonian
 * path constraint.
 *
 * Time Complexity: O(4^(M×N)) worst-case — each cell branches up to 4
 * directions before pruning via visited/obstacle checks
 *
 * Space Complexity: O(M×N) — boolean visited grid plus recursion depth up to
 * M×N
 *
 * Edge Cases Handled: out-of-bounds moves rejected, obstacles (value -1)
 * blocked, already-visited cells skipped, end cell reached with unvisited
 * non-obstacle cells (returns 0), no valid path exists from start to end
 */
class UniquePathsIII {
    public int uniquePathsIII(int[][] grid) {
        boolean[][] used = new boolean[grid.length][grid[0].length];
        int row = 0;
        int col = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        return helper(grid, used, row, col);
    }

    private int helper(int[][] grid, boolean[][] used, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || used[row][col] || grid[row][col] == -1) {
            return 0;
        }

        used[row][col] = true;

        if (grid[row][col] == 2) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] != -1 && !used[i][j]) {
                        used[row][col] = false;
                        return 0;
                    }
                }
            }

            used[row][col] = false;
            return 1;
        }

        int up = helper(grid, used, row + 1, col);
        int down = helper(grid, used, row - 1, col);
        int left = helper(grid, used, row, col - 1);
        int right = helper(grid, used, row, col + 1);

        used[row][col] = false;

        return up + down + left + right;
    }
}
