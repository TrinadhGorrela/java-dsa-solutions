/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Difficulty: Medium | Tags: String, Stack
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 *
 * Pattern: Stack + Two-Pass Greedy Balancing
 *
 * Key insight: A ')' is valid only when an unmatched '(' precedes it, so
 * tracking the live open count lets the first pass discard surplus closing
 * brackets; the second pass then removes exactly the leftover opening brackets
 * counted in `open`, which is the minimum possible deletion.
 *
 * Time Complexity: O(N) - Two linear passes over the string, each performing
 * O(1) stack work per character.
 *
 * Space Complexity: O(N) - The stack stores up to N surviving characters and
 * the result builder is O(N).
 *
 * Edge Cases Handled: empty string, strings of only non-parenthesis characters,
 * unmatched ')' with no open partner (dropped), surplus '(' removed in the
 * reverse pass, and already-valid parentheses left unchanged.
 */
class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int open = 0;

        for (char c : s.toCharArray()) {
            if (c == '(' || c == ')') {
                if (c == '(') {
                    stack.push(c);
                    open++;
                }

                if (c == ')' && open > 0) {
                    stack.push(c);
                    open--;
                }

            } else {
                stack.push(c);
            }
        }

        StringBuilder res = new StringBuilder();

        while (!stack.isEmpty()) {
            char c = stack.pop();
            if (c == '(' && open != 0) {
                open--;
            } else {
                res.append(c);
            }
        }

        return res.reverse().toString();
    }
}
