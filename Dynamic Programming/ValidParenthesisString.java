/**
 * 678. Valid Parenthesis String
 * Difficulty: Medium | Tags: String, Dynamic Programming, Stack, Greedy, Bracket Sequences
 * https://leetcode.com/problems/valid-parenthesis-string/
 *
 * Pattern: Two Stacks + Greedy Wildcard Matching
 * Key insight: Each ')' must consume the most recent '(' before falling back to a '*' (a star
 * *            can stand in for a '('), so leftover '(' can only be repaired by '*' characters
 * *            that appear strictly after them. Recording indices rather than counts lets us
 * *            enforce that ordering, which is exactly the condition for a valid assignment.
 *
 * Time Complexity: O(N) - Every character is pushed and popped at most once across both stacks
 * Space Complexity: O(N) - The two index stacks can each hold up to N entries in the worst case
 *
 * Edge Cases Handled: empty string (vacuously valid), strings with no '*' at all, strings with no
 * parentheses, a ')' that arrives with no '(' and no '*' available (rejects early), leftover
 * unmatched '(' with no trailing '*' (rejects), surplus '*' characters left over (accepted, since
 * a star may be empty), and '*' occurring before the '(' it must rescue (caught by the index
 * comparison star.peek() < open.peek()).
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
