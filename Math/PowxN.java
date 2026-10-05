/**
 * 50. Pow(x, n)
 * Difficulty: Medium | Tags: Math, Recursion
 * https://leetcode.com/problems/powx-n/
 *
 * Pattern: Binary Exponentiation by Squaring
 *
 * Key insight: Repeatedly square the base while halving the exponent,
 * multiplying the accumulator only for set bits of |N|; using long for the
 * exponent avoids the negation overflow of Integer.MIN_VALUE, and negative
 * exponents are handled by inverting the base.
 *
 * Time Complexity: O(log |n|) - Halving the exponent each iteration
 *
 * Space Complexity: O(1) - Only res, x, and the long N variable
 *
 * Edge Cases Handled: n = 0 (returns 1 without loop), negative n (base inverted
 * via 1/x), Integer.MIN_VALUE (N as long so -N is valid), odd vs even bits of N
 * handled by the % 2 fold
 */
class PowxN {
    public double myPow(double x, int n) {
        double res = 1;
        long N = n;

        if (n < 0) {
            x = 1 / x;
            N = -N;
        }
        while (N > 0) {
            if (N % 2 == 1) {
                res = res * x;
            }
            x = x * x;
            N = N / 2;
        }
        return res;
    }
}
