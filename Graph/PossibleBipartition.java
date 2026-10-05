/**
 * 886. Possible Bipartition
 * Difficulty: Medium | Tags: Depth-First Search, Breadth-First Search,
 * Union-Find, Graph Theory, Graph Coloring, Bipartite Graph
 * https://leetcode.com/problems/possible-bipartition/
 *
 * Pattern: DFS Graph 2-Coloring (Bipartite Check)
 *
 * Key insight: Build an undirected dislike graph and attempt to 2-color it via
 * DFS. If any neighbor has the same color as the current node, an odd cycle
 * exists and partitioning is impossible. Iterating over adjacency list keys
 * naturally skips isolated nodes (no dislikes).
 *
 * Time Complexity: O(V + E) - DFS visits each person and dislike edge once; V =
 * number of people with dislikes, E = number of dislike pairs
 *
 * Space Complexity: O(V + E) - Adjacency list plus group color array of size
 * n+1
 *
 * Edge Cases Handled: empty dislikes array (trivially bipartite), n = 1 with no
 * dislikes (true), disconnected components (each independently colored from
 * unvisited keys), odd-cycle conflict detected via same-color neighbor
 */
class PossibleBipartition {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] groups = new int[n + 1];

        for (int[] dislike : dislikes) {
            adjList.putIfAbsent(dislike[0], new ArrayList<>());
            adjList.putIfAbsent(dislike[1], new ArrayList<>());
            adjList.get(dislike[0]).add(dislike[1]);
            adjList.get(dislike[1]).add(dislike[0]);
        }

        for (int i : adjList.keySet()) {
            if (groups[i] == 0) {
                if (!helper(i, 1, adjList, groups)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean helper(int curr, int group, Map<Integer, List<Integer>> adjList, int[] groups) {
        groups[curr] = group;

        for (int i : adjList.get(curr)) {
            if (groups[i] == 0) {
                if (!helper(i, groups[curr] == 1 ? 2 : 1, adjList, groups)) {
                    return false;
                }
            } else if (groups[i] == groups[curr]) {
                return false;
            }
        }

        return true;
    }
}
