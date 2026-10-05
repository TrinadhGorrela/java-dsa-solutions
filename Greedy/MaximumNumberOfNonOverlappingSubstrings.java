/**
 * 1520. Maximum Number of Non-Overlapping Substrings
 * Difficulty: Hard | Tags: Hash Table, String, Greedy, Sorting
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/
 *
 * Pattern: Greedy + Interval Expansion via First/Last Occurrence Tables
 *
 * Key insight: A valid substring must start at the first occurrence of its
 * leading character; as [left, right] is expanded to cover the last occurrences
 * of every enclosed character, any character whose first occurrence lies before
 * left invalidates the substring, since it would force an earlier start mark.
 * Selecting candidate substrings by leftmost start, and replacing the last
 * chosen one whenever a new valid substring lies strictly inside it, maximizes
 * the count while keeping the endpoint minimal.
 *
 * Time Complexity: O(N) - One pass builds the first/last tables and the scan
 * triggers getRight at most 26 times (once per distinct starting character),
 * each running linearly
 *
 * Space Complexity: O(1) - Two fixed-size arrays of 26; only a constant number
 * of substrings is held in the result
 *
 * Edge Cases Handled: empty string (returns an empty list); single character
 * (returns the whole string); characters occurring exactly once
 * (single-character valid substrings); a valid substring fully contained within
 * the previous selection (replaced via the newRight < lastRight branch);
 * invalid substrings rejected by getRight returning -1. Only correct for
 * lowercase 'a'..'z' (uses a size-26 table).
 */
class MaximumNumberOfNonOverlappingSubstrings {
    public List<String> maxNumOfSubstrings(String s) {
        List<String> res = new ArrayList<>();
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < s.length(); i++) {
            int in = s.charAt(i) - 'a';

            if (first[in] == -1) {
                first[in] = i;
            }

            last[in] = i;
        }

        int lastRight = -1;

        for (int i = 0; i < s.length(); i++) {
            int in = s.charAt(i) - 'a';
            if (first[in] == i) {
                int newRight = getRight(s, first, last, i);

                if (newRight != -1 && newRight < lastRight) {
                    res.remove(res.size() - 1);
                    res.add(s.substring(i, newRight + 1));
                } else if (newRight != -1) {
                    res.add(s.substring(i, newRight + 1));
                }

                if (newRight != -1) {
                    lastRight = newRight;
                }

            }
        }

        return res;
    }

    private static int getRight(String s, int[] first, int[] last, int left) {
        int right = last[s.charAt(left) - 'a'];

        for (int i = left; i < right; i++) {
            int in = s.charAt(i) - 'a';
            if (first[in] < left) {
                return -1;
            }
            right = Math.max(right, last[in]);
        }
        return right;
    }
}
