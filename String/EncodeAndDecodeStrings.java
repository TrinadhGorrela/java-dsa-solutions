/**
 * 271. Encode and Decode Strings
 * Difficulty: Medium | Tags: Array, String
 * https://leetcode.com/problems/encode-and-decode-strings/
 *
 * Pattern: Length Prefix + Delimiter
 *
 * Key insight: Prefix each string with its length followed by a delimiter
 * (e.g., '#'). During decoding, read the length, skip the delimiter, and
 * extract the string.
 *
 * Time Complexity: O(n) - n is total characters across all strings; both encode
 * and decode scan each character once.
 *
 * Space Complexity: O(n) - Output string or list holds all original characters.
 *
 * Edge Cases Handled: empty list of strings, empty string in the list, strings
 * containing '#', single string input, strings with special characters and
 * unicode
 */
class EncodeAndDecodeStrings {

    public String encode(List<String> strs) {
        StringBuilder res = new StringBuilder();

        for (String s : strs) {
            res.append(s.length() + "#").append(s);
        }

        return res.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int prev = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                int n = Integer.parseInt(str.substring(prev, i));
                String temp = str.substring(i + 1, i + n + 1);
                res.add(temp);
                prev = n + i + 1;
                i = prev;
            }
        }

        return res;
    }
}
