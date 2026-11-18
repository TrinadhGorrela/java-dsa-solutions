/**
 * 2187. Minimum Time to Complete Trips
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/minimum-time-to-complete-trips/
 *
 * Pattern: Binary Search on Answer (Monotonic Feasibility)
 *
 * Key insight: For a fixed time T, the number of trips completed is the sum of
 * floor(T / t[i]) over all buses, which is nondecreasing in T; hence the first
 * feasible T is the minimum time. The bound max(t) * totalTrips is always
 * feasible because the slowest bus alone finishes all trips by then.
 *
 * Time Complexity: O(N log N + N log(max(t) * totalTrips)) - Sorting costs O(N
 * log N); the binary search runs O(log(max(t) * totalTrips)) iterations, each
 * scanning all N buses
 *
 * Space Complexity: O(log N) - Arrays.sort uses dual-pivot quicksort with O(log
 * N) stack depth; the search itself uses O(1) extra space
 *
 * Edge Cases Handled: single bus; totalTrips = 1; all-equal times; long
 * arithmetic in the upper bound and trip accumulator prevents int overflow;
 * assumes non-null, non-empty time with positive entries and positive
 * totalTrips (no null/zero validation)
 */
class MinimumTimeToCompleteTrips {
    public long minimumTime(int[] time, int totalTrips) {
        Arrays.sort(time);

        long left = 1;
        long right = (long) time[time.length - 1] * totalTrips;
        long minTime = Long.MAX_VALUE;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (isValid(time, mid) >= totalTrips) {
                minTime = Math.min(minTime, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return minTime;
    }

    private long isValid(int[] t, long time) {
        long trips = 0;

        for (int i = 0; i < t.length; i++) {
            trips += time / t[i];
        }

        return trips;
    }
}
