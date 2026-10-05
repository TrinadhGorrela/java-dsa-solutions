/**
 * 704. Binary Search
 * Difficulty: Easy | Tags: Array, Binary Search
 * https://leetcode.com/problems/binary-search/
 *
 * Pattern: Classical Binary Search
 *
 * Key insight: Maintain [start, end] inclusive; at each step, compare nums[mid]
 * to target and discard the half that cannot contain it, halving the search
 * space every iteration.
 *
 * Time Complexity: O(log n) - Search space halves each step.
 *
 * Space Complexity: O(1) - Three integer variables (start, end, mid).
 *
 * Edge Cases Handled: target absent (returns -1), single-element array, target
 * at first or last index
 */
class BinarySearch {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target)
                return mid;

            else if (nums[mid] < target)
                start = mid + 1;

            else
                end = mid - 1;
        }

        return -1;
    }
}
