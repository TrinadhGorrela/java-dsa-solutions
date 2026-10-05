/**
 * 213. House Robber II
 * Difficulty: Medium | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/house-robber-ii/
 *
 * Pattern: Circular array reduction via two linear House Robber sub-problems
 *
 * Key insight: Since first and last houses are adjacent in a circle, no valid
 * solution can rob both; solve two independent linear House Robber instances
 * (nums[0..n-2] and nums[1..n-1]) and return the max.
 *
 * Time Complexity: O(n) - Two linear DP passes, each O(n)
 *
 * Space Complexity: O(n) - Two auxiliary arrays of length n-1
 *
 * Edge Cases Handled: n=0 returns 0; n=1 returns nums[0]; n=2 returns max of
 * both; circular adjacency resolved by exclusive sub-arrays
 */
class HouseRobberII {
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        int[] skipLast = new int[nums.length - 1];
        int[] skipFirst = new int[nums.length - 1];

        for (int i = 0; i < nums.length - 1; i++) {
            skipLast[i] = nums[i];
            skipFirst[i] = nums[i + 1];
        }

        int lootSkippingLast = robbery(skipLast);
        int lootSkippingFirst = robbery(skipFirst);

        return Math.max(lootSkippingLast, lootSkippingFirst);
    }

    public static int robbery(int[] houses) {
        int[] loot = new int[houses.length];

        loot[0] = houses[0];
        loot[1] = Math.max(houses[0], houses[1]);

        for (int i = 2; i < houses.length; i++) {
            loot[i] = Math.max(loot[i - 2] + houses[i], loot[i - 1]);
        }
        return loot[houses.length - 1];
    }
}
