/**
 * 416. Partition Equal Subset Sum
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Knapsack Problem, 0-1
 * Knapsack
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * Pattern: 0-1 Knapsack (Subset Sum DP)
 *
 * Key insight: Partitioning into two equal-sum subsets is equivalent to asking
 * whether any subset sums to total/2. If the total is odd, partitioning is
 * impossible. A 2D DP table tracks the maximum value achievable for each (item,
 * capacity) pair; when dp[n][target] == target, an exact-sum subset exists.
 *
 * Time Complexity: O(N * S) where S = sum/2 - Two nested loops over N items and
 * S capacity values
 *
 * Space Complexity: O(N * S) - 2D DP array of dimensions (N+1) x (S+1)
 *
 * Edge Cases Handled: odd total sum immediately returns false; single element
 * cannot form two subsets; all elements larger than half the sum; zero-valued
 * elements not present per constraints
 */
class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int[][] dp = new int[nums.length + 1][target + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], nums[i - 1] + dp[i - 1][j - nums[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        if (dp[nums.length][target] == target) {
            return true;
        }

        return false;
    }
}
