/**
 * 13. Roman to Integer
 * Difficulty: Easy | Tags: Hash Table, Math, String
 * https://leetcode.com/problems/roman-to-integer/
 *
 * Pattern: Right-to-Left Scan with Subtract Notation
 *
 * Key insight: Scan right to left through the Roman numeral string; when a
 * numeral is smaller than the one immediately to its right, subtract it (e.g.,
 * IV = 5−1), otherwise add it. This handles all subtractive pairs in a single
 * pass.
 *
 * Time Complexity: O(n) - Single right-to-left pass.
 *
 * Space Complexity: O(1) - HashMap of 7 entries, fixed.
 *
 * Edge Cases Handled: single-character numeral (e.g., "V"), all additive (e.g.,
 * "VIII"), all subtractive (e.g., "IV", "XC"), maximum value "MMMCMXCIX" (3999)
 */
class RomanToInteger {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = map.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; i--) {
            int cur = map.get(s.charAt(i));
            int next = map.get(s.charAt(i + 1));
            if (cur < next) {
                result = result - cur;
            } else {
                result = result + cur;
            }
        }
        return result;
    }
}
