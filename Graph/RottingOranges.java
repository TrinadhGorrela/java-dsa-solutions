/**
 * 994. Rotting Oranges
 * Difficulty: Medium | Tags: Array, Breadth-First Search, Matrix
 * https://leetcode.com/problems/rotting-oranges/
 *
 * Pattern: Multi-Source BFS (Simultaneous Expansion)
 *
 * Key insight: All initially rotten oranges start at distance 0 and expand in
 * parallel; each BFS level represents one minute. An `isRot` flag per level
 * tracks whether any new orange rotted, so the minute counter only increments
 * when propagation actually occurs.
 *
 * Time Complexity: O(M * N) - Each cell enqueued at most once; initial scan
 * plus BFS each visit every cell once
 *
 * Space Complexity: O(M * N) - BFS queue worst-case holds all cells (e.g.,
 * checkerboard pattern)
 *
 * Edge Cases Handled: no fresh oranges (returns 0), fresh oranges isolated from
 * all rotten sources (returns -1 after post-BFS scan), no initially rotten
 * oranges with fresh present (returns -1), all oranges already rotten (returns
 * 0)
 */
class RottingOranges {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });
                }
            }
        }
        int res = solve(queue, grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return res;
    }

    public static int solve(Queue<int[]> queue, int[][] grid) {
        int minutes = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isRot = false;
            while (size != 0) {
                int[] arr = queue.poll();
                int i = arr[0];
                int j = arr[1];

                if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                    grid[i - 1][j] = 2;
                    queue.offer(new int[] { i - 1, j });
                    isRot = true;
                }

                if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                    grid[i + 1][j] = 2;
                    queue.offer(new int[] { i + 1, j });
                    isRot = true;
                }

                if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                    grid[i][j - 1] = 2;
                    queue.offer(new int[] { i, j - 1 });
                    isRot = true;
                }

                if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                    grid[i][j + 1] = 2;
                    queue.offer(new int[] { i, j + 1 });
                    isRot = true;
                }

                size--;
            }
            if (isRot) {
                minutes++;
            }
        }
        return minutes;
    }
}
