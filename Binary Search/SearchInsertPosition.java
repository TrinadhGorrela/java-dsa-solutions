/**
 * 35. Search Insert Position
 * Difficulty: Easy | Tags: Array, Binary Search
 * https://leetcode.com/problems/search-insert-position/
 *
 * Pattern: Binary Search (Lower Bound)
 *
 * Key insight: Standard binary search on [low, high]; if mid == target return
 * mid, otherwise narrow. When the loop ends, low is the first index where
 * nums[low] >= target—the correct insertion point.
 *
 * Time Complexity: O(log n) - Interval halves each step.
 *
 * Space Complexity: O(1) - Three integers: low, high, mid.
 *
 * Edge Cases Handled: target present (returns its index), target smaller than
 * all elements (returns 0), target larger than all (returns n), single-element
 * array
 */
class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
