/**
 * 1748. Sum of Unique Elements
 * Difficulty: Easy | Tags: Array, Hash Table, Counting
 * https://leetcode.com/problems/sum-of-unique-elements/
 *
 * Pattern: Frequency Map + Selective Sum
 *
 * Key insight: A single HashMap pass establishes each element's frequency; a
 * second pass sums only values whose count equals 1, separating uniques from
 * duplicates in O(1) lookup per element.
 *
 * Time Complexity: O(N) - First pass builds the frequency map; second pass
 * filters and sums — both linear
 *
 * Space Complexity: O(N) - HashMap stores up to N distinct keys with their
 * integer counts
 *
 * Edge Cases Handled: all unique (returns total sum), all duplicates (returns
 * 0), single element (returns that element), negative values counted correctly
 * by HashMap
 */
class SumOfUniqueElements {
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 1) {
                res = res + nums[i];
            }
        }
        return res;
    }
}
