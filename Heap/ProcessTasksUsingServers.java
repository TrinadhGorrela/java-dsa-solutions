/**
 * 1882. Process Tasks Using Servers
 * Difficulty: Medium | Tags: Array, Heap (Priority Queue)
 * https://leetcode.com/problems/process-tasks-using-servers/
 *
 * Pattern: Two Heaps (Min-Heap by Weight + Min-Heap by Finish Time)
 *
 * Key insight: Keep free servers ordered by (weight, index) and busy servers
 * ordered by finish time; since the earliest-finishing server is always the
 * best one to wait for, advancing the clock to it when no server is free
 * preserves optimality while each server moves between heaps at most once per
 * task.
 *
 * Time Complexity: O((N + M) log N) - N servers are seeded once, then each of
 * the M tasks causes a bounded number of heap pushes/polls, each O(log N).
 *
 * Space Complexity: O(N + M) - Two heaps hold at most N server entries, plus
 * the O(M) result array.
 *
 * Edge Cases Handled: more tasks than servers (waits for earliest-finishing
 * server), more servers than tasks (idle extras), equal weights broken by
 * smaller index, equal finish times all released via <= currentTime, tasks
 * queued at future arrival index via currentTime = max(currentTime, i), and
 * long-based time arithmetic to avoid int overflow; does not guard against an
 * empty servers array (LeetCode guarantees at least one server).
 */
class ProcessTasksUsingServers {
    public int[] assignTasks(int[] servers, int[] tasks) {
        Queue<long[]> server = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return Long.compare(a[1], b[1]);
            }

            return Long.compare(a[0], b[0]);
        });

        Queue<long[]> busyServers = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        int[] res = new int[tasks.length];

        for (int i = 0; i < servers.length; i++) {
            server.add(new long[] { servers[i], i });
        }

        long currentTime = 0;

        for (int i = 0; i < tasks.length; i++) {
            currentTime = Math.max(currentTime, i);

            if (server.isEmpty()) {
                long[] temp = busyServers.poll();
                currentTime = temp[0];

                server.add(new long[] { temp[1], temp[2] });
            }

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                server.add(new long[] { busyServers.peek()[1], busyServers.peek()[2] });
                busyServers.poll();
            }

            long[] temp = server.poll();

            busyServers.add(new long[] { currentTime + tasks[i], temp[0], temp[1] });
            res[i] = (int) temp[1];
        }

        return res;
    }
}
