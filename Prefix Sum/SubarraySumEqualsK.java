/**
 * 560. Subarray Sum Equals K
 * Difficulty: Medium | Tags: Array, Hash Table, Prefix Sum
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * Pattern: Prefix Sum + HashMap Lookup
 *
 * Key insight: If prefixSum[j] == prefixSum[i] - k, then the subarray (j, i]
 * sums to k — seeding the map with {0: 1} handles subarrays starting at index
 * 0, and duplicate prefix sums are counted via frequency.
 *
 * Time Complexity: O(N) - Single pass accumulating prefix sum and querying the
 * map
 *
 * Space Complexity: O(N) - HashMap stores up to N+1 distinct prefix sum
 * frequencies
 *
 * Edge Cases Handled: k = 0 (subarrays summing to 0 via repeated prefix sums),
 * negative numbers (prefix sums can repeat), entire array is the answer, single
 * element equal to k
 */
class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefixSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int need = prefixSum - k;
            if (map.containsKey(need)) {
                res += map.get(need);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }
}
