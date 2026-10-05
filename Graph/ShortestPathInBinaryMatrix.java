/**
 * 1091. Shortest Path in Binary Matrix
 * Difficulty: Medium | Tags: Array, Breadth-First Search, Matrix
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 *
 * Pattern: BFS on 8-Directional Grid
 *
 * Key insight: Treat each open cell as a node with up to 8 neighbors (including
 * diagonals); BFS guarantees shortest path since all edges have unit weight.
 * The grid itself stores distances in-place (cell value = distance from
 * origin), eliminating a separate distance array.
 *
 * Time Complexity: O(M * N) - Each cell visited at most once; 8 neighbor checks
 * per cell
 *
 * Space Complexity: O(M * N) - BFS queue worst-case holds an entire BFS
 * frontier
 *
 * Edge Cases Handled: blocked start or end cell (value 1 returns -1),
 * unreachable destination (grid[rows-1][cols-1] stays 0 returns -1), single 1x1
 * open cell (returns 1 immediately)
 */
class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid == null || grid.length == 0 || grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1) {
            return -1;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { 0, 0 });
        grid[0][0] = 1;

        int[][] dirs = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int[] temp = queue.poll();

                for (int[] dir : dirs) {
                    int i = temp[0] + dir[0];
                    int j = temp[1] + dir[1];

                    if (i >= 0 && i < rows && j >= 0 && j < cols && grid[i][j] == 0) {
                        grid[i][j] = grid[temp[0]][temp[1]] + 1;
                        queue.offer(new int[] { i, j });
                    }
                }
                size--;
            }
        }

        return grid[rows - 1][cols - 1] == 0 ? -1 : grid[rows - 1][cols - 1];
    }
}
