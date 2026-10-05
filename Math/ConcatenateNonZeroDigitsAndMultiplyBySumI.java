/**
 * 3754. Concatenate Non-Zero Digits and Multiply by Sum I
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum
 * -i/
 *
 * Pattern: Two-Pass Digit Rebuild (Reverse Then Filter)
 *
 * Key insight: First reverse n's digits while accumulating their sum — reusing
 * the reversal to expose digits in original order — then rebuild a number that
 * skips zero digits, and return the product of that rebuilt number and the
 * digit sum as a long to avoid overflowing int.
 *
 * Time Complexity: O(log10 n) - Two linear passes over the digits
 *
 * Space Complexity: O(1) - Only sum, reversedNum, num, and digit temporaries
 *
 * Edge Cases Handled: zero digits dropped during reconstruction, trailing zeros
 * (reversal's leading zeros vanish harmlessly), single-digit n (sum and rebuilt
 * value both match the digit), result exceeding int range (long accumulator
 * prevents overflow)
 */
class ConcatenateNonZeroDigitsAndMultiplyBySumI {
    public long sumAndMultiply(int n) {
        int sum = 0;
        long num = n;
        long reversedNum = 0;
        while (num != 0) {
            long digit = num % 10;
            reversedNum = reversedNum * 10 + digit;
            sum += digit;
            num /= 10;
        }
        num = 0;
        while (reversedNum != 0) {
            long digit = reversedNum % 10;
            if (digit != 0) {
                num = num * 10 + digit;
            }
            reversedNum /= 10;
        }
        return num * sum;
    }
}
