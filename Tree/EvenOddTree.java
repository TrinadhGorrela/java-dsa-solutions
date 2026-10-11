/**
 * 1609. Even Odd Tree
 * Difficulty: Medium | Tags: Tree, Breadth-First Search, Binary Tree
 * https://leetcode.com/problems/even-odd-tree/
 *
 * Pattern: Breadth-First Search (Level-Order Validation)
 *
 * Key insight: Validate one level at a time; on a 0-indexed even level values
 * must be odd and strictly increasing, on an odd level even and strictly
 * decreasing. Tracking the previous value with a per-level sentinel (0 for
 * even, Integer.MAX_VALUE for odd) collapses the ordering check to one
 * comparison per node.
 *
 * Time Complexity: O(N) - Each node is visited and compared exactly once.
 *
 * Space Complexity: O(W) - The BFS queue stores at most one tree level, i.e.
 * O(W) for the maximum width (O(N) worst case).
 *
 * Edge Cases Handled: single-node tree, single-element levels, null children
 * skipped, strict (non-equal) monotonicity enforced within a level, alternating
 * parity across levels, and the sentinel initializations that keep the first
 * node of every level valid; only safe for a non-null root per constraints.
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
class EvenOddTree {
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean even = true;
        int prev;

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (even) {
                prev = 0;
            } else {
                prev = Integer.MAX_VALUE;
            }

            while (size-- != 0) {
                TreeNode curr = queue.poll();
                if (even) {
                    if (curr.val % 2 == 0 || prev >= curr.val) {
                        return false;
                    }
                } else {
                    if (curr.val % 2 != 0 || prev <= curr.val) {
                        return false;
                    }
                }

                if (curr.left != null) {
                    queue.add(curr.left);
                }

                if (curr.right != null) {
                    queue.add(curr.right);
                }

                prev = curr.val;

            }

            even = !even;
        }

        return true;
    }
}
