/**
 * 718. Maximum Length of Repeated Subarray
 * Difficulty: Medium | Tags: Array, Binary Search, Dynamic Programming, Sliding
 * Window, Rolling Hash, Hash Function
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 *
 * Pattern: 2D Dynamic Programming (Longest Common Substring via Diagonal DP)
 *
 * Key insight: A common subarray ending at (i-1, j-1) extends dp[i-1][j-1] by
 * one only when the two elements match; a mismatch breaks contiguity, so the
 * streak resets to 0. The maximum over all cells is therefore the longest
 * repeated subarray.
 *
 * Time Complexity: O(M*N) - Every cell of the (M+1)x(N+1) table is computed
 * once, where M = nums1.length and N = nums2.length.
 *
 * Space Complexity: O(M*N) - Allocates the full 2D DP table (optimizable to
 * O(N)).
 *
 * Edge Cases Handled: Empty input array (loops skipped, returns 0), no common
 * element (all cells remain 0), single-element arrays, identical arrays
 * (returns min length), and partial overlap.
 */
class MaximumLengthOfRepeatedSubarray {
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }

                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
