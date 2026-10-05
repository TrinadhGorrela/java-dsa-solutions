/**
 * 297. Serialize and Deserialize Binary Tree
 * Difficulty: Hard | Tags: String, Tree, Depth-First Search, Breadth-First
 * Search, Design, Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Pattern: BFS Level-Order Serialization
 *
 * Key insight: Traverse the tree level by level using a queue, recording each
 * node's value (or "null" for absent children). During deserialization, consume
 * the token list in the same BFS order and reconstruct the tree by assigning
 * left and right children from the queue. This mirrors the exact traversal
 * order, making round-trip faithful.
 *
 * Time Complexity: O(N) - Each node and edge is visited exactly once in both
 * serialize and deserialize
 *
 * Space Complexity: O(N) - Queue and level lists hold at most one full level
 * (worst case ~N/2 nodes); output string scales with N
 *
 * Edge Cases Handled: null tree (serialize returns empty string, deserialize on
 * empty string returns null); single node tree; skewed/degenerate trees; trees
 * with all null children at a level
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<String>> inOrder = new ArrayList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<String> level = new ArrayList<>();
            int size = queue.size();
            while (size-- != 0) {
                TreeNode curr = queue.poll();
                String temp = "";
                if (curr != null) {
                    temp = curr.val + "";
                } else {
                    temp = "null";
                }

                level.add(temp);

                if (curr != null) {
                    queue.offer(curr.left);
                }

                if (curr != null) {
                    queue.offer(curr.right);
                }
            }
            inOrder.add(level);
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < inOrder.size(); i++) {
            List<String> list = inOrder.get(i);
            for (int j = 0; j < list.size(); j++) {
                res.append(list.get(j));

                if (j != list.size() - 1) {
                    res.append(",");
                }
            }
            if (i != inOrder.size() - 1) {
                res.append(",");
            }
        }

        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.offer(root);
        int in = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- != 0) {
                TreeNode curr = queue.poll();

                if (!values[in].equals("null")) {
                    TreeNode left = new TreeNode(Integer.parseInt(values[in]));
                    curr.left = left;
                    queue.offer(left);
                }
                in++;

                if (!values[in].equals("null")) {
                    TreeNode right = new TreeNode(Integer.parseInt(values[in]));
                    curr.right = right;
                    queue.offer(right);
                }
                in++;

            }
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
