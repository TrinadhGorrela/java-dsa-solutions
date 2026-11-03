/**
 * 3882. Minimum XOR Path in a Grid
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Bit Manipulation,
 * Matrix
 * https://leetcode.com/problems/minimum-xor-path-in-a-grid/
 *
 * Pattern: Dynamic Programming with Bitmask XOR State Compression
 *
 * Key insight: Only the set of reachable XOR values matters at each cell, so
 * track them as a 1024-entry boolean table and propagate each value right and
 * down XORed with the current cell. This holds because every path prefix is
 * summarized by its XOR, and values stay within 10 bits.
 *
 * Time Complexity: O(R * C * 1024) - Every cell scans all 1024 possible XOR
 * states; the state space is bounded because grid values are < 1024.
 *
 * Space Complexity: O(C * 1024) - Two rolling rows of 1024 booleans each are
 * kept instead of the full grid of states.
 *
 * Edge Cases Handled: single cell (returns its value directly), single row and
 * single column (only one move direction is ever taken), zero-valued cells,
 * duplicate XOR results from different paths (deduplicated by the boolean set);
 * only safe when values fit in 10 bits (< 1024)
 */
class MinimumXorPathInAGrid {
    public int minCost(int[][] grid) {
        int t = grid.length;
        int s = grid[0].length;
        boolean[][] prev = new boolean[s][1024];

        prev[0][grid[0][0]] = true;

        for (int i = 0; i < t; i++) {
            boolean[][] curr = new boolean[s][1024];

            for (int j = 0; j < s; j++) {
                if (i == 0 && j == 0) {
                    curr = prev;
                }

                for (int k = 0; k < 1024; k++) {
                    if (i > 0) {
                        if (prev[j][k]) {
                            curr[j][k ^ grid[i][j]] = true;
                        }
                    }

                    if (j > 0) {
                        if (curr[j - 1][k]) {
                            curr[j][k ^ grid[i][j]] = true;
                        }
                    }
                }
            }

            prev = curr;
        }

        for (int i = 0; i < 1024; i++) {
            if (prev[s - 1][i]) {
                return i;
            }
        }

        return 0;
    }
}
