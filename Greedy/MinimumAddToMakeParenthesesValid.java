/**
 * 921. Minimum Add to Make Parentheses Valid
 * Difficulty: Medium | Tags: String, Stack, Greedy, Bracket Sequences
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 *
 * Pattern: Greedy Balance Counter (Single Pass, No Stack)
 * Key insight: Every prefix of a valid string must contain at least as many '('
 * as ')',
 * so a ')' that arrives while the balance is zero can only be repaired by
 * inserting a '('
 * right before it; conversely the '(' still unmatched at the end each force an
 * inserted
 * ')'. Both counts are unavoidable, so their sum is provably minimal - which is
 * why a
 * single counter pair replaces a stack.
 * Time Complexity: O(N) - one pass with O(1) work per character.
 * Space Complexity: O(1) of algorithm state - just the two counters open and
 * close; the
 * only allocation is the char[] copy made by s.toCharArray(), which is O(N) if
 * counted.
 * Edge Cases Handled: empty string returns 0; a lone '(' or ')' returns 1;
 * all-'(' input
 * returns its length via open; all-')' input returns its length via close;
 * already-valid
 * input returns 0; interleaved worst case such as ")()(" adds one on each side;
 * deeply
 * nested input. Honest limitation: every non-'(' character is treated as ')',
 * so the
 * result is only meaningful for input made up solely of parentheses.
 */
class MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int close = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {

                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
        }

        return open + close;
    }
}
