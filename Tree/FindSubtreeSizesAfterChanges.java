/**
 * 3331. Find Subtree Sizes After Changes
 * Difficulty: Medium | Tags: Array, Hash Table, String, Tree, Depth-First
 * Search
 * https://leetcode.com/problems/find-subtree-sizes-after-changes/
 *
 * Pattern: Tree DFS (Ancestor Rewiring Pass + Subtree-Size Pass)
 *
 * Key insight: Only the nearest ancestor sharing a node's character can change
 * its parent, so a DFS remembers the last-seen node per letter and restores the
 * previous owner on exit. That rewires the tree in place, after which a second
 * post-order DFS sums subtree sizes bottom-up.
 *
 * Time Complexity: O(N) - Each DFS visits every node and edge once with O(1)
 * letter-table lookups; N is the node count.
 *
 * Space Complexity: O(N) - Two adjacency lists, the cloned parent array, the
 * answer array, and the recursion stack on a skewed tree all scale with N.
 *
 * Edge Cases Handled: single-node tree (no rewiring, size 1); root with no
 * matching ancestor; repeated labels on one root-to-leaf path (rewire to
 * nearest ancestor); the same label in different branches (backtracking
 * restores the correct ancestor); all labels distinct (tree unchanged); nearest
 * same-label ancestor is the direct parent. Input is assumed to be a valid
 * rooted tree with lowercase 'a'-'z' labels.
 */
class FindSubtreeSizesAfterChanges {
    public int[] findSubtreeSizes(int[] parent, String s) {
        List<List<Integer>> list = new ArrayList<>();
        int[] arr = new int[26];
        int[] newParent = new int[parent.length];

        newParent = parent.clone();
        Arrays.fill(arr, -1);

        for (int i = 0; i < parent.length; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 1; i < parent.length; i++) {
            list.get(parent[i]).add(i);
        }

        dfs(list, arr, newParent, s, 0);

        List<List<Integer>> newList = new ArrayList<>();
        int[] ans = new int[parent.length];

        for (int i = 0; i < parent.length; i++) {
            newList.add(new ArrayList<>());
        }

        for (int i = 1; i < parent.length; i++) {
            newList.get(newParent[i]).add(i);
        }

        helper(newList, ans, 0);

        return ans;
    }

    private void dfs(List<List<Integer>> list, int[] arr, int[] newParent, String s, int node) {
        int t = s.charAt(node) - 'a';

        if (arr[t] != -1) {
            newParent[node] = arr[t];
        }

        int prev = arr[t];
        arr[t] = node;

        for (int i : list.get(node)) {
            dfs(list, arr, newParent, s, i);
        }

        arr[t] = prev;
    }

    private int helper(List<List<Integer>> newList, int[] ans, int node) {
        int size = 1;

        for (int i : newList.get(node)) {
            size += helper(newList, ans, i);
        }

        ans[node] = size;

        return size;
    }
}
