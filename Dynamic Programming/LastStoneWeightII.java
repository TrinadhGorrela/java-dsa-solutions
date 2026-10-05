/**
 * 1049. Last Stone Weight II
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Knapsack Problem, 0-1
 * Knapsack
 * https://leetcode.com/problems/last-stone-weight-ii/
 *
 * Pattern: 0-1 Knapsack (Partition DP)
 *
 * Key insight: Smashing stones is equivalent to splitting them into two groups
 * and minimizing the absolute difference of their sums. By finding the maximum
 * achievable sum up to total/2 using a 0-1 knapsack, the answer is total - 2 *
 * best, which is the minimum possible leftover weight.
 *
 * Time Complexity: O(N * S) where S = sum/2 - Two nested loops over N stones
 * and S capacity values
 *
 * Space Complexity: O(N * S) - 2D DP array of dimensions (N+1) x (S+1)
 *
 * Edge Cases Handled: single stone (returns its own weight); two equal stones
 * (return 0); all stones the same weight; total sum is odd (target floors to
 * floor(sum/2))
 */
class LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }

        int target = sum / 2;
        int[][] dp = new int[stones.length + 1][target + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (stones[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], stones[i - 1] + dp[i - 1][j - stones[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return sum - (2 * dp[stones.length][target]);
    }
}
