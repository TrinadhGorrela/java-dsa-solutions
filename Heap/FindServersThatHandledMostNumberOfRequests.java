/**
 * 1606. Find Servers That Handled Most Number of Requests
 * Difficulty: Hard | Tags: Array, Heap (Priority Queue), Simulation, Ordered
 * Set
 * https://leetcode.com/problems/find-servers-that-handled-most-number-of-reques
 * ts/
 *
 * Pattern: Greedy Assignment + Min-Heap (Release Times) + Ordered Set (TreeSet)
 *
 * Key insight: A TreeSet of free servers lets `ceiling(i % k)` pick the
 * smallest eligible server in O(log K), wrapping to `first()` when none
 * qualifies, while a min-heap keyed by finish time releases servers exactly
 * when they become free. Because every request touches each structure at most
 * once, the simulation stays near-linear in the number of requests.
 *
 * Time Complexity: O(N log K) - Each of the N requests performs a constant
 * number of O(log K) TreeSet and priority-queue operations, where K is the
 * server count.
 *
 * Space Complexity: O(K) - The free-server set, busy heap, and per-server
 * counter each hold at most K entries.
 *
 * Edge Cases Handled: all servers busy (request skipped via `continue`), no
 * eligible server at or after `i % k` (wrap to `available.first()`), server
 * finishing exactly at the next arrival (`<=` comparison), single server (K =
 * 1), and ties for the maximum count (all such indices are returned).
 */
class FindServersThatHandledMostNumberOfRequests {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        Queue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int[] count = new int[k];
        int max = 0;

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                available.add(busy.poll()[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            Integer server = available.ceiling(i % k);

            if (server == null) {
                server = available.first();
            }

            available.remove(server);
            busy.add(new int[] { arrival[i] + load[i], server });
            count[server] += 1;

            max = Math.max(max, count[server]);
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < count.length; i++) {
            if (count[i] == max) {
                res.add(i);
            }
        }

        return res;
    }
}
