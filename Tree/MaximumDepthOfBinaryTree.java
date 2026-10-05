/**
 * 104. Maximum Depth of Binary Tree
 * Difficulty: Easy | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * Pattern: Post-order DFS Height Calculation
 *
 * Key insight: Return 0 for null nodes; otherwise the depth is 1 +
 * max(leftDepth, rightDepth). The max naturally handles unbalanced subtrees
 * without special-casing.
 *
 * Time Complexity: O(n) - Every node is visited exactly once.
 *
 * Space Complexity: O(h) - Recursion stack depth is tree height; O(log n)
 * balanced, O(n) skewed.
 *
 * Edge Cases Handled: null root returns 0, single node returns 1, left-only
 * chain, right-only chain, complete binary tree, highly unbalanced tree
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
class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
