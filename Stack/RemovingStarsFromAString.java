/**
 * 2390. Removing Stars From a String
 * Difficulty: Medium | Tags: String, Stack, Simulation
 * https://leetcode.com/problems/removing-stars-from-a-string/
 *
 * Pattern: StringBuilder as Stack
 *
 * Key insight: Append each non-'*' character to a StringBuilder; on '*', delete
 * the last character (the nearest left neighbor). The StringBuilder naturally
 * acts as a stack with O(1) amortized append/deleteAt-end.
 *
 * Time Complexity: O(n) - Each character appended or removed at most once.
 *
 * Space Complexity: O(n) - StringBuilder holds the surviving characters.
 *
 * Edge Cases Handled: no stars (string unchanged), all stars (empty result),
 * star at the beginning (guarded delete), alternating star/letter pattern
 */
class RemovingStarsFromAString {
    public String removeStars(String s) {
        StringBuilder stb = new StringBuilder();
        int length = s.length();
        char ch;

        for (int i = 0; i < length; i++) {
            ch = s.charAt(i);
            if (ch == '*') {
                if (stb.length() > 0) {
                    stb.deleteCharAt(stb.length() - 1);
                }
            } else {
                stb.append(ch);
            }
        }
        return stb.toString();
    }
}
