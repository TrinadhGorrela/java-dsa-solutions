/**
 * 413. Arithmetic Slices
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Sliding Window
 * https://leetcode.com/problems/arithmetic-slices/
 *
 * Pattern: Linear DP (Suffix Count)
 *
 * Key insight: dp[i] counts arithmetic slices ending exactly at index i. If the
 * current triple extends the previous run, dp[i] = dp[i-1] + 1; otherwise dp[i]
 * = 0. The running sum of dp[] gives the total, because each dp[i] represents
 * all new slices that newly end at i.
 *
 * Time Complexity: O(N) - Single pass over the array with constant work per
 * element.
 *
 * Space Complexity: O(N) - dp array of length N to track per-position slice
 * counts.
 *
 * Edge Cases Handled: fewer than 3 elements (loop starts at i=3 so dp stays
 * all-zero, returns 0), all-same array (every triple qualifies, counts
 * accumulate), already arithmetic (continuous run produces maximal count).
 */
class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] nums) {
        int[] dp = new int[nums.length];

        for (int i = 3; i <= nums.length; i++) {
            if (nums[i - 2] - nums[i - 3] == nums[i - 1] - nums[i - 2]) {
                dp[i - 1] = dp[i - 2] + 1;
            }
        }

        int res = 0;

        for (int i : dp) {
            res += i;
        }

        return res;
    }
}
