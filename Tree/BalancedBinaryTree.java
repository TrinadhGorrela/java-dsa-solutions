/**
 * 110. Balanced Binary Tree
 * Difficulty: Easy | Tags: Tree, Depth-First Search, Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree/
 *
 * Pattern: Recursive Divide & Conquer (Height Comparison at Every Node)
 * Key insight: A tree is balanced exactly when, at every node, the heights of
 * its two
 * subtrees differ by at most 1. Each recursive call answers "how tall is this
 * subtree?"
 * and compares, so no extra state or global flag is needed - balance is a
 * purely local
 * property, which is why the whole problem reduces to one helper, checkHeight.
 * Time Complexity: O(N^2) worst case - heights are recomputed from scratch for
 * every
 * node, so a degenerate left-leaning chain of N nodes re-walks up to O(N)
 * ancestors per
 * node; it degrades to O(N) on a perfectly balanced tree.
 * Space Complexity: O(H) - only the recursive call stack shared by isBalanced
 * and
 * checkHeight, where H is the tree height (O(N) in the skewed worst case, O(log
 * N) when
 * balanced).
 * Edge Cases Handled: null/empty root returns true; single-node tree; node with
 * only
 * one child; fully degenerate single chain (the worst case for the complexity
 * above);
 * exactly at the threshold (difference of 1 is accepted, 2 is rejected). Honest
 * limitation: no memoization, so this is O(N^2) instead of the optimal
 * single-pass O(N).
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
class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(checkHeight(root.left) - checkHeight(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);

    }

    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(checkHeight(node.left), checkHeight(node.right));
    }
}
