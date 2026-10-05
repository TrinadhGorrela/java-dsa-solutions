/**
 * 101. Symmetric Tree
 * Difficulty: Easy | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/symmetric-tree/
 *
 * Pattern: Recursive Mirror Check
 *
 * Key insight: Treat symmetry as a simultaneous two-pointer walk: compare
 * left.left with right.right and left.right with right.left at every step,
 * effectively folding the tree along its root.
 *
 * Time Complexity: O(n) - Each of the n nodes is visited exactly once in the
 * paired recursion.
 *
 * Space Complexity: O(h) - Recursion stack depth equals tree height h (log n
 * balanced, n worst-case).
 *
 * Edge Cases Handled: null root (trivially symmetric), single node, perfectly
 * balanced tree, left-only or right-only skewed chain (asymmetric), mismatched
 * values at mirror positions
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
class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isSym(root.left, root.right);
    }

    public static boolean isSym(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return isSym(left.right, right.left) && isSym(left.left, right.right);
    }
}
