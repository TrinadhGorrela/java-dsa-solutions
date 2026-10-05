/**
 * 2259. Remove Digit From Number to Maximize Result
 * Difficulty: Easy | Tags: String, Greedy, Enumeration
 * https://leetcode.com/problems/remove-digit-from-number-to-maximize-result/
 *
 * Pattern: Brute-Force Enumeration
 *
 * Key insight: At every index where number.charAt(i) == digit, build the
 * candidate string by removing that index (substring concatenation) and track
 * the lexicographically maximum candidate via compareTo.
 *
 * Time Complexity: O(n²) - Up to n candidates, each substring operation is
 * O(n).
 *
 * Space Complexity: O(n) - Each candidate string is O(n).
 *
 * Edge Cases Handled: digit appears once (only one candidate), digit at first
 * or last position, multiple occurrences (all compared), leading zeros in
 * candidates (compareTo handles naturally)
 */
class RemoveDigitFromNumberToMaximizeResult {
    public String removeDigit(String number, char digit) {
        String max = "";

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == digit) {
                String current = number.substring(0, i) + number.substring(i + 1);
                if (current.compareTo(max) > 0) {
                    max = current;
                }
            }
        }
        return max;
    }
}
