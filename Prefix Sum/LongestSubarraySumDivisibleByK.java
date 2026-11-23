/**
 * 702926. Longest Subarray Sum Divisible by K
 * Difficulty: Medium | Tags: Prefix Sum, Arrays, Hash
 * https://www.geeksforgeeks.org/problems/longest-subarray-with-sum-divisible-by
 * -k1259/1
 *
 * Pattern: Prefix Sum + Remainder Hash Map (First-Occurrence Indexing)
 *
 * Key insight: The sum of arr[i+1..j] is divisible by k exactly when the prefix
 * sums at i and j share a remainder modulo k, so storing the earliest index of
 * each remainder maximizes j-i. Normalizing with ((r % k) + k) % k makes
 * negative prefix sums comparable, and seeding remainder 0 at index -1 allows
 * matches that start at index 0.
 *
 * Time Complexity: O(N) - One pass over the array with O(1) map lookups
 *
 * Space Complexity: O(min(N, |k|)) - The map stores only the first index of
 * each distinct normalized remainder, at most N+1 entries
 *
 * Edge Cases Handled: empty array (returns 0), single element, match starting
 * at index 0 (seeded 0 -> -1), negative numbers (normalized remainders), no
 * divisible subarray (returns 0), assumes k != 0 (modulo by zero throws),
 * prefix sums must stay within int range (no long widening)
 */
class LongestSubarraySumDivisibleByK {
    public int longestSubarrayDivK(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int prefixSum = 0;
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            int need = ((prefixSum % k) + k) % k;

            if (map.containsKey(need)) {
                res = Math.max(res, i - map.get(need));
            } else {
                map.put(need, i);
            }
        }

        return res;
    }
}
