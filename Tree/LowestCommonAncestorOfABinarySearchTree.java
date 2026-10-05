/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Difficulty: Medium | Tags: Tree, Depth-First Search, Binary Search Tree,
 * Binary Tree, Binary Lifting, Lowest Common Ancestor
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Pattern: BST Traversal (Exploiting BST Invariant)
 *
 * Key insight: In a BST all nodes in the left subtree are smaller and all in
 * the right subtree are larger, so the LCA is the first node where p and q
 * diverge to different subtrees—or the node itself if one equals root. Walking
 * left when both are smaller and right when both are larger converges on the
 * LCA in O(h) time.
 *
 * Time Complexity: O(h) where h is the tree height - O(log N) on a balanced
 * BST; O(N) worst case if degenerate.
 *
 * Space Complexity: O(1) - An iterative pointer walk with no recursion or
 * auxiliary data structures.
 *
 * Edge Cases Handled: p or q equals the root (returned immediately as LCA);
 * both nodes lie in the same subtree and the tree is degenerate (traversal
 * walks down a single chain); null root (returns null).
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class LowestCommonAncestorOfABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }

        return null;
    }
}
