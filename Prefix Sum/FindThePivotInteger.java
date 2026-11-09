/**
 * 2485. Find the Pivot Integer
 * Difficulty: Easy | Tags: Math, Prefix Sum
 * https://leetcode.com/problems/find-the-pivot-integer/
 *
 * Pattern: Prefix Sum (Running Sum vs. Shrinking Suffix)
 *
 * Key insight: Start with `total = n(n+1)/2`, then grow `rem` as the prefix sum
 * while subtracting each `i` from `total`; at step `i` the two values compare
 * `1..i` against `i..n`, so equality means the prefix and suffix excluding `i`
 * are balanced and `i` is the pivot.
 *
 * Time Complexity: O(N) - A single pass over 1..N with O(1) work per iteration.
 *
 * Space Complexity: O(1) - Only two integer accumulators are used regardless of
 * N.
 *
 * Edge Cases Handled: single element (n = 1 returns 1), no valid pivot (returns
 * -1), and pivots at either end of the range; only safe for small n where `n *
 * (n + 1)` stays within int range (fine for LeetCode's n <= 1000).
 */
class FindThePivotInteger {
    public int pivotInteger(int n) {
        int total = n * (n + 1) / 2;
        int rem = 0;

        for (int i = 1; i <= n; i++) {
            rem += i;

            if (rem == total) {
                return i;
            }

            total -= i;
        }

        return -1;
    }
}
