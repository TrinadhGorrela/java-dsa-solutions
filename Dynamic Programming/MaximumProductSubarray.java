/**
 * 152. Maximum Product Subarray
 * Difficulty: Medium | Tags: Array, Dynamic Programming
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Pattern: Dynamic Programming (Kadane's Variant - Track Min & Max)
 *
 * Key insight: Because a negative number multiplied by a very negative product
 * becomes a large positive, the optimal product ending at each index may come
 * from either the previous max OR the previous min. Tracking both running
 * extremes (and always considering restarting at nums[i] alone) therefore
 * captures the true maximum subarray product in a single pass.
 *
 * Time Complexity: O(N) - One linear pass over the input array, constant work
 * per element.
 *
 * Space Complexity: O(1) - Only a fixed set of running variables (currMax,
 * currMin, prevMax, prevMin, max).
 *
 * Edge Cases Handled: single-element array (initializes all variables to
 * nums[0], so max is correct); negative numbers (the currMin branch lets a
 * negative pair flip to a positive product); zeros (restarting at nums[i]
 * resets the product); arrays that wrap negative values like [-2, 3, -4] via
 * the min-tracking symmetry.
 */
class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int currMax = nums[0];
        int currMin = nums[0];
        int prevMax = nums[0];
        int prevMin = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currMax = Math.max(prevMax * nums[i], Math.max(prevMin * nums[i], nums[i]));
            currMin = Math.min(prevMax * nums[i], Math.min(prevMin * nums[i], nums[i]));
            max = Math.max(max, currMax);
            prevMax = currMax;
            prevMin = currMin;
        }

        return max;
    }
}
