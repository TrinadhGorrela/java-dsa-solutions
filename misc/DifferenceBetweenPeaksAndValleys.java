/**
 * 4088. Difference Between Peaks and Valleys
 * Difficulty: Easy | Tags: None
 * https://leetcode.com/problems/difference-between-peaks-and-valleys/
 *
 * Pattern: Linear Scan (Local Extrema Detection)
 *
 * Key insight: A peak or valley is fully determined by comparing each interior
 * element with its immediate neighbors, so a single pass suffices. Summing peak
 * values and subtracting valley values in the same traversal accumulates the
 * answer without storing anything.
 *
 * Time Complexity: O(N) - One pass over the interior elements of the array.
 *
 * Space Complexity: O(1) - Only two running sums and the loop index are used.
 *
 * Edge Cases Handled: array length < 3 (loop never runs, returns 0); no peaks
 * or valleys; plateaus / equal adjacent values are not counted as extrema;
 * negative values; multiple peaks and valleys; endpoints are never treated as
 * extrema.
 */
class DifferenceBetweenPeaksAndValleys {
    public int peakValleyDifference(int[] nums) {
        int peak = 0;
        int valley = 0;

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                peak += nums[i];
            }

            if (nums[i - 1] > nums[i] && nums[i] < nums[i + 1]) {
                valley += nums[i];
            }
        }

        return peak - valley;
    }
}
