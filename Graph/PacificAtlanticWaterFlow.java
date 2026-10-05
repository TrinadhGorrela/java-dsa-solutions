/**
 * 417. Pacific Atlantic Water Flow
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Matrix
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 *
 * Pattern: Dual Reverse BFS (Ocean Inward Flow)
 *
 * Key insight: Instead of simulating water flowing downhill from each cell
 * (O(M^2*N^2)), reverse the direction: BFS inward from Pacific border cells and
 * Atlantic border cells separately, moving only to equal-or-higher neighbors.
 * Cells reachable in both BFS passes form the intersection and can drain to
 * both oceans.
 *
 * Time Complexity: O(M * N) - Two independent BFS passes each visit every cell
 * at most once
 *
 * Space Complexity: O(M * N) - Two boolean reachability matrices plus BFS queue
 *
 * Edge Cases Handled: null or empty grid (returns empty list), single-cell grid
 * touching both oceans, monotonic height plateaus (equal heights allow flow),
 * shared-border cells (top-right corner touches both oceans directly)
 */
class PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();

        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];

        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < cols; i++) {
            queue.offer(new int[] { 0, i });
            pacificReachable[0][i] = true;
        }

        for (int i = 0; i < rows; i++) {
            queue.offer(new int[] { i, 0 });
            pacificReachable[i][0] = true;
        }
        solve(pacificReachable, heights, queue);

        for (int i = 0; i < cols; i++) {
            queue.offer(new int[] { rows - 1, i });
            atlanticReachable[rows - 1][i] = true;
        }

        for (int i = 0; i < rows; i++) {
            queue.offer(new int[] { i, cols - 1 });
            atlanticReachable[i][cols - 1] = true;
        }
        solve(atlanticReachable, heights, queue);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    public static void solve(boolean[][] reachable, int[][] heights, Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int[] temp = queue.poll();
                int i = temp[0];
                int j = temp[1];
                if (i - 1 >= 0 && heights[i][j] <= heights[i - 1][j] && !reachable[i - 1][j]) {
                    reachable[i - 1][j] = true;
                    queue.offer(new int[] { i - 1, j });
                }

                if (i + 1 < heights.length && heights[i][j] <= heights[i + 1][j] && !reachable[i + 1][j]) {
                    reachable[i + 1][j] = true;
                    queue.offer(new int[] { i + 1, j });
                }

                if (j - 1 >= 0 && heights[i][j] <= heights[i][j - 1] && !reachable[i][j - 1]) {
                    reachable[i][j - 1] = true;
                    queue.offer(new int[] { i, j - 1 });
                }

                if (j + 1 < heights[0].length && heights[i][j] <= heights[i][j + 1] && !reachable[i][j + 1]) {
                    reachable[i][j + 1] = true;
                    queue.offer(new int[] { i, j + 1 });
                }

                size--;
            }
        }
    }
}
