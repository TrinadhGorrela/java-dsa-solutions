/**
 * 1547. Minimum Cost to Cut a Stick
 * Difficulty: Hard | Tags: Array, Dynamic Programming, Sorting
 * https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
 *
 * Pattern: Interval DP (Top-Down Memoized Recursion with Sentinel Boundaries)
 *
 * Key insight: The first cut in any interval splits the stick into two
 * independent sub-problems, so the optimal cost is the current segment length
 * plus the optimal costs of the left and right sub-intervals. Adding sentinels
 * 0 and n and sorting the marks makes every interval addressable by index
 * bounds, so each of the O(M^2) intervals is solved exactly once.
 *
 * Time Complexity: O(M^3) where M = cuts.length + 2 - O(M^2) memoized intervals
 * each try O(M) first-cut positions; sorting adds only O(M log M)
 *
 * Space Complexity: O(M^2) - The memoization table holds one entry per
 * interval; marks array and recursion depth are O(M)
 *
 * Edge Cases Handled: No cuts (st > end returns 0), single cut, unsorted cuts
 * (sorted with sentinels 0 and n), empty interior sub-intervals (base case
 * returns 0), overlapping sub-problems (memo table initialized to -1); assumes
 * cuts lie strictly between 0 and n per constraints
 */
class MinimumCostToCutAStick {
    public int minCost(int n, int[] cuts) {
        int[] marks = new int[cuts.length + 2];
        int[][] dp = new int[marks.length][marks.length];

        marks[0] = 0;
        marks[1] = n;

        for (int i = 0; i < cuts.length; i++) {
            marks[i + 2] = cuts[i];
        }

        Arrays.sort(marks);

        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }

        return helper(dp, marks, 1, marks.length - 2);
    }

    private int helper(int[][] dp, int[] marks, int st, int end) {
        if (st > end) {
            return 0;
        }

        if (dp[st][end] != -1) {
            return dp[st][end];
        }

        int min = Integer.MAX_VALUE;

        for (int i = st; i <= end; i++) {
            int cost = (marks[end + 1] - marks[st - 1]) + helper(dp, marks, st, i - 1) + helper(dp, marks, i + 1, end);

            min = Math.min(min, cost);
        }

        dp[st][end] = min;

        return min;
    }
}
