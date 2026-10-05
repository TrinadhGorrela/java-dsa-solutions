/**
 * 547. Number of Provinces
 * Difficulty: Medium | Tags: Depth-First Search, Breadth-First Search,
 * Union-Find, Graph Theory
 * https://leetcode.com/problems/number-of-provinces/
 *
 * Pattern: Depth-First Search (Flood Fill over Connected Components)
 *
 * Key insight: Provinces are precisely the connected components of the graph
 * whose adjacency is the matrix, and one DFS from an unvisited city reaches
 * every city in its component and no other. So each city is chosen as a DFS
 * root at most once, and the province count is simply the number of times a new
 * flood fill had to be started.
 *
 * Time Complexity: O(N^2) - each of the N cities is visited once and each visit
 * scans its entire row of N entries, so N rows x N columns; the matrix is read
 * in place, never rebuilt.
 *
 * Space Complexity: O(N) - a boolean[] of length N plus a recursion stack that
 * can reach depth N when a whole province is one long chain.
 *
 * Edge Cases Handled: single city (1x1 returns 1); fully connected matrix
 * (returns 1); no connections at all (returns N, one trivial DFS per isolated
 * city); asymmetric or self-referencing entries such as isConnected[i][i] == 1
 * are harmless because the city is already marked visited before the scan.
 * Honest limitation: assumes a non-empty square matrix - it reads
 * isConnected[0].length, so an empty or ragged matrix throws.
 */
class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;

        for (int i = 0; i < visited.length; i++) {

            if (!visited[i]) {
                count++;
                visited[i] = true;
                dfs(isConnected, visited, i);
            }

        }

        return count;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int n) {

        for (int i = 0; i < isConnected[0].length; i++) {
            if (isConnected[n][i] == 1) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(isConnected, visited, i);
                }
            }
        }

    }
}
