/**
 * 904. Fruit Into Baskets
 * Difficulty: Medium | Tags: Array, Hash Table, Sliding Window
 * https://leetcode.com/problems/fruit-into-baskets/
 *
 * Pattern: Variable Sliding Window with At Most 2 Distinct Keys
 *
 * Key insight: Expand right, adding each fruit type to a frequency map. When
 * the map has more than 2 distinct types, shrink from the left, decrementing
 * counts and removing keys that drop to 0. The map never exceeds 3 entries
 * temporarily (the third triggers the shrink), so all operations are
 * effectively O(1).
 *
 * Time Complexity: O(N) - Right advances once per step; left advances at most N
 * times total; map operations are O(1)
 *
 * Space Complexity: O(1) - HashMap holds at most 3 entries (two valid + one
 * triggering removal)
 *
 * Edge Cases Handled: single element (result 1), all same type (entire array),
 * exactly two types (entire array), alternating types with a third type
 * inserted mid-sequence
 */
class FruitIntoBaskets {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int right = 0; right < fruits.length; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
