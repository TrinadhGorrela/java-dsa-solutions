/**
 * 1446. Consecutive Characters
 * Difficulty: Easy | Tags: String
 * https://leetcode.com/problems/consecutive-characters/
 *
 * Pattern: Linear Scan with Run-Length Tracking
 * Key insight: Use left as the start of the current run. When s[right] differs from s[left], the run is broken: record
 * the length, reset count to 1, and move left to right. The final max() after the loop captures a run that extends to
 * the string's end without a breaking character.
 *
 * Time Complexity: O(N) - Single pass; each character is compared once
 * Space Complexity: O(1) - Three integer scalars (left, count, max)
 *
 * Edge Cases Handled: single character (returns 1), all identical characters (returns N), alternating characters (max =
 * 1), longest run at the very end of the string (final max() captures it)
 */
class ConsecutiveCharacters {
    public int maxPower(String s) {
        int left = 0;
        int count = 0;
        int max = 0;

        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(left) == s.charAt(right)) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 1;
                left = right;
            }
        }
        max = Math.max(max, count);
        return max;
    }
}
