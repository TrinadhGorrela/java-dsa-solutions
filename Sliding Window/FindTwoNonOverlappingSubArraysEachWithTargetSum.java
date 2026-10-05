/**
 * 1477. Find Two Non-overlapping Sub-arrays Each With Target Sum
 * Difficulty: Medium | Tags: Array, Hash Table, Binary Search, Dynamic
 * Programming, Sliding Window
 * https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-t
 * arget-sum/
 *
 * Pattern: Sliding Window (Variable Length) + DP Running Minimum
 *
 * Key insight: A variable-length window locates every contiguous subarray
 * summing to target in one pass. dp[right] stores the smallest valid subarray
 * length ending at or before right; when the current window [left, right] sums
 * to target, pairing it with dp[left-1] (best valid subarray strictly to the
 * left) yields candidates that can never overlap. Because the window is
 * monotonic, each element enters and leaves at most once, giving linear time.
 *
 * Time Complexity: O(N) - Single pass; each element is added to the sum once
 * and subtracted at most once by the sliding window, plus O(1) DP work per
 * index.
 *
 * Space Complexity: O(N) - dp array of size equal to the input array length.
 *
 * Edge Cases Handled: no valid pair exists (returns -1 via the
 * Integer.MAX_VALUE sentinel); single-element input (right > 0 and left > 0
 * guards skip prefix pairing); adjacent non-overlapping subarrays (prefix ends
 * at left-1, current starts at left, so they share no elements); current window
 * longer than the best-so-far is ignored by the min check.
 * Honest caveat: relies on all-positive input so that sum > target correctly
 * shrinks the window.
 */
class FindTwoNonOverlappingSubArraysEachWithTargetSum {
    public int minSumOfLengths(int[] arr, int target) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int left = 0;
        int result = Integer.MAX_VALUE;
        int sum = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (right > 0) {
                dp[right] = dp[right - 1];
            }

            if (sum == target) {
                if (right - left + 1 < dp[right]) {
                    dp[right] = right - left + 1;
                }

                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    int curr = (right - left + 1) + dp[left - 1];
                    result = Math.min(result, curr);
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
