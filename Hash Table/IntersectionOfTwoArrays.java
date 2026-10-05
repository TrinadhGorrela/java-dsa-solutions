/**
 * 349. Intersection of Two Arrays
 * Difficulty: Easy | Tags: Array, Hash Table, Two Pointers, Binary Search,
 * Sorting
 * https://leetcode.com/problems/intersection-of-two-arrays/
 *
 * Pattern: Dual HashSet Intersection
 *
 * Key insight: Building a set from nums2 and probing it while iterating nums1
 * gives O(1) membership checks; the result set handles deduplication
 * automatically.
 *
 * Time Complexity: O(N + M) - Build set from nums2 in O(M), probe each nums1
 * element in O(1)
 *
 * Space Complexity: O(N + M) - Two sets: one for nums2 lookup, one for unique
 * results
 *
 * Edge Cases Handled: no intersection (returns empty array), duplicates within
 * one input (deduplicated by result set), one or both arrays empty
 */
class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums2Set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int i : nums2) {
            nums2Set.add(i);
        }
        int length = nums1.length;
        for (int i = 0; i < length; i++) {
            if (nums2Set.contains(nums1[i])) {
                result.add(nums1[i]);
            }
        }
        int[] resultArray = new int[result.size()];
        int index = 0;
        for (Integer key : result) {
            resultArray[index] = key;
            index++;
        }
        return resultArray;
    }
}
