/**
 * 498. Diagonal Traverse
 * Difficulty: Medium | Tags: Array, Matrix, Simulation
 * https://leetcode.com/problems/diagonal-traverse/
 *
 * Pattern: HashMap Grouping + Direction Toggle
 *
 * Key insight: Group all cells by diagonal index (i + j); even-indexed
 * diagonals are emitted top-to-bottom, odd-indexed ones bottom-to-top
 * (reversed), producing the required zig-zag order.
 *
 * Time Complexity: O(m·n) - Build map in O(m·n), read out in O(m·n).
 *
 * Space Complexity: O(m·n) - HashMap stores every element once.
 *
 * Edge Cases Handled: 1×1 matrix, single row or column (each diagonal has one
 * element), non-square matrices
 */
class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!map.containsKey(i + j)) {
                    map.put(i + j, new ArrayList<>());
                }
                map.get(i + j).add(mat[i][j]);
            }
        }

        int[] res = new int[m * n];
        int in = 0;
        int maxKey = (m - 1) + (n - 1);
        boolean flip = true;

        for (int k = 0; k <= maxKey; k++) {
            List<Integer> curr = map.get(k);

            if (flip) {
                Collections.reverse(curr);
            }
            for (int j = 0; j < curr.size(); j++) {
                res[in++] = curr.get(j);
            }

            flip = !flip;
        }
        return res;
    }
}
