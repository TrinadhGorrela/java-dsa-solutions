/**
 * 1353. Maximum Number of Events That Can Be Attended
 * Difficulty: Medium | Tags: Array, Greedy, Sorting, Heap (Priority Queue)
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 *
 * Pattern: Greedy + Min-Heap Day Sweep (Sorting + Priority Queue)
 *
 * Key insight: Sweep the calendar day by day and on each day attend the
 * available event that ends earliest - that event has the least slack, so
 * postponing it risks losing it, while a later-ending event stays valid for any
 * day the earliest-ending one could have been used. The exchange is therefore
 * always safe and the greedy choice never lowers the total.
 *
 * Time Complexity: O(N log N + D) - Sorting costs O(N log N) and each of the N
 * events enters and leaves the heap exactly once at O(log N); the day sweep
 * adds O(D) for D = the largest end day (N = number of events, D <= 10^5)
 *
 * Space Complexity: O(N) - The min-heap holds one entry per event that is
 * started but not yet attended, which is at most N
 *
 * Edge Cases Handled: empty input (returns 0, the day loop never runs); events
 * ending exactly on the current day stay attendable (the expiry test is end <
 * day, not end <= day); several events starting on the same day are all
 * enqueued before one is chosen (inner while loop); only one event is attended
 * per day even when many are available; ties on end day are broken arbitrarily
 * and do not change the count; a single event; assumes start days >= 1, since
 * the sweep starts at day 1
 */
class MaximumNumberOfEventsThatCanBeAttended {
    public int maxEvents(int[][] events) {
        Queue<int[]> meetings = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int maxDay = 0;
        int attend = 0;
        int in = 0;

        for (int i = 0; i < events.length; i++) {
            maxDay = Math.max(maxDay, events[i][1]);
        }

        for (int day = 1; day <= maxDay; day++) {
            while (!meetings.isEmpty() && meetings.peek()[1] < day) {
                meetings.poll();
            }

            while (in < events.length && events[in][0] == day) {
                meetings.add(events[in]);
                in++;
            }

            if (!meetings.isEmpty()) {
                meetings.poll();
                attend++;
            }
        }

        return attend;
    }
}
