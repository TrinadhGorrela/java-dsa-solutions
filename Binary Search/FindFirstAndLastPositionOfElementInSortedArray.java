/**
 * 34. Find First and Last Position of Element in Sorted Array
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sort
 * ed-array/
 *
 * Pattern: Dual Binary Search (Lower/Upper Bound)
 *
 * Key insight: A single helper runs standard binary search but takes an
 * `isLeft` flag: on a match, record the index and continue searching left (for
 * first) or right (for last) instead of returning immediately.
 *
 * Time Complexity: O(log n) - Two independent binary searches, each halving the
 * range.
 *
 * Space Complexity: O(1) - Only pointer variables and one intermediate index.
 *
 * Edge Cases Handled: target absent (both return -1), single element array,
 * first == last when target appears exactly once, target at array boundaries
 */
class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        return new int[] { findIndex(nums, target, true), findIndex(nums, target, false) };
    }

    private static int findIndex(int[] nums, int target, boolean isLeft) {
        int left = 0;
        int right = nums.length - 1;
        int foundIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                foundIndex = mid;
                if (isLeft) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return foundIndex;
    }
}
