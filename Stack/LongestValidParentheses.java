/**
 * 32. Longest Valid Parentheses
 * Difficulty: Hard | Tags: String, Dynamic Programming, Stack, Bracket Sequences
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Pattern: Stack of Indices with a Sentinel Boundary Marker
 * Key insight: Keep a stack of indices that are NOT yet matched, with a sentinel
 * at the bottom standing for "the position just before the current valid run".
 * On '(' we push its index; on ')' we pop, and whatever is now on top is the
 * boundary that the substring ending here cannot cross. A valid run ending at i
 * therefore has length exactly i - stack.peek(): the gap between i and the last
 * unmatched ')' or unmatched '(' is fully balanced. Since every index is pushed
 * and popped at most once, the scan is linear.
 *
 * Time Complexity: O(N) - Each character is pushed onto the stack at most once
 *   and popped at most once during a single left-to-right scan of the string.
 * Space Complexity: O(N) - The stack holds one index per currently unmatched '('
 *   (worst case an all-'(' input), i.e. it grows linearly with the input.
 *
 * Edge Cases Handled: empty string (loop never runs, returns 0); null input is
 *   not guarded and would throw NullPointerException; string beginning with an
 *   unmatched ')' (e.g. ")()()"), where the popped sentinel is replaced by that
 *   index to restart the boundary; no valid substring at all ("(((" returns 0);
 *   single character; adjacent pairs "()()" and deeply nested "((()))"; the
 *   sentinel -1 keeps stack.pop() from underflowing, so no index arithmetic on
 *   an empty stack.
 */
class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();

                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());

                }

            }
        }

        return res;
    }
}
