/**
 * 463. Island Perimeter
 * Difficulty: Easy | Tags: Array, Depth-First Search, Breadth-First Search,
 * Matrix
 * https://leetcode.com/problems/island-perimeter/
 *
 * Pattern: Per-Cell Neighbor Counting
 *
 * Key insight: Each land cell contributes 4 edges to the perimeter, minus 1 for
 * each adjacent land neighbor (shared edge is subtracted from both sides). By
 * counting all adjacent land pairs and subtracting from 4*(total land cells),
 * shared edges cancel correctly. Border cells naturally have fewer neighbors so
 * their contribution is higher.
 *
 * Time Complexity: O(M * N) - Single pass over the grid with up to 4 neighbor
 * checks per land cell
 *
 * Space Complexity: O(M * N) - Boolean land matrix mirrors the input grid
 * (could be eliminated for O(1) extra space)
 *
 * Edge Cases Handled: all-water grid (perimeter 0), single land cell (perimeter
 * 4), land on grid border/corners (out-of-bounds neighbors simply not counted),
 * large connected land mass (internal shared edges subtracted correctly)
 */
class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int adjacentLandCount = 0;
        int perimeter = 0;
        boolean[][] land = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    land[i][j] = true;
                } else {
                    land[i][j] = false;
                }
            }
        }
        for (int row = 0; row < land.length; row++) {
            for (int col = 0; col < land[0].length; col++) {
                if (land[row][col]) {
                    if (col > 0 && land[row][col - 1])
                        adjacentLandCount++;
                    if (col < land[row].length - 1 && land[row][col + 1])
                        adjacentLandCount++;
                    if (row > 0 && land[row - 1][col])
                        adjacentLandCount++;
                    if (row < land.length - 1 && land[row + 1][col])
                        adjacentLandCount++;

                    perimeter = perimeter + (4 - adjacentLandCount);
                    adjacentLandCount = 0;
                }
            }
        }
        return perimeter;
    }
}
