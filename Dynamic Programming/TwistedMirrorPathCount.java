/**
 * 3665. Twisted Mirror Path Count
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Matrix
 * https://leetcode.com/problems/twisted-mirror-path-count/
 *
 * Pattern: Dynamic Programming (Memoized DFS on Grid + Direction State)
 *
 * Key insight: A mirror cell (value 1) forces a turn, so whether a cell can be
 * traversed depends on the incoming direction; encoding (row, col, direction)
 * as the memo state makes the overlapping subproblems cacheable. Because each
 * state is solved once, exponential path enumeration collapses to
 * linear-in-cells work.
 *
 * Time Complexity: O(m * n) - Each of the m*n cells is computed for at most two
 * direction states with constant work per state.
 *
 * Space Complexity: O(m * n) - The 3D memo table stores up to two values per
 * cell; the recursion stack adds O(m + n).
 *
 * Edge Cases Handled: moves outside the grid (return 0), destination reached
 * (returns 1), single-cell grid, single-row and single-column grids; null/empty
 * input is not handled because grid[0].length is dereferenced up front.
 */
class TwistedMirrorPathCount {
    int MOD = 1000000007;

    public int uniquePaths(int[][] grid) {
        Integer[][][] memo = new Integer[grid.length][grid[0].length][2];

        return helper(grid, 0, 0, 0, memo);
    }

    private int helper(int[][] grid, int row, int col, int dir, Integer[][][] memo) {
        int m = grid.length;
        int n = grid[0].length;

        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 0;
        }

        if (row == m - 1 && col == n - 1) {
            return 1;
        }

        if (memo[row][col][dir] != null) {
            return memo[row][col][dir];
        }

        int paths = 0;

        if (grid[row][col] == 1) {
            if (dir == 0) {
                paths = helper(grid, row + 1, col, 1, memo);
            } else {
                paths = helper(grid, row, col + 1, 0, memo);
            }
        } else {
            paths = (helper(grid, row, col + 1, 0, memo) + helper(grid, row + 1, col, 1, memo)) % MOD;
        }

        memo[row][col][dir] = paths;

        return paths;
    }
}
