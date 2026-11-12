/**
 * 3105. Longest Strictly Increasing or Strictly Decreasing Subarray
 * Difficulty: Easy | Tags: Array
 * https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreas
 * ing-subarray/
 *
 * Pattern: Brute-Force Expansion (Nested Scan)
 *
 * Key insight: Whenever an adjacent pair strictly rises or falls, expand
 * forward from that start as far as the same direction holds and record the run
 * length. Equal neighbors break both runs, so each start is checked
 * independently rather than in a single linear pass.
 *
 * Time Complexity: O(N^2) - The inner expansion re-scans overlapping elements
 * from each starting point (an already-increasing array is the worst case), not
 * the optimal O(N).
 *
 * Space Complexity: O(1) - Only constant counter variables are used.
 *
 * Edge Cases Handled: single element (returns 1), all-equal array (no strict
 * pair, returns 1), plateaus break both increasing and decreasing runs,
 * strictly increasing or strictly decreasing array; empty input is NOT handled
 * (returns 1) since constraints guarantee length >= 1
 */
class LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {
    public int longestMonotonicSubarray(int[] nums) {
        int incCount = 1;
        int decCount = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                int count = 1;

                for (int j = i; j < nums.length; j++) {
                    if (nums[j - 1] >= nums[j]) {
                        break;
                    }

                    count++;
                }

                incCount = Math.max(incCount, count);
            }

            if (nums[i - 1] > nums[i]) {
                int count = 1;

                for (int j = i; j < nums.length; j++) {
                    if (nums[j - 1] <= nums[j]) {
                        break;
                    }

                    count++;
                }

                decCount = Math.max(decCount, count);
            }
        }

        return Math.max(incCount, decCount);
    }
}
