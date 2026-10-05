/**
 * 3718. Smallest Missing Multiple of K
 * Difficulty: Easy | Tags: Array, Hash Table
 * https://leetcode.com/problems/smallest-missing-multiple-of-k/
 *
 * Pattern: Hash Set + Arithmetic Progression Scan
 *
 * Key insight: Store all array values in a HashSet, then walk through k, 2k,
 * 3k, ... until a multiple is absent from the set. The while-loop terminates
 * because the set is finite.
 *
 * Time Complexity: O(N + max/k) - O(N) to build the set, then the while-loop
 * checks at most max_value/k multiples
 *
 * Space Complexity: O(N) - HashSet stores all array elements for O(1)
 * membership queries
 *
 * Edge Cases Handled: k itself missing from array (returns k immediately),
 * consecutive multiples all present, duplicates in array (deduplicated by set)
 */
class SmallestMissingMultipleOfK {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int i : nums) {
            set.add(i);
        }

        int res = k;

        while (set.contains(res)) {
            res += k;
        }
        return res;
    }
}
