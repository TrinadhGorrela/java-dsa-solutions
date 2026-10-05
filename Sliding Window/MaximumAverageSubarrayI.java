/**
 * 643. Maximum Average Subarray I
 * Difficulty: Easy | Tags: Array, Sliding Window
 * https://leetcode.com/problems/maximum-average-subarray-i/
 *
 * Pattern: Fixed-Size Sliding Window (Rolling Sum)
 *
 * Key insight: Compute the sum of the initial k-element window, then slide by
 * adding nums[right] and subtracting nums[right - k] in O(1). Compare the
 * running sum (not average) against the max to avoid repeated division; divide
 * once at the end.
 *
 * Time Complexity: O(N) - Initial window O(k), then N - k slide steps each O(1)
 *
 * Space Complexity: O(1) - Two double scalars (sum, max) with no auxiliary data
 *
 * Edge Cases Handled: k equals array length (entire array is one window),
 * all-negative values (max is seeded from the first window, not 0), k = 1
 * (reduces to finding the max element)
 */
class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {

        double sum = 0;
        double max = 0;
        for (int i = 0; i < k; i++) {
            sum = sum + nums[i];
        }
        max = sum;
        for (int right = k; right < nums.length; right++) {
            sum = sum + nums[right] - nums[right - k];
            max = Math.max(max, sum);
        }
        return max / k;
    }
}
