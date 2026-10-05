/**
 * 53. Maximum Subarray
 * Difficulty: Medium | Tags: Array, Divide and Conquer, Dynamic Programming
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Pattern: Kadane's Dynamic Programming
 *
 * Key insight: The maximum subarray ending at position i is either nums[i]
 * alone or the previous running sum extended — this recurrence collapses the 2D
 * subarray search into a single O(N) pass.
 *
 * Time Complexity: O(N) - Single pass tracking running and global maximum
 *
 * Space Complexity: O(1) - Only two variables: curSum and max
 *
 * Edge Cases Handled: all negative numbers (returns least negative, not 0),
 * single element, all positive (returns total sum), zero interspersed with
 * negatives
 */
class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int curSum = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            max = Math.max(curSum, max);
        }
        return max;
    }
}
