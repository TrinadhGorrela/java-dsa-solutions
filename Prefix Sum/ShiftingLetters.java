/**
 * 848. Shifting Letters
 * Difficulty: Medium | Tags: Array, String, Prefix Sum
 * https://leetcode.com/problems/shifting-letters/
 *
 * Pattern: Suffix Sum (Backward Modular Accumulation)
 *
 * Key insight: A letter at index i receives every shift from i onward, because
 * shifting position j moves all letters at or before j; summing shifts
 * right-to-left modulo 26 turns n successive shifts into one lookup per
 * character. Shifts are additive and commute modulo 26, so the accumulated
 * total can be applied in a single step.
 *
 * Time Complexity: O(N) - One backward pass builds the suffix sums and one
 * forward pass builds the result, where N = s.length().
 *
 * Space Complexity: O(N) - Suffix array of N + 1 ints plus the N-character
 * result builder; the shifts array itself is reused in place.
 *
 * Edge Cases Handled: Empty string / zero-length shifts (returns ""),
 * single-character input, shift values of 0 and values >= 26 (reduced mod 26),
 * wraparound past 'z', and full-length cumulative shifts; assumes lowercase
 * English letters.
 */
class ShiftingLetters {
    public String shiftingLetters(String s, int[] shifts) {
        int[] suffix = new int[shifts.length + 1];

        for (int i = suffix.length - 2; i >= 0; i--) {
            shifts[i] %= 26;
            suffix[i] = shifts[i] + suffix[i + 1];
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < shifts.length; i++) {
            int temp = s.charAt(i) + suffix[i] - 'a';
            temp %= 26;
            temp += 'a';
            res.append((char) (temp));
        }

        return res.toString();
    }
}
