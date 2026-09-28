/**
 * 260. Single Number III
 * Difficulty: Medium | Tags: Array, Bit Manipulation
 * https://leetcode.com/problems/single-number-iii/
 *
 * Pattern: XOR Partitioning (Bit Manipulation)
 * Key insight: XOR of the whole array isolates the XOR of the two unique numbers (all duplicates cancel).
 *   Since those two differ in at least one bit, any set bit in that XOR splits the array into two
 *   groups — each group then XORs down to exactly one of the answers.
 *
 * Time Complexity: O(N) - Two linear passes over the array with constant work per element
 * Space Complexity: O(1) - Only a few integer accumulators; output array not counted
 *
 * Edge Cases Handled: negative numbers (XOR and bitwise mask work on two's-complement ints),
 *   numbers with the sign bit as the splitting bit, duplicates that cancel pairwise;
 *   assumes exactly two elements appear once (if all values appear twice the mask loop never
 *   terminates — safe only under the problem's guaranteed input shape)
 */
class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int xorVal = 0;

        for (int i : nums) {
            xorVal ^= i;
        }

        int mask = 1;

        while ((mask & xorVal) == 0) {
            mask <<= 1;
        }

        int num1 = 0;
        int num2 = 0;
        for (int i : nums) {
            if ((mask & i) == 0) {
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }

        return new int[] { num1, num2 };
    }
}
