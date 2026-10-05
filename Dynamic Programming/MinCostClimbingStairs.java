/**
 * 746. Min Cost Climbing Stairs
 * Difficulty: Easy | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 *
 * Pattern: 1D bottom-up DP with free start
 *
 * Key insight: dp[i] = min cost to reach step i from the ground; since you can
 * start at step 0 or 1 for free, dp[0]=dp[1]=0, and each subsequent step pays
 * the cost of the step you jumped from.
 *
 * Time Complexity: O(n) - Single loop from index 2 to n
 *
 * Space Complexity: O(n) - DP array of size n+1
 *
 * Edge Cases Handled: n=2 returns 0 (both starting positions); dp[0] and dp[1]
 * are free; answer is dp[n] not dp[n-1] since "top" is past the last step
 */
class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2]);
        }
        return dp[dp.length - 1];
    }
}
