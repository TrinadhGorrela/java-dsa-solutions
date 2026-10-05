/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Difficulty: Medium | Tags: Tree, Depth-First Search, Binary Tree, Binary
 * Lifting, Lowest Common Ancestor
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Pattern: Recursive DFS (Post-Order) with Two-Match Return
 *
 * Key insight: The LCA is the deepest node at which both p and q exist in
 * opposite subtrees. A post-order traversal returns the first node matching
 * either p or q's value upward; if both children return non-null, the current
 * node is the LCA—this holds because any match in the left subtree is an
 * ancestor (or a child) of p, and the same for q.
 *
 * Time Complexity: O(N) - Every node is visited exactly once.
 *
 * Space Complexity: O(H) - Recursion stack depth equals tree height H (O(log N)
 * balanced, O(N) degenerate).
 *
 * Edge Cases Handled: null root (returns null); one node is ancestor of the
 * other (the ancestor node is found first and propagated upward); p and q are
 * in opposite subtrees at depth 2 (parent returned immediately); single-node
 * tree (returns null if neither target is present).
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
class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return ancestor(root, p.val, q.val);
    }

    private static TreeNode ancestor(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }

        if (root.val == p || root.val == q) {
            return root;
        }

        TreeNode left = ancestor(root.left, p, q);
        TreeNode right = ancestor(root.right, p, q);

        if ((left != null) && (right != null)) {
            return root;
        }

        return (left != null) ? left : right;
    }
}
