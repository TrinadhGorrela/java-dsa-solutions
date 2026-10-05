/**
 * 100. Same Tree
 * Difficulty: Easy | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/same-tree/
 *
 * Pattern: Recursive Structural Equality
 *
 * Key insight: Simultaneously walk both trees in pre-order; fail immediately on
 * any structural mismatch (one null, one not) or value mismatch, otherwise
 * recurse into both children. No hash map needed.
 *
 * Time Complexity: O(n) - Each node is compared at most once; early termination
 * on mismatch.
 *
 * Space Complexity: O(h) - Recursion depth is the minimum height of the two
 * trees.
 *
 * Edge Cases Handled: both null roots, one null and one non-null, same values
 * but different structure, different values at same position, single-node
 * trees, deep left-only vs right-only chains
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
class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
