/**
 * 376. Wiggle Subsequence
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Greedy
 * https://leetcode.com/problems/wiggle-subsequence/
 *
 * Pattern: Greedy (Difference Sign Tracking)
 *
 * Key insight: Only direction changes (positive-to-negative or
 * negative-to-positive differences) extend the wiggle sequence, so tracking the
 * previous non-zero difference lets us count peaks/valleys in one pass while
 * skipping flats.
 *
 * Time Complexity: O(N) - Single linear scan over the array.
 *
 * Space Complexity: O(1) - Uses only a few variables for state tracking.
 *
 * Edge Cases Handled: Single element, all-equal values (no wiggle),
 * plateaus/duplicate adjacent values, monotonic sequences.
 */
class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        int prev = 0;
        int curr = 0;
        int res = 1;

        for (int i = 1; i < nums.length; i++) {
            curr = nums[i - 1] - nums[i];

            if ((prev >= 0 && curr < 0) || (prev <= 0 && curr > 0)) {
                res++;
                prev = curr;
            }
        }

        return res;
    }
}
