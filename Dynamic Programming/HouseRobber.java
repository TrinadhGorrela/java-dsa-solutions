/**
 * 198. House Robber
 * Difficulty: Medium | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/house-robber/
 *
 * Pattern: 1D DP, maximum-weight independent set on a path graph
 *
 * Key insight: dp[i] = max(dp[i-1], dp[i-2]+nums[i]) captures the choice at
 * each house: either skip it (inherit previous best) or rob it (add to best
 * from two houses back), enforcing the no-adjacent constraint.
 *
 * Time Complexity: O(n) - Single pass through the houses
 *
 * Space Complexity: O(n) - DP array of length n
 *
 * Edge Cases Handled: empty array returns 0; single house returns its value;
 * two houses returns the max
 */
class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
