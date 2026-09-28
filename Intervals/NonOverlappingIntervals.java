/**
 * 435. Non-overlapping Intervals
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Greedy, Sorting
 * https://leetcode.com/problems/non-overlapping-intervals/
 *
 * Pattern: End-Time Greedy Interval Scheduling
 * Key insight: Sorting by end time and always keeping the earliest-finishing non-overlapping interval maximizes the
 * number of kept intervals (classic interval scheduling optimality). Removals = N - kept, so minimizing removals is
 * equivalent to maximizing kept.
 *
 * Time Complexity: O(N log N) - Sort by end time dominates; the scan is O(N)
 * Space Complexity: O(1) - Two tracking variables (last end, keptCount)
 *
 * Edge Cases Handled: single interval (0 removals), all overlapping (all but one removed), touching boundaries (start
 * >= last end means non-overlapping, kept), intervals sorted by start not end (sorting corrects this)
 */
class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int last = intervals[0][1];
        int keptCount = 1;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= last) {
                keptCount++;
                last = intervals[i][1];
            }
        }
        return intervals.length - keptCount;
    }
}
