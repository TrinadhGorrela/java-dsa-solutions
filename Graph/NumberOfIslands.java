/**
 * 200. Number of Islands
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Union-Find, Matrix
 * https://leetcode.com/problems/number-of-islands/
 *
 * Pattern: BFS Connected Components on Grid
 *
 * Key insight: Each unvisited '1' cell starts a new island; BFS floods the
 * entire 4-connected component of land cells, marking them visited. The flood
 * guarantees no cell in the same island is counted twice, since BFS only
 * enqueues unvisited land neighbors.
 *
 * Time Complexity: O(M * N) - Grid scan is M*N; BFS visits each land cell
 * exactly once across all islands
 *
 * Space Complexity: O(M * N) - Visited matrix and BFS queue both scale with
 * grid size
 *
 * Edge Cases Handled: all-water grid (returns 0), single-cell island at any
 * position, diagonal '1's not connected (treated as separate islands), islands
 * touching grid borders or corners
 */
class NumberOfIslands {
    public int numIslands(char[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res++;
                    visited[i][j] = true;
                    queue.offer(new int[] { i, j });
                    solve(queue, grid, visited);
                }
            }
        }
        return res;
    }

    public static void solve(Queue<int[]> queue, char[][] grid, boolean[][] visited) {
        int[][] dirs = {
                { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 }
        };
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int[] temp = queue.poll();
                int i = temp[0];
                int j = temp[1];
                for (int[] dir : dirs) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];

                    if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[0].length && grid[newI][newJ] == '1'
                            && !visited[newI][newJ]) {
                        visited[newI][newJ] = true;
                        queue.offer(new int[] { newI, newJ });
                    }
                }
                size--;
            }
        }
    }
}
