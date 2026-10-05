/**
 * 515. Find Largest Value in Each Tree Row
 * Difficulty: Medium | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 *
 * Pattern: BFS with Per-Level Maximum
 *
 * Key insight: Snapshot the queue size before processing each level, track the
 * max within that batch, then enqueue children for the next level. The running
 * max naturally handles any number of nodes per level.
 *
 * Time Complexity: O(n) - Each node is enqueued and dequeued once.
 *
 * Space Complexity: O(w) - Queue width equals the maximum number of nodes at
 * any level (up to n/2 at the widest).
 *
 * Edge Cases Handled: null root returns empty list, single node, skewed tree
 * (one node per level), all-negative values (Integer.MIN_VALUE initialization),
 * complete wide tree
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
class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while (size != 0) {
                TreeNode curr = queue.poll();
                if (max < curr.val) {
                    max = curr.val;
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                size--;
            }
            result.add(max);
        }
        return result;
    }
}
