/**
 * 2206. Divide Array Into Equal Pairs
 * Difficulty: Easy | Tags: Array, Hash Table, Bit Manipulation, Counting
 * https://leetcode.com/problems/divide-array-into-equal-pairs/
 *
 * Pattern: Frequency Map Parity Check
 *
 * Key insight: Pairing is possible if and only if every distinct value appears
 * an even number of times. A HashMap counts frequencies; a single value with an
 * odd count immediately makes pairing impossible.
 *
 * Time Complexity: O(N) - One pass to populate the frequency map, one pass over
 * distinct values to check parity
 *
 * Space Complexity: O(N) - HashMap stores one entry per distinct value
 *
 * Edge Cases Handled: odd-length array (early return false), all values
 * distinct with odd counts (returns false), all values identical with even
 * count (returns true), exactly two distinct values both with even counts
 */
class DivideArrayIntoEqualPairs {
    public boolean divideArray(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        if (nums.length % 2 != 0) {
            return false;
        }
        for (int i : nums) {
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1);
        }
        for (int count : frequencyMap.values()) {
            if (count % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}
