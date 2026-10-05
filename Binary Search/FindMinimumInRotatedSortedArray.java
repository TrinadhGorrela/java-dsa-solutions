/**
 * 153. Find Minimum in Rotated Sorted Array
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * Pattern: Binary Search (Rotated Array)
 *
 * Key insight: The minimum is located at the rotation point, the only position
 * where a value dips below its predecessor. Comparing the midpoint against the
 * left boundary reveals which side the pivot
 * lies on: because the array is sorted apart from one rotation, the unsorted
 * (smaller-value) half always contains the minimum.
 *
 * Time Complexity: O(log N) - Each iteration halves the search space via the
 * monotonic boundary comparison.
 *
 * Space Complexity: O(1) - Only constant extra space is used.
 *
 * Edge Cases Handled: array rotated -K times (minimum equals the pivot
 * element); already sorted array with no rotation (leftmost element is the
 * min); single-element array; result initialized to Integer.MAX_VALUE covers
 * all valid rotated inputs of length >= 1.
 */
class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int res = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[left] <= nums[mid]) {
                res = Math.min(res, nums[left]);
                left = mid + 1;
            } else {
                res = Math.min(res, nums[mid]);
                right = mid - 1;
            }
        }

        return res;
    }
}
