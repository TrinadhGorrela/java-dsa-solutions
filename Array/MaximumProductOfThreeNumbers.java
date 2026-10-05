/**
 * 628. Maximum Product of Three Numbers
 * Difficulty: Easy | Tags: Array, Math, Sorting
 * https://leetcode.com/problems/maximum-product-of-three-numbers/
 *
 * Pattern: Sort + Candidate Comparison
 *
 * Key insight: After sorting, the maximum 3-product is either the three largest
 * positives (tail) or the two most-negative values times the largest positive
 * (two negatives yield a positive product).
 *
 * Time Complexity: O(N log N) - Arrays.sort dominates with N log N
 * comparison-based sorting
 *
 * Space Complexity: O(1) - Two local product variables after in-place sort, no
 * extra data structures
 *
 * Edge Cases Handled: two large negatives times a positive beats three
 * positives, all-negative arrays, zeros in the middle, exactly three elements
 * (both candidates overlap)
 */
class MaximumProductOfThreeNumbers {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int mul1 = nums[0] * nums[1] * nums[nums.length - 1];
        int mul2 = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
        return Math.max(mul1, mul2);
    }
}
