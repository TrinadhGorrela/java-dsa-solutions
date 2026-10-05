/**
 * 56. Merge Intervals
 * Difficulty: Medium | Tags: Array, Sorting, Quicksort
 * https://leetcode.com/problems/merge-intervals/
 *
 * Pattern: Sort-by-Start Linear Merge
 *
 * Key insight: Sorting by start time guarantees that any overlap with the
 * running merged interval is detected by comparing `lastEnd >= currentStart`;
 * the merged interval's end simply extends to `max(lastEnd, currentEnd)`,
 * otherwise the interval is disjoint and gets appended.
 *
 * Time Complexity: O(N log N) - Sorting dominates; the merge scan is O(N)
 *
 * Space Complexity: O(N) - Output list may hold up to N intervals (no overlaps
 * case); auxiliary sort space not counted
 *
 * Edge Cases Handled: fully contained intervals (end extends via max), adjacent
 * intervals touching at boundary (merged), single interval (output equals
 * input), all overlapping (single merged interval in output)
 */
class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] last = merged.get(merged.size() - 1);
            int[] cur = intervals[i];
            if (last[1] >= cur[0]) {
                last[1] = Math.max(last[1], cur[1]);
            } else {
                merged.add(cur);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
