/**
 * 325. Maximum Size Subarray Sum Equals k
 * Difficulty: Medium | Tags: Array, Hash Table, Prefix Sum
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 *
 * Pattern: Prefix Sum + Hash Map (Earliest-Index Lookup)
 * Key insight: The sum of a subarray nums[j..i] is prefix[i] - prefix[j-1], so it equals k exactly
 *             when two running prefix sums differ by k. Storing the EARLIEST index at which each
 *             prefix sum occurred means that when prefix[i] - k has been seen, i - earliestIndex is
 *             the longest valid subarray ending at i -- and one linear scan covers every right endpoint.
 *
 * Time Complexity: O(N) - Single pass; each element triggers one O(1) average hash map lookup/store.
 * Space Complexity: O(N) - Up to one entry per distinct prefix sum is kept in the hash map.
 *
 * Edge Cases Handled: empty array (loop never runs, returns 0); no matching subarray returns 0;
 *                     subarray starting at index 0 (the `curr == k` branch, since the empty prefix
 *                     is never inserted into the map); duplicate/repeated prefix sums (only the
 *                     earliest index is kept, which maximizes length); k = 0 (the map is consulted
 *                     before insertion, so the current index is never matched against itself);
 *                     negative numbers and values that overflow int (prefix sums accumulate in a
 *                     long, so int sums cannot wrap); single-element arrays. A null array is not
 *                     guarded and would throw NullPointerException.
 */
class MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Long, Integer> map = new HashMap<>();
        long curr = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            curr += nums[i];

            if (curr == k) {
                max = i + 1;
            }

            long need = curr - k;

            if (map.containsKey(need)) {
                max = Math.max(max, i - map.get(need));
            }

            if (!map.containsKey(curr)) {
                map.put(curr, i);
            }

        }

        return max;
    }
}
