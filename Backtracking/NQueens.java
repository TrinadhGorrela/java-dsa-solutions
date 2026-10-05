/**
 * 51. N-Queens
 * Difficulty: Hard | Tags: Array, Backtracking, Algorithm X
 * https://leetcode.com/problems/n-queens/
 *
 * Pattern: Row-by-Row Backtracking with Board State Recording
 *
 * Key insight: Attempt to place a queen in each column of the current row;
 * validate against all previously placed queens by checking shared column, main
 * diagonal (row-col), and anti-diagonal (row+col); upon completing all n rows,
 * snapshot the board as a List<String> solution.
 *
 * Time Complexity: O(n!) - each row narrows the safe-column candidates;
 * diagonal/column checks prune early
 *
 * Space Complexity: O(n^2) per board snapshot * number of solutions - stores
 * every valid configuration
 *
 * Edge Cases Handled: n = 1 (single queen, one solution), n = 2 or n = 3 (no
 * solutions exist), queens placed on board edges where diagonals extend beyond
 * bounds (guarded), full board snapshot conversion to dot/Q string format at
 * each solution
 */
class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        placeQueens(board, res, n, 0);
        return res;
    }

    public void placeQueens(char[][] board, List<List<String>> res, int n, int row) {
        if (row == n) {
            List<String> solution = new ArrayList<>();
            for (char[] line : board) {
                solution.add(new String(line));
            }
            res.add(solution);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';
                placeQueens(board, res, n, row + 1);
                board[row][col] = '.';
            }
        }
    }

    public boolean isSafe(char[][] board, int row, int col, int n) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 'Q') {
                    if (c == col) {
                        return false;
                    }
                    if (Math.abs(r - row) == Math.abs(c - col)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
