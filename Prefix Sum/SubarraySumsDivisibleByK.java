/**
 * 974. Subarray Sums Divisible by K
 * Difficulty: Medium | Tags: Array, Hash Table, Prefix Sum
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 *
 * Pattern: Prefix Sum + Hash Map (Modulo Remainders)
 *
 * Key insight: A subarray sum is divisible by k exactly when its two prefix
 * sums share the same remainder modulo k, since their difference is then a
 * multiple of k. Normalizing each remainder with ((sum % k) + k) % k makes
 * negative sums safe, and seeding the map with remainder 0 (count 1) counts
 * subarrays that begin at index 0.
 *
 * Time Complexity: O(N) - Single pass over the array with O(1) expected hash
 * map operations per element.
 *
 * Space Complexity: O(min(N, k)) - The map holds at most one entry per distinct
 * remainder, and there are at most k remainders (and at most N).
 *
 * Edge Cases Handled: Negative numbers (remainders normalized into 0..k-1);
 * subarrays starting at index 0 via the seeded remainder 0; k = 1 (every
 * subarray qualifies); zero values; empty input returns 0; note k = 0 is NOT
 * handled and would throw a divide-by-zero.
 */
class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int prefixSum = 0;
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int need = ((prefixSum % k) + k) % k;

            if (map.containsKey(need)) {
                res += map.get(need);
            }

            map.put(need, map.getOrDefault(need, 0) + 1);
        }

        return res;
    }
}
