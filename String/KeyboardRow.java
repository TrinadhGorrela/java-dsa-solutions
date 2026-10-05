/**
 * 500. Keyboard Row
 * Difficulty: Easy | Tags: Array, Hash Table, String
 * https://leetcode.com/problems/keyboard-row/
 *
 * Pattern: Row-Flag Intersection
 *
 * Key insight: For each word, mark which of the three rows are touched using a
 * 3-element flags array; if exactly one flag is set, all characters come from a
 * single row.
 *
 * Time Complexity: O(N * L) - N words, each scanned character-by-character with
 * indexOf on a 10-char row string
 *
 * Space Complexity: O(N) - LinkedList accumulates qualifying words; 3-element
 * flags array per word is constant
 *
 * Edge Cases Handled: uppercase letters (lowercased before lookup),
 * single-letter words (always qualify), words crossing row boundaries (flags
 * sum > 1, rejected)
 */
class KeyboardRow {
    public String[] findWords(String[] words) {
        String firstRow = "qwertyuiop";
        String secondRow = "asdfghjkl";
        String thirdRow = "zxcvbnm";
        List<String> list = new LinkedList<>();
        for (String word : words) {
            int[] rowFlags = new int[3];
            for (char ch : word.toLowerCase().toCharArray()) {
                if (firstRow.indexOf(ch) != -1)
                    rowFlags[0] = 1;
                else if (secondRow.indexOf(ch) != -1)
                    rowFlags[1] = 1;
                else if (thirdRow.indexOf(ch) != -1)
                    rowFlags[2] = 1;
            }
            int rowCount = rowFlags[0] + rowFlags[1] + rowFlags[2];
            if (rowCount == 1)
                list.add(word);
        }
        String[] result = new String[list.size()];
        int i = 0;
        for (String word : list)
            result[i++] = word;
        return result;
    }
}
