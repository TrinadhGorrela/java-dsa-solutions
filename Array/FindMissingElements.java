/**
 * 3731. Find Missing Elements
 * Difficulty: Easy | Tags: Array, Hash Table, Sorting
 * https://leetcode.com/problems/find-missing-elements/
 *
 * Pattern: Sort + Two-Pointer Gap Walk
 *
 * Key insight: Sort the array, then walk a `low` pointer from the minimum to
 * the maximum while advancing through sorted values. Whenever `low` doesn't
 * match the current element, it's a gap — add it to the result and advance
 * `low`.
 *
 * Time Complexity: O(N log N) - Dominated by sorting; the subsequent scan is
 * O(N)
 *
 * Space Complexity: O(N) - ArrayList to collect missing values (sorting is
 * in-place for arrays)
 *
 * Edge Cases Handled: duplicates (skipped by the else-if branch), contiguous
 * range with no gaps (returns empty list), gaps at the start or end of the
 * range
 */
class FindMissingElements {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> missingList = new ArrayList<>();
        Arrays.sort(nums);
        int index = 0;
        int low = nums[0];
        int high = nums[nums.length - 1];

        while (index < nums.length) {
            if (nums[index] == low) {
                low++;
                index++;
            } else if (nums[index] < low) {
                index++;
            } else {
                missingList.add(low);
                low++;
            }
        }
        return missingList;
    }
}
