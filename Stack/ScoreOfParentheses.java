/**
 * 856. Score of Parentheses
 * Difficulty: Medium | Tags: String, Stack, Bracket Sequences
 * https://leetcode.com/problems/score-of-parentheses/
 *
 * Pattern: Explicit Stack (Running Partial Score per Nesting Level)
 * Key insight: The score is built bottom-up - a bare "()" contributes 1, and a
 * wrapped
 * sub-sequence of score k becomes 2k because every enclosing pair doubles it. A
 * stack
 * with one accumulator per open level collapses that recursion: on ')' a popped
 * 0 reveals
 * the pair was "()" (add 1), while any non-zero popped value is a finished
 * sub-sequence
 * that gets doubled into the parent level.
 * Time Complexity: O(N) - a single pass in which each character performs
 * exactly one push
 * or one pop.
 * Space Complexity: O(N) - the stack grows with nesting depth on deeply nested
 * input, and
 * s.toCharArray() copies the whole string.
 * Edge Cases Handled: single pair "()" returns 1; adjacent siblings "()()"
 * returns 2;
 * fully nested "((()))" returns 4; empty string never enters the loop so the
 * sentinel 0 is
 * returned; the zero-vs-non-zero test cleanly separates empty pairs from
 * wrapped content;
 * no integer overflow at LeetCode's length bound of 100. Honest limitation:
 * assumes s is a
 * balanced parentheses string as guaranteed - unbalanced input pops the
 * sentinel and throws
 * EmptyStackException.
 */
class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int innerScore = stack.pop();
                int outerScore = stack.pop();

                if (innerScore == 0) {
                    stack.push(innerScore + 1 + outerScore);
                } else {
                    stack.push((innerScore * 2) + outerScore);
                }

            }
        }

        return stack.peek();
    }
}
