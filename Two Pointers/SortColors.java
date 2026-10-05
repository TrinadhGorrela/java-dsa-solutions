/**
 * 75. Sort Colors
 * Difficulty: Medium | Tags: Array, Two Pointers, Sorting, Quicksort, Bubble
 * Sort
 * https://leetcode.com/problems/sort-colors/
 *
 * Pattern: Dutch National Flag Three-Way Partition
 *
 * Key insight: Three pointers (left, mid, right) maintain the invariant
 * [0..left-1]=0, [left..mid-1]=1, [right+1..end]=2; swapping 0 past left and 2
 * past right lets mid advance through unprocessed elements in one pass.
 *
 * Time Complexity: O(N) - Single traversal; each element is swapped at most
 * once per boundary
 *
 * Space Complexity: O(1) - Three index variables and one temp swap variable,
 * in-place on input array
 *
 * Edge Cases Handled: Empty or single-element array, already sorted input, all
 * same color, only two of three colors present, consecutive runs of one color
 */
class SortColors {
    public void sortColors(int[] nums) {
        int left = 0;
        int mid = 0;
        int right = nums.length - 1;

        while (mid <= right) {
            if (nums[mid] == 0) {
                int temp = nums[mid];
                nums[mid] = nums[left];
                nums[left] = temp;
                left++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[mid];
                nums[mid] = nums[right];
                nums[right] = temp;
                right--;
            }
        }
    }
}
