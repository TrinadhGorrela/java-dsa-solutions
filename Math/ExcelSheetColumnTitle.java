/**
 * 168. Excel Sheet Column Title
 * Difficulty: Easy | Tags: Math, String
 * https://leetcode.com/problems/excel-sheet-column-title/
 *
 * Pattern: 1-Indexed Base-26 Encoding
 *
 * Key insight: Treat columns as a 1-based base-26 system: use (col - 1) % 26 to
 * pick the current letter (so that 26 maps to Z, not to the zero slot), then
 * reduce col via (col - 1) / 26 before the next level; build digits
 * least-significant first and reverse at the end.
 *
 * Time Complexity: O(log_26 col) - One iteration per resulting letter in the
 * title
 *
 * Space Complexity: O(log_26 col) - StringBuilder holds the digits before
 * reversal (excluding the output string itself)
 *
 * Edge Cases Handled: col = 1 (single letter A), exact multiples of 26 (e.g.,
 * 26 -> Z, 52 -> AZ, handled by the col - 1 shift), two- and three-letter
 * rollovers (27 -> AA)
 */
class ExcelSheetColumnTitle {
    public String convertToTitle(int col) {
        StringBuilder res = new StringBuilder();

        while (col > 0) {
            int remainder = (col - 1) % 26;
            res.append((char) (remainder + 'A'));
            col = (col - 1) / 26;
        }
        return res.reverse().toString();
    }
}
