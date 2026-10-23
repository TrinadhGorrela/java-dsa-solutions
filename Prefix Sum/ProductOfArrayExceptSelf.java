/**
 * 238. Product of Array Except Self
 * Difficulty: Medium | Tags: Array, Prefix Sum
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Pattern: Prefix Product + Running Suffix Product (Two Pass)
 *
 * Key insight: The product of everything except nums[i] is (product of all
 * elements left of i) * (product of all elements right of i). The first pass
 * stores left products in res[i], and the second pass sweeps right-to-left
 * while maintaining a running suffix product, so no division is needed and each
 * index combines independent left/right values.
 *
 * Time Complexity: O(N) - Two linear passes over the array, each element
 * visited once per pass
 *
 * Space Complexity: O(1) - Only a running suffix product variable is used; the
 * returned output array is not counted as auxiliary space
 *
 * Edge Cases Handled: input containing zeroes (no division, so one or multiple
 * zeroes are computed correctly), negative numbers, duplicate values, single
 * element (returns [1]); assumes non-null input with at least one element
 * (empty input would throw on res[0])
 */
class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;

        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int pro = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] = res[i] * pro;
            pro *= nums[i];
        }

        return res;
    }
}
