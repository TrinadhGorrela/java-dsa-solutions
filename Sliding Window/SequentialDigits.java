/**
 * 1291. Sequential Digits
 * Difficulty: Medium | Tags: Enumeration
 * https://leetcode.com/problems/sequential-digits/
 *
 * Pattern: Enumeration (Sliding Window over "123456789")
 * Key insight: Every sequential-digit number is a contiguous substring of "123456789". Iterate window lengths from
 * len(low) to len(high), slide a window of each length, convert to int, and keep values in [low, high]. Early-return
 * once a window of a given length exceeds high since subsequent windows are larger.
 *
 * Time Complexity: O(1) - The digit pool has only 9 characters, yielding at most 36 candidate substrings (9 + 8 + ... +
 * 1)
 * Space Complexity: O(1) - Result list holds at most 36 integers regardless of input
 *
 * Edge Cases Handled: low equals high (single-element result), low and high differing in digit count (multiple window
 * lengths explored), no valid number exists (empty result), early termination when candidate exceeds high
 */
class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        String digits = "123456789";
        String lowStr = String.valueOf(low);
        String highStr = String.valueOf(high);
        List<Integer> res = new ArrayList<>();
        for (int i = lowStr.length(); i <= highStr.length(); i++) {
            for (int j = 0; j < digits.length() - i + 1; j++) {
                int temp = Integer.valueOf(digits.substring(j, j + i));
                if (temp >= low && temp <= high) {
                    res.add(temp);
                }
                if (temp > high) {
                    return res;
                }
            }
        }
        return res;
    }
}
