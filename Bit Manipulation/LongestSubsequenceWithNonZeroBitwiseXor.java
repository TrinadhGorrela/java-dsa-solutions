/**
 * 3702. Longest Subsequence With Non-Zero Bitwise XOR
 * Difficulty: Medium | Tags: Array, Bit Manipulation
 * https://leetcode.com/problems/longest-subsequence-with-non-zero-bitwise-xor/
 *
 * Pattern: Total XOR Evaluation
 * Key insight: Compute the XOR of the entire array in one pass. If the total is already non-zero, all elements form a
 * valid subsequence. If zero (but not all elements are zero), dropping exactly one non-zero element breaks the
 * cancellation. If all elements are zero, no non-zero XOR is possible.
 *
 * Time Complexity: O(N) - Single pass to compute the XOR and count zero elements
 * Space Complexity: O(1) - Only an XOR accumulator and a zero counter
 *
 * Edge Cases Handled: all zeros (returns 0), total XOR non-zero (return N), total XOR zero with at least one non-zero
 * element (return N-1), single non-zero element
 */
class LongestSubsequenceWithNonZeroBitwiseXor {
    public int longestSubsequence(int[] nums) {
        int xorSum = 0;
        int zeros = 0;
        for (int i : nums) {
            xorSum ^= i;
            if (i == 0) {
                zeros++;
            }
        }

        if (zeros == nums.length) {
            return 0;
        }

        if (xorSum == 0) {
            return nums.length - 1;
        }
        return nums.length;
    }
}
