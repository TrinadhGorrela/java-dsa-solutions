/**
 * 162. Find Peak Element
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/find-peak-element/
 *
 * Pattern: Binary Search (Half-Space Elimination via Slope Comparison)
 *
 * Key insight: Treating the virtual neighbors outside the array as -infinity, a
 * rise nums[mid] < nums[mid+1] guarantees a peak exists to the right (the
 * ascent must eventually fall), otherwise a peak sits at mid or to the left -
 * so each comparison safely discards half the array until left == right at a
 * peak.
 *
 * Time Complexity: O(log N) - The candidate range halves on every loop
 * iteration
 *
 * Space Complexity: O(1) - Only the left, right, and mid index variables
 * persist
 *
 * Edge Cases Handled: single element (loop never runs, returns index 0);
 * two-element arrays; strictly increasing (converges to the last index);
 * strictly decreasing (converges to index 0); mid computed as left + (right -
 * left) / 2 to avoid overflow; equal adjacent values are out of scope per
 * constraints (nums[i] != nums[i+1])
 */
class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        return left;
    }
}
