/**
 * 637. Average of Levels in Binary Tree
 * Difficulty: Easy | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 *
 * Pattern: BFS Level-Order Aggregation
 *
 * Key insight: Snapshot the level size, accumulate values into a long sum to
 * prevent integer overflow, then cast to double for precise division. The long
 * accumulator handles up to 10^5 nodes at max val.
 *
 * Time Complexity: O(n) - Each node enqueued and dequeued once; per-level sum
 * is O(w).
 *
 * Space Complexity: O(w) - Queue holds at most one level's width of nodes.
 *
 * Edge Cases Handled: null root returns empty list, single node, skewed tree,
 * all-negative values, large node counts per level (long sum prevents
 * overflow), division by level count cast to double
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
class AverageOfLevelsInBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int temp = size;
            long sum = 0;
            while (size != 0) {
                TreeNode curr = queue.poll();
                sum += curr.val;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                size--;
            }
            result.add((double) sum / temp);
        }
        return result;
    }
}
