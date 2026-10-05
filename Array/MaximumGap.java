/**
 * 164. Maximum Gap
 * Difficulty: Medium | Tags: Array, Sorting, Bucket Sort, Radix Sort,
 * Pigeonhole Principle
 * https://leetcode.com/problems/maximum-gap/
 *
 * Pattern: Sort-and-Scan Consecutive Gaps
 *
 * Key insight: After sorting, the maximum gap must be between some consecutive
 * pair — a single linear scan over the sorted array finds it. (A pigeonhole
 * bucket sort would achieve O(N) time.)
 *
 * Time Complexity: O(N log N) - Arrays.sort dominates; the post-sort scan is
 * O(N)
 *
 * Space Complexity: O(1) - In-place sort with only a running maximum variable
 *
 * Edge Cases Handled: fewer than 2 elements (early return 0), all elements
 * equal (max gap is 0), already sorted or reverse-sorted input
 */
class MaximumGap {
    public int maximumGap(int[] nums) {

        if (nums.length < 2)
            return 0;

        Arrays.sort(nums);
        int maxGap = 0;

        for (int i = 1; i < nums.length; i++) {
            int currentGap = nums[i] - nums[i - 1];
            maxGap = Math.max(maxGap, currentGap);
        }

        return maxGap;
    }
}
