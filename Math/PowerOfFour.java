/**
 * 342. Power of Four
 * Difficulty: Easy | Tags: Math, Bit Manipulation, Recursion
 * https://leetcode.com/problems/power-of-four/
 *
 * Pattern: Repeated Multiplication (Incremental Power Construction)
 *
 * Key insight: Start at 4^0 = 1 and multiply by 4 until the running power
 * reaches or exceeds n; return true only if it lands exactly on n.
 *
 * Time Complexity: O(log_4 n) - Each iteration multiplies the power by 4, up to
 * the largest power of 4 that fits in an int
 *
 * Space Complexity: O(1) - Only power and isPower flags
 *
 * Edge Cases Handled: n = 1 (4^0, matched on the first iteration), non-powers
 * of 4 (loop exits once power exceeds n and isPower stays false), n values
 * beyond the largest representable power of 4
 */
class PowerOfFour {
    public boolean isPowerOfFour(int n) {
        int power = 1;
        boolean isPower = false;
        for (int i = 0; i < power; i++) {
            if (power == n) {
                isPower = true;
                break;
            }
            power = power * 4;
        }
        return isPower;
    }
}
