/**
 * 999. Available Captures for Rook
 * Difficulty: Easy | Tags: Array, Matrix, Simulation
 * https://leetcode.com/problems/available-captures-for-rook/
 *
 * Pattern: Fixed-Board Directional Scan
 *
 * Key insight: Locate the rook on the 8×8 board, then walk outward in the four
 * cardinal directions; count a capture the first time a pawn ('p') is
 * encountered before any bishop ('B') blocks the path.
 *
 * Time Complexity: O(1) - Board is fixed 8×8; at most 4 × 7 = 28 cells checked.
 *
 * Space Complexity: O(1) - Only rook coordinates and a capture counter.
 *
 * Edge Cases Handled: rook at board edge (shortened scan), no pawns on the
 * board, pawn blocked by bishop, multiple pawns in one direction (only the
 * first counts)
 */
class AvailableCapturesForRook {
    public int numRookCaptures(char[][] board) {
        int x = -1, y = -1;
        boolean found = false;
        for (int i = 0; i < 8 && !found; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                    found = true;
                    break;
                }

            }
        }
        return captures(x, y, board);
    }

    public int captures(int r, int c, char[][] board) {
        int captureCount = 0;
        for (int i = c - 1; i >= 0; i--) {
            if (board[r][i] == 'B')
                break;
            if (board[r][i] == 'p') {
                captureCount++;
                break;
            }
        }

        for (int i = c + 1; i < 8; i++) {
            if (board[r][i] == 'B')
                break;
            if (board[r][i] == 'p') {
                captureCount++;
                break;
            }
        }

        for (int i = r + 1; i < 8; i++) {
            if (board[i][c] == 'B')
                break;
            if (board[i][c] == 'p') {
                captureCount++;
                break;
            }
        }

        for (int i = r - 1; i >= 0; i--) {
            if (board[i][c] == 'B')
                break;
            if (board[i][c] == 'p') {
                captureCount++;
                break;
            }
        }

        return captureCount;

    }
}
