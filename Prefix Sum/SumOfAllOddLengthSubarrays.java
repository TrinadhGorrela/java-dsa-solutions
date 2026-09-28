/**
 * 1588. Sum of All Odd Length Subarrays
 * Difficulty: Easy | Tags: Array, Math, Prefix Sum
 * https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
 *
 * Pattern: Odd-Length Fixed Window Enumeration
 * Key insight: For each odd window length L, build the first window sum in O(L), then slide across the array using the
 * additive/subtractive update curr = curr - nums[k - L] + nums[k], accumulating each window's sum into the total.
 *
 * Time Complexity: O(N^2) - Outer loop iterates over O(N) odd lengths, inner loop slides each window across up to N
 * positions
 * Space Complexity: O(1) - Only scalar accumulators (sum, curr) with no auxiliary data structures
 *
 * Edge Cases Handled: single-element array (only window of length 1), even-length array (no window of length N), all
 * elements identical
 */
class SumOfAllOddLengthSubarrays {
    public int sumOddLengthSubarrays(int[] nums) {
        int sum = 0;
        for (int i = 1; i <= nums.length; i += 2) {
            int curr = 0;
            for (int j = 0; j < i; j++) {
                curr += nums[j];
            }
            sum += curr;
            for (int k = i; k < nums.length; k++) {
                curr = curr - nums[k - i] + nums[k];
                sum += curr;
            }
        }
        return sum;
    }
}
