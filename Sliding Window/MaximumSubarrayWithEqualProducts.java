/**
 * 3411. Maximum Subarray With Equal Products
 * Difficulty: Easy | Tags: Array, Math, Sliding Window, Enumeration, Number
 * Theory
 * https://leetcode.com/problems/maximum-subarray-with-equal-products/
 *
 * Pattern: All-Starts Enumeration with Incremental GCD/LCM (Early-Break
 * Pruning)
 *
 * Key insight: For a fixed start, product, GCD and LCM extend in O(1) per
 * appended element; once product > GCD * LCM the gap can never close because
 * GCD only decreases and LCM grows by at most the new factor, so the rest of
 * that suffix is pruned. Equality means each prime's exponent appears only at
 * the minimum and maximum level - e.g., pairwise-coprime values and 1s.
 *
 * Time Complexity: O(N^2 log V) - N starts each extend up to N ends (realized
 * by all-ones input); every step runs Euclid's GCD on values <= V, while the
 * break prunes invalid prefixes early.
 *
 * Space Complexity: O(1) - Only scalar accumulators and the iterative GCD
 * helper; no auxiliary data structures.
 *
 * Edge Cases Handled: Empty array and no valid window (returns 0), all ones
 * (full length qualifies), single-element windows qualifying only when the
 * value is 1, repeated values (e.g. 2,2,2 breaks on the third), and no
 * int-overflow guard for large or long windows.
 */
class MaximumSubarrayWithEqualProducts {
    public int maxLength(int[] nums) {
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            int pro = 1;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < nums.length; j++) {
                pro *= nums[j];
                gcd = helper(gcd, nums[j]);
                lcm = (lcm * nums[j]) / helper(lcm, nums[j]);

                if (pro == gcd * lcm) {
                    max = Math.max(max, j - i + 1);
                }

                if (pro > gcd * lcm) {
                    break;
                }
            }
        }

        return max;
    }

    private int helper(int t, int s) {

        while (t != 0) {
            int temp = t;
            t = s % t;
            s = temp;
        }

        return s;
    }
}
