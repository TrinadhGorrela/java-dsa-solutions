/**
 * 3886. Sum of Sortable Integers
 * Difficulty: Hard | Tags: Array, Math, Sorting, Enumeration
 * https://leetcode.com/problems/sum-of-sortable-integers/
 *
 * Pattern: Divisor Enumeration + Greedy Block Validation (Prefix-Max Chaining)
 *
 * Key insight: Sorting contiguous blocks of size k can only yield a globally
 * non-decreasing array if k divides n and every block is a rotation of a sorted
 * run (at most one adjacent descent, and with exactly one descent the block
 * must
 * wrap: first >= last) while each block's max stays <= the next block's min;
 * validating blocks left-to-right lets an invalid k be rejected as soon as the
 * chain breaks.
 *
 * Time Complexity: O(N^2) - Tries every k in 1..N and, for each candidate,
 * sweeps all N elements once to check block validity; no sub-linear pruning on
 * k.
 *
 * Space Complexity: O(1) - Uses only a handful of scalar variables (counters
 * and per-block min/max sentinels); no auxiliary structures scale with input
 * size.
 *
 * Edge Cases Handled: empty input (n = 0 skips all loops, returns 0), single
 * element (k = 1 trivially valid, returns 1), k = 1 always tested first,
 * all-same or already-sorted input (zero descents, every divisor of n
 * accepted), strictly descending input, duplicate elements inside a block, and
 * elements at Integer.MIN_VALUE / Integer.MAX_VALUE (min/max sentinels are
 * re-initialized per block, so extremes never leak across blocks)
 */
class SumOfSortableIntegers {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int res = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int prevMax = Integer.MIN_VALUE;

            for (int i = 0; i < n; i += k) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int count = 0;

                for (int j = i; j < i + k; j++) {
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);

                    if (j > i && nums[j - 1] > nums[j]) {
                        count++;
                    }
                }

                if (count > 1 || (count == 1 && nums[i + k - 1] > nums[i]) || prevMax > min) {
                    valid = false;
                    break;
                }

                prevMax = max;
            }

            if (valid) {
                res += k;
            }

        }

        return res;
    }
}
