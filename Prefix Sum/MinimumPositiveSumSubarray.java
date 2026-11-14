/**
 * 3364. Minimum Positive Sum Subarray
 * Difficulty: Easy | Tags: Array, Sliding Window, Prefix Sum
 * https://leetcode.com/problems/minimum-positive-sum-subarray/
 *
 * Pattern: Prefix Sum + Brute Force Window Enumeration
 *
 * Key insight: A prefix-sum array lets any subarray sum be computed in O(1) as
 * prefix[j] - prefix[j - i], so every window length in [l, r] can be checked by
 * enumerating start/end indices; only strictly positive sums update the
 * minimum.
 *
 * Time Complexity: O(N * (R - L + 1)) - Builds the prefix array in O(N), then
 * scans every window length from l to r across all end positions, worst case
 * O(N^2) when r is O(N).
 *
 * Space Complexity: O(N) - Allocates a prefix-sum array of size nums.size() +
 * 1.
 *
 * Edge Cases Handled: no positive-sum subarray exists in any allowed length
 * (returns -1), single-element windows when l == 1, negative and zero values
 * via the sum > 0 filter, windows shorter than l or longer than r are skipped,
 * l == r (only one window length); noted limitation: int prefix sums can
 * overflow for inputs whose cumulative sums exceed Integer.MAX_VALUE
 */
class MinimumPositiveSumSubarray {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int[] prefix = new int[nums.size() + 1];
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums.get(i - 1);
        }

        for (int i = l; i <= r; i++) {
            for (int j = i; j < prefix.length; j++) {
                int sum = prefix[j] - prefix[j - i];

                if (sum > 0) {
                    min = Math.min(min, sum);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
