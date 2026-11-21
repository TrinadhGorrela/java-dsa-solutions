/**
 * 150. Evaluate Reverse Polish Notation
 * Difficulty: Medium | Tags: Array, Math, Stack
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Pattern: Stack (Postfix / Reverse Polish Expression Evaluation)
 *
 * Key insight: RPN places each operator after its operands, so one stack
 * suffices: push numbers, then on an operator pop the two most recent values
 * and push their result. Left-to-right evaluation preserves operand order
 * without any precedence rules.
 *
 * Time Complexity: O(N) - Each of the N tokens is read once with constant-time
 * push/pop work.
 *
 * Space Complexity: O(N) - The operand stack can grow to O(N) for a deeply
 * nested expression.
 *
 * Edge Cases Handled: single-token expression; negative integer operands
 * (treated as numbers because only exact operator strings match); Java division
 * truncation toward zero; multi-operator and deeply nested expressions; stack
 * growth up to the full token count. Input is assumed to be valid RPN -
 * malformed tokens or too few operands would throw.
 */
class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if ((tokens[i]).equals("+")) {
                int s = stack.pop();
                int t = stack.pop();

                stack.push(s + t);
            } else if ((tokens[i]).equals("-")) {
                int s = stack.pop();
                int t = stack.pop();

                stack.push(t - s);
            } else if ((tokens[i]).equals("*")) {
                int s = stack.pop();
                int t = stack.pop();

                stack.push(s * t);
            } else if ((tokens[i]).equals("/")) {
                int s = stack.pop();
                int t = stack.pop();

                stack.push(t / s);
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }

        return stack.pop();
    }
}
