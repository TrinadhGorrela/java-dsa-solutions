/**
 * 102. Binary Tree Level Order Traversal
 * Difficulty: Medium | Tags: Tree, Breadth-First Search, Binary Tree
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * Pattern: BFS with Level-Size Snapshot
 *
 * Key insight: Snapshot queue.size() before the inner loop to isolate exactly
 * one level's nodes — children enqueued during processing land in the next
 * level's batch, keeping levels cleanly separated.
 *
 * Time Complexity: O(n) - Each node enqueued and dequeued exactly once.
 *
 * Space Complexity: O(w) - Queue width equals the maximum number of nodes at
 * any single level.
 *
 * Edge Cases Handled: null root returns empty list, single node, single-level
 * tree, skewed tree (one node per level), complete binary tree (widest level at
 * bottom)
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
class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (size != 0) {
                TreeNode curr = queue.poll();
                temp.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                size--;
            }
            result.add(temp);
        }
        return result;
    }
}
