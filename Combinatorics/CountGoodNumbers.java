/**
 * 1922. Count Good Numbers
 * Difficulty: Medium | Tags: Math, Recursion
 * https://leetcode.com/problems/count-good-numbers/
 *
 * Pattern: Modular Exponentiation (Binary Fast Power) + Combinatorics
 *
 * Key insight: Positions are independent, with 5 valid even digits at each even
 * index and 4 prime digits at each odd index, so the answer is 5^ceil(n/2) *
 * 4^floor(n/2) mod 1e9+7; binary exponentiation gets each power in O(log n) by
 * squaring the base and halving the exponent.
 *
 * Time Complexity: O(log N) - Two fast-power calls, each halving the exponent
 * per iteration.
 *
 * Space Complexity: O(1) - Iterative exponentiation stores only a few long
 * variables.
 *
 * Edge Cases Handled: n = 1 and other odd lengths (the half * 2 == n check
 * gives even positions the extra one), exponent 0 (loop skipped, returns 1),
 * very large n (exponent held as long, no overflow), products reduced modulo
 * 1e9+7 to stay within long range.
 */
class CountGoodNumbers {
    int mod = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long evenPos = 0;
        long oddPos = 0;

        long half = n / 2;

        if (half * 2 == n) {
            evenPos = half;
            oddPos = half;
        } else {
            evenPos = half + 1;
            oddPos = half;
        }

        long res = (helper(5, evenPos) * helper(4, oddPos)) % mod;

        return (int) res;
    }

    private long helper(long base, long exp) {
        long res = 1;

        while (exp > 0) {
            if (exp % 2 != 0) {
                res = (res * base) % mod;
            }

            base = ((base * base) % mod);
            exp /= 2;
        }

        return res;
    }
}
