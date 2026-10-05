/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Difficulty: Medium | Tags: Tree, Breadth-First Search, Binary Tree
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * Pattern: BFS Level-Order with Direction Flip
 *
 * Key insight: Standard BFS collects each level left-to-right; on even-numbered
 * levels (2nd, 4th, ...), reverse the collected list before adding it. Uses a
 * level counter modulo 2 rather than a deque-based insertion trick.
 *
 * Time Complexity: O(n) - Each node visited once; reversal is O(w) per level,
 * summing to O(n).
 *
 * Space Complexity: O(w) - Queue holds at most one level's worth of nodes (w =
 * max width).
 *
 * Edge Cases Handled: null root returns empty list, single node (no reversal),
 * single-level tree, skewed tree (every level has one node, reversal is a
 * no-op), perfect binary tree (full reversal on even levels)
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
class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 1;

        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
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
            if (level % 2 == 0) {
                Collections.reverse(temp);
            }
            level++;
            result.add(temp);
        }
        return result;
    }
}
