/**
 * 37. Sudoku Solver
 * Difficulty: Hard | Tags: Array, Hash Table, Backtracking, Matrix, Algorithm
 * X, Dancing Links
 * https://leetcode.com/problems/sudoku-solver/
 *
 * Pattern: Constraint Satisfaction via Cell-by-Cell Trial with Rollback
 *
 * Key insight: Locate the next empty cell (value 0), try every digit 1-9 that
 * does not violate the row, column, or 3x3 sub-box constraint; on success
 * recurse to the next cell, and on failure reset the cell to 0 (backtrack)
 * before trying the next digit.
 *
 * Time Complexity: O(9^(81)) worst case - up to 9 choices per empty cell, at
 * most 81 cells; in practice constraint propagation makes this extremely fast
 *
 * Space Complexity: O(81) recursion depth (constant, bounded by the 9x9 board
 * size)
 *
 * Edge Cases Handled: already-solved board (no zeros, returns immediately),
 * fully empty board (deepest recursion), invalid input with no solution (all 9
 * digits fail, unwinds completely), zeros at sub-box boundaries (3x3 box index
 * computed via i/3*3, j/3*3), single empty cell
 */
class SudokuSolver {
    public void solveSudoku(char[][] board) {
        int[][] intBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    intBoard[i][j] = 0;
                } else {
                    intBoard[i][j] = board[i][j] - '0';
                }

            }
        }

        solve(intBoard);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = (char) (intBoard[i][j] + '0');
            }
        }
    }

    private boolean solve(int[][] board) {
        int row = -1;
        int col = -1;
        boolean hasEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    hasEmpty = false;
                    break;
                }
            }
            if (!hasEmpty) {
                break;
            }

        }
        if (hasEmpty)
            return true;

        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;
                if (solve(board))
                    return true;
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isValid(int[][] board, int row, int col, int num) {

        for (int i = 0; i < 9; i++) {
            if ((board[row][i] == num) || (board[i][col] == num))
                return false;
        }
        
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num)
                    return false;
            }

        }
        return true;
    }
}
