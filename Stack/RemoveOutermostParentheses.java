/**
 * 1021. Remove Outermost Parentheses
 * Difficulty: Easy | Tags: String, Stack, Bracket Sequences
 * https://leetcode.com/problems/remove-outermost-parentheses/
 *
 * Pattern: Balanced Parentheses (Depth Counter + Segment Slicing)
 *
 * Key insight: A primitive substring is exactly the span where the running
 * bracket depth returns to zero, so tracking depth with a single integer lets
 * us slice off each primitive's interior (start+1..i) as soon as it closes — no
 * explicit stack needed because depth alone identifies the boundaries.
 *
 * Time Complexity: O(N) - Single pass over the string; each substring is copied
 * once, so total work is linear in the output/input length
 *
 * Space Complexity: O(N) - StringBuilder stores the result, which can be up to
 * N characters (depth counter itself is O(1))
 *
 * Edge Cases Handled: empty string (loop never runs, returns ""), minimal input
 * "()" (interior slice is empty, returns ""), adjacent primitive groups
 * ("()()"), deeply nested input ("((()))"), and unbalanced input is not handled
 * — assumes valid parentheses per the problem statement
 */
class RemoveOutermostParentheses {
    public String removeOuterParentheses(String s) {
        StringBuilder res = new StringBuilder();
        int count = 0;
        int st = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                String temp = s.substring(st + 1, i);
                st = i + 1;
                res.append(temp);
            }
        }

        return res.toString();
    }
}
