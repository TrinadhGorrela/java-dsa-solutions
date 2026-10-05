/**
 * 2091. Removing Minimum and Maximum From Array
 * Difficulty: Medium | Tags: Array, Greedy
 * https://leetcode.com/problems/removing-minimum-and-maximum-from-array/
 *
 * Pattern: Single-Pass Index Tracking + Three-Strategy Minimization
 *
 * Key insight: A single linear scan records the indices of the min and max
 * elements. Removing both requires a contiguous prefix, contiguous suffix, or
 * one from each end — so the answer is min(farther index + 1, N - nearer index,
 * nearer + 1 + N - farther).
 *
 * Time Complexity: O(N) - One pass over the array to locate min and max indices
 *
 * Space Complexity: O(1) - Only two integer index variables plus three
 * candidate counts
 *
 * Edge Cases Handled: all elements equal (min == max index), min or max already
 * at an array boundary, array of size 2
 */
class RemovingMinimumAndMaximumFromArray {
    public int minimumDeletions(int[] nums) {
        int min = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[min] > nums[i]) {
                min = i;
            }

            if (nums[max] < nums[i]) {
                max = i;
            }
        }

        int front = Math.max(min, max) + 1;
        int back = nums.length - Math.min(min, max);
        int both = (Math.min(min, max) + 1) + (nums.length - Math.max(min, max));

        return Math.min(front, Math.min(back, both));
    }
}
