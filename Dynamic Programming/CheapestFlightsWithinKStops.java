/**
 * 787. Cheapest Flights Within K Stops
 * Difficulty: Medium | Tags: Dynamic Programming, Depth-First Search,
 * Breadth-First Search, Graph Theory, Heap (Priority Queue), Shortest Path
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 *
 * Pattern: BFS with Cost Pruning (SPFA-style Relaxation on a Stop-Limited
 * Graph)
 *
 * Key insight: A path is worth expanding only if it reaches a city cheaper than
 * any known cost, and the hop counter is checked against K before expanding -
 * so the queue simulates Bellman-Ford rounds without re-exploring dominated
 * states.
 *
 * Time Complexity: O(V * E) worst case - each edge is re-relaxed only on a
 * strict cost improvement (SPFA behavior); effectively near O(K * E) on typical
 * inputs
 *
 * Space Complexity: O(V + E) - Adjacency list holds all E edges; dist[], and
 * the queue of (stops, city, cost) triples scale with vertices and edges
 *
 * Edge Cases Handled: unreachable destination (returns -1 when dist stays
 * MAX_VALUE), empty flights array (same -1 path), src == dst (returns 0), paths
 * exceeding K stops discarded via the stops > k guard, no relaxation from
 * unexplored nodes so Integer.MAX_VALUE never overflows a cost sum
 */
class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Queue<int[]> queue = new LinkedList<>();

        // GRAPH (The "Filing Cabinet")
        // Array index = departure city.
        // Inside the list at that index = int[]{destination_city, flight_price}
        List<int[]>[] adj = new ArrayList[n];

        int[] dist = new int[n];
        queue.add(new int[] { 0, src, 0 });

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < flights.length; i++) {
            adj[flights[i][0]].add(new int[] { flights[i][1], flights[i][2] });
        }

        while (queue.size() != 0) {
            int[] temp = queue.poll();

            int stops = temp[0];
            int city = temp[1];
            int cost = temp[2];

            if (stops > k) {
                continue;
            }

            for (int[] neighbour : adj[city]) {
                int newCost = cost + neighbour[1];
                if (newCost < dist[neighbour[0]]) {
                    dist[neighbour[0]] = newCost;
                    queue.add(new int[] { stops + 1, neighbour[0], newCost });
                }
            }

        }

        if (dist[dst] == Integer.MAX_VALUE) {
            return -1;
        }

        return dist[dst];
    }
}
