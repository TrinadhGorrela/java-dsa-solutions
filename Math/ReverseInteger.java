/**
 * 7. Reverse Integer
 * Difficulty: Medium | Tags: Math
 * https://leetcode.com/problems/reverse-integer/
 *
 * Pattern: Digit Pop and Rebuild with Overflow Pre-Check
 *
 * Key insight: Pop the last digit (x % 10) and push it onto the reversed
 * accumulator, but before each multiply, verify rev would not exceed
 * MAX_VALUE/10 or drop below MIN_VALUE/10 to bail out and return 0 on overflow.
 *
 * Time Complexity: O(log n) - One iteration per decimal digit of x
 *
 * Space Complexity: O(1) - Only rev and a single digit variable
 *
 * Edge Cases Handled: overflow past 32-bit int bounds (returns 0), negative
 * inputs reversed naturally by the sign of x % 10, trailing zero digits (e.g.,
 * 120 -> 21), Integer.MIN_VALUE (whose negation overflows, hence reversed
 * numerically without negation)
 */
class ReverseInteger {
    public int reverse(int x) {
        int b;
        int rev = 0;

        while (x != 0) {
            int lastDigit = x % 10;
            if (rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE / 10) {
                return 0;
            }
            rev = rev * 10 + lastDigit;
            x = x / 10;
        }
        return rev;
    }
}
