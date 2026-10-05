/**
 * 1288. Remove Covered Intervals
 * Difficulty: Medium | Tags: Array, Sorting
 * https://leetcode.com/problems/remove-covered-intervals/
 *
 * Pattern: Sort-then-Scan Cover Detection
 *
 * Key insight: Sorting by ascending start (and descending end for ties) ensures
 * the widest interval for a given start is encountered first; a subsequent
 * interval is covered if it falls within the last kept interval's bounds (start
 * ≥ kept.start && end ≤ kept.end).
 *
 * Time Complexity: O(N log N) - Custom sort dominates; linear scan is O(N)
 *
 * Space Complexity: O(1) - Index pointer and count; sort modifies in place
 *
 * Edge Cases Handled: identical intervals (second is covered), same start
 * different end (longer one kept first), partial overlaps (not covered, new
 * kept interval set), fully nested intervals (inner removed)
 */
class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        int count = intervals.length;
        int lastKeptIndex = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] last = intervals[lastKeptIndex];
            int[] curr = intervals[i];
            if (last[0] <= curr[0] && last[1] >= curr[1]) {
                count--;
            } else {
                lastKeptIndex = i;
            }
        }
        return count;
    }
}
