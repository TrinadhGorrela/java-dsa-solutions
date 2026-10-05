/**
 * 494. Target Sum
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Backtracking, Knapsack
 * Problem, 0-1 Knapsack
 * https://leetcode.com/problems/target-sum/
 *
 * Pattern: 0-1 Knapsack (Subset Sum Transformation)
 *
 * Key insight: Assigning '+' or '-' to each number partitions the array into
 * two subsets P and N. Since P + N = sum and P - N = target, solving gives P =
 * (sum + target) / 2. The problem reduces to counting subsets that sum to P — a
 * classic 0-1 knapsack count variant.
 *
 * Time Complexity: O(N * P) where P = (sum + target) / 2 — DP table of size N×P
 * filled with constant work per cell
 *
 * Space Complexity: O(N * P) — 2D DP array of dimensions (N+1) × (P+1)
 *
 * Edge Cases Handled: (sum + target) is negative or odd (returns 0
 * immediately), target unreachable from any subset, all zeros in input
 * (multiple valid assignments)
 */
class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;

        for (int i : nums) {
            sum += i;
        }

        int pos = sum + target;

        if (pos < 0 || pos % 2 != 0) {
            return 0;
        }

        pos /= 2;

        int[][] dp = new int[nums.length + 1][pos + 1];
        dp[0][0] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length][pos];
    }
}
