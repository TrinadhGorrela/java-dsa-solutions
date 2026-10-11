/**
 * 623. Add One Row to Tree
 * Difficulty: Medium | Tags: Tree, Depth-First Search, Breadth-First Search,
 * Binary Tree
 * https://leetcode.com/problems/add-one-row-to-tree/
 *
 * Pattern: Breadth-First Search (Level-Order Traversal)
 *
 * Key insight: Every node on level depth-1 becomes the parent of a new node on
 * the inserted level, so the new node is spliced in front of the original child
 * to preserve the existing subtree. Running BFS only until that parent level
 * keeps the re-linking local and O(1) per node.
 *
 * Time Complexity: O(N) - Each node is dequeued at most once while descending
 * to and rewiring level depth-1.
 *
 * Space Complexity: O(W) - The BFS queue holds at most one tree level, i.e.
 * O(W) for the maximum width (O(N) in the skewed/complete worst case).
 *
 * Edge Cases Handled: depth == 1 (wraps the original root in a new root node
 * with the old tree as its left child); depth greater than the tree height
 * (tree returned unchanged); target nodes with missing left/right children;
 * concave insertion where only one child exists; only safe for a non-null root
 * when depth > 1 (queue.add(null) would NPE otherwise).
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
class AddOneRowToTree {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode dummy = new TreeNode(val);
            dummy.left = root;
            return dummy;
        }

        int dep = 1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            if (dep == depth - 1) {

                int size = queue.size();

                while (size-- != 0) {
                    TreeNode curr = queue.poll();
                    TreeNode tempLeft = curr.left;
                    TreeNode tempRight = curr.right;

                    curr.left = new TreeNode(val);
                    curr.right = new TreeNode(val);

                    curr.left.left = tempLeft;
                    curr.right.right = tempRight;
                }

                break;
            }

            int size = queue.size();
            while (size-- != 0) {
                TreeNode curr = queue.poll();

                if (curr.left != null) {
                    queue.add(curr.left);
                }

                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }

            dep++;
        }

        return root;
    }
}
