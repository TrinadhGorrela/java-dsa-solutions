/**
 * 3875. Construct Uniform Parity Array I
 * Difficulty: Easy | Tags: Array, Math
 * https://leetcode.com/problems/construct-uniform-parity-array-i/
 *
 * Pattern: Parity Invariant Argument (Mathematical Proof)
 *
 * Key insight: The answer is always true. The odd-even parity of nums1[i] -
 * nums1[j]
 * equals the XOR of the two parities: it is even iff nums1[i] and nums1[j]
 * share the same parity, and odd iff they differ. Because nums1 has only two
 * parity classes and n >= 1, pick any element as a reference. For each index i,
 * if nums1[i] already has the target parity, keep it as nums1[i]; otherwise
 * subtract a reference element of the opposite parity, which flips it to the
 * target. Since n <= 100 and values are distinct, a suitable j != i always
 * exists, so every index can be driven to a single parity.
 *
 * Time Complexity: O(1) - The solution is a constant-time decision; no element
 * of the array is examined to reach the answer.
 *
 * Space Complexity: O(1) - Only constant extra space is used.
 *
 * Edge Cases Handled: single-element array (n == 1) - the lone element defines
 * its own parity and is always a valid uniform array; constraints guarantee n
 * >= 1, so empty or null input never occurs under the official problem
 * statement.
 */
class ConstructUniformParityArrayI {
    public boolean uniformArray(int[] nums1) {
        return true;
    }
}
