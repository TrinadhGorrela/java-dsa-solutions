/**
 * 256. Paint House
 * Difficulty: Medium | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/paint-house/
 *
 * Pattern: 2D bottom-up DP with adjacency constraint
 *
 * Key insight: dp[i][j] = min cost to paint house i with color j, where house i
 * cannot share the color of house i-1; each cell picks the minimum of the two
 * non-conflicting previous colors, yielding the optimal non-adjacent color
 * assignment.
 *
 * Time Complexity: O(n) - Two nested loops: n houses x 3 colors
 *
 * Space Complexity: O(n) - DP array of size (n+1) x 3
 *
 * Edge Cases Handled: empty costs array returns 0; single house returns min of
 * its three costs; negative costs are supported by the recurrence structure
 */
class PaintHouse {
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        
        int[][] dp = new int[costs.length + 1][3];

        dp[1][0] = costs[0][0];
        dp[1][1] = costs[0][1];
        dp[1][2] = costs[0][2];

        for (int i = 2; i < dp.length; i++) {
            dp[i][0] = costs[i - 1][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i - 1][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i - 1][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        return Math.min(dp[costs.length][0], Math.min(dp[costs.length][1], dp[costs.length][2]));
    }
}
