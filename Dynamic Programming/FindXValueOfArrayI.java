/**
 * 3524. Find X Value of Array I
 * Difficulty: Medium | Tags: Array, Math, Dynamic Programming
 * https://leetcode.com/problems/find-x-value-of-array-i/
 *
 * Pattern: Dynamic Programming (Mod-k Remainder DP)
 *
 * Key insight: When counting subsequences by the product of their elements mod
 * k, only the remainder matters, so state collapses into k buckets: each new
 * element extends every previously counted subsequence, and remainders compose
 * as (j * nums[i]) % k with a single-element subsequence added for itself.
 *
 * Time Complexity: O(N * K) - N elements, each scans all k remainder buckets to
 * build the next count set.
 *
 * Space Complexity: O(K) - Uses three count arrays (current, previous, total)
 * that each scale with k.
 *
 * Edge Cases Handled: empty input (returns an all-zero array), single element,
 * k = 1 (every remainder collapses to 0), and int overflow avoided by promoting
 * intermediate products to long. Note: assumes non-negative nums[i] per
 * constraints, since a negative remainder would index out of bounds.
 */
class FindXValueOfArrayI {
    public long[] resultArray(int[] nums, int k) {
        long[] totalCounts = new long[k];
        long[] prevCounts = new long[k];

        for (int i = 0; i < nums.length; i++) {
            long[] currCounts = new long[k];
            long curr = nums[i] % k;
            currCounts[(int) curr]++;
            totalCounts[(int) curr]++;

            for (int j = 0; j < k; j++) {
                long newRem = ((long) j * nums[i]) % k;

                currCounts[(int) newRem] += prevCounts[j];
                totalCounts[(int) newRem] += prevCounts[j];
            }
            prevCounts = currCounts;

        }

        return totalCounts;
    }
}
