/**
 * 145. Binary Tree Postorder Traversal
 * Difficulty: Easy | Tags: Stack, Tree, Depth-First Search, Binary Tree
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 * Pattern: Recursive DFS (Left-Right-Root)
 *
 * Key insight: Defer appending the node's value until both subtrees are fully
 * visited — naturally produces bottom-up order used in expression tree
 * evaluation and dependency resolution.
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
class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list;
    }

    public static void traversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        traversal(root.left, res);
        traversal(root.right, res);
        res.add(root.val);
    }
}
