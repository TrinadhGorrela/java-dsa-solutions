/**
 * 845. Longest Mountain in Array
 * Difficulty: Medium | Tags: Array, Two Pointers, Dynamic Programming,
 * Enumeration
 * https://leetcode.com/problems/longest-mountain-in-array/
 *
 * Pattern: Peak Detection with Two-Way Expansion
 *
 * Key insight: A valid mountain has a single peak with strictly increasing left
 * side and strictly decreasing right side, so expanding outward from each
 * detected peak yields its full span and the maximum among them is the answer.
 *
 * Time Complexity: O(N^2) - Each candidate peak may expand across the array in
 * the worst case.
 *
 * Space Complexity: O(1) - Uses constant extra space aside from input.
 *
 * Edge Cases Handled: Arrays shorter than 3, no valid mountain, plateaus
 * (non-strict slopes) rejected, monotonic arrays.
 */
class LongestMountainInArray {
    public int longestMountain(int[] arr) {
        int res = 0;

        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                int temp = helper(arr, i);
                res = Math.max(res, temp);
            }
        }

        return res;
    }

    private int helper(int[] arr, int in) {
        int left = in - 1;
        int right = in + 1;

        while (left >= 0 && arr[left] < arr[left + 1]) {
            left--;
        }

        while (right < arr.length && arr[right] < arr[right - 1]) {
            right++;
        }

        return right - left - 1;
    }
}
