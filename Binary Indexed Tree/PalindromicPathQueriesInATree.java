/**
 * 3841. Palindromic Path Queries in a Tree
 * Difficulty: Hard | Tags: Array, String, Divide and Conquer, Bit Manipulation,
 * Tree, Depth-First Search, Binary Indexed Tree, Segment Tree
 * https://leetcode.com/problems/palindromic-path-queries-in-a-tree/
 *
 * Pattern: Euler Tour + Binary Lifting (LCA) + XOR Fenwick Tree (BIT)
 *
 * Key insight: A path's letters form a palindrome exactly when the XOR of their
 * 26 letter-masks has at most one set bit. Storing each node's root-path mask
 * and applying a node's letter change as an Euler-tour range toggle on a BIT
 * keeps every query's mask correct in O(log N).
 *
 * Time Complexity: O((N + Q) log N) - O(N log N) preprocessing for the Euler
 * tour and binary-lifting table, then O(log N) per query for the LCA and BIT
 * operations.
 *
 * Space Complexity: O(N log N) - The binary-lifting table up[n][20] dominates;
 * masks, timestamps, and the BIT are each O(N).
 *
 * Edge Cases Handled: single-node tree and u == v queries, LCA lying on the
 * path (its letter XORed back once via lcaLetterMask), updates issued before
 * any query, repeated updates to the same node, lowercase-only input (masks
 * assume 'a'..'z')
 */
class PalindromicPathQueriesInATree {
    int timer = 0;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<List<Integer>> adjList = new ArrayList<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        List<Boolean> res = new ArrayList<>();

        int[][] up = new int[n][20];
        int[] startTime = new int[n];
        int[] endTime = new int[n];
        int[] masks = new int[n];
        int[] bit = new int[n + 1];
        timer = 0;

        helper(adjList, up, startTime, endTime, -1, 0, masks, chars, 0);

        for (int i = 0; i < queries.length; i++) {
            String[] temp = queries[i].split(" ");

            if (temp[0].equals("update")) {
                int u = Integer.parseInt(temp[1]);
                char newChar = temp[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (newChar - 'a');
                int changeMask = oldMask ^ newMask;

                updateBIT(startTime[u] + 1, changeMask, bit);
                updateBIT(endTime[u] + 1, changeMask, bit);

                chars[u] = newChar;
            } else {
                int u = Integer.parseInt(temp[1]);
                int v = Integer.parseInt(temp[2]);

                int trueMaskU = masks[u] ^ queryBIT(startTime[u] + 1, bit);
                int trueMaskV = masks[v] ^ queryBIT(startTime[v] + 1, bit);

                int lca = getLCA(u, v, up, startTime, endTime);
                int lcaLetterMask = 1 << (chars[lca] - 'a');

                int pathMask = trueMaskU ^ trueMaskV ^ lcaLetterMask;

                boolean isPalindrome = (pathMask & (pathMask - 1)) == 0;
                res.add(isPalindrome);
            }
        }

        return res;
    }

    private void helper(List<List<Integer>> adjList, int[][] up, int[] startTime, int[] endTime, int parent, int node,
            int[] masks, char[] chars, int currentMask) {

        int mask = 1 << (chars[node] - 'a');
        currentMask ^= mask;
        masks[node] = currentMask;

        up[node][0] = parent == -1 ? node : parent;

        for (int i = 1; i < 20; i++) {
            int curr = up[node][i - 1];
            up[node][i] = up[curr][i - 1];
        }

        startTime[node] = timer;
        timer += 1;

        for (int neighbor : adjList.get(node)) {
            if (neighbor == parent) {
                continue;
            }

            helper(adjList, up, startTime, endTime, node, neighbor, masks, chars, currentMask);
        }

        endTime[node] = timer;
    }

    private int getLCA(int u, int v, int[][] up, int[] startTime, int[] endTime) {
        if (isAncestor(u, v, startTime, endTime)) {
            return u;
        }

        if (isAncestor(v, u, startTime, endTime)) {
            return v;
        }

        for (int i = 19; i >= 0; i--) {
            int jumpCity = up[u][i];

            if (!isAncestor(jumpCity, v, startTime, endTime)) {
                u = jumpCity;
            }
        }

        return up[u][0];
    }

    private void updateBIT(int index, int val, int[] bit) {
        while (index < bit.length) {
            bit[index] ^= val;
            index += (index & -index);
        }
    }

    private boolean isAncestor(int a, int b, int[] startTime, int[] endTime) {
        if (startTime[a] <= startTime[b] && endTime[a] >= endTime[b]) {
            return true;
        }

        return false;
    }

    private int queryBIT(int index, int[] bit) {
        int res = 0;

        while (index > 0) {
            res ^= bit[index];
            index -= (index & -index);
        }

        return res;
    }
}
