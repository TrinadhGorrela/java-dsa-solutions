/**
 * 1807. Evaluate the Bracket Pairs of a String
 * Difficulty: Medium | Tags: Array, Hash Table, String
 * https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/
 *
 * Pattern: Single-Pass State Machine (Flag-Guided Bracket Parsing + Hash Map
 * Lookup)
 *
 * Key insight: Every bracket pair is self-delimiting - '(' flips an isKey flag
 * that routes characters into a side buffer instead of the output, and the
 * matching ')' closes the pair and forces an immediate resolution. So one
 * left-to-right scan is
 * enough: there is no need to pre-split the string, find boundaries, or
 * maintain a stack, because the delimiters tell us exactly where each key ends.
 * Indexing knowledge into a HashMap first makes every resolution an expected
 * O(1) probe.
 *
 * Time Complexity: O(N + K) - N = length of s and K = total characters across
 * all knowledge entries; s is walked exactly once and each key is
 * built/resolved once, while the knowledge list is flattened into the map in a
 * single pass.
 *
 * Space Complexity: O(K + N) - The HashMap stores one entry per knowledge pair,
 * and the output buffer plus the key buffer together hold at most O(N)
 * characters.
 *
 * Edge Cases Handled: null/empty string (returns ""), string with no brackets
 * at all (copied through unchanged), adjacent pairs like "(b)(c)" (flag resets
 * after each close), single-character keys such as "(a)", keys that are
 * prefixes of other keys like "a" vs "ab" (resolved by exact delimited match,
 * never by prefix), unknown or missing knowledge entries (emit "?" per LeetCode
 * convention), and an empty knowledge list (every pair becomes "?"). Not
 * handled: nested or unbalanced brackets such as "(a(b))" and a trailing
 * unterminated "(" key, which is silently dropped since the buffer is only
 * flushed on ')'; the task guarantees neither.
 */
class EvaluateTheBracketPairsOfAString {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < knowledge.size(); i++) {
            map.put(knowledge.get(i).get(0), knowledge.get(i).get(1));
        }

        StringBuilder res = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean isKey = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                String temp = key.toString();
                if (map.containsKey(temp)) {
                    res.append(map.get(temp));
                } else {
                    res.append('?');
                }
                key = new StringBuilder();
                isKey = false;
            } else if (s.charAt(i) == '(') {
                isKey = true;
            } else if (!isKey) {
                res.append(c);
            } else {
                key.append(c);
            }
        }

        return res.toString();
    }
}
