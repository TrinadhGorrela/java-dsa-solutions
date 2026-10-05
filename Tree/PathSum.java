/**
 * 112. Path Sum
 * Difficulty: Easy | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/path-sum/
 *
 * Pattern: DFS with Running Subtraction
 *
 * Key insight: Decrement targetSum by each node's value on the way down; a
 * valid path exists only when the remaining sum reaches zero at a leaf (both
 * children null), not at an internal node.
 *
 * Time Complexity: O(n) - Worst case visits every node if no valid path exists.
 *
 * Space Complexity: O(h) - Recursion stack height equals tree height.
 *
 * Edge Cases Handled: null root returns false, single node equal to target,
 * path ending at non-leaf (intermediate zero rejected), negative values on
 * path, skewed tree requiring traversal to deepest leaf
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
class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return pathSum(root, targetSum);
    }

    public static boolean pathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }

        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            return true;
        }
        return pathSum(root.left, target) || pathSum(root.right, target);
    }
}
