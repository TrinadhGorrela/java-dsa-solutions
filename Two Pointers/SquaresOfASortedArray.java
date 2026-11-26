/**
 * 977. Squares of a Sorted Array
 * Difficulty: Easy | Tags: Array, Two Pointers, Sorting
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * Pattern: Two Pointers (Opposite Ends, Reverse Fill)
 *
 * Key insight: The largest square comes from the element with the largest
 * absolute value, which in a sorted array must be at one of the two ends.
 * Comparing the end squares and writing the larger one into the result from
 * right to left produces sorted output in a single pass, since each pointer
 * only moves inward.
 *
 * Time Complexity: O(N) - One pass; each element is examined once as the
 * pointers converge.
 *
 * Space Complexity: O(N) - Allocates a result array the same length as the
 * input.
 *
 * Edge Cases Handled: empty and single-element input, all-negative and
 * all-non-negative arrays, zero and mixed-sign arrays, duplicates/all-same
 * values, and values at constraint bounds (squares fit in int only within the
 * LeetCode limits)
 */
class SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int in = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                res[in--] = leftSquare;
                left++;
            } else {
                res[in--] = rightSquare;
                right--;
            }
        }

        return res;
    }
}
