/**
 * 832. Flipping an Image
 * Difficulty: Easy | Tags: Array, Two Pointers, Bit Manipulation, Matrix,
 * Simulation
 * https://leetcode.com/problems/flipping-an-image/
 *
 * Pattern: Two-Pointer Row Reverse + Bitwise Invert
 *
 * Key insight: Reverse each row in-place with two pointers, then invert every
 * cell with 1 - value; both operations run on the original array with no extra
 * space.
 *
 * Time Complexity: O(n²) - Reverse is O(n) per row, invert is O(n) per row.
 *
 * Space Complexity: O(1) - In-place swaps and subtraction.
 *
 * Edge Cases Handled: 1×1 image, odd-width rows (center element swapped with
 * itself then inverted), all zeros, all ones
 */
class FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            int start = 0;
            int end = image[i].length - 1;
            while (start < end) {
                int temp = image[i][start];
                image[i][start] = image[i][end];
                image[i][end] = temp;
                start++;
                end--;
            }
        }

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                image[i][j] = 1 - image[i][j];
            }
        }
        return image;
    }
}
