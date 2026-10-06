/**
 * 796. Rotate String
 * Difficulty: Easy | Tags: String, String Matching
 * https://leetcode.com/problems/rotate-string/
 *
 * Pattern: String Matching (Concatenation Trick)
 *
 * Key insight: Every rotation of s appears as a substring of s + s (doubling s
 * exposes all rotation boundaries in one string), so goal is a rotation iff it
 * is contained in s + s. The length check up front is required because
 * containment alone would accept goals that are shorter than s.
 *
 * Time Complexity: O(N) - Concatenation is O(N) and substring search over the
 * 2N-character doubled string is linear in practice
 *
 * Space Complexity: O(N) - Building s + s allocates a new string of twice the
 * input length
 *
 * Edge Cases Handled: differing lengths (rejected immediately), both strings
 * empty ("" + "" contains ""), s already equal to goal, highly repetitive
 * strings with many equal rotations (e.g. "aaa"), rotations of length-1 input
 */
class RotateString {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        return (s + s).contains(goal);
    }
}
