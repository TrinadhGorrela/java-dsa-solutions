/**
 * 1971. Find if Path Exists in Graph
 * Difficulty: Easy | Tags: Depth-First Search, Breadth-First Search,
 * Union-Find, Graph Theory
 * https://leetcode.com/problems/find-if-path-exists-in-graph/
 *
 * Pattern: BFS Reachability Check
 *
 * Key insight: Build an undirected adjacency list from edges, then BFS from
 * source. Since we only need to answer yes/no (not shortest path), we can check
 * `curr == destination` on dequeue for early termination. Visited set prevents
 * revisiting nodes in cyclic or densely connected graphs.
 *
 * Time Complexity: O(V + E) - Adjacency list construction is O(E); BFS visits
 * each reachable node and edge once
 *
 * Space Complexity: O(V + E) - Adjacency list stores 2E entries; visited set
 * and queue hold up to V nodes
 *
 * Edge Cases Handled: source equals destination (immediate return true),
 * destination in a disconnected component (BFS exhausts without finding it,
 * returns false), single-node graph with no edges (source == destination),
 * empty edge list with n > 1
 */
class FindIfPathExistsInGraph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] i : edges) {
            addEdge(graph, i[0], i[1]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int curr = queue.poll();

                if (curr == destination) {
                    return true;
                }

                for (int i : graph.get(curr)) {
                    if (!visited.contains(i)) {
                        queue.offer(i);
                        visited.add(i);
                    }
                }
                size--;
            }
        }
        return false;
    }

    static void addEdge(List<List<Integer>> graph, int u, int v) {
        List<Integer> adjU = graph.get(u);
        List<Integer> adjV = graph.get(v);
        adjU.add(v);
        adjV.add(u);
    }
}
