/**
 * 673. Number of Longest Increasing Subsequence
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Binary Indexed Tree,
 * Segment Tree, Longest Increasing Subsequence
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 *
 * Pattern: Dynamic Programming (O(N^2) LIS with Count Tracking)
 *
 * Key insight: For each index i, len[i] is the length of the longest increasing
 * subsequence ending at i and counts[i] is how many such subsequences exist.
 * Because every longer LIS is formed by appending nums[i] to a shorter LIS
 * ending at some j < i, a strictly longer extension resets counts[i] to
 * counts[j] while an equal-length one adds counts[j], so the counts remain
 * correct.
 *
 * Time Complexity: O(N^2) - The nested loop compares every pair (j, i) with j
 * <= i.
 *
 * Space Complexity: O(N) - Two auxiliary arrays (lengths and counts) that scale
 * with the input size.
 *
 * Edge Cases Handled: empty input (returns 0), single element (length 1, count
 * 1), all-equal input (no strict increase so every count stays 1),
 * already-sorted input (a single LIS of length N), negative numbers, and
 * duplicate values (only strictly smaller j extend i).
 */
class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int[] len = new int[nums.length];
        int[] counts = new int[nums.length];

        Arrays.fill(len, 1);
        Arrays.fill(counts, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (nums[j] < nums[i]) {
                    if (len[j] + 1 > len[i]) {
                        counts[i] = counts[j];
                    } else if (len[j] + 1 == len[i]) {
                        counts[i] += counts[j];
                    }

                    len[i] = Math.max(len[i], len[j] + 1);
                }
            }
        }

        int max = 0;
        int res = 0;

        for (int i = 0; i < len.length; i++) {
            if (len[max] < len[i]) {
                max = i;
            }
        }

        for (int i = 0; i < len.length; i++) {
            if (len[max] == len[i]) {
                res += counts[i];
            }
        }

        return res;
    }
}
