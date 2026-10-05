/**
 * 1658. Minimum Operations to Reduce X to Zero
 * Difficulty: Medium | Tags: Array, Hash Table, Binary Search, Sliding Window,
 * Prefix Sum
 * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 *
 * Pattern: Prefix Sum + Suffix Sum with HashMap Lookup (Reduce Ends to Longest
 * Middle Subarray)
 *
 * Key insight: Removing elements from both ends is equivalent to keeping a
 * contiguous middle subarray, so minimizing operations means maximizing the
 * kept segment whose sum is total - x. Precomputing every prefix sum in a
 * HashMap lets each suffix length be checked
 * in O(1): for a suffix of length i summing to s, we need a prefix summing to x
 * - s.
 *
 * Time Complexity: O(N) - One pass builds the prefix/suffix sums and the map,
 * then one pass checks every suffix length against the map; both passes are
 * linear.
 *
 * Space Complexity: O(N) - Uses a HashMap of prefix sums plus prefix/suffix
 * arrays that scale with input size.
 *
 * Edge Cases Handled: x == 0 (returns 0, no operations needed) - x equals the
 * total sum, requiring the entire array to be removed (returns n + 1) - total
 * sum < x, impossible to reach target (returns -1) - single-element array -
 * empty array - no prefix/suffix pair sums to x, sentinel min stays MAX_VALUE
 * (returns -1). Note: null input is not handled (would throw NPE) and the
 * approach assumes the LeetCode constraint of positive nums[i], relying on
 * prefix/suffix monotonicity.
 */
class MinimumOperationsToReduceXToZero {
    public int minOperations(int[] nums, int x) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] prefix = new int[nums.length + 1];
        int[] suffix = new int[nums.length + 1];
        int n = nums.length - 1;
        map.put(0, 0);

        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
            suffix[i + 1] = suffix[i] + nums[n - i];

            map.put(prefix[i + 1], i + 1);
        }

        if (prefix[n + 1] < x) {
            return -1;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < suffix.length; i++) {
            int need = x - suffix[i];
            if (map.containsKey(need)) {
                min = Math.min(min, i + map.get(need));
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
