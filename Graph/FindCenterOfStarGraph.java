/**
 * 1791. Find Center of Star Graph
 * Difficulty: Easy | Tags: Graph Theory
 * https://leetcode.com/problems/find-center-of-star-graph/
 *
 * Pattern: Degree Counting on First Edge
 * Key insight: In a valid star graph the center node appears in every edge. The center must be one of the two endpoints
 * of the first edge; count how many times each appears across all edges and return the one with count == E (appears in
 * all edges). No full degree map needed.
 *
 * Time Complexity: O(E) - Single pass over all edges, checking two endpoints per edge
 * Space Complexity: O(1) - Only four integer variables (two endpoints, two counters)
 *
 * Edge Cases Handled: minimal star graph (single edge, either endpoint is center), both endpoints of first edge checked
 * against all edges to find the true center
 */
class FindCenterOfStarGraph {
    public int findCenter(int[][] edges) {
        int firstNode = edges[0][0];
        int secondNode = edges[0][1];

        int countFirstNode = 0;
        int countSecondNode = 0;

        for (int[] edge : edges) {
            if (edge[0] == firstNode || edge[1] == firstNode) {
                countFirstNode++;
            }

            if (edge[0] == secondNode || edge[1] == secondNode) {
                countSecondNode++;
            }
        }

        if (countFirstNode > countSecondNode) {
            return firstNode;
        }

        return secondNode;
    }
}
