/**
 * 191. Number of 1 Bits
 * Difficulty: Easy | Tags: Divide and Conquer, Bit Manipulation
 * https://leetcode.com/problems/number-of-1-bits/
 *
 * Pattern: Bitwise Pop-Count (Shift and Mask)
 * Key insight: AND each number with 1 to test the current least significant bit, accumulate the count, then
 * logical-shift right (>>>) so negatives are treated as unsigned 32-bit patterns.
 *
 * Time Complexity: O(1) - Bounded by the fixed 32-bit width; at most 32 iterations regardless of n
 * Space Complexity: O(1) - Only the count accumulator
 *
 * Edge Cases Handled: n = 0 (zero iterations' worth of set bits), negative inputs (>>> treats sign bit as a data bit,
 * so all 32 bits counted), n with only the sign bit set (count = 1)
 */
class NumberOf1Bits {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }
}
