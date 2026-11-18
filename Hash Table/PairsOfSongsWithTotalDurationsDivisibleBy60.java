/**
 * 1010. Pairs of Songs With Total Durations Divisible by 60
 * Difficulty: Medium | Tags: Array, Hash Table, Counting
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-b
 * y-60/
 *
 * Pattern: Hash Map Remainder Counting (Single Pass Complement Lookup)
 *
 * Key insight: Two durations sum to a multiple of 60 exactly when their
 * remainders modulo 60 are complementary (0 with 0, or r with 60 - r); counting
 * previously seen complements as each song arrives tallies every valid pair
 * exactly once.
 *
 * Time Complexity: O(N) - One pass over N songs with O(1) average-time map
 * lookup and update per song.
 *
 * Space Complexity: O(1) - The map stores at most 60 remainder keys (0..59),
 * regardless of input size.
 *
 * Edge Cases Handled: Empty array and single song (both return 0), durations
 * that are exact multiples of 60 (remainder 0 pairs with itself thanks to the
 * need > 0 guard), repeated remainders such as 30 + 30, all-same durations, and
 * inputs with no valid pairs; assumes non-negative durations per the
 * constraints since Java's % on negatives would break the complement logic.
 */
class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int pairs = 0;

        for (int i = 0; i < time.length; i++) {
            int need = time[i] % 60;
            int target = need;

            if (need > 0) {
                target = 60 - need;
            }

            if (map.containsKey(target)) {
                pairs += map.get(target);
            }

            map.put(need, map.getOrDefault(need, 0) + 1);
        }

        return pairs;
    }
}
