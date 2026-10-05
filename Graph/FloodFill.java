/**
 * 733. Flood Fill
 * Difficulty: Easy | Tags: Array, Depth-First Search, Breadth-First Search,
 * Matrix
 * https://leetcode.com/problems/flood-fill/
 *
 * Pattern: BFS Flood Fill on Pixel Grid
 *
 * Key insight: BFS from the starting pixel, replacing every 4-connected
 * neighbor that matches the original color with the new color. The early exit
 * when `originalColor == color` prevents infinite re-enqueueing of the same
 * connected component.
 *
 * Time Complexity: O(M * N) - Worst case visits every pixel in the image once
 *
 * Space Complexity: O(M * N) - BFS queue worst-case holds the entire connected
 * component (e.g., uniform-color image)
 *
 * Edge Cases Handled: start pixel already equals target color (early return,
 * image unchanged), single-pixel image, entire image is one color (all pixels
 * recolored), start pixel on border or corner (fewer neighbors)
 */
class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        Queue<int[]> queue = new ArrayDeque<>();
        int originalColor = image[sr][sc];
        if (originalColor == color) {
            return image;
        }

        queue.offer(new int[] { sr, sc });
        bfsFill(queue, image, sr, sc, originalColor, color);
        image[sr][sc] = color;
        return image;

    }

    public static void bfsFill(Queue<int[]> queue, int[][] image, int sr, int sc, int originalColor, int color) {
        int[][] dirs = {
                { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }
        };
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int[] curr = queue.poll();
                int i = curr[0];
                int j = curr[1];

                for (int[] dir : dirs) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    if (newI >= 0 && newI < image.length && newJ >= 0 && newJ < image[0].length
                            && originalColor == image[newI][newJ]) {
                        queue.offer(new int[] { newI, newJ });
                        image[newI][newJ] = color;
                    }
                }
                size--;
            }
        }
    }
}
