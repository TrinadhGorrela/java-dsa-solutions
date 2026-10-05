/**
 * 171. Excel Sheet Column Number
 * Difficulty: Easy | Tags: Math, String
 * https://leetcode.com/problems/excel-sheet-column-number/
 *
 * Pattern: Base-26 Decoding (Horner Folding)
 *
 * Key insight: Map each letter to its 1-based value (A=1..Z=26) via c - 'A' +
 * 1, then fold left-to-right as sum = sum * 26 + digit, yielding the numeric
 * column index in one pass.
 *
 * Time Complexity: O(L) - One iteration per character in the title
 *
 * Space Complexity: O(1) - Only the running sum and per-character value
 *
 * Edge Cases Handled: single-letter titles (A -> 1, Z -> 26), multi-letter
 * titles folded correctly with the base-26 multiplier, uppercase-only input
 * assumed by the c - 'A' mapping
 */
class ExcelSheetColumnNumber {
    public int titleToNumber(String columnTitle) {
        int sum = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            int letterValue = c - 64;
            sum = (sum * 26) + letterValue;
        }
        return sum;
    }
}
