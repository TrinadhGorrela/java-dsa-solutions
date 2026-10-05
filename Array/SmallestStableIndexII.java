/**
 * 3904. Smallest Stable Index II
 * Difficulty: Medium | Tags: Array, Prefix Sum
 * https://leetcode.com/problems/smallest-stable-index-ii/
 *
 * Pattern: Prefix-Suffix Running Extremum (Two-Pass Scan)
 *
 * Key insight: Precompute the running maximum from the left and running minimum
 * from the right; an index i is stable when large[i] - small[i] <= k, because
 * large[i] dominates everything left of i and small[i] is the smallest
 * reachable value from i onward.
 *
 * Time Complexity: O(N) - Two linear passes to build prefix/suffix arrays plus
 * one linear scan to find the answer.
 *
 * Space Complexity: O(N) - Two auxiliary arrays of length N store the running
 * max and running min.
 *
 * Edge Cases Handled: single-element array, all identical elements (difference
 * always 0), strictly increasing/decreasing arrays, k = 0 requiring exact
 * stability, no valid index found (returns -1).
 */
class SmallestStableIndexII {
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
