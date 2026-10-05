/**
 * 300. Longest Increasing Subsequence
 * Difficulty: Medium | Tags: Array, Binary Search, Dynamic Programming, Longest
 * Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Pattern: Quadratic DP, LIS ending at each index
 *
 * Key insight: dp[i] stores the length of the longest increasing subsequence
 * that must include nums[i]; for each i, scan all j<i and extend dp[j]+1 when
 * nums[j]<nums[i], then take the global max.
 *
 * Time Complexity: O(n^2) - Nested loops: for each i, scan all j<i
 *
 * Space Complexity: O(n) - Single DP array of length n
 *
 * Edge Cases Handled: single element returns 1; strictly decreasing array
 * yields LIS=1; all equal elements (no strict increase) yields 1; already
 * sorted yields n
 */
class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
            }
        }
        return maxLength;
    }
}
