/**
 * 32. Longest Valid Parentheses
 * Difficulty: Hard | Tags: String, Dynamic Programming, Stack, Bracket Sequences
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Pattern: Stack (Index tracking)
 * Key insight: By storing the index of the last unmatched parenthesis (or -1 initially) in a stack, we can continuously calculate the length of valid substrings by subtracting the new top of the stack from the current index whenever a pair is matched.
 *
 * Time Complexity: O(N) - Single pass through the string where each character is pushed and popped at most once.
 * Space Complexity: O(N) - Stack can grow up to the size of the string in the worst case.
 *
 * Edge Cases Handled: Empty string, string with no valid parentheses, entirely valid string, and disconnected valid blocks.
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

