/**
 * 1614. Maximum Nesting Depth of the Parentheses
 * Difficulty: Easy | Tags: String, Stack, Bracket Sequences
 * https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
 *
 * Pattern: Single-Pass Depth Counter (Running Balance, no stack)
 * Key insight: Nesting depth at any position is simply the number of '(' seen so far
 * minus the number of ')' seen so far, so a single running counter replaces an explicit
 * stack. The maximum of that counter is the answer, because every deeper level must first
 * pass through a shallower one on its way to the maximum.
 *
 * Time Complexity: O(N) - One left-to-right scan; every character is examined exactly once
 * Space Complexity: O(1) - Only two int counters are maintained (the toCharArray() copy is
 * a transient O(N) allocation that is released immediately)
 *
 * Edge Cases Handled: empty string (returns 0), a single '(' or ')' with no pair,
 * non-parenthesis characters (ignored, so nesting through other text is handled), all
 * parentheses at one flat level (depth 1), and worst-case fully nested input like "(((("
 * (depth N). Noted honestly: assumes balanced input, since an unbalanced ')' drives the
 * counter negative rather than being clamped at zero.
 */
class MaximumNestingDepthOfTheParentheses {
    public int maxDepth(String s) {
        int max = 0;
        int curr = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                curr++;
            } else if (c == ')') {
                max = Math.max(max, curr);
                curr--;
            }
        }
        return max;
    }
}
