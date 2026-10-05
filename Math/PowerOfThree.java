/**
 * 326. Power of Three
 * Difficulty: Easy | Tags: Math, Recursion
 * https://leetcode.com/problems/power-of-three/
 *
 * Pattern: Repeated Multiplication (Incremental Power Construction)
 *
 * Key insight: Start at 3^0 = 1 and multiply by 3 until the running power
 * reaches or exceeds n; return true only if it lands exactly on n.
 *
 * Time Complexity: O(log_3 n) - Each iteration multiplies the power by 3, up to
 * the largest power of 3 that fits in an int
 *
 * Space Complexity: O(1) - Only power and isPower flags
 *
 * Edge Cases Handled: n = 1 (3^0, matched on the first iteration), non-powers
 * of 3 (loop exits once power exceeds n and isPower stays false), n values
 * beyond the largest representable power of 3
 */
class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        int power = 1;
        boolean isPower = false;
        for (int i = 0; i < power; i++) {
            if (power == n) {
                isPower = true;
                break;
            }
            power = power * 3;
        }
        return isPower;
    }
}
