/**
 * 452. Minimum Number of Arrows to Burst Balloons
 * Difficulty: Medium | Tags: Array, Greedy, Sorting
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * Pattern: End-Point Greedy Interval Covering
 * Key insight: Shooting at the earliest-ending balloon's endpoint is optimal because it covers all overlapping balloons
 * sharing that range; a new arrow is needed only when the next balloon starts strictly after the current shot position.
 *
 * Time Complexity: O(N log N) - Sort by end coordinate dominates; linear scan is O(N)
 * Space Complexity: O(1) - Two tracking variables (arrow position, arrow count)
 *
 * Edge Cases Handled: single balloon (1 arrow), fully overlapping balloons (one arrow covers all), touching balloons
 * where start == previous end (non-overlapping, new arrow needed), all disjoint balloons (N arrows)
 */
class MinimumNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        int last = points[0][1];
        int arrows = 1;

        for (int i = 0; i < points.length; i++) {
            if (last < points[i][0]) {
                arrows++;
                last = points[i][1];
            }
        }
        return arrows;
    }
}
