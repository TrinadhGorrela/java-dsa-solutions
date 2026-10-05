/**
 * 199. Binary Tree Right Side View
 * Difficulty: Medium | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * Pattern: BFS Level-Order with Last-Node Capture
 *
 * Key insight: Snapshot the queue size for each level; the node dequeued when
 * size reaches 1 (the final node of that level) is the rightmost visible node —
 * children are enqueued first so left/right order is preserved.
 *
 * Time Complexity: O(n) - Each node is enqueued and dequeued exactly once.
 *
 * Space Complexity: O(w) - Queue holds at most one level's width of nodes.
 *
 * Edge Cases Handled: null root returns empty list, single node, missing right
 * child (falls back to leftmost as visible), skewed tree, complete binary tree,
 * single-level root only
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
class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                TreeNode curr = queue.poll();

                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                if (size == 1) {
                    result.add(curr.val);
                }
                size--;
            }
        }
        return result;
    }
}
