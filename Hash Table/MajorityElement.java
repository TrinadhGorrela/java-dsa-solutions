/**
 * 169. Majority Element
 * Difficulty: Easy | Tags: Array, Hash Table, Divide and Conquer, Sorting,
 * Counting, Boyer-Moore Majority Vote Algorithm
 * https://leetcode.com/problems/majority-element/
 *
 * Pattern: Frequency Counting with HashMap
 *
 * Key insight: Build a frequency map in one pass, then find the entry with the
 * highest count — guaranteed to be > n/2 by the problem contract, so no
 * tie-breaking logic is needed.
 *
 * Time Complexity: O(N) - One pass to build the map, one pass over at most N
 * distinct entries
 *
 * Space Complexity: O(N) - HashMap stores one entry per distinct element
 *
 * Edge Cases Handled: single element (returned immediately), all elements
 * identical, negative values, array of length 2 with a clear majority
 */
class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int maxCount = 0;
        int majorityElement = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                majorityElement = entry.getKey();
            }
        }

        return majorityElement;
    }
}
