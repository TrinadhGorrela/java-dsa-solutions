/**
 * 3619. Count Islands With Total Value Divisible by K
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Union-Find, Matrix
 * https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/
 *
 * Pattern: Island Traversal (BFS) with Component Sum (4-Connectivity)
 *
 * Key insight: Treat connected components of cells with value != 0 (4
 * directions) as islands; traverse each unvisited island via BFS, sum all grid
 * values in that component (accumulated in long), and count it if (sum % k ==
 * 0). Each cell is visited exactly once via the visited matrix.
 *
 * Time Complexity: O(MN) - Every cell of the MxN grid is enqueued/dequeued and
 * checked at most once, with O(1) work per cell.
 *
 * Space Complexity: O(MN) - Uses a boolean visited matrix of size M*N and an
 * ArrayDeque that can hold up to M*N cells in the worst case (a single large
 * island).
 *
 * Edge Cases Handled: single-cell islands; islands of various sizes; grid
 * entirely of zeros (returns 0); large cell values (summed in long). No
 * explicit null/empty grid checks or k==0 handling (relies on problem
 * constraints).
 */
class CountIslandsWithTotalValueDivisibleByK {
    public int countIslands(int[][] grid, int k) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int counts = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0 && !visited[i][j]) {
                    long sum = helper(grid, visited, i, j);
                    if (sum % k == 0) {
                        counts++;
                    }
                }
            }
        }

        return counts;
    }

    private long helper(int[][] grid, boolean[][] visited, int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] dirs = {
                { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }
        };
        long sum = grid[row][col];
        queue.add(new int[] { row, col });
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- != 0) {
                int[] temp = queue.poll();

                for (int[] dir : dirs) {
                    int newRow = dir[0] + temp[0];
                    int newCol = dir[1] + temp[1];

                    if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                            && grid[newRow][newCol] != 0 && !visited[newRow][newCol]) {
                        sum += grid[newRow][newCol];
                        visited[newRow][newCol] = true;
                        queue.add(new int[] { newRow, newCol });
                    }
                }
            }
        }

        return sum;
    }
}
