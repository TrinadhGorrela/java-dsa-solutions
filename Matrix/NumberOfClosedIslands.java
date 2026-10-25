/**
 * 1254. Number of Closed Islands
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Union-Find, Matrix
 * https://leetcode.com/problems/number-of-closed-islands/
 *
 * Pattern: BFS Flood Fill (Boundary Seeding + Component Counting)
 *
 * Key insight: Any land connected to the grid border can never be enclosed, so
 * first flood-fill every border `0` to `1`, then each remaining `0` component
 * is guaranteed to be a closed island. Because every cell is filled at most
 * once, the total work stays linear in the number of cells.
 *
 * Time Complexity: O(M * N) - Each cell is enqueued and visited at most once
 * across all BFS passes.
 *
 * Space Complexity: O(M * N) - The BFS queue can hold an entire connected
 * component in the worst case.
 *
 * Edge Cases Handled: islands touching any border are excluded; single row or
 * column grid (every land cell touches the border, returns 0); all-water grid;
 * all-land grid; no closed islands present; input grid is mutated in place (not
 * preserved).
 */
class NumberOfClosedIslands {
    public int closedIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == 0) {
                grid[i][0] = 1;
                helper(grid, i, 0);
            }

            if (grid[i][grid[0].length - 1] == 0) {
                grid[i][grid[0].length - 1] = 1;
                helper(grid, i, grid[0].length - 1);
            }
        }

        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 0) {
                grid[0][i] = 1;
                helper(grid, 0, i);
            }

            if (grid[grid.length - 1][i] == 0) {
                grid[grid.length - 1][i] = 1;
                helper(grid, grid.length - 1, i);
            }
        }

        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    grid[i][j] = 1;
                    helper(grid, i, j);
                }
            }
        }

        return count;
    }

    private void helper(int[][] grid, int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        queue.add(new int[] { row, col });

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- != 0) {
                int[] temp = queue.poll();
                for (int[] dir : dirs) {
                    int newRow = temp[0] + dir[0];
                    int newCol = temp[1] + dir[1];

                    if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                            && grid[newRow][newCol] == 0) {
                        grid[newRow][newCol] = 1;
                        queue.add(new int[] { newRow, newCol });
                    }
                }
            }
        }
    }
}
