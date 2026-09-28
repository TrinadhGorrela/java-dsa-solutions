/**
 * 3498. Reverse Degree of a String
 * Difficulty: Easy | Tags: String, Simulation
 * https://leetcode.com/problems/reverse-degree-of-a-string/
 *
 * Pattern: Single-Pass Simulation (Linear Scan)
 * Key insight: A character's reverse value is 26 - ('c' - 'a'), the alphabet index mirrored about its midpoint so 'a' → 26 and 'z' → 1. Because each position contributes independently, a single scan accumulating (reverse value × 1-based index) yields the answer with no extra structure.
 *
 * Time Complexity: O(N) - One pass over the string, doing O(1) work per character.
 * Space Complexity: O(1) - Uses only a constant number of integer accumulators.
 *
 * Edge Cases Handled: empty string (sum stays 0), single character, boundary letters 'a' (value 26) and 'z' (value 1), longer inputs whose weighted sum may grow large.
 */
class ReverseDegreeOfAString {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int temp = 26 - (s.charAt(i) - 'a');
            sum = sum + (temp * (i + 1));
        }

        return sum;
    }
}
