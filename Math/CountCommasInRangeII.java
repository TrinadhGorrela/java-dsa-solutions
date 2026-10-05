/**
 * 3871. Count Commas in Range II
 * Difficulty: Medium | Tags: Math
 * https://leetcode.com/problems/count-commas-in-range-ii/
 *
 * Pattern: Cumulative Sum over Powers-of-1000 Thresholds (Math)
 *
 * Key insight: A number n has k commas iff it has k+1 digit groups of three.
 * For each threshold t = 1000, 10^6, 10^9, …, every value in [t, n] contributes
 * one additional comma, so we accumulate (n - t + 1) per qualifying tier and
 * multiply the threshold by 1000 for the next tier.
 *
 * Time Complexity: O(log₁₀₀₀ n) - Loop iterates once per comma tier (at most ~7
 * iterations for 64-bit longs)
 *
 * Space Complexity: O(1) - Only two scalar variables (start and res)
 *
 * Edge Cases Handled: n < 1000 (returns 0, no commas); n = 1000 (single
 * iteration, result = 1); very large n near Long.MAX_VALUE (all tiers visited
 * until threshold overflows past n)
 */
class CountCommasInRangeII {
    public long countCommas(long n) {
        long start = 1000;
        long res = 0;

        while (n >= start) {
            res += n - start + 1;
            start *= 1000;
        }

        return res;
    }
}
