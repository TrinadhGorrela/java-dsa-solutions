/**
 * 3970. Shortest Path With At Most K Consecutive Identical Characters
 * Difficulty: Medium | Tags: String, Graph Theory, Heap (Priority Queue),
 * Shortest Path
 * https://leetcode.com/problems/shortest-path-with-at-most-k-consecutive-identi
 * cal-characters/
 *
 * Pattern: Dijkstra's Algorithm + State Augmentation (Node + Consecutive-Label
 * Run Length)
 *
 * Key insight: Distance-to-node alone is insufficient here, because a longer
 * current run of identical labels may be unusable later; tracking (node,
 * current consecutive identical count) as the state restores optimal
 * substructure. Dijkstra then pops states in increasing distance, so the first
 * state reaching node n-1 is the global shortest path.
 *
 * Time Complexity: O(E * K log(V * K)) - Each edge is relaxed for each of the K
 * run-length states, with priority-queue operations over V*K states.
 *
 * Space Complexity: O(V * K + E) - The distance table holds V*(K+1) entries and
 * the adjacency list stores E directed edges.
 *
 * Edge Cases Handled: unreachable target (returns -1), single-node graph
 * (returns 0 from the start state), runs longer than K are pruned, non-matching
 * labels reset the run to 1; k < 1 is not safe because dis[u][1] and the
 * initial count assume at least one allowed consecutive character, and edges
 * are treated as directed since only u -> v is added.
 */
class ShortestPathWithAtMostKConsecutiveIdenticalCharacters {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<List<int[]>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adjList.get(u).add(new int[] { v, w });
        }

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int[][] dis = new int[n][k + 1];

        for (int[] i : dis) {
            Arrays.fill(i, Integer.MAX_VALUE);
        }

        queue.add(new int[] { 0, 0, 1 });
        dis[0][1] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int u = curr[0];
            int d = curr[1];
            int count = curr[2];

            if (u == n - 1) {
                return d;
            }

            if (d > dis[u][count]) {
                continue;
            }

            for (int[] neighbour : adjList.get(u)) {
                int v = neighbour[0];
                int wt = neighbour[1];

                int newCount = (labels.charAt(u) == labels.charAt(v)) ? count + 1 : 1;

                if (newCount <= k && d + wt < dis[v][newCount]) {
                    dis[v][newCount] = d + wt;
                    queue.offer(new int[] { v, d + wt, newCount });
                }
            }
        }

        return -1;
    }
}
