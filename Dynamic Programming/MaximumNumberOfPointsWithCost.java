/**
 * 1937. Maximum Number of Points with Cost
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Matrix
 * https://leetcode.com/problems/maximum-number-of-points-with-cost/
 *
 * Pattern: DP over Rows with Two-Pass Prefix/Suffix Max (1D Distance Transform)
 *
 * Key insight: dp[i][j] = points[i][j] + max over k of (dp[i-1][k] - |j-k|);
 * splitting the absolute value into k <= j and k >= j lets two running scans of
 * dp[i-1][k] +/- k track each side's best predecessor. Each column's best
 * contribution shifts by only one, turning an O(C^2) per-row transition into
 * two O(C) passes.
 *
 * Time Complexity: O(R*C) - Two linear passes over C columns for each of the R
 * rows (R = points.length, C = points[0].length)
 *
 * Space Complexity: O(R*C) - The full dp table is retained and the two
 * running-max arrays are O(C); a rolling O(C) table would suffice but is not
 * used
 *
 * Edge Cases Handled: Single row (transition loop skipped), single column (both
 * passes degenerate), non-negative scores letting the left running max safely
 * start at 0, large totals using long arithmetic to avoid int overflow; assumes
 * a rectangular matrix with non-negative points per constraints
 */
class MaximumNumberOfPointsWithCost {
    public long maxPoints(int[][] points) {
        long[][] dp = new long[points.length][points[0].length];

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = points[0][i];
        }

        for (int i = 1; i < dp.length; i++) {
            long[] leftMax = new long[points[0].length];
            long[] rightMax = new long[points[0].length];
            long runningMax = 0;

            for (int j = 0; j < leftMax.length; j++) {
                long power = dp[i - 1][j] + j;
                runningMax = Math.max(runningMax, power);
                leftMax[j] = runningMax - j;
            }

            runningMax = Integer.MIN_VALUE;

            for (int j = rightMax.length - 1; j >= 0; j--) {
                long power = dp[i - 1][j] - j;
                runningMax = Math.max(runningMax, power);
                rightMax[j] = runningMax + j;
            }

            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = Math.max(leftMax[j], rightMax[j]) + points[i][j];
            }
        }

        long max = 0;

        for (int i = 0; i < dp[0].length; i++) {
            max = Math.max(max, dp[dp.length - 1][i]);
        }

        return max;
    }
}
