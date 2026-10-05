/**
 * 26. Remove Duplicates from Sorted Array
 * Difficulty: Easy | Tags: Array, Two Pointers
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * Pattern: Sorted Dedup via Write Pointer
 *
 * Key insight: The sorted guarantee means a new unique value always differs
 * from nums[i-1]; writeIndex advances only on such transitions, packing unique
 * values contiguously at the front.
 *
 * Time Complexity: O(N) - One pass from index 1; each element compared once to
 * its predecessor
 *
 * Space Complexity: O(1) - One read and one write pointer, in-place on input
 * array
 *
 * Edge Cases Handled: All identical elements (result length 1), already fully
 * unique (writeIndex reaches N), single-element array, consecutive runs of the
 * same value
 */
class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int writeIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[writeIndex] = nums[i];
                writeIndex++;
            }
        }
        return writeIndex;
    }
}
