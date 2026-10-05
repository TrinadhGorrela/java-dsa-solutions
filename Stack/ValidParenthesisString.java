/**
 * 678. Valid Parenthesis String
 * Difficulty: Medium | Tags: String, Dynamic Programming, Stack, Greedy,
 * Bracket Sequences
 * https://leetcode.com/problems/valid-parenthesis-string/
 *
 * Pattern: Two Stacks + Greedy (prefer real '(' over '*' when closing a
 * bracket)
 *
 * Key insight: A '*' can be reinterpreted as '(', ')' or nothing, so we only
 * ever need two stacks of pending positions - unmatched '(' and unused '*'. On
 * ')' we always cancel a real '(' first and fall back to a '*' (turned into
 * '('), which is the greedy choice that keeps the oldest, most constrained
 * wildcard available for later brackets. Afterwards, each leftover '(' must be
 * closed by a '*' that appears after it, so any '(' whose position exceeds the
 * newest remaining '*' proves the string invalid.
 *
 * Time Complexity: O(N) - Every index is pushed onto a stack once and popped at
 * most once across the scan and the final matching loop.
 *
 * Space Complexity: O(N) - The two stacks together hold at most one index per
 * character (all-'(' or all-'*' input is the worst case).
 *
 * Edge Cases Handled: empty string (both loops no-op, returns true); null input
 * is not guarded and would throw NullPointerException; leading ')' with no '('
 * or '*' before it (both stacks empty, returns false); leading '*' acting as
 * the opening bracket for that ')' (star.pop() branch); consecutive '*)' and
 * '*' swallowing its neighbour; unmatched '(' with no '*' after it
 * (star.isEmpty() in the drain loop); trailing '*' closing leftover '(';
 * already-balanced input returns true without using any wildcard.
 */
class ValidParenthesisString {
    public boolean checkValidString(String s) {
        Stack<Integer> open = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open.push(i);
            } else if (c == '*') {
                star.push(i);
            } else {
                if (open.isEmpty() && star.isEmpty()) {
                    return false;
                }

                if (!open.isEmpty()) {
                    open.pop();
                } else {
                    star.pop();
                }
            }
        }

        while (!open.isEmpty()) {
            if (star.isEmpty() || star.peek() < open.peek()) {
                return false;
            }
            star.pop();
            open.pop();
        }

        return true;
    }
}
