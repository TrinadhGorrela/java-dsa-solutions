/**
 * 231. Power of Two
 * Difficulty: Easy | Tags: Math, Bit Manipulation, Recursion
 * https://leetcode.com/problems/power-of-two/
 *
 * Pattern: Set-Bit Test (n & (n - 1))
 * Key insight: A power of two has exactly one set bit, so clearing the lowest set bit with n & (n - 1) yields 0;
 * combined with n > 0 it cleanly rejects zero and negatives.
 *
 * Time Complexity: O(1) - Single bitwise operation and comparison
 * Space Complexity: O(1) - No auxiliary data structures
 *
 * Edge Cases Handled: n <= 0 (rejected by the positivity guard), n = 1 (2^0, its single set bit clears to 0), large
 * powers near int range
 */
class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}