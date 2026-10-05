/**
 * 12. Integer to Roman
 * Difficulty: Medium | Tags: Hash Table, Math, String
 * https://leetcode.com/problems/integer-to-roman/
 *
 * Pattern: Descending Greedy Value Decomposition
 *
 * Key insight: Encode subtractive forms (CM, CD, XC, XL, IX, IV) as explicit
 * entries in a descending lookup table; the greedy while-loop repeatedly
 * extracts the largest fitting value, producing a canonical Roman numeral with
 * no backtracking.
 *
 * Time Complexity: O(1) - At most 15 iterations across the 13-entry table (max
 * value 3999 = 3×M + 1×C + 1×D…)
 *
 * Space Complexity: O(1) - 13-entry LinkedHashMap; output string bounded by ~15
 * characters
 *
 * Edge Cases Handled: all subtractive forms (4=IV, 9=IX, 40=XL, 90=XC, 400=CD,
 * 900=CM), repeated symbols (3=MMM, 388=CCCLXXXVIII), boundary values (1=I,
 * 3999=MMMCMXCIX)
 */
class IntegerToRoman {
    public String intToRoman(int num) {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        StringBuilder roman = new StringBuilder();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            while (num >= entry.getKey()) {
                roman.append(entry.getValue());
                num = num - entry.getKey();
            }
        }
        return roman.toString();
    }
}
