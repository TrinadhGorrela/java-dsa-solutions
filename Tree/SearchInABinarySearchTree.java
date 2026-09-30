/**
 * 700. Search in a Binary Search Tree
 * Difficulty: Easy | Tags: Tree, Binary Search Tree, Binary Tree
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 *
 * Pattern: Iterative BST Search (Iterative Two-Branch Descent)
 * Key insight: The BST ordering invariant guarantees that if val is smaller than the current node's
 * value it cannot exist in the right subtree, so exactly one subtree can be discarded per step. This
 * halves the search space at every hop, and the search ends the moment it walks off the tree, which
 * is the signal that the key is absent.
 *
 * Time Complexity: O(H) - H is the tree height; O(log N) on a balanced tree, O(N) on a degenerate
 *                  one-node-per-level tree, O(1) for an empty tree
 * Space Complexity: O(1) - Iterative descent keeps only a single node reference, no stack or queue
 *
 * Edge Cases Handled: Empty tree (root == null skips the loop, returns null); key smaller than the
 * root (descends left to null); key larger than the root (descends right to null); key equal to the
 * root (immediate return); target absent from the tree (walk-off yields null); skewed/chain trees;
 * duplicate keys (returns the first matching node encountered on the descent). NOT handled: null
 * input tree (violates the problem's guarantee); only valid for values the tree actually stores, and
 * it never mutates the input tree.
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class SearchInABinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                return root;
            } else if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return null;
    }
}
