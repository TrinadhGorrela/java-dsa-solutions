/**
 * 1652. Defuse the Bomb
 * Difficulty: Easy | Tags: Array, Sliding Window
 * https://leetcode.com/problems/defuse-the-bomb/
 *
 * Pattern: Circular Array via Prefix Sum on Doubled Array
 *
 * Key insight: Duplicate the code array to length 2N and build a prefix sum
 * over it. Each window sum becomes a single
 * O(1) prefix-difference subtraction: result[i] = prefix[i+k] - prefix[i] for k
 * > 0, and symmetrically for k < 0 using the mirrored region.
 *
 * Time Complexity: O(N) - One pass to build the 2N prefix array, one pass to
 * compute each element of the result
 *
 * Space Complexity: O(N) - Prefix array of length 2N (input-sized, constant
 * factor)
 *
 * Edge Cases Handled: k = 0 (returns all zeros immediately), negative k (sums
 * preceding elements via the second half of the prefix), single-element array
 */
class DefuseTheBomb {
    public int[] decrypt(int[] code, int k) {
        int length = code.length;
        int[] prefix = new int[2 * length];
        int[] result = new int[length];
        prefix[0] = code[0];

        if (k == 0)
            return result;

        for (int i = 1; i < 2 * length; i++) {
            prefix[i] = code[i % length] + prefix[i - 1];
        }
        if (k > 0) {
            for (int i = 0; i < length; i++) {
                result[i] = prefix[i + k] - prefix[i];
            }
        } else {
            for (int i = length; i < 2 * length; i++) {
                result[i - length] = prefix[i - 1] - prefix[i + k - 1];

            }
        }
        return result;
    }
}
