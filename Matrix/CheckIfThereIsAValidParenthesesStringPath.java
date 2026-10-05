/**
 * 2267. Check if There Is a Valid Parentheses String Path
 * Difficulty: Hard | Tags: Array, Dynamic Programming, Matrix, Bracket
 * Sequences
 * https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-pa
 * th/
 *
 * Pattern: Memoized DFS (Top-Down DP over row, column, balance)
 *
 * Key insight: From a given cell, which moves can still succeed depends only on
 * the current open-bracket balance, never on the path taken to reach it, so
 * (row, col, balance) is a single reusable state. Caching one verdict per state
 * collapses the exponential enumeration of right/down paths into a single sweep
 * of the state space, while the same recurrence prunes the instant a balance
 * would go negative.
 *
 * Time Complexity: O(m * n * (m + n)) - The memo guarantees each (row, col,
 * balance) state is expanded at most once, with balance bounded by the path
 * length m + n - 1.
 *
 * Space Complexity: O(m * n * (m + n)) - The 3D dp array holds rows * cols *
 * (len + 1) entries; the DFS call stack adds at most O(m + n) frames.
 *
 * Edge Cases Handled: an odd rows + cols - 1 path length is rejected up front,
 * since a balanced string must have even length; a start cell that is not '('
 * or an end cell that is not ')' is rejected before the dp table is allocated;
 * a negative intermediate balance is pruned immediately, so ')' can never
 * outnumber '('; a non-zero balance left over at the destination is rejected;
 * out-of-grid steps are caught by the bounds guard, which also covers
 * single-row and single-column grids that have no branching; failed states are
 * memoized as -1, not just successes, so dead ends are never re-explored; a 1x1
 * grid falls out of the parity check. Not handled: a null or empty grid is
 * dereferenced directly, and ragged rows are assumed impossible.
 */
class CheckIfThereIsAValidParenthesesStringPath {
    public boolean hasValidPath(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int len = rows + cols - 1;

        if (len % 2 != 0) {
            return false;
        }

        if (grid[0][0] != '(' || grid[rows - 1][cols - 1] != ')') {
            return false;
        }

        int[][][] dp = new int[rows][cols][len + 1];

        return helper(grid, dp, 0, 0, 0);
    }

    private boolean helper(char[][] grid, int[][][] dp, int row, int col, int balance) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return false;
        }

        int nxtBalance = balance + (grid[row][col] == '(' ? 1 : -1);

        if (nxtBalance < 0) {
            return false;
        }

        if (dp[row][col][balance] != 0) {
            return dp[row][col][balance] == 1;
        }

        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return nxtBalance == 0;
        }

        boolean success = helper(grid, dp, row + 1, col, nxtBalance)
                || helper(grid, dp, row, col + 1, nxtBalance);

        dp[row][col][balance] = success ? 1 : -1;

        return success;
    }
}
