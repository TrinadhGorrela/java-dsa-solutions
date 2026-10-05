/**
 * 111. Minimum Depth of Binary Tree
 * Difficulty: Easy | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 *
 * Pattern: Post-order DFS with Single-Child Guard
 *
 * Key insight: A depth-1 leaf (both children null) is the base case; when a
 * node has only one non-null child, skip the zero-depth null side and return 1
 * + the non-null child's depth, preventing a skewed tree from reporting depth
 * 1.
 *
 * Time Complexity: O(n) - Visits each node once in the worst case.
 *
 * Space Complexity: O(h) - Recursion stack depth equals tree height.
 *
 * Edge Cases Handled: null root returns 0, single node returns 1, left-only
 * chain, right-only chain, perfectly balanced tree, internal node with one null
 * child (must not be treated as leaf)
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
class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left == 0) {
            return 1 + right;
        }

        if (right == 0) {
            return 1 + left;
        }
        return 1 + Math.min(left, right);
    }
}
