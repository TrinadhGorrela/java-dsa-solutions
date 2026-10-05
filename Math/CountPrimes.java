/**
 * 204. Count Primes
 * Difficulty: Medium | Tags: Array, Math, Enumeration, Number Theory, Primality
 * Test, Sieve Theory, Prime Number Sieve
 * https://leetcode.com/problems/count-primes/
 *
 * Pattern: Sieve of Eratosthenes
 *
 * Key insight: Instead of testing each number for primality, mark all multiples
 * of each discovered prime as composite — the unmarked entries below n are the
 * primes, counted in a final scan.
 *
 * Time Complexity: O(N log log N) - Harmonic-series sum of N/p for each prime p
 * <= sqrt(N)
 *
 * Space Complexity: O(N) - Boolean array of size n to track composite flags
 *
 * Edge Cases Handled: n <= 1 (returns 0 immediately), n = 2 (no primes below
 * 2), n = 3 (only 2 is prime), large n (sieve scales efficiently)
 */
class CountPrimes {
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        boolean[] isComposite = new boolean[n];

        for (int i = 2; i < isComposite.length; i++) {
            if (!isComposite[i]) {
                for (int j = i * 2; j < isComposite.length; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < isComposite.length; i++) {
            if (!isComposite[i]) {
                count++;
            }
        }
        return count;
    }
}
