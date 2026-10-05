/**
 * 52. N-Queens II
 * Difficulty: Hard | Tags: Backtracking, Algorithm X
 * https://leetcode.com/problems/n-queens-ii/
 *
 * Pattern: Row-by-Row Backtracking with Three-Way Conflict Detection
 *
 * Key insight: Place one queen per row; for each candidate column, verify no
 * existing queen shares the same column, main diagonal (row - col constant), or
 * anti-diagonal (row + col constant) -- these three O(1) checks per cell prune
 * the entire search tree without auxiliary data structures.
 *
 * Time Complexity: O(n!) - place first queen in n columns, second in at most
 * n-1, etc.; conflict checks reduce this further in practice
 *
 * Space Complexity: O(n^2) for the board representation + O(n) recursion depth
 *
 * Edge Cases Handled: n = 1 (one solution), n = 2 or n = 3 (zero solutions),
 * boundary cells on first/last column where diagonal indices go out of bounds
 * (guarded by bounds check), full backtracking when no safe column exists in a
 * row
 */
class NQueensII {
    public int totalNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        placeQueens(board, res, n, 0);
        return res.size();
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
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q')
                return false;
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }
}
