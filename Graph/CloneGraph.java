/**
 * 133. Clone Graph
 * Difficulty: Medium | Tags: Hash Table, Depth-First Search, Breadth-First
 * Search, Graph Theory
 * https://leetcode.com/problems/clone-graph/
 *
 * Pattern: DFS with Memoization Map
 *
 * Key insight: Clone each node on first visit and store it in a value-keyed
 * map. When DFS encounters a neighbor already in the map, it reuses the
 * existing clone instead of creating a duplicate, which both breaks cycles and
 * avoids redundant work. The map serves as both a visited set and a clone
 * registry.
 *
 * Time Complexity: O(V + E) - Each node cloned once; each edge traversed once
 * to link neighbors
 *
 * Space Complexity: O(V) - HashMap stores V cloned nodes; recursion stack up to
 * V deep
 *
 * Edge Cases Handled: null input node (returns null immediately), self-loop
 * (map lookup returns the node's own clone), cycle in graph (map prevents
 * infinite recursion), single isolated node with no neighbors (cloned with
 * empty neighbor list)
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class CloneGraph {
    public Node cloneGraph(Node node) {
        Map<Integer, Node> visited = new HashMap<>();
        return dfs(visited, node);
    }

    private Node dfs(Map<Integer, Node> visited, Node node) {
        if (node == null) {
            return null;
        }

        if (visited.containsKey(node.val)) {
            return visited.get(node.val);
        }

        Node clone = new Node(node.val);
        visited.put(node.val, clone);

        for (Node i : node.neighbors) {
            if (!visited.containsKey(i.val)) {
                clone.neighbors.add(dfs(visited, i));
            } else {
                clone.neighbors.add(visited.get(i.val));
            }
        }
        return clone;
    }
}
