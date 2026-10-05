/**
 * 1111. Maximum Nesting Depth of Two Valid Parentheses Strings
 * Difficulty: Medium | Tags: String, Stack, Bracket Sequences
 * https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-
 * strings/
 *
 * Pattern: Parity of Nesting Depth (Depth Tracking with No Stack)
 *
 * Key insight: Instead of assigning brackets by alternating colors top-down,
 * this labels each bracket with the parity (curr % 2) of the nesting depth just
 * outside it. Because a bracket pair always spans one even depth and one odd
 * depth, its opening and closing labels always differ regardless of how deep
 * the nesting goes, which halves the maximum nesting depth of every
 * sub-sequence.
 *
 * Time Complexity: O(N) - A single pass scans the string once, with O(1) work
 * per character
 *
 * Space Complexity: O(N) - Returns an int array of length seq.length() (the
 * input string is not copied)
 *
 * Edge Cases Handled: Empty string (loop never runs, returns a zero-length
 * array); single pair "()" (labels [0,1], max depth 1 is minimal); deeply
 * nested input where every other level shares a label; nested pairs followed by
 * concatenated pairs like "(())(())" (each pair still straddles a parity
 * boundary); all-parens input is safe against underflow since the sequence is
 * guaranteed valid. NOT handled: null input (would throw NullPointerException);
 * invalid/unbalanced sequences, which would drive curr negative and Java's %
 * would yield a negative label.
 */
class MaximumNestingDepthOfTwoValidParenthesesStrings {
    public int[] maxDepthAfterSplit(String seq) {
        int[] res = new int[seq.length()];
        int curr = 0;

        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                res[i] = curr % 2;
                curr++;

            } else {
                curr--;
                res[i] = curr % 2;
            }
        }

        return res;
    }
}
