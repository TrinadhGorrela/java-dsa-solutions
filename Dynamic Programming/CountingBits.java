/**
 * 338. Counting Bits
 * Difficulty: Easy | Tags: Dynamic Programming, Bit Manipulation
 * https://leetcode.com/problems/counting-bits/
 *
 * Pattern: Dynamic Programming (Parity Recurrence on Bits)
 *
 * Key insight: popcount(i) relates to popcount(i/2) because i >> 1 clears the
 * least significant bit - an even i has the same count as i/2, while an odd i
 * has one more, so each answer is derived from an already-computed smaller
 * index.
 *
 * Time Complexity: O(N) - single forward pass computing one entry per value
 * 0..N
 *
 * Space Complexity: O(N) - output array of size N + 1 (O(1) extra working
 * memory)
 *
 * Edge Cases Handled: n = 0 (explicit early return of [0]), n = 1 (dp[1] seeded
 * directly), even/odd branching covers all i >= 2; only safe for non-negative n
 * (negative n would throw NegativeArraySizeException on new int[n + 1])
 */
class CountingBits {
    public int[] countBits(int n) {
        if (n == 0) {
            return new int[] { 0 };
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i / 2] + 1;
            }
        }

        return dp;
    }
}
