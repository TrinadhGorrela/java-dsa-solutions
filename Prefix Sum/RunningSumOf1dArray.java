/**
 * 1480. Running Sum of 1d Array
 * Difficulty: Easy | Tags: Array, Prefix Sum
 * https://leetcode.com/problems/running-sum-of-1d-array/
 *
 * Pattern: In-place Prefix Sum
 *
 * Key insight: Each element becomes the sum of all previous elements plus
 * itself, computed by maintaining a single running accumulator and overwriting
 * the array in left-to-right order.
 *
 * Time Complexity: O(N) - One linear pass accumulating and writing each
 * position exactly once
 *
 * Space Complexity: O(1) - A single int accumulator (sum) is used; the result
 * is written back into the input array
 *
 * Edge Cases Handled: single element (returned as-is), negative values
 * accumulating to a negative prefix, zeros at the start (prefix remains 0 until
 * first non-zero)
 */
class RunningSumOf1dArray {
    public int[] runningSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            nums[i] = sum;
        }
        return nums;
    }
}
