/**
 * 743. Network Delay Time
 * Difficulty: Medium | Tags: Depth-First Search, Breadth-First Search, Graph
 * Theory, Heap (Priority Queue), Shortest Path, Dijkstra's Algorithm
 * https://leetcode.com/problems/network-delay-time/
 *
 * Pattern: Dijkstra's Algorithm (Lazy Min-Heap, No Decrease-Key)
 *
 * Key insight: With strictly positive edge weights, the entry popped with the
 * smallest tentative distance is permanently settled, and a relaxation only
 * fires when it strictly improves dis[dest] — so duplicate or stale heap
 * entries are harmless because their relaxations simply fail the improvement
 * check.
 *
 * Time Complexity: O(E log E) - each edge relaxation can push one entry into
 * the min-heap, and every push/poll costs O(log heap size), bounded by O(log E)
 * since the heap holds at most one entry per relaxation.
 *
 * Space Complexity: O(V + E) - adjacency list graph, the dis[] distance array,
 * and a lazy heap that may hold up to O(E) entries.
 *
 * Edge Cases Handled: unreachable nodes (dis[i] stays Integer.MAX_VALUE,
 * returning -1), the source node itself (dis[k] = 0, so a single-node network
 * returns 0), parallel/duplicate edges and self-loops (relaxation keeps the
 * minimum weight; a positive self-loop never improves), and disconnected graphs
 * (-1). No overflow risk: with weights <= 100 and n <= 100, path sums stay far
 * below Integer.MAX_VALUE.
 */
class NetworkDelayTime {
    class Edge {
        int dest;
        int wt;

        public Edge(int dest, int wt) {
            this.dest = dest;
            this.wt = wt;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int[] dis = new int[n + 1];

        queue.add(new int[] { k, 0 });
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k] = 0;

        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : times) {
            Edge temp = new Edge(edge[1], edge[2]);
            graph.get(edge[0]).add(temp);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- != 0) {
                int[] temp = queue.poll();

                for (Edge neighbor : graph.get(temp[0])) {
                    int newWt = temp[1] + neighbor.wt;

                    if (dis[neighbor.dest] > newWt) {
                        queue.add(new int[] { neighbor.dest, newWt });
                        dis[neighbor.dest] = newWt;
                    }

                }

            }
        }

        int res = 0;

        for (int i = 1; i < dis.length; i++) {
            if (dis[i] == Integer.MAX_VALUE) {
                return -1;
            }

            res = Math.max(res, dis[i]);
        }

        return res;
    }
}
