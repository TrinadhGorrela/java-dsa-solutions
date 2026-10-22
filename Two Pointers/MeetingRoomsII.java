/**
 * 253. Meeting Rooms II
 * Difficulty: Medium | Tags: Array, Two Pointers, Greedy, Sorting, Heap
 * (Priority Queue), Prefix Sum
 * https://leetcode.com/problems/meeting-rooms-ii/
 *
 * Pattern: Sort-by-Start + Min-Heap (Priority Queue) Room Reuse
 *
 * Key insight: Sorting the meetings by start time makes arrivals chronological,
 * and the room that frees up first is always the one whose current meeting ends
 * earliest, so a min-heap keyed by end time exposes the next reusable room in
 * O(log N); a meeting reuses that room whenever the earliest end time is <= its
 * own start time instead of forcing a brand-new room.
 *
 * Time Complexity: O(N log N) - Sorting by start time dominates (N log N); the
 * N heap insertions plus the same number of removals add another O(N log N)
 *
 * Space Complexity: O(N) - The min-heap keeps one entry per meeting that still
 * occupies a room (all N when every meeting overlaps)
 *
 * Edge Cases Handled: single meeting (returns 1); back-to-back meetings where
 * one ends exactly when the next starts (room reused thanks to the `<=` test);
 * meetings sharing the same start time (each one gets its own room); fully
 * nested or mutually overlapping meetings; the caller's array is sorted in
 * place as a side effect. Not handled: empty or null input (throws
 * ArrayIndexOutOfBoundsException / NullPointerException) and the case where
 * several rooms free up before the next meeting starts - only one room is
 * reclaimed per arrival (a single `if` instead of `while`), so finished
 * meetings can linger in the heap and inflate the count, e.g.
 * [[0,1],[0,1],[2,3],[2,3]] reports 3 instead of 2
 */
class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        queue.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (!queue.isEmpty() && queue.peek()[1] <= intervals[i][0]) {
                queue.poll();
            }

            queue.add(intervals[i]);
        }

        return queue.size();
    }
}
