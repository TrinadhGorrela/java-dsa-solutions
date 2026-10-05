/**
 * 1464. Maximum Product of Two Elements in an Array
 * Difficulty: Easy | Tags: Array, Sorting, Heap (Priority Queue)
 * https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/
 *
 * Pattern: Single-Pass Top-Two Tracking
 *
 * Key insight: Since the product increases with each operand, the max product
 * of (nums[i]-1)*(nums[j]-1) is always achieved by the two largest values — no
 * sorting needed.
 *
 * Time Complexity: O(N) - Single linear scan comparing each element against
 * current max and secondMax
 *
 * Space Complexity: O(1) - Two integer variables (max, secondMax) hold the
 * running top-two values
 *
 * Edge Cases Handled: duplicate maximums (secondMax stays below max), all equal
 * values (secondMax = max = same element value), two-element input, element
 * value 1 (product becomes 0)
 */
class MaximumProductOfTwoElementsInAnArray {
    public int maxProduct(int[] nums) {
        int max = 0;
        int secondMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max <= nums[i]) {
                secondMax = max;
                max = nums[i];
            } else if (secondMax < nums[i] && max != nums[i]) {
                secondMax = nums[i];
            }
        }
        return (max - 1) * (secondMax - 1);
    }
}
