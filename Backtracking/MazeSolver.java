/**
 * Rat in a Maze (Maze Solver)
 * Difficulty: Medium | Tags: Array, Backtracking, Matrix
 * https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
 *
 * Pattern: Backtracking (Graph DFS)
 *
 * Key insight: Explore Up/Down/Left/Right from each cell, marking visited cells
 * with -1 and restoring them on backtrack to find every path, pruning
 * out-of-bounds and blocked cells.
 *
 * Time Complexity: O(4^(N^2)) - Four choices at each step, max path length is
 * N^2
 *
 * Space Complexity: O(N^2) - Max recursion depth in the worst-case scenario
 *
 * Edge Cases Handled: start or destination cell blocked, no possible path
 * (empty result), out-of-bounds in all four directions, already-visited cells,
 * single-cell maze
 */
class MazeSolver {

    public List<String> findPaths(int[][] maze, int n) {
        List<String> res = new ArrayList<>();
        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0)
            return res;

        String path = "";
        explorePaths(maze, n, 0, 0, path, res);
        return res;
    }

    public void explorePaths(int[][] maze, int n, int row, int col, String path, List<String> res) {
        if (row < 0 || col < 0 || row >= n || col >= n || maze[row][col] == 0 || maze[row][col] == -1) {
            return;
        }

        if (row == n - 1 && col == n - 1) {
            res.add(path);
            return;
        }

        maze[row][col] = -1;

        explorePaths(maze, n, row - 1, col, path + 'U', res);
        explorePaths(maze, n, row + 1, col, path + 'D', res);
        explorePaths(maze, n, row, col + 1, path + 'R', res);
        explorePaths(maze, n, row, col - 1, path + 'L', res);

        maze[row][col] = 1;
    }
}
