/**
 * 22. Generate Parentheses
 * Difficulty: Medium | Tags: String, Dynamic Programming, Backtracking, Bracket
 * Sequences
 * https://leetcode.com/problems/generate-parentheses/
 *
 * Pattern: Backtracking with Open/Close Count Invariants
 *
 * Key insight: Track remaining open and close counts; always allow '(' when
 * open > 0, but only allow ')' when close > open, which guarantees no prefix
 * has more closing than opening brackets -- the fundamental invariant of
 * well-formed parentheses.
 *
 * Time Complexity: O(Catalan(n)) = O(4^n / n^(3/2)) - exactly the number of
 * valid parenthesis sequences of length 2n
 *
 * Space Complexity: O(2n) recursion depth + O(Catalan(n) * 2n) for output
 * storage
 *
 * Edge Cases Handled: n = 1 (single "()" result), open count reaches zero (only
 * ')' allowed from that point), close count equals open count (only '(' allowed
 * to avoid premature closing), all 2n characters placed (base case)
 */
class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, n, new String(), res);
        return res;
    }

    public static void backtrack(int open, int close, String s, List<String> res) {
        if (open == 0 && close == 0) {
            res.add(s);
            return;
        }

        if (open > 0) {
            backtrack(open - 1, close, s + '(', res);
        }

        if (close > open) {
            backtrack(open, close - 1, s + ')', res);
        }
    }
}
