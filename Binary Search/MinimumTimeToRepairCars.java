/**
 * 2594. Minimum Time to Repair Cars
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/minimum-time-to-repair-cars/
 *
 * Pattern: Binary Search on Answer (Monotonic Feasibility Check)
 *
 * Key insight: The number of cars repairable by time t never decreases as t
 * grows, so the minimum feasible time can be binary searched. A rank-r mechanic
 * completes floor(sqrt(t / r)) cars because their k-th car costs r * k^2 time,
 * giving a constant-time per-mechanic feasibility test.
 *
 * Time Complexity: O(M log(maxRank * cars^2)) - Each of the O(log(maxRank *
 * cars^2)) iterations scans all M mechanics; the search interval is [1,
 * maxRank * cars^2].
 *
 * Space Complexity: O(1) - Only scalar long variables are used; no auxiliary
 * collection.
 *
 * Edge Cases Handled: cars = 1; duplicate ranks; large inputs where long
 * arithmetic avoids overflow in maxRank * cars^2; an answer exactly at a bound;
 * the slowest mechanic determining the upper bound; floor truncation of sqrt to
 * whole cars. Assumes cars >= 1 and positive ranks; cars = 0 falls through to
 * the Long.MAX_VALUE sentinel.
 */
class MinimumTimeToRepairCars {
    public long repairCars(int[] ranks, int cars) {
        long max = 0;

        for (int i = 0; i < ranks.length; i++) {
            max = Math.max(max, ranks[i]);
        }

        long low = 1;
        long high = max * (long) cars * cars;
        long time = Long.MAX_VALUE;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (isValid(ranks, mid) >= cars) {
                time = Math.min(time, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return time;
    }

    private long isValid(int[] ranks, long mid) {
        long res = 0;

        for (int i = 0; i < ranks.length; i++) {
            res += Math.sqrt(mid / (long) ranks[i]);
        }

        return res;
    }
}
