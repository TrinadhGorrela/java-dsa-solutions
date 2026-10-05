/**
 * 3345. Smallest Divisible Digit Product I
 * Difficulty: Easy | Tags: Math, Enumeration
 * https://leetcode.com/problems/smallest-divisible-digit-product-i/
 *
 * Pattern: Linear Scan with Digit Product Check
 *
 * Key insight: Starting from n, increment one at a time, computing the product
 * of each candidate's digits via repeated mod-10 extraction; return the first n
 * where digitProduct % t == 0.
 *
 * Time Complexity: O(k * d) - k = number of increments until a match, d =
 * average digit count per candidate (at most 4 since n <= 100)
 *
 * Space Complexity: O(1) - Only temp variables for the digit product and loop
 * state
 *
 * Edge Cases Handled: any candidate containing a 0 digit (product = 0,
 * divisible by any t), n itself already satisfying divisibility (zero
 * increments), t = 1 (always satisfied on first check)
 */
class SmallestDivisibleDigitProductI {
    public int smallestNumber(int n, int t) {
        while (true) {
            int digitProduct = multiply(n);
            if (digitProduct % t == 0) {
                return n;
            }
            n++;
        }
    }

    public static int multiply(int n) {
        int res = 1;
        while (n != 0) {
            int last = n % 10;
            res *= last;
            n /= 10;
        }
        return res;
    }
}
