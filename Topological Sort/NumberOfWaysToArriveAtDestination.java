/**
 * 1976. Number of Ways to Arrive at Destination
 * Difficulty: Medium | Tags: Dynamic Programming, Graph Theory, Topological
 * Sort, Shortest Path, Dijkstra's Algorithm
 * https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
 *
 * Pattern: Dijkstra's Shortest Path + Path Counting (DP over Distances)
 *
 * Key insight: Dijkstra settles nodes in nondecreasing distance, so every
 * shortest path to a node must come from a predecessor with a smaller distance;
 * on an equal-distance relaxation, adding that predecessor's path count
 * accumulates all shortest paths, kept modulo 1e9+7.
 *
 * Time Complexity: O((V + E) log V) - With V = n and E = roads.length, each
 * edge is relaxed per settled node and each successful relaxation pushes one
 * priority-queue entry.
 *
 * Space Complexity: O(V + E) - Adjacency lists, distance/ways arrays of size n
 * + 1, and the priority queue all scale with nodes and roads.
 *
 * Edge Cases Handled: n = 1 (source is the destination, returns 1),
 * disconnected graph or unreachable destination (returns 0), multiple shortest
 * paths summed modulo 1e9+7 to avoid overflow, parallel/duplicate roads,
 * cycles, and stale heap entries skipped by the distance check.
 */
class NumberOfWaysToArriveAtDestination {
    class Edges {
        int wt;
        int node;

        Edges(int wt, int node) {
            this.wt = wt;
            this.node = node;
        }
    }

    public int countPaths(int n, int[][] roads) {
        List<List<Edges>> graph = new ArrayList<>();
        int mod = 1_000_000_007;

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            graph.get(road[0]).add(new Edges(road[2], road[1]));
            graph.get(road[1]).add(new Edges(road[2], road[0]));
        }

        Queue<long[]> queue = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        queue.add(new long[] { 0, 0 });
        long[] dis = new long[n + 1];
        long[] ways = new long[n + 1];

        Arrays.fill(dis, Long.MAX_VALUE);

        dis[0] = 0;
        ways[0] = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- != 0) {
                long[] curr = queue.poll();
                int currNode = (int) curr[0];

                if (curr[1] > dis[currNode]) {
                    continue;
                }

                for (Edges neighbor : graph.get(currNode)) {
                    long dist = neighbor.wt + curr[1];

                    if (dist < dis[neighbor.node]) {
                        dis[neighbor.node] = dist;
                        ways[neighbor.node] = ways[currNode] % mod;
                        queue.add(new long[] { neighbor.node, dist });
                    } else if (dist == dis[neighbor.node]) {
                        ways[neighbor.node] = (ways[neighbor.node] + ways[currNode]) % mod;
                    }
                }
            }
        }

        return (int) ways[n - 1];
    }
}
