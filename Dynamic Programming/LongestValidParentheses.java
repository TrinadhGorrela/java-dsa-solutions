/**
 * 32. Longest Valid Parentheses
 * Difficulty: Hard | Tags: String, Dynamic Programming, Stack, Bracket Sequences
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Pattern: Stack of Indices with a Sentinel Boundary Marker
 * Key insight: Keep a stack holding the indices of unmatched '(' , always topped by a
 *            sentinel that records the position where the last invalid ')' occurred (-1 at
 *            the start). Every ')' pops one entry: if the stack empties, no '(' is left to
 * *            match it and that ')' becomes the new boundary; otherwise the longest valid
 * *            substring ending at i is exactly i - stack.peek(), because everything after the
 * *            top (an unmatched '(' or the boundary) up to i is balanced.
 *
 * Time Complexity: O(N) - One pass over the string, each index pushed and popped at most once
 * Space Complexity: O(N) - The index stack can grow to the size of the input in the all-'(' case
 *
 * Edge Cases Handled: empty string (returns 0 from the -1 sentinel), single-character input,
 * unbalanced strings, strings that are entirely '(' or entirely ')', leading ')' characters
 * (each resets the boundary sentinel), fully balanced input (returns s.length()), and runs of
 * ')' with no preceding '(' (detected by the stack becoming empty).
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
