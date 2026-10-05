/**
 * 3903. Smallest Stable Index I
 * Difficulty: Easy | Tags: Array, Prefix Sum
 * https://leetcode.com/problems/smallest-stable-index-i/
 *
 * Pattern: Prefix Max + Suffix Min Scan
 *
 * Key insight: Precompute large[i] (max in nums[0..i]) and small[i] (min in
 * nums[i..n-1]) so that at every index the range extremities are known in O(1).
 * The first index where large[i] - small[i] <= k is the answer.
 *
 * Time Complexity: O(N) - Three linear passes: suffix min, prefix max, and
 * final scan.
 *
 * Space Complexity: O(N) - Two auxiliary arrays of length N.
 *
 * Edge Cases Handled: single element (large[0] - small[0] = 0 <= k, returns 0),
 * all-same elements (difference is 0 everywhere, returns 0), strictly
 * increasing/decreasing (difference grows with index; only early indices or -1
 * possible), no valid index found (returns -1).
 */
class SmallestStableIndexI {
    public int firstStableIndex(int[] nums, int k) {
        int[] small = new int[nums.length];
        int[] large = new int[nums.length];
        small[nums.length - 1] = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            if (small[i + 1] > nums[i]) {
                small[i] = nums[i];
            } else {
                small[i] = small[i + 1];
            }
        }

        large[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (large[i - 1] < nums[i]) {
                large[i] = nums[i];
            } else {
                large[i] = large[i - 1];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (large[i] - small[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}
