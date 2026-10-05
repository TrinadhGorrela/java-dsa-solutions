/**
 * 1004. Max Consecutive Ones III
 * Difficulty: Medium | Tags: Array, Binary Search, Sliding Window, Prefix Sum
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 *
 * Pattern: Variable Sliding Window (At Most k Zeros)
 *
 * Key insight: Count zeros in the window; when the count exceeds k, advance
 * left until a zero is evicted, restoring the budget. The window represents a
 * subarray that can be fully converted to ones by flipping at most k zeros.
 * Track the maximum window length throughout.
 *
 * Time Complexity: O(N) - Right pointer scans once; left pointer advances at
 * most N times total
 *
 * Space Complexity: O(1) - Three integer scalars (left, max, count) with no
 * heap or map
 *
 * Edge Cases Handled: k = 0 (no flips, answer is the longest existing run of
 * 1s), all zeros (answer is k if k <= N), all ones (answer is N), k >= total
 * zero count (entire array is flippable)
 */
class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int max = 0;
        int count = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0 && count <= k) {
                count++;
            }
            while (count > k) {
                if (nums[left] == 0) {
                    count--;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
