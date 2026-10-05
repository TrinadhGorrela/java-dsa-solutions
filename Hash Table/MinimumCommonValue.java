/**
 * 2540. Minimum Common Value
 * Difficulty: Easy | Tags: Array, Hash Table, Two Pointers, Binary Search
 * https://leetcode.com/problems/minimum-common-value/
 *
 * Pattern: Hash Set Lookup
 *
 * Key insight: Insert all nums1 elements into a HashSet, then scan nums2 for
 * the smallest value present in the set. The set makes membership O(1), and the
 * linear scan naturally finds the minimum without sorting.
 *
 * Time Complexity: O(N + M) - One pass to populate the set from nums1, one pass
 * over nums2 to find the minimum common value
 *
 * Space Complexity: O(N) - HashSet stores all distinct elements of nums1
 *
 * Edge Cases Handled: no common element (returns -1), duplicates in both arrays
 * (deduplicated by set), common value at index 0, single-element arrays
 */
class MinimumCommonValue {
     public int getCommon(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        for (int i : nums2) {
            if (set.contains(i)) {
                return i;
            }
        }
        
        return -1;
    }
}
