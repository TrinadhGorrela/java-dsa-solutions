/**
 * 107. Binary Tree Level Order Traversal II
 * Difficulty: Medium | Tags: Tree, Breadth-First Search, Binary Tree
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 *
 * Pattern: BFS Level-Order with Final Reverse
 *
 * Key insight: Run standard BFS (left-to-right, top-to-bottom) collecting one
 * list per level, then reverse the outer list at the end. Cheaper than using a
 * LinkedList with addFirst or a Deque.
 *
 * Time Complexity: O(n) - BFS visits each node once; the final reverse is O(d)
 * where d is tree depth.
 *
 * Space Complexity: O(w) - Queue holds at most max-width nodes; result list
 * holds all n values.
 *
 * Edge Cases Handled: null root returns empty list, single node, single-level
 * tree (reverse is no-op), skewed tree (each level has one node), deep balanced
 * tree
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
class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
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
        Collections.reverse(result);
        return result;
    }
}
