/**
 * 1043. Partition Array for Maximum Sum
 * Difficulty: Medium | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/partition-array-for-maximum-sum/
 *
 * Pattern: Dynamic Programming (Top-Down Memoization)
 *
 * Key insight: Every element in a segment is replaced by that segment's
 * maximum, so a segment contributes length * max regardless of internal order;
 * trying all first-segment lengths 1..k while extending a running max covers
 * every split, and memoizing by start index ensures each suffix is solved once.
 *
 * Time Complexity: O(N * K) - Up to N start indices, each trying min(k, N -
 * start) segment lengths.
 *
 * Space Complexity: O(N) - The dp memo array holds N entries and the recursion
 * stack can reach depth N.
 *
 * Edge Cases Handled: empty array (base case returns 0), single element, k
 * larger than the array (Math.min clamps the segment end), all-equal or
 * already-optimal input; only safe for non-negative values, and recursion depth
 * can reach N.
 */
class PartitionArrayForMaximumSum {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length];

        Arrays.fill(dp, -1);

        return helper(dp, arr, k, 0);
    }

    private int helper(int[] dp, int[] arr, int k, int in) {
        if (in == arr.length) {
            return 0;
        }

        if (dp[in] != -1) {
            return dp[in];
        }

        int max = Integer.MIN_VALUE;
        int res = 0;

        for (int i = in; i < Math.min(arr.length, in + k); i++) {
            max = Math.max(max, arr[i]);
            int sum = ((i - in + 1) * max) + helper(dp, arr, k, i + 1);
            res = Math.max(res, sum);
        }

        dp[in] = res;

        return dp[in];
    }
}
