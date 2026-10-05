/**
 * 2265. Count Nodes Equal to Average of Subtree
 * Difficulty: Medium | Tags: Tree, Depth-First Search, Binary Tree
 * https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
 *
 * Pattern: Post-Order DFS (Bottom-Up Aggregation)
 *
 * Key insight: Each recursive call returns the (sum, count) of its subtree, so
 * every node can compute its subtree average in O(1) as it unwinds — no
 * redundant recomputation needed.
 *
 * Time Complexity: O(N) - Every node is visited exactly once in the post-order
 * traversal
 *
 * Space Complexity: O(H) - Recursion stack depth equals the tree height (O(log
 * N) balanced, O(N) worst-case skewed)
 *
 * Edge Cases Handled: null root (returns 0 immediately), single-node tree (leaf
 * check increments count), integer division truncation (average is
 * floor-divided, so only exact matches count — e.g., a subtree sum of 5 with 2
 * nodes gives avg=2, not 2.5), static counter reset between calls via avg=0
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
class CountNodesEqualToAverageOfSubtree {
    static int avg = 0;

    public int averageOfSubtree(TreeNode root) {
        avg = 0;
        helper(root);

        return avg;
    }

    private static int[] helper(TreeNode node) {
        if (node == null) {
            return new int[] { 0, 0 };
        }

        if (node.left == null && node.right == null) {
            avg++;
            return new int[] { node.val, 1 };
        }

        int[] left = helper(node.left);
        int[] right = helper(node.right);

        int currAvg = (left[0] + right[0] + node.val) / (left[1] + right[1] + 1);

        if (currAvg == node.val) {
            avg++;
        }

        return new int[] { left[0] + right[0] + node.val, left[1] + right[1] + 1 };
    }
}
