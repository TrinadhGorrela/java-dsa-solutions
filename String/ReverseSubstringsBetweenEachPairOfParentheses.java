/**
 * 1190. Reverse Substrings Between Each Pair of Parentheses
 * Difficulty: Medium | Tags: String, Stack, Bracket Sequences
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 *
 * Pattern: Precomputed Bracket Matching + Direction-Reversal Traversal (O(N) "wormhole" walk)
 * Key insight: Every '(' has exactly one matching ')', so reversing a bracketed region is just
 *   a matter of jumping to its match and stepping backwards for as long as we are inside it.
 *   Precompute pair[i] with a stack, then sweep the string flipping direction at each bracket:
 *   the walk only ever visits each index once, which performs all nested reversals at once
 *   without ever building an intermediate string.
 *
 * Time Complexity: O(N) - Two linear passes; the pointer visits each index at most once, and
 *   every bracket push/pop is O(1) amortized.
 * Space Complexity: O(N) - An int[] pair mapping plus a Stack holding up to N open indices.
 *
 * Edge Cases Handled: empty string (walk loop never runs, returns ""); a string with no
 *   parentheses at all (returned unchanged); a single character / single pair "(a)";
 *   deeply nested and adjacent pairs "((a))", "()()"; multiple top-level segments "(a)b(c)";
 *   characters other than brackets (appended verbatim, so the logic is not limited to
 *   lowercase letters). Assumes well-formed input: unbalanced parentheses would make
 *   stack.pop() throw EmptyStackException.
 */
class ReverseSubstringsBetweenEachPairOfParentheses {
    public String reverseParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int[] pair = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            }
            if (c == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder res = new StringBuilder();

        int curr = 0;
        int dir = 1;
        while (curr < s.length()) {
            char c = s.charAt(curr);
            if (c == '(' || c == ')') {
                curr = pair[curr];
                dir *= -1;
            } else {
                res.append(c);
            }

            if (dir >= 0) {
                curr++;
            } else {
                curr--;
            }

        }

        return res.toString();
    }
}
