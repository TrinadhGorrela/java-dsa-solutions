/**
 * 3090. Maximum Length Substring With Two Occurrences
 * Difficulty: Easy | Tags: Hash Table, String, Sliding Window
 * https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/
 *
 * Pattern: Variable Sliding Window with Per-Character Frequency Cap (max 2)
 *
 * Key insight: Grow the window rightward, incrementing each character's count
 * in a map. When any character's count exceeds 2, shrink from the left
 * (decrementing and advancing) until that character is back to exactly 2. The
 * window is always valid after the while loop, so record its length.
 *
 * Time Complexity: O(N) - Right advances once per iteration; left advances at
 * most N times total; each map operation is O(1)
 *
 * Space Complexity: O(1) - HashMap holds at most 26 lowercase letters
 *
 * Edge Cases Handled: single character string (result 1), all same character
 * (capped at 2), all distinct characters (full string length), third occurrence
 * of any character triggers left advancement
 */
class MaximumLengthSubstringWithTwoOccurrences {
    public int maximumLengthSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;

        for (int right = 0; right < s.length(); right++) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);

            while (map.get(s.charAt(right)) > 2) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
