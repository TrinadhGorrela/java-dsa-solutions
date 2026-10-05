/**
 * 3536. Maximum Product of Two Digits
 * Difficulty: Easy | Tags: Math, Sorting
 * https://leetcode.com/problems/maximum-product-of-two-digits/
 *
 * Pattern: Two-Pass Top-K Selection (Digit Scan)
 *
 * Key insight: Sweep the digits of n right to left while maintaining the
 * current maximum and the runner-up (second maximum); a digit at least as large
 * as max becomes the new max and demotes max to the runner-up.
 *
 * Time Complexity: O(log10 n) - One iteration per digit; at most 10 digits for
 * an int
 *
 * Space Complexity: O(1) - Only max and secondMax accumulators
 *
 * Edge Cases Handled: single-digit n (secondMax stays 0, product 0), repeated
 * equal digits (e.g., 999 -> 9 * 9), digit equal to the current max correctly
 * bumps the runner-up (uses <= to promote)
 */
class MaximumProductOfTwoDigits {
    public int maxProduct(int n) {
        int max = 0;
        int secondMax = 0;
        while (n != 0) {
            int digit = n % 10;
            if (max <= digit) {
                secondMax = max;
                max = digit;
            } else if (secondMax < digit && max != digit) {
                secondMax = digit;
            }
            n /= 10;
        }
        return max * secondMax;
    }
}
