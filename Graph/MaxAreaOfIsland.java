/**
 * 695. Max Area of Island
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Union-Find, Matrix
 * https://leetcode.com/problems/max-area-of-island/
 *
 * Pattern: BFS Connected Components with Area Tracking
 *
 * Key insight: Similar to counting islands, but each BFS also counts cells as
 * it floods the component. The area counter increments on enqueue (not
 * dequeue), ensuring each land cell contributes exactly 1. Running max across
 * all components yields the answer.
 *
 * Time Complexity: O(M * N) - Grid scan plus BFS visits each land cell once;
 * water cells only checked during neighbor bounds
 *
 * Space Complexity: O(M * N) - Visited matrix and BFS queue scale with grid
 * dimensions
 *
 * Edge Cases Handled: all-water grid (returns 0), single-cell island (area 1),
 * entire grid is one island (area = M*N), multiple disconnected islands of
 * varying sizes
 */
class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        int res = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                    res = Math.max(res, bfs(queue, visited, grid));
                }
            }
        }
        return res;
    }

    private int bfs(Queue<int[]> queue, boolean[][] visited, int[][] grid) {
        int[][] dirs = {
                { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 }
        };
        int res = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int[] temp = queue.poll();
                int i = temp[0];
                int j = temp[1];

                for (int[] dir : dirs) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];

                    if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[0].length
                            && grid[newI][newJ] == 1 && !visited[newI][newJ]) {
                        queue.offer(new int[] { newI, newJ });
                        visited[newI][newJ] = true;
                        res++;
                    }
                }
                size--;
            }
        }

        return res;
    }
}
