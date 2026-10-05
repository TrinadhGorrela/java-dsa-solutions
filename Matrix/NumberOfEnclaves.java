/**
 * 1020. Number of Enclaves
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Union-Find, Matrix
 * https://leetcode.com/problems/number-of-enclaves/
 *
 * Pattern: Boundary Flood Fill (Iterative Border Scan + Recursive DFS)
 * Key insight: An enclave is a land region that never touches the grid border,
 * so instead
 * of enumerating regions we flood inward from every border land cell and mark
 * everything
 * that is provably NOT an enclave. Connectivity is symmetric, so a cell is
 * enclosed exactly
 * when no border cell can reach it - which turns the count into a leftover
 * scan.
 * Time Complexity: O(R*C) - each cell is examined a constant number of times:
 * once by the
 * border scan, once when first flooded, and once per incoming direction;
 * re-scanning an
 * already-flooded border cell only re-checks 4 marked neighbours and does not
 * recurse.
 * Space Complexity: O(R*C) - the boolean[][] visited table dominates, and the
 * recursion
 * stack can also reach O(R*C) depth along a serpentine land mass.
 * Edge Cases Handled: all land (the border fill reaches every cell, returns 0);
 * all water
 * (returns 0); single row or single column (every cell is a border cell, so
 * returns 0);
 * 1x1 grid (border cell, returns 0); several separate enclaves (counted
 * individually);
 * the input grid is left unmodified - a visited table is used instead of
 * overwriting land
 * with 0. Honest limitation: assumes a non-null, non-empty rectangular grid
 * since it
 * reads grid[0].length, so an empty grid throws.
 */
class NumberOfEnclaves {
    public int numEnclaves(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
                    if (grid[i][j] == 1) {
                        visited[i][j] = true;
                        dfs(grid, visited, i, j);
                    }
                }
            }
        }

        int count = 0;

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (!visited[i][j]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, boolean[][] visited, int row, int col) {
        int[][] dirs = {
                { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 }
        };

        for (int[] i : dirs) {
            int newRow = row + i[0];
            int newCol = col + i[1];

            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                    && grid[newRow][newCol] == 1) {
                if (!visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    dfs(grid, visited, newRow, newCol);
                }
            }
        }

    }
}
