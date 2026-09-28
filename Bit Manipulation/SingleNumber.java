/**
 * 136. Single Number
 * Difficulty: Easy | Tags: Array, Bit Manipulation
 * https://leetcode.com/problems/single-number/
 *
 * Pattern: Accumulative XOR Cancellation
 * Key insight: XOR is commutative and associative, and x ^ x = 0, so folding the entire array cancels all paired
 * elements — the accumulator holds the unique number in O(1) space.
 *
 * Time Complexity: O(N) - Single pass XOR-ing each element
 * Space Complexity: O(1) - One integer accumulator; no data structures
 *
 * Edge Cases Handled: single element (XOR of 0 and nums[0]), zero as the unique value, negative numbers (two's
 * complement XOR works correctly), all pairs except one
 */
class SingleNumber {
    public int singleNumber(int[] nums) {
        int xorResult = 0;
        for (int i = 0; i < nums.length; i++) {
            xorResult = xorResult ^ nums[i];
        }
        return xorResult;
    }
}
