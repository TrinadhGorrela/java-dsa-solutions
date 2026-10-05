/**
 * 20. Valid Parentheses
 * Difficulty: Easy | Tags: String, Stack, Bracket Sequences
 * https://leetcode.com/problems/valid-parentheses/
 *
 * Pattern: Stack-Based Bracket Matching
 *
 * Key insight: Push every opening bracket; on a closing bracket, check the
 * stack top for the matching type and pop. The string is valid iff the stack is
 * empty at the end.
 *
 * Time Complexity: O(n) - Each character pushed and popped at most once.
 *
 * Space Complexity: O(n) - Worst case: all opening brackets pushed.
 *
 * Edge Cases Handled: empty string (valid), single bracket (unmatched), closing
 * bracket with empty stack, mismatched pair types, deeply nested valid sequence
 */
class ValidParentheses {
     public boolean isValid(String s) {
        Stack<Character> res = new Stack<>();

        for (char c : s.toCharArray()) {

            if (c == '(' || c == '{' || c == '[') {
                res.push(c);
            } else {
                if (res.isEmpty()) {
                    return false;
                }

                char top = res.pop();
                
                if ((c == ')' && top != '(') ||
                        (c == ']' && top != '[') ||
                        (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return res.isEmpty();
    }
}
