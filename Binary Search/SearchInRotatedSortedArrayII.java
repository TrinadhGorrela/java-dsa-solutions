/**
 * 81. Search in Rotated Sorted Array II
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *
 * Pattern: Binary Search (Rotated Array with Duplicates)
 *
 * Key insight: One half of a rotated array is always sorted, so binary search
 * can decide which half to descend into. Duplicates break the clean
 * mid-boundary comparison, so when all three of left, mid, and right are equal,
 * shrink both endpoints by one to break the tie and continue; correctness holds
 * because the target, if present, stays inside the remaining range.
 *
 * Time Complexity: O(log N) on average, but O(N) worst case - The shrink-by-one
 * tie-breaking can degenerate to linear scans when many duplicates are present
 * (the reason rotated-sorted properties degrade).
 *
 * Space Complexity: O(1) - Only constant extra space is used beyond the input
 * array.
 *
 * Edge Cases Handled: duplicates where all three of left/mid/right collide
 * (handled by shrinking both ends); target at either extreme of the sorted or
 * rotated range; single-element array; target not present returns false.
 */
class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
                continue;
            }

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }
}
