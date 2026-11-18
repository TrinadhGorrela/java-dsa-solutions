/**
 * 3185. Count Pairs That Form a Complete Day II
 * Difficulty: Medium | Tags: Array, Hash Table, Counting
 * https://leetcode.com/problems/count-pairs-that-form-a-complete-day-ii/
 *
 * Pattern: Hash Map Remainder Counting (Single Pass Complement Lookup)
 *
 * Key insight: Two hours sum to a multiple of 24 exactly when their remainders
 * modulo 24 are complementary (0 with 0, or r with 24 - r); counting previously
 * seen complements before inserting the current hour tallies every valid pair
 * exactly once.
 *
 * Time Complexity: O(N) - One pass over N hours with O(1) average-time map
 * lookup and update per element.
 *
 * Space Complexity: O(1) - The map stores at most 24 remainder keys (0..23),
 * independent of input size.
 *
 * Edge Cases Handled: Empty array and single element (return 0), exact
 * multiples of 24 (remainder 0 pairs with itself thanks to the need > 0 guard),
 * repeated remainders such as 12 + 12, all-same hours, inputs with no valid
 * pairs, and large inputs whose pair count exceeds int range (long
 * accumulator); assumes non-negative hours per the constraints.
 */
class CountPairsThatFormACompleteDayII {
    public long countCompleteDayPairs(int[] hours) {
        Map<Integer, Integer> map = new HashMap<>();
        long pairs = 0;

        for (int i = 0; i < hours.length; i++) {
            int need = hours[i] % 24;
            int target = need;

            if (need > 0) {
                target = 24 - need;
            }

            if (map.containsKey(target)) {
                pairs += map.get(target);
            }

            map.put(need, map.getOrDefault(need, 0) + 1);
        }

        return pairs;
    }
}
