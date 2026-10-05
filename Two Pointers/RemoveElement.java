/**
 * 27. Remove Element
 * Difficulty: Easy | Tags: Array, Two Pointers
 * https://leetcode.com/problems/remove-element/
 *
 * Pattern: Fast/Slow Write-Pointer Compression
 *
 * Key insight: A writeIndex tracks where the next non-val element goes; the
 * read pointer sweeps once, copying qualifying elements forward—no swap needed
 * since we only care about the prefix's content.
 *
 * Time Complexity: O(N) - Single pass; every element compared exactly once
 *
 * Space Complexity: O(1) - Two index variables, in-place modification of input
 * array
 *
 * Edge Cases Handled: All elements equal val (writeIndex stays 0), val absent
 * (all copied in place, no extra work), empty array, single element matching or
 * not matching val
 */
class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int writeIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[writeIndex] = nums[i];
                writeIndex++;
            }
        }
        return writeIndex;
    }
}
