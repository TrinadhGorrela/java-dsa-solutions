/**
 * 1541. Minimum Insertions to Balance a Parentheses String
 * Difficulty: Medium | Tags: String, Stack, Greedy, Bracket Sequences
 * https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-str
 * ing/
 *
 * Pattern: Greedy (Single Pass Counter)
 *
 * Key insight: Every '(' ultimately needs two ')', so we only track how many
 * ')' are still owed; an odd debt means a previous ')' run fell one short, and
 * a ')' seen with zero debt means it is orphaned. Because each imbalance is
 * fully corrected the moment it becomes visible (pair the odd debt before
 * consuming a new '(', insert for an orphaned ')'), a single left-to-right scan
 * suffices with no stack or backtracking.
 *
 * Time Complexity: O(N) - Single pass over the character array with constant
 * work per character
 *
 * Space Complexity: O(1) - Only two integer counters; s.toCharArray() makes an
 * O(N) copy, but no auxiliary structure scales with input size
 *
 * Edge Cases Handled: empty string (loop skipped, returns 0), already-balanced
 * input (returns 0), all-'(' input (each adds 2 to the owed count), all-')'
 * input (inserts as needed), odd-length runs of consecutive ')', trailing
 * unmatched '(' settled by the final neededRight + insertions sum, single
 * character input
 */
class MinimumInsertionsToBalanceAParenthesesString {
    public int minInsertions(String s) {
        int neededRight = 0;
        int insertions = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (neededRight % 2 != 0) {
                    insertions++;
                    neededRight--;
                }
                neededRight += 2;

            } else {
                if (neededRight == 0) {
                    insertions++;
                    neededRight++;
                } else {
                    neededRight--;
                }
            }
        }

        return neededRight + insertions;
    }
}
