/**
 * 3918. Sum of Primes Between Number and Its Reverse
 * Difficulty: Medium | Tags: Math, Number Theory
 * https://leetcode.com/problems/sum-of-primes-between-number-and-its-reverse/
 *
 * Pattern: Digit Reversal + Trial Division Prime Summation
 *
 * Key insight: Reverse n's digits to form the range endpoints, then iterate
 * from min(n, rev) to max(n, rev) accumulating primes found via trial division
 * up to sqrt(i).
 *
 * Time Complexity: O(R * sqrt(M)) - R = |n - rev| range width, M = max value in
 * range, trial division up to sqrt(M) per candidate
 *
 * Space Complexity: O(1) - Only int variables for rev, st, end, res, and the
 * loop counter
 *
 * Edge Cases Handled: n = 0 or 1 (not prime, contribute nothing), n = 2 or 3
 * (prime, included), n with trailing zeros (rev < n, range flips via min/max),
 * single-element range where n equals its reverse
 */
class SumOfPrimesBetweenNumberAndItsReverse {
    public int sumOfPrimesInRange(int n) {
        int remaining = n;
        int rev = 0;
        while (remaining != 0) {
            int last = remaining % 10;
            rev = rev * 10 + last;
            remaining /= 10;
        }

        int st = Math.min(n, rev);
        int end = Math.max(n, rev);
        int res = 0;

        for (int i = st; i <= end; i++) {
            if (isPrime(i)) {
                res += i;
            }
        }
        return res;
    }

    public static boolean isPrime(int n) {

        if (n == 0 || n == 1) {
            return false;
        }

        if (n == 2 || n == 3) {
            return true;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
