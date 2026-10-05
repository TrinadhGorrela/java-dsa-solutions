/**
 * 350. Intersection of Two Arrays II
 * Difficulty: Easy | Tags: Array, Hash Table, Two Pointers, Binary Search,
 * Sorting
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 *
 * Pattern: Frequency Map with Countdown
 *
 * Key insight: A frequency map of nums1 tracks how many times each element is
 * available; as nums2 is scanned, matching elements are emitted and their count
 * decremented, naturally respecting multiplicity.
 *
 * Time Complexity: O(N + M) - Build frequency map in O(N), scan nums2 in O(M)
 * with O(1) lookups
 *
 * Space Complexity: O(N) - HashMap holds up to N distinct entries from nums1
 *
 * Edge Cases Handled: no common elements (empty result), one element appears
 * more times in nums2 than nums1 (capped by frequency), both arrays empty
 */
class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        for (int i : nums1) {
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1);
        }
        for (int i : nums2) {
            if (frequencyMap.containsKey(i) && frequencyMap.get(i) > 0) {
                resultList.add(i);
                frequencyMap.put(i, frequencyMap.get(i) - 1);
            }
        }
        int[] result = new int[resultList.size()];
        int index = 0;
        for (int val : resultList) {
            result[index] = val;
            index++;
        }
        return result;
    }
}
