/**
 * 785. Is Graph Bipartite?
 * Difficulty: Medium | Tags: Depth-First Search, Breadth-First Search,
 * Union-Find, Graph Theory, Graph Coloring, Bipartite Graph
 * https://leetcode.com/problems/is-graph-bipartite/
 *
 * Pattern: DFS 2-Coloring (Bipartite Verification)
 *
 * Key insight: Iterate over all nodes (handling disconnected components) and
 * DFS-color each unvisited node with 0 or 1, forcing neighbors to the opposite
 * color. A same-color neighbor immediately proves an odd cycle exists, so the
 * graph is not bipartite.
 *
 * Time Complexity: O(V + E) - Each node and edge visited exactly once across
 * all connected components
 *
 * Space Complexity: O(V) - Color array of size V; recursion stack up to V deep
 * in worst case
 *
 * Edge Cases Handled: disconnected components (each independently colored from
 * unvisited nodes), self-loop (neighbor == current, same color detected
 * immediately), empty graph with n = 0 (vacuously bipartite)
 */
class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);

        for (int i = 0; i < color.length; i++) {
            if (color[i] == -1) {
                if (!check(i, 0, graph, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean check(int node, int nodeColor, int[][] graph, int[] color) {
        color[node] = nodeColor;

        for (int neighbor : graph[node]) {
            if (color[neighbor] == -1) {
                if (!check(neighbor, color[node] == 0 ? 1 : 0, graph, color)) {
                    return false;
                }
            } else if (color[node] == color[neighbor]) {
                return false;
            }
        }
        return true;
    }
}
