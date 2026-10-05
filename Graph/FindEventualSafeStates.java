/**
 * 802. Find Eventual Safe States
 * Difficulty: Medium | Tags: Depth-First Search, Breadth-First Search, Graph
 * Theory, Topological Sort, Kosaraju's Algorithm, Tarjan's SCC Algorithm
 * https://leetcode.com/problems/find-eventual-safe-states/
 *
 * Pattern: DFS Cycle Detection (Three-State Marking)
 *
 * Key insight: Each node is in one of three states: unvisited, on the current
 * recursion path (recPath), or resolved. If DFS encounters a node still on the
 * recursion path, a cycle exists and the current branch is unsafe. A node is
 * marked safe only after all its descendants are confirmed safe (recursion
 * unwinds without detecting a cycle).
 *
 * Time Complexity: O(V + E) - Each node and edge processed once; visited array
 * prevents redundant DFS calls
 *
 * Space Complexity: O(V) - Three boolean arrays (visited, recPath, safe) of
 * size V; recursion stack up to V deep
 *
 * Edge Cases Handled: terminal nodes with no outgoing edges (immediately safe),
 * nodes on a cycle (recPath back-edge detection marks entire cycle unsafe),
 * self-loop (immediate cycle detection), disconnected components (each DFS call
 * starts from unvisited node)
 */
class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        boolean[] recPath = new boolean[graph.length];
        boolean[] safe = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                dfs(graph, visited, recPath, safe, i);
            }
        }

        for (int i = 0; i < safe.length; i++) {
            if (safe[i]) {
                res.add(i);
            }
        }
        return res;
    }

    public static boolean dfs(int[][] graph, boolean[] visited, boolean[] recPath, boolean[] safe, int curr) {
        visited[curr] = true;
        recPath[curr] = true;

        for (int neighbor : graph[curr]) {
            if (!visited[neighbor]) {
                if (dfs(graph, visited, recPath, safe, neighbor)) {
                    return true;
                }
            } else if (recPath[neighbor]) {
                return true;
            }
        }
        recPath[curr] = false;
        safe[curr] = true;
        return false;
    }
}
