/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Difficulty: Medium | Tags: Array, Hash Table, Divide and Conquer, Tree,
 * Binary Tree
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder
 * -traversal/
 *
 * Pattern: Recursive Divide-and-Conquer with Hash Map Lookup
 *
 * Key insight: Each preorder element is the root of the current subtree; look
 * it up in the inorder map to find the split point — everything left of mid
 * goes to the left subtree, everything right to the right. A global preIndex
 * advances sequentially through preorder.
 *
 * Time Complexity: O(n) - Each node constructed once; hash map eliminates
 * linear inorder scans.
 *
 * Space Complexity: O(n) - Hash map for inorder indices plus O(h) recursion
 * stack.
 *
 * Edge Cases Handled: single node, boundary guards (st < 0, end > length, st >
 * end), left-only or right-only skewed chains, duplicate-value-free trees
 * (required for unique reconstruction)
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
class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private static int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        preIndex = 0;

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return helper(inorderMap, preorder, 0, inorder.length - 1);
    }

    private static TreeNode helper(Map<Integer, Integer> inorderMap, int[] preorder, int st, int end) {
        if (st < 0 || end > preorder.length || st > end) {
            return null;
        }

        int mid = inorderMap.get(preorder[preIndex]);
        TreeNode root = new TreeNode(preorder[preIndex++]);
        root.left = helper(inorderMap, preorder, st, mid - 1);
        root.right = helper(inorderMap, preorder, mid + 1, end);
        return root;
    }
}
