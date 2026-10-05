/**
 * 172. Factorial Trailing Zeroes
 * Difficulty: Medium | Tags: Math
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 *
 * Pattern: Legendre's Formula (Counting Factor 5 Multiples)
 *
 * Key insight: Each trailing zero needs a factor pair of (2, 5); 5 is scarcer
 * than 2 in n!, so the total count is floor(n/5) + floor(n/25) + floor(n/125) +
 * ... accumulated by repeatedly dividing n by 5.
 *
 * Time Complexity: O(log_5 n) - n is divided by 5 each iteration until it
 * reaches 0
 *
 * Space Complexity: O(1) - Only the running res accumulator
 *
 * Edge Cases Handled: n = 0 (loop never runs, returns 0), n < 5 (no factor of 5
 * present, zero trailing zeros), large n where the 5^i terms naturally thin out
 */
class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            res += n / 5;
            n = n / 5;
        }
        return res;
    }
}
