/**
 * 144. Binary Tree Preorder Traversal
 * Difficulty: Easy | Tags: Stack, Tree, Depth-First Search, Binary Tree
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 *
 * Pattern: Recursive DFS (Root-Left-Right)
 *
 * Key insight: Visit the root before its children, then recurse left then right
 * — produces a top-down traversal useful for serialization and expression tree
 * prefix notation.
 *
 * Time Complexity: O(n) - Each node is appended to the result list exactly
 * once.
 *
 * Space Complexity: O(h) - Recursion stack depth equals tree height.
 *
 * Edge Cases Handled: null root returns empty list, single node, left-only
 * chain, right-only chain, leaf nodes (null children terminate recursion)
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
class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list;
    }

    public static void traversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        res.add(root.val);
        traversal(root.left, res);
        traversal(root.right, res);
    }
}
