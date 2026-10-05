/**
 * 130. Surrounded Regions
 * Difficulty: Medium | Tags: Array, Depth-First Search, Breadth-First Search,
 * Union-Find, Matrix
 * https://leetcode.com/problems/surrounded-regions/
 *
 * Pattern: Border-Seeded BFS Flood Fill
 *
 * Key insight: Instead of searching for surrounded regions, invert the problem:
 * BFS from every border 'O' cell inward, marking all reachable 'O's as safe.
 * After the flood, any unvisited 'O' must be interior and gets flipped to 'X'.
 * This avoids tracking enclosure per region.
 *
 * Time Complexity: O(M * N) - Each cell is enqueued at most once; border scan
 * is O(M + N)
 *
 * Space Complexity: O(M * N) - Visited matrix and BFS queue both scale with
 * grid dimensions
 *
 * Edge Cases Handled: single-row or single-column boards (all cells are border,
 * nothing flipped), all 'X' grid (no BFS needed), all 'O' grid (all preserved),
 * interior 'O' region fully enclosed and flipped to 'X'
 */
class SurroundedRegions {
    public void solve(char[][] board) {
        int rows = board.length - 1;
        int cols = board[0].length - 1;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows + 1][cols + 1];

        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[] { 0, i });
                visited[0][i] = true;
            }

            if (board[rows][i] == 'O') {
                queue.offer(new int[] { rows, i });
                visited[rows][i] = true;
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O' && !visited[i][0]) {
                queue.offer(new int[] { i, 0 });
                visited[i][0] = true;
            }

            if (board[i][cols] == 'O' && !visited[i][cols]) {
                queue.offer(new int[] { i, cols });
                visited[i][cols] = true;
            }
        }

        bfs(queue, board, visited);

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                if (visited[i][j]) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void bfs(Queue<int[]> queue, char[][] board, boolean[][] visited) {
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

                    if (newI >= 0 && newI < board.length && newJ >= 0 && newJ < board[0].length) {
                        if (board[newI][newJ] == 'O' && !visited[newI][newJ]) {
                            queue.offer(new int[] { newI, newJ });
                            visited[newI][newJ] = true;
                        }
                    }
                }
                size--;
            }
        }
    }
}
