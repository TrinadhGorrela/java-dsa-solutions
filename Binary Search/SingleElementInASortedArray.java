/**
 * 540. Single Element in a Sorted Array
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 *
 * Pattern: Binary Search (Even/Odd Pair-Parity Invariant)
 *
 * Key insight: Every value except the single one forms a duplicate pair, so
 * scanning left to right the first member of each pair sits at an even index
 * until the single element is passed, after which the pairing shifts by one.
 * That is why, at any mid, seeing nums[mid] == nums[mid + 1] with an even mid
 * (or nums[mid] == nums[mid - 1] with an odd mid) proves the single element
 * lies strictly to the right, while the opposite alignment proves it lies to
 * the left - each comparison discards half the window without ever unrolling
 * the array.
 *
 * Time Complexity: O(log N) - Each iteration bisects the search window [1, n -
 * 2]
 *
 * Space Complexity: O(1) - Only the fixed set of index variables left, right,
 * mid and n
 *
 * Edge Cases Handled: single-element array (n == 1 returned directly); unique
 * value at the front or the back (nums[0] != nums[1] / nums[n - 1] != nums[n -
 * 2] return it before searching); duplicate pair pinned at either end, which
 * trims the window off that side; any int value including negatives, zero and
 * Integer.MIN/MAX_VALUE (only equality comparisons are used, never arithmetic
 * on values); mid always stays within [1, n - 2] so nums[mid - 1] / nums[mid +
 * 1] are never out of bounds; loop-exhaustion fallback return 0 for total
 * robustness. Assumes the LeetCode guarantee of exactly one unpaired value in
 * an odd-length array - it is NOT safe for even-length input or for a value
 * repeated more than twice.
 */
class SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int left = 1;
        int right = n - 2;

        if ((n == 1) || nums[0] != nums[1]) {
            return nums[0];
        }

        if (nums[n - 1] != nums[n - 2]) {
            return nums[n - 1];
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            } else if ((mid % 2 == 0 && nums[mid] == nums[mid + 1]) || (mid % 2 == 1 && nums[mid] == nums[mid - 1])) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return 0;
    }
}
