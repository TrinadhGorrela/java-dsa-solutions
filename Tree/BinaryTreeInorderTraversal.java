/**
 * 94. Binary Tree Inorder Traversal
 * Difficulty: Easy | Tags: Stack, Tree, Depth-First Search, Binary Tree
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 * Pattern: Recursive DFS (Left-Root-Right)
 *
 * Key insight: Visit the root between its left and right subtrees — for a BST
 * this yields values in ascending sorted order; the left-first descent ensures
 * all smaller values appear before the root.
 *
 * Time Complexity: O(n) - Each node appended exactly once.
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
class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list;
    }

    public static void traversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        traversal(root.left, res);
        res.add(root.val);
        traversal(root.right, res);
    }
}
