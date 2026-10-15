/**
 * 323. Number of Connected Components in an Undirected Graph
 * Difficulty: Medium | Tags: Depth-First Search, Breadth-First Search,
 * Union-Find, Graph Theory
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected
 * -graph/
 *
 * Pattern: Graph DFS (Adjacency List + Visited Array)
 *
 * Key insight: Each unvisited node starts a new component, and DFS from it
 * marks exactly the nodes reachable from it — so counting how many times we
 * launch a DFS equals the number of connected components. The visited array
 * guarantees every node is claimed by exactly one DFS, preventing revisits and
 * cycles.
 *
 * Time Complexity: O(N + E) - Every node and every edge is traversed a constant
 * number of times while building the adjacency list and running the DFS passes
 *
 * Space Complexity: O(N + E) - Adjacency list stores 2E entries, plus O(N) for
 * the visited array and the recursion stack in the worst case (a path graph)
 *
 * Edge Cases Handled: empty edge list (returns n isolated components), n = 0
 * (returns 0), fully disconnected graph, already-connected graph (single DFS),
 * cycles and repeated visits guarded by the visited array; assumes node indices
 * are valid within [0, n) — out-of-range indices would throw
 */
class NumberOfConnectedComponentsInAnUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int res = 0;

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                res++;
                dfs(graph, visited, i);
            }
        }

        return res;
    }

    private void dfs(List<List<Integer>> graph, boolean[] visited, int node) {
        visited[node] = true;

        for (int neighbour : graph.get(node)) {
            if (!visited[neighbour]) {
                dfs(graph, visited, neighbour);
            }
        }
    }
}
