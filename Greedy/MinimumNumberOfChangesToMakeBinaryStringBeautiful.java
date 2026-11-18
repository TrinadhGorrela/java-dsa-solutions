/**
 * 2914. Minimum Number of Changes to Make Binary String Beautiful
 * Difficulty: Medium | Tags: String
 * https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string
 * -beautiful/
 *
 * Pattern: Greedy Pairwise Scan (Consecutive 2-Character Blocks)
 *
 * Key insight: A beautiful string can be partitioned into even-length uniform
 * blocks, so the two bits occupying each consecutive pair must agree; a
 * mismatched pair forces at least one flip, and flipping either of its bits
 * fixes that pair independently of all others.
 *
 * Time Complexity: O(N) - A single scan visits each 2-character pair of the
 * length-N string exactly once.
 *
 * Space Complexity: O(1) - Only the mismatch counter is allocated.
 *
 * Edge Cases Handled: Already beautiful and all-same strings (0 changes),
 * minimum length-2 input, fully alternating strings (one change per pair), and
 * inputs whose valid partition uses longer uniform runs spanning several pairs
 * (e.g. 0011 or 0000); assumes even-length input per the constraints, since an
 * odd length would run past the final character.
 */
class MinimumNumberOfChangesToMakeBinaryStringBeautiful {
    public int minChanges(String s) {
        int changes = 0;

        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }

        return changes;
    }
}
