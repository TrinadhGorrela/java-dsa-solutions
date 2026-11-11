/**
 * 1235. Maximum Profit in Job Scheduling
 * Difficulty: Hard | Tags: Array, Binary Search, Dynamic Programming, Sorting
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 *
 * Pattern: Weighted Interval Scheduling (Sort by End Time + TreeMap DP with
 * Binary Search)
 *
 * Key insight: After sorting jobs by end time, dp maps endTime to the best
 * profit achievable using jobs finishing at or before that time; taking a job
 * chains onto the best entry whose key is at or before its start, which
 * floorEntry answers in O(log N) because the TreeMap keeps its keys sorted.
 *
 * Time Complexity: O(N log N) - Sorting dominates, and each of the N jobs
 * performs O(log N) TreeMap floor/put operations.
 *
 * Space Complexity: O(N) - The jobs array and the TreeMap each scale linearly
 * with the number of jobs.
 *
 * Edge Cases Handled: Empty input (the sentinel entry (0,0) makes lastEntry()
 * return 0), a job starting at time 0 (the (0,0) sentinel anchors its floor
 * query), and jobs sharing an end time (the put-overwrite keeps the maximum of
 * the competing values).
 */
class MaximumProfitInJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];

        for (int i = 0; i < jobs.length; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }

        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);

        for (int i = 0; i < jobs.length; i++) {
            int take = jobs[i][2] + dp.floorEntry(jobs[i][0]).getValue();
            int skip = dp.floorEntry(jobs[i][1]).getValue();

            dp.put(jobs[i][1], Math.max(take, skip));
        }

        return dp.lastEntry().getValue();
    }
}
