/**
 * 1631. Path With Minimum Effort
 * Difficulty: Medium | Tags: Array, Binary Search, Depth-First Search,
 * Breadth-First Search, Union-Find, Heap (Priority Queue), Matrix, Dijkstra's
 * Algorithm
 * https://leetcode.com/problems/path-with-minimum-effort/
 *
 * Pattern: Dijkstra's Algorithm (Min-Heap on Bottleneck / Min-Max Path)
 *
 * Key insight: Path effort is the maximum height difference along it, and
 * taking max() makes this bottleneck monotone non-decreasing - so expanding
 * cells in ascending effort order (heap keyed on the running max) guarantees
 * the first time the destination is polled its effort is optimal, exactly as in
 * Dijkstra.
 *
 * Time Complexity: O(M * N * log(M * N)) - Each of the M*N cells relaxes its 4
 * edges, each push/pop costing a logarithmic binary-heap operation
 *
 * Space Complexity: O(M * N) - The dist matrix and the priority queue each hold
 * up to one entry per cell
 *
 * Edge Cases Handled: 1x1 grid (destination polled immediately, effort 0);
 * boundary cells guarded by the row/col range check; already-improved cells
 * skipped by the strict effort < dist test; flat (equal-height) steps
 * contribute effort 0; dist pre-filled with Integer.MAX_VALUE so no cell looks
 * settled too early; final `return 0` is a dead fallback since the grid is
 * always connected
 */
class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[][] dist = new int[heights.length][heights[0].length];
        queue.add(new int[] { 0, 0, 0 });

        int[][] dirs = {
                { 0, -1 }, { -1, 0 }, { 1, 0 }, { 0, 1 }
        };

        for (int[] dis : dist) {
            Arrays.fill(dis, Integer.MAX_VALUE);
        }

        int max = 0;

        while (queue.size() != 0) {
            int[] temp = queue.poll();

            if (temp[1] == heights.length - 1 && temp[2] == heights[0].length - 1) {
                return temp[0];
            }

            for (int[] dir : dirs) {
                int newRow = temp[1] + dir[0];
                int newCol = temp[2] + dir[1];

                if (newRow >= 0 && newRow < heights.length && newCol >= 0 && newCol < heights[0].length) {
                    int effort = Math.max(temp[0], Math.abs(heights[temp[1]][temp[2]] - heights[newRow][newCol]));

                    if (effort < dist[newRow][newCol]) {
                        dist[newRow][newCol] = effort;
                        queue.add(new int[] { effort, newRow, newCol });
                    }
                }
            }

        }

        return max;
    }
}
