/**
 * 3863. Minimum Operations to Sort a String
 * Difficulty: Medium | Tags: String
 * https://leetcode.com/problems/minimum-operations-to-sort-a-string/
 *
 * Pattern: Greedy Case Analysis (Sorting a Proper Substring)
 *
 * Key insight: one operation may sort any substring except the whole string, so
 * the answer is decided purely by where the global minimum and maximum
 * characters sit: if the minimum is already first or the maximum already last,
 * sorting the remaining proper substring finishes the job in one operation,
 * otherwise the extremes must first be pushed to an end (one operation) before
 * the rest can be sorted, and only the fully reversed extreme case (minimum
 * last, maximum first) needs three.
 *
 * Time Complexity: O(N log N) - Dominated by sorting the character copy used to
 * detect an already-sorted string; the min/max position scan is a single linear
 * pass
 *
 * Space Complexity: O(N) - The char[] copy and the rebuilt sorted String used
 * for the sortedness comparison both scale with the input
 *
 * Edge Cases Handled: single-character string (0 operations), two-character
 * string (returns -1 when descending, since the only proper substrings are
 * single characters, and 0 when already ascending), already-sorted input (0
 * operations, detected by comparing against a sorted copy), empty string
 * (returns 0), all-identical characters, and repeated extremes (the branch is
 * chosen by the FIRST occurrence of the minimum and the LAST occurrence of the
 * maximum); the answer never exceeds 3 operations.
 */
class MinimumOperationsToSortAString {
    public int minOperations(String s) {
        if (s.length() == 1) {
            return 0;
        }

        if (s.length() == 2) {
            if (s.charAt(0) <= s.charAt(1)) {
                return 0;
            } else {
                return -1;
            }
        }

        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        String temp = new String(arr);

        if (temp.equals(s)) {
            return 0;
        }

        int min = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(min) > s.charAt(i)) {
                min = i;
            }

            if (s.charAt(max) <= s.charAt(i)) {
                max = Math.max(max, i);
            }
        }

        if (min == s.length() - 1 && max == 0) {
            return 3;
        }

        if (min == 0 || max == s.length() - 1) {
            return 1;
        }

        return 2;
    }
}
