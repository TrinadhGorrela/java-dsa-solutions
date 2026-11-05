/**
 * 581. Shortest Unsorted Continuous Subarray
 * Difficulty: Medium | Tags: Array, Two Pointers, Stack, Greedy, Sorting,
 * Monotonic Stack
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 *
 * Pattern: Two Passes (Running Max / Running Min Boundary Detection)
 *
 * Key insight: An element is out of place if a larger value exists to its left
 * or a smaller value exists to its right. A forward pass with a running maximum
 * marks the rightmost such violation, and a backward pass with a running
 * minimum marks the leftmost, so the gap between them is exactly the shortest
 * subarray that must be sorted.
 *
 * Time Complexity: O(N) - Two independent linear scans over the array.
 *
 * Space Complexity: O(1) - Only scalar running max/min and boundary indices are
 * stored.
 *
 * Edge Cases Handled: already-sorted input (both boundaries stay 0, returns 0),
 * single-element and empty arrays (loops never record a violation), duplicate
 * values (strict > / < comparisons avoid false violations), negative numbers.
 */
class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int rightIn = 0;

        for (int i = 0; i < nums.length; i++) {
            if (max > nums[i]) {
                rightIn = i;
            }

            max = Math.max(max, nums[i]);
        }

        int min = Integer.MAX_VALUE;
        int leftIn = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (min < nums[i]) {
                leftIn = i;
            }

            min = Math.min(min, nums[i]);
        }

        if (rightIn == 0 && leftIn == 0) {
            return 0;
        }

        return rightIn - leftIn + 1;
    }
}
