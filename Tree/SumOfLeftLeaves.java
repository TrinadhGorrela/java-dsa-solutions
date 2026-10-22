/**
 * 404. Sum of Left Leaves
 * Difficulty: Easy | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/sum-of-left-leaves/
 *
 * Pattern: Depth-First Search (Parent-Aware Recursive Traversal)
 *
 * Key insight: A leaf counts only because of where it hangs, so the decision
 * must be made by the parent: during a pre-order DFS an internal node adds its
 * left child's value exactly when that child has no children of its own.
 * Because the added child is itself a leaf, the recursive visit that later
 * reaches it stops immediately, so every left leaf is summed exactly once.
 *
 * Time Complexity: O(N) - Every node is visited once and each visit does O(1)
 * work (two null checks on the children)
 *
 * Space Complexity: O(H) - One stack frame per level of the tree (worst case
 * O(N) for a fully skewed tree); the running total is a single int field
 *
 * Edge Cases Handled: null root (returns 0); single-node tree (the root is a
 * leaf with no parent, so nothing is summed); root with only a right child (a
 * right child is never a left leaf); a left child that is itself a leaf (added
 * once by its parent and skipped by its own visit, so it is never double
 * counted); left leaves nested deeper (found by recursing into the left
 * subtree); the leaf test `root.left == null && root.right == null` relies on
 * `&&` binding tighter than `||`; the accumulator is reset at the start of
 * every public call, so repeated calls on the same instance stay correct (the
 * shared `sum` field is not thread-safe); the int total is safe because
 * LeetCode bounds the leaf sum to int range
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
class SumOfLeftLeaves {
    int sum;

    public int sumOfLeftLeaves(TreeNode root) {
        sum = 0;
        dfs(root);
        return sum;
    }

    private void dfs(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return;
        }

        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }

        dfs(root.left);
        dfs(root.right);
    }
}
