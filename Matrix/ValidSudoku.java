/**
 * 36. Valid Sudoku
 * Difficulty: Medium | Tags: Array, Hash Table, Matrix
 * https://leetcode.com/problems/valid-sudoku/
 *
 * Pattern: In-Place Cell-Blanking Validation
 *
 * Key insight: For each filled cell, temporarily blank it and check whether any
 * other cell in the same row, column, or 3×3 box has the same digit—if not,
 * restore it; this avoids extra hash-set storage.
 *
 * Time Complexity: O(81·3) = O(1) - Board is fixed 9×9; each cell scans at most
 * 20 peers.
 *
 * Space Complexity: O(1) - In-place int[9][9] conversion, no extra data
 * structures.
 *
 * Edge Cases Handled: empty board (all '.'); duplicate confined to one row,
 * column, or box; single filled cell; all cells filled
 */
class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
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
        return isValid(intBoard);
    }

    private boolean isValid(int[][] intBoard) {
        int row = -1;
        int col = -1;
        int num = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                num = intBoard[i][j];
                if (num != 0) {
                    intBoard[i][j] = 0;
                    row = i;
                    col = j;
                    if (!isSafe(intBoard, row, col, num))
                        return false;
                    intBoard[i][j] = num;
                }
            }
        }
        return true;
    }

    public boolean isSafe(int[][] intBoard, int row, int col, int num) {

        for (int i = 0; i < 9; i++) {
            if ((intBoard[row][i] == num) || (intBoard[i][col] == num))
                return false;
        }
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (intBoard[startRow + i][startCol + j] == num)
                    return false;
            }

        }
        return true;
    }
}
