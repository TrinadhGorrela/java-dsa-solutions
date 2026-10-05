/**
 * 450. Delete Node in a BST
 * Difficulty: Medium | Tags: Tree, Binary Search Tree, Binary Tree
 * https://leetcode.com/problems/delete-node-in-a-bst/
 *
 * Pattern: BST Search + Node Splice (In-Place Deletion)
 *
 * Key insight: When deleting a node with two children, replace it with its
 * in-order successor by wiring the left subtree's rightmost node (in-order
 * predecessor) to the deleted node's right child. This preserves the BST
 * property in one pass because every node in the detached right subtree is
 * larger than every node in the left subtree, so attaching the right subtree at
 * the left subtree's rightmost point maintains sort order.
 *
 * Time Complexity: O(H) where H is tree height - The search and deletion each
 * walk down one path to height H.
 *
 * Space Complexity: O(1) - An iterative traversal is used; findRight is the
 * only recursion (tail-call deep as the right spine of the tree, amortized O(H)
 * stack space).
 *
 * Edge Cases Handled: node not found in tree (root unchanged, dummy returned);
 * node has zero children (replaced with null); node has one child (child
 * returned directly); node has two children (left-subtree splice applied); key
 * equals root itself (root node replaced); null root (returns null).
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
class DeleteNodeInABst {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            return helper(root);
        }

        TreeNode dummy = root;

        while (root != null) {
            if (root.val > key) {
                if (root.left != null && root.left.val == key) {
                    root.left = helper(root.left);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                if (root.right != null && root.right.val == key) {
                    root.right = helper(root.right);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return dummy;
    }

    private static TreeNode helper(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }

        if (root.right == null) {
            return root.left;
        }

        TreeNode rightChild = root.right;
        TreeNode leftChild = root.left;
        TreeNode lastRight = findRight(leftChild);
        lastRight.right = rightChild;
        return root.left;
    }

    private static TreeNode findRight(TreeNode root) {
        if (root.right == null) {
            return root;
        }
        return findRight(root.right);
    }
}
