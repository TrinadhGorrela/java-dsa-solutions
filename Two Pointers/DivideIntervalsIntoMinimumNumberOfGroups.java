/**
 * 2406. Divide Intervals Into Minimum Number of Groups
 * Difficulty: Medium | Tags: Array, Two Pointers, Greedy, Sorting, Heap
 * (Priority Queue), Prefix Sum
 * https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/
 *
 * Pattern: Greedy + Min-Heap (Sorting + Priority Queue)
 *
 * Key insight: Process the intervals in ascending start order and keep a
 * min-heap of group end days. The smallest end day is the first group that
 * frees up: if it is still >= the next interval's start, then every group's
 * last interval contains that start point, so they all mutually intersect and a
 * new group is unavoidable; otherwise the freed group is reused. The heap size
 * therefore settles on the maximum number of overlapping intervals, which is
 * exactly the minimum number of groups.
 *
 * Time Complexity: O(N log N) - Sorting by start day plus N heap insertions and
 * removals at O(log N) each; N = number of intervals
 *
 * Space Complexity: O(N) - The heap stores one end day per allocated group, at
 * most N when every interval overlaps
 *
 * Edge Cases Handled: empty input (returns 0); a single interval (returns 1);
 * intervals touching at a shared endpoint, e.g. [1,5] and [5,8], intersect
 * under the inclusive interval definition, so the strict end < start test keeps
 * them in different groups; duplicate intervals each get their own group;
 * already-sorted input (normalised by the sort); interval values are only
 * compared, never added, so there is no overflow (bounds up to 10^6)
 */
class DivideIntervalsIntoMinimumNumberOfGroups {
    public int minGroups(int[][] intervals) {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < intervals.length; i++) {
            if (!queue.isEmpty() && queue.peek() < intervals[i][0]) {
                queue.poll();
            }

            queue.add(intervals[i][1]);
        }

        return queue.size();
    }
}
