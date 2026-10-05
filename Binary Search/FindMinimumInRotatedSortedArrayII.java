/**
 * 154. Find Minimum in Rotated Sorted Array II
 * Difficulty: Hard | Tags: Array, Binary Search
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * Pattern: Binary Search (Rotated Array with Duplicates)
 *
 * Key insight: The minimum is the first drop below its predecessor. Duplicates
 * make left==mid==right ambiguous, so both bounds are shrunk by one to strip an
 * uninformative duplicate and keep the true minimum inside; otherwise the
 * larger half is discarded using the same monotonic comparison as the
 * no-duplicate variant.
 *
 * Time Complexity: O(log N) on average, O(N) worst case - The shrink-by-one
 * tie-break degenerates to linear scanning when many duplicates exist (e.g. all
 * elements equal).
 *
 * Space Complexity: O(1) - Only constant extra space is used beyond the input
 * array.
 *
 * Edge Cases Handled: all-equal array (tie-break reduces bounds until the same
 * value is returned); duplicates around the pivot; single-element array;
 * already sorted array; result initialized to Integer.MAX_VALUE covers full
 * rotations.
 */
class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int res = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                res = Math.min(res, nums[left]);
                left++;
                right--;
                continue;
            }

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
