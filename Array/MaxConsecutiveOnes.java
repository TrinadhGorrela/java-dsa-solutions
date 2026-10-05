/**
 * 485. Max Consecutive Ones
 * Difficulty: Easy | Tags: Array
 * https://leetcode.com/problems/max-consecutive-ones/
 *
 * Pattern: Linear Scan with Run-Length Counter
 *
 * Key insight: Increment a counter on each 1 and reset it to 0 on each 0,
 * taking the max after every element. The final max() call after the loop
 * handles a trailing run of ones that never encounters a 0 to trigger the
 * mid-loop max update.
 *
 * Time Complexity: O(N) - Single pass through the array
 *
 * Space Complexity: O(1) - Two integer scalars (count, max)
 *
 * Edge Cases Handled: all zeros (count never exceeds 0), all ones (final max()
 * after loop captures the full run), single element (1 or 0), leading zeros
 * followed by ones, trailing ones with no terminating 0
 */
class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i : nums) {
            if (i == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        return max;
    }
}
