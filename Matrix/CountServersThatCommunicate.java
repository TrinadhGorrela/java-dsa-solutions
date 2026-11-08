/**
 * 1267. Count Servers that Communicate
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Union-Find, Matrix, Counting
 * https://leetcode.com/problems/count-servers-that-communicate/
 *
 * Pattern: Row/Column Counting
 *
 * Key insight: A server communicates iff its row or column contains at least
 * one other server, so precomputing per-row and per-column counts turns each
 * cell check into an O(1) test and avoids any graph traversal.
 *
 * Time Complexity: O(M * N) - Two passes over the matrix; counting and
 * qualifying each cell are constant-time operations.
 *
 * Space Complexity: O(M + N) - Two auxiliary arrays, one per row and one per
 * column, independent of the number of servers.
 *
 * Edge Cases Handled: isolated servers with no row/column peer, all-empty grids
 * (returns 0), single-row or single-column boards, and multiple servers sharing
 * a row or column. Assumes a non-empty, rectangular grid because grid[0].length
 * is read eagerly.
 */
class CountServersThatCommunicate {
    public int countServers(int[][] grid) {
        int[] rowCount = new int[grid.length];
        int[] colCount = new int[grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i] += 1;
                    colCount[j] += 1;
                }
            }
        }

        int total = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && (rowCount[i] > 1 || colCount[j] > 1)) {
                    total++;
                }
            }
        }

        return total;
    }
}
