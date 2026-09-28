/**
 * 29. Divide Two Integers
 * Difficulty: Medium | Tags: Math, Bit Manipulation
 * https://leetcode.com/problems/divide-two-integers/
 *
 * Pattern: Bit-by-Bit Long Division (Unsigned Shifting)
 * Key insight: Work with the absolute values widened to long, then scan bit positions from 31 down to 0; for each bit,
 * if the divisor shifted left that far still fits in the remaining dividend, subtract it and accumulate that power of
 * two in the quotient. Handle the single overflow case (MIN_VALUE / -1) explicitly up front.
 *
 * Time Complexity: O(32) - Fixed 32 bit positions, independent of the operands
 * Space Complexity: O(1) - Only absDividend, absDivisor, res, and the loop index
 *
 * Edge Cases Handled: Integer.MIN_VALUE / -1 overflow (clamped to MAX_VALUE), negative dividend or divisor (sign folded
 * in via XOR at the end), zero dividend (all bit tests fail, result 0), shifting operands widened to long so 31-bit
 * left shifts never overflow
 */
class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        int res = 0;
        
        for (int i = 31; i >= 0; i--) {
            if ((absDividend >> i) >= absDivisor) {
                res += (1 << i);
                absDividend -= (absDivisor << i);
            }
        }
        
        return isNegative ? -res : res;
    }
}
