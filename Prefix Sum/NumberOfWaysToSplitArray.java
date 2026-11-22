/**
 * 2270. Number of Ways to Split Array
 * Difficulty: Medium | Tags: Array, Prefix Sum
 * https://leetcode.com/problems/number-of-ways-to-split-array/
 *
 * Pattern: Prefix Sum (Running Left Sum with 64-bit Accumulator)
 *
 * Key insight: After one pass computes the total, a running left prefix gives
 * each split's right sum as (total - left) in O(1), so validity is checked in
 * constant time per split. Accumulating in long prevents overflow when the
 * array sum exceeds int range.
 *
 * Time Complexity: O(N) - Two linear passes: one for the total, one over split
 * points.
 *
 * Space Complexity: O(1) - Only scalar accumulators and the result counter are
 * used.
 *
 * Edge Cases Handled: Two-element array (single split), array shorter than two
 * (loop skipped, returns 0), zero and negative values, sums beyond int range
 * (long arithmetic), and no empty-right split (loop stops at index n-2).
 */
class NumberOfWaysToSplitArray {
    public int waysToSplitArray(int[] nums) {
        long total = 0;

        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }

        long curr = 0;
        int res = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            curr += nums[i];

            if (curr >= (total - curr)) {
                res++;
            }
        }

        return res;
    }
}
