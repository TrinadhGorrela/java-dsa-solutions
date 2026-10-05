/**
 * 2413. Smallest Even Multiple
 * Difficulty: Easy | Tags: Math, Number Theory
 * https://leetcode.com/problems/smallest-even-multiple/
 *
 * Pattern: Parity Check (LCM with 2)
 *
 * Key insight: The smallest even multiple of n is n itself when n is even, or
 * 2n when n is odd — equivalent to lcm(n, 2) without explicitly computing it.
 *
 * Time Complexity: O(1) - Single modulo check and possibly one multiplication
 *
 * Space Complexity: O(1) - No auxiliary data structures
 *
 * Edge Cases Handled: n = 1 (odd, returns 2), n already even (returned
 * unchanged), large odd n (doubled without overflow since result fits int)
 */
class SmallestEvenMultiple {
    public int smallestEvenMultiple(int n) {
        if (n % 2 != 0) {
            return n*2;
        }
        return n;
    }
}
