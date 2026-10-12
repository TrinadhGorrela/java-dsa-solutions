/**
 * 2897. Apply Operations on Array to Maximize Sum of Squares
 * Difficulty: Hard | Tags: Array, Hash Table, Greedy, Bit Manipulation
 * https://leetcode.com/problems/apply-operations-on-array-to-maximize-sum-of-sq
 * uares/
 *
 * Pattern: Greedy + Bit Manipulation (Bit Redistribution)
 *
 * Key insight: Replacing (a, b) with (a & b, a | b) only sorts their bits at
 * every position, so the total number of set bits per bit position across the
 * whole array is invariant. Since bits can therefore be freely relocated, the
 * sum of squares is maximized by packing the highest bits together into the k
 * largest numbers; greedily give each of the k numbers one bit from every
 * position that still has bits remaining.
 *
 * Time Complexity: O(32 * (N + K)) = O(N + K) - One pass counts set bits per
 * position over all numbers, then k passes each build one number from the 32
 * positions.
 *
 * Space Complexity: O(1) - A fixed 32-element bit-count array independent of
 * input size.
 *
 * Edge Cases Handled: k = 0 (returns 0), single element, large intermediate
 * squares (accumulated in long and reduced modulo 1e9+7 to prevent overflow),
 * and values within the 1e9 constraint (bit 31 stays unused); only safe for
 * non-negative values below 2^30, since 1 << 31 would overflow int.
 */
class ApplyOperationsOnArrayToMaximizeSumOfSquares {
    public int maxSum(List<Integer> nums, int k) {
        int[] counts = new int[32];
        int mod = 1_000_000_007;
        int n = 1;

        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                if ((num & n) == 1) {
                    counts[bit]++;
                }
                num >>= 1;
            }
        }
        long total = 0;

        for (int i = 0; i < k; i++) {
            long temp = 0;

            for (int j = 0; j < 32; j++) {
                if (counts[j] > 0) {
                    temp += 1 << j;
                    counts[j]--;
                }
            }

            total += (temp * temp) % mod;
            total %= mod;
        }

        return (int) total;
    }
}
