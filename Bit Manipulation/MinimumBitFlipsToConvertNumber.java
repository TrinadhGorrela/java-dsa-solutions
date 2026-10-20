/**
 * 2220. Minimum Bit Flips to Convert Number
 * Difficulty: Easy | Tags: Bit Manipulation
 * https://leetcode.com/problems/minimum-bit-flips-to-convert-number/
 *
 * Pattern: XOR + Population Count (Bitwise Hamming Distance)
 *
 * Key insight: XOR sets exactly the bits where start and goal differ, so the
 * minimum flips equals the number of set bits in start ^ goal (their Hamming
 * distance) - one XOR plus a fixed 32-bit scan yields the answer directly.
 *
 * Time Complexity: O(32) = O(1) - Fixed loop over the 32 bit positions,
 * independent of input magnitude
 *
 * Space Complexity: O(1) - Only a few integer locals; no auxiliary data
 * structures
 *
 * Edge Cases Handled: start == goal (0 flips), equal values at extremes 0 and
 * Integer.MAX_VALUE, negative numbers (arithmetic right shift over exactly 32
 * iterations still counts the two's-complement bits correctly), differing only
 * in the sign bit
 */
class MinimumBitFlipsToConvertNumber {
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        int mask = 1;
        int count = 0;

        for (int i = 0; i < 32; i++) {
            if ((xor & mask) == 1) {
                count++;
            }
            xor >>= 1;
        }

        return count;
    }
}
