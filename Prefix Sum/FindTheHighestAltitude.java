/**
 * 1732. Find the Highest Altitude
 * Difficulty: Easy | Tags: Array, Prefix Sum
 * https://leetcode.com/problems/find-the-highest-altitude/
 *
 * Pattern: Prefix Sum with Max Tracking
 *
 * Key insight: The biker starts at altitude 0; building a prefix sum array of
 * gains yields all altitudes, and the maximum of this array (including index 0)
 * is the answer.
 *
 * Time Complexity: O(N) - First pass fills the N+1 prefix sum array; second
 * pass finds the max
 *
 * Space Complexity: O(N) - N+1 element presum array stores cumulative altitudes
 *
 * Edge Cases Handled: all negative gains (max altitude is 0 at start), single
 * gain element, maximum altitude at the starting point (index 0)
 */
class FindTheHighestAltitude {
    public int largestAltitude(int[] gain) {
        int[] presum = new int[gain.length + 1];

        for (int i = 0; i < gain.length; i++) {
            presum[i + 1] = presum[i] + gain[i];
        }
        int max = 0;
        for (int i = 0; i < gain.length + 1; i++) {
            if (max < presum[i]) {
                max = presum[i];
            }
        }
        return max;
    }
}
