/**
 * 889. Construct Binary Tree from Preorder and Postorder Traversal
 * Difficulty: Medium | Tags: Array, Hash Table, Divide and Conquer, Tree,
 * Binary Tree
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postord
 * er-traversal/
 *
 * Pattern: Recursive Divide-and-Conquer with Hash Map Lookup
 *
 * Key insight: Preorder's second element is always the left child; look it up
 * in postorder to find the left-subtree boundary, then split both arrays. The
 * root is always consumed last in each postorder sub-range (end - 1).
 *
 * Time Complexity: O(n) - Each node is constructed once; hash map gives O(1)
 * index lookups.
 *
 * Space Complexity: O(n) - Hash map stores n entries; recursion depth is O(h).
 *
 * Edge Cases Handled: single node (st == end short-circuit), st > end or
 * preIndex out-of-bounds returns null, left-only or right-only skewed chains,
 * full binary tree
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
class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    private static int preIndex = 0;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        Map<Integer, Integer> postorderMap = new HashMap<>();
        preIndex = 0;

        for (int i = 0; i < postorder.length; i++) {
            postorderMap.put(postorder[i], i);
        }
        return helper(postorderMap, preorder, postorder, 0, postorder.length - 1);
    }

    private static TreeNode helper(Map<Integer, Integer> postorderMap, int[] preorder, int[] postorder, int st,
            int end) {
        if (st > end || preIndex >= postorder.length) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preIndex++]);

        if (st == end) {
            return root;
        }

        int leftIn = preorder[preIndex];
        int mid = postorderMap.get(leftIn);

        root.left = helper(postorderMap, preorder, postorder, st, mid);
        root.right = helper(postorderMap, preorder, postorder, mid + 1, end - 1);
        return root;
    }
}
