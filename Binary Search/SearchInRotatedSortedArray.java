/**
 * 33. Search in Rotated Sorted Array
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Pattern: Modified Binary Search on Rotated Array
 *
 * Key insight: At every mid, one half (left-to-mid or mid-to-right) is
 * guaranteed sorted; compare nums[left] ≤ nums[mid] to identify which half is
 * sorted, then check if the target falls within that sorted range to decide the
 * search direction.
 *
 * Time Complexity: O(log n) - Interval halves each step.
 *
 * Space Complexity: O(1) - Three pointers: left, right, mid.
 *
 * Edge Cases Handled: no rotation (array still sorted), target absent (returns
 * -1), single element, pivot at boundary
 */
class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
