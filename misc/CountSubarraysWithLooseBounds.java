/**
 * 4089. Count Subarrays With Loose Bounds
 * Difficulty: Medium | Tags: None
 * https://leetcode.com/problems/count-subarrays-with-loose-bounds/
 *
 * Pattern: Linear Scan (Run-Length Counting of Valid Subarrays)
 *
 * Key insight: Every subarray must consist solely of elements inside [minK,
 * maxK], so any out-of-range element splits the array. If the current valid run
 * has length `prev`, exactly `prev` valid subarrays end at the current index;
 * summing these counts over the scan yields the total.
 *
 * Time Complexity: O(N) - Single pass over the array.
 *
 * Space Complexity: O(1) - Only the current run length and the total are
 * tracked.
 *
 * Edge Cases Handled: empty array; all elements out of range (returns 0);
 * single element; valid runs separated by invalid elements; minK > maxK (no
 * element can satisfy it, returns 0); negative values; result stored in `long`
 * to avoid overflow on long runs. Note: unlike LeetCode 2444, it does not
 * require both minK and maxK to be present.
 */
class CountSubarraysWithLooseBounds {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long prev = 0;
        long res = 0;

        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] >= minK && nums[i - 1] <= maxK) {
                prev += 1;
                res += prev;
            } else {
                prev = 0;
            }
        }

        return res;
    }
}
