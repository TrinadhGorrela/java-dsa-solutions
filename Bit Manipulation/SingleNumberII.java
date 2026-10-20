/**
 * 137. Single Number II
 * Difficulty: Medium | Tags: Array, Bit Manipulation
 * https://leetcode.com/problems/single-number-ii/
 *
 * Pattern: Bit Counting per Bit Position (Count % 3)
 *
 * Key insight: Every number except one appears exactly three times, so each bit
 * position's total set-bit count is a multiple of 3 plus the lone number's bit.
 * Counting set bits position-by-position and taking count % 3 reconstructs the
 * unique value without needing extra space proportional to the input.
 *
 * Time Complexity: O(32 * N) = O(N) - Visits each of the 32 bit positions once
 * per element
 *
 * Space Complexity: O(1) - Fixed 32-entry counter array independent of input
 * size
 *
 * Edge Cases Handled: empty input (returns 0), single element, negative numbers
 * (arithmetic right shift over a fixed 32 iterations correctly captures the
 * sign bit, and 1 << 31 rebuilds it), result 0 / mixed with negatives, all
 * other values appearing exactly three times
 */
class SingleNumberII {
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        int mask = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < bits.length; j++) {
                if ((nums[i] & mask) == 1) {
                    bits[j]++;
                }
                nums[i] >>= 1;
            }
        }

        int res = 0;

        for (int i = 0; i < bits.length; i++) {
            if (bits[i] % 3 != 0) {
                res |= (1 << i);
            }
        }

        return res;
    }
}
