/**
 * 1339. Maximum Product of Splitted Binary Tree
 * Difficulty: Medium | Tags: Tree, Depth-First Search, Binary Tree
 * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
 *
 * Pattern: Post-Order DFS + Subtree Sum Enumeration
 *
 * Key insight: Cutting any edge splits the tree into a subtree and its
 * complement (total - subtree), so once the total sum is known each subtree sum
 * directly yields a candidate product; a single post-order traversal records
 * them all.
 *
 * Time Complexity: O(N) - One DFS visits each node once, followed by a linear
 * scan over the N recorded subtree sums.
 *
 * Space Complexity: O(N) - allSums stores one value per node, plus O(H)
 * recursion depth (O(N) for a skewed tree).
 *
 * Edge Cases Handled: empty tree returns 0, single-node tree (one side is
 * empty, so product 0), skewed/deep trees, repeated calls on the same instance
 * (allSums is cleared), and overflow-safe products via long before the final
 * mod.
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
class MaximumProductOfSplittedBinaryTree {
    List<Long> allSums = new ArrayList<>();
    int mod = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        allSums.clear();
        long max = 0;
        long total = calculateSum(root);

        for (long i : allSums) {
            long rem = total - i;

            max = Math.max(max, rem * i);
        }

        return (int) (max % mod);
    }

    private long calculateSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        long curr = node.val + calculateSum(node.left) + calculateSum(node.right);
        allSums.add(curr);

        return curr;
    }
}
