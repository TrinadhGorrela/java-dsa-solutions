/**
 * 217. Contains Duplicate
 * Difficulty: Easy | Tags: Array, Hash Table, Sorting
 * https://leetcode.com/problems/contains-duplicate/
 *
 * Pattern: HashSet Presence Check
 *
 * Key insight: A HashSet insertion returns false (or contains returns true) the
 * instant a duplicate is encountered, enabling early termination without a full
 * scan.
 *
 * Time Complexity: O(N) - Single pass with O(1) average HashSet contains/add
 *
 * Space Complexity: O(N) - HashSet stores each unique element until a duplicate
 * is found
 *
 * Edge Cases Handled: single element array (always false), all identical
 * elements (returns true on second element), no duplicates (exhausts entire
 * array), negative and zero values
 */
class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
