/**
 * 98. Validate Binary Search Tree
 * Difficulty: Medium | Tags: Tree, Depth-First Search, Binary Search Tree,
 * Binary Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Pattern: Depth-First Search with Range Constraints
 *
 * Key insight: Each node must lie strictly within a valid (min, max) interval
 * that tightens as you descend; the left child's upper bound becomes the
 * parent's value and the right child's lower bound becomes the parent's value,
 * enforcing the global BST ordering invariant — not just a local parent-child
 * check.
 *
 * Time Complexity: O(N) - Each node is visited exactly once in a single DFS
 * pass.
 *
 * Space Complexity: O(H) - Recursion stack depth equals tree height; O(log N)
 * balanced, O(N) worst case (skewed).
 *
 * Edge Cases Handled: null/empty tree (returns true); single-node tree;
 * duplicate values in subtree (strict >= and <= comparisons reject equal
 * values); deeply nested invalid nodes deep in left or right subtree;
 * all-same-value input (rejected immediately at first non-root duplicate).
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
class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    private static boolean helper(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        if (min != null && min >= node.val) {
            return false;
        }

        if (max != null && max <= node.val) {
            return false;
        }

        return helper(node.left, min, node.val) && helper(node.right, node.val, max);

    }
}
