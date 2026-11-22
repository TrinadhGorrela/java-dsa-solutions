/**
 * 2124. Check if All A's Appears Before All B's
 * Difficulty: Easy | Tags: String
 * https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/
 *
 * Pattern: Single-Pass Scan (First-Occurrence Index)
 *
 * Key insight: The string is valid exactly when no 'a' appears after the first
 * 'b'; once that index is known (or set past the end when no 'b' exists), any
 * later 'a' proves the required ordering is violated. This makes one scan
 * sufficient.
 *
 * Time Complexity: O(N) - indexOf and the verification loop each scan the
 * string once.
 *
 * Space Complexity: O(1) - Only an index variable and a loop counter are used.
 *
 * Edge Cases Handled: No 'b' (all 'a's, valid), no 'a' (all 'b's, valid), empty
 * string, single character, all-same characters, and an 'a' after the first 'b'
 * (returns false).
 */
class CheckIfAllASAppearsBeforeAllBS {
    public boolean checkString(String s) {
        int t = s.indexOf("b");
        t = (t == -1) ? s.length() : t;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a' && i > t) {
                return false;
            }
        }

        return true;
    }
}
