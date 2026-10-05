/**
 * 89. Gray Code
 * Difficulty: Medium | Tags: Math, Backtracking, Bit Manipulation
 * https://leetcode.com/problems/gray-code/
 *
 * Pattern: Binary Reflected Gray Code (Mirror Doubling)
 *
 * Key insight: To extend from n-1 to n bits, iterate the existing sequence in
 * reverse (the mirror) and add the new high bit via addition (list values +
 * bit); the mirrored order guarantees exactly one differing bit across the
 * reflection boundary.
 *
 * Time Complexity: O(2^n) - Sequence doubles in length each bit level; total
 * elements = 2^n
 *
 * Space Complexity: O(2^n) - Stores the full 2^n-element result list
 *
 * Edge Cases Handled: n = 0 (sequence is just [0], loop never runs), the
 * boundary between the last original element and the first mirrored element
 * differing in exactly one bit
 */
class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        int bit = 1;
        res.add(0);
        for (int i = 1; i <= n; i++) {
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) + bit);
            }
            bit <<= 1;
        }
        return res;
    }
}
