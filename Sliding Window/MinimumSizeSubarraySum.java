/**
 * 209. Minimum Size Subarray Sum
 * Difficulty: Medium | Tags: Array, Binary Search, Sliding Window, Prefix Sum
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * Pattern: Variable-Length Sliding Window (Minimum Sum >= Target)
 *
 * Key insight: Expand right to grow the running sum; once sum >= target,
 * repeatedly shrink from the left (subtracting nums[left]) while maintaining
 * the >= target invariant, recording the minimum window length at each shrink
 * step. Both pointers advance monotonically, ensuring O(N).
 *
 * Time Complexity: O(N) - Right pointer iterates N times; left pointer advances
 * at most N times total across all iterations
 *
 * Space Complexity: O(1) - Only three scalars (left, sum, res) with no
 * auxiliary structures
 *
 * Edge Cases Handled: no subarray sums to target or more (return 0), single
 * element equals target, target requires the entire array, target met exactly
 * at window boundary
 */
class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum = sum + nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum = sum - nums[left];
                left++;
            }

        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
