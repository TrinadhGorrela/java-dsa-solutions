/**
 * 543. Diameter of Binary Tree
 * Difficulty: Easy | Tags: Tree, Depth-First Search, Binary Tree, DP on Trees
 * https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * Pattern: Post-order DFS (Height Computation on Trees)
 *
 * Key insight: The diameter through a node equals the sum of its left and right
 * subtree heights, and the longest path always passes through some node - so a
 * single post-order traversal that returns each subtree's height while tracking
 * the global maximum of leftHeight + rightHeight finds the diameter in one
 * pass.
 *
 * Time Complexity: O(N) - Visits every node exactly once
 *
 * Space Complexity: O(H) - Recursion stack depth equals tree height (O(N) worst
 * case for a skewed tree, O(log N) for a balanced one)
 *
 * Edge Cases Handled: null root (returns 0), single node (diameter 0), left- or
 * right-skewed trees, diameter not passing through the root, repeated calls on
 * the same instance (maxDiameter is reset at the start of each invocation)
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
class DiameterOfBinaryTree {
    int maxDiameter;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        height(root);

        return maxDiameter;
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
