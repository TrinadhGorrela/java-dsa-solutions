/**
 * 977. Squares of a Sorted Array
 * Difficulty: Easy | Tags: Array, Two Pointers, Sorting
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * Pattern: Square + Selection Sort
 *
 * Key insight: Square every element into a fresh array, then sort with an
 * O(N^2) selection-sort pass. (Optimal O(N) is achievable with a two-pointer
 * merge from the negative/positive split.)
 *
 * Time Complexity: O(N^2) - Nested comparison loops implement a selection sort
 * on the squared values (suboptimal)
 *
 * Space Complexity: O(N) - Allocates a separate result array of the same length
 * for the squared values
 *
 * Edge Cases Handled: negative values squaring and reordering, zeros, all
 * identical values, single-element array
 */
class SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int writeIdx = 0;
        for (int i : nums) {
            res[writeIdx] = i * i;
            writeIdx++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (res[i] > res[j]) {
                    int temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }

        return res;
    }
}
