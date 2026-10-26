/**
 * 3619. Count Islands With Total Value Divisible by K
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Union-Find, Matrix
 * https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/
 *
 * Pattern: Grid Traversal (BFS / Flood Fill)
 * 
 * Key insight: To count islands whose total value is divisible by `K`, we iterate through the grid. Upon finding an unvisited, non-zero cell, we initiate a Breadth-First Search (BFS) to traverse the entire connected island, accumulating the sum of its cell values. Cells are marked visited during traversal to ensure each island is processed once, and the accumulated sum is then checked for divisibility by `K`.
 * 
 * Time Complexity: O(M * N) - Each cell in the M x N grid is visited and processed at most once by the nested loops and the BFS traversal.
 * 
 * Space Complexity: O(M * N) - The `visited` boolean array requires O(M * N) space. In the worst case, the BFS `queue` can hold up to O(M * N) elements (e.g., a grid entirely filled with a single island).
 * 
 * Edge Cases Handled: Single element grid (correctly identifies and sums); grid with all zero values (returns 0 islands); multiple distinct islands; large integer sums (long used); K=1 and K>1 divisibility checks.
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
