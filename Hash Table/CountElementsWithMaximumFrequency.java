/**
 * 3005. Count Elements With Maximum Frequency
 * Difficulty: Easy | Tags: Array, Hash Table, Counting
 * https://leetcode.com/problems/count-elements-with-maximum-frequency/
 *
 * Pattern: Two-Pass Frequency Counting
 *
 * Key insight: First pass builds a frequency map while tracking the running max
 * frequency; second pass counts how many array positions belong to elements at
 * that max frequency (counting per occurrence, not per distinct value).
 *
 * Time Complexity: O(N) - Two linear passes: one to build the frequency map,
 * one to count qualifying elements
 *
 * Space Complexity: O(N) - HashMap stores one entry per distinct element
 *
 * Edge Cases Handled: single element, all distinct (max freq = 1, answer = N),
 * all equal (max freq = N, answer = N), multiple distinct values tied at max
 * frequency
 */
class CountElementsWithMaximumFrequency {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxFreq = 0, count = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if (map.get(i) > maxFreq)
                maxFreq = map.get(i);
        }
        for (int i : nums) {
            if (map.get(i) == maxFreq)
                count = count + 1;
        }
        return count;
    }
}
