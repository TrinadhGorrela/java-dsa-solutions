/**
 * 79. Word Search
 * Difficulty: Medium | Tags: Array, String, Backtracking, Depth-First Search,
 * Matrix
 * https://leetcode.com/problems/word-search/
 *
 * Pattern: Backtracking / DFS on Grid (In-Place Visited Marking)
 *
 * Key insight: A match must trace adjacent cells without reusing any, so the
 * DFS stamps each visited cell with '*' and restores it on unwind - the board
 * itself serves as the visited set. Bounds and character-mismatch checks prune
 * dead branches immediately, so only simple paths of length |word| are ever
 * explored.
 *
 * Time Complexity: O(M * N * 4^L) - M*N candidate start cells, each spawning a
 * DFS that branches into at most 4 directions for each of the L word characters
 *
 * Space Complexity: O(L) - Recursion depth is capped at the word length;
 * visited state lives inside the board, so no auxiliary matrix is allocated
 *
 * Edge Cases Handled: single-character word (matches at the in == len-1 base
 * case); word longer than any grid path (search dies at boundaries); no cell
 * equals word[0]; out-of-bounds neighbors rejected before indexing; cell reuse
 * blocked by the in-place marker and undone after backtracking; empty board
 * returns false without touching board[0]; empty word is NOT handled - it would
 * throw on word.charAt(0), assumed non-empty per constraints
 */
class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backtrack(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean backtrack(char[][] board, String word, int in, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || in >= word.length()
                || word.charAt(in) != board[row][col]) {
            return false;
        }

        if (in == word.length() - 1) {
            return true;
        }
        char c = board[row][col];
        board[row][col] = '*';

        boolean isFound = backtrack(board, word, in + 1, row + 1, col) || backtrack(board, word, in + 1, row, col + 1)
                || backtrack(board, word, in + 1, row - 1, col) || backtrack(board, word, in + 1, row, col - 1);

        board[row][col] = c;

        return isFound;
    }
}
