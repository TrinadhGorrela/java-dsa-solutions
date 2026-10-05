/**
 * 835. Image Overlap
 * Difficulty: Medium | Tags: Array, Matrix
 * https://leetcode.com/problems/image-overlap/
 *
 * Pattern: Brute-Force Translation Enumeration
 *
 * Key insight: There are exactly (2n-1)^2 possible translations of img1
 * relative to img2. For each translation, count pixel-wise overlaps in O(n^2),
 * and the maximum across all shifts is the answer. This is fast enough because
 * n <= 30, making the total work ~30^4 ≈ 810K.
 *
 * Time Complexity: O(n^4) - Two nested loops over O(n^2) shifts, each invoking
 * an O(n^2) overlap count
 *
 * Space Complexity: O(1) - Only a fixed number of integer variables; no
 * auxiliary data structures
 *
 * Edge Cases Handled: single-pixel images (n=1); images with no overlapping 1s
 * (returns 0); images already perfectly aligned; all-zero or all-one images;
 * shifts that move img1 entirely off img2 (boundary clamping in helper)
 */
class ImageOverlap {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int res = 0;

        for (int i = -(n - 1); i < n; i++) {
            for (int j = -(n - 1); j < n; j++) {
                int temp = helper(img1, img2, i, j);
                res = Math.max(res, temp);
            }
        }

        return res;
    }

    private int helper(int[][] img1, int[][] img2, int rowShift, int colShift) {
        int n = img1.length;
        int count = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (img1[row][col] == 1) {
                    int newRow = row + rowShift;
                    int newCol = col + colShift;

                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && img2[newRow][newCol] == 1) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
