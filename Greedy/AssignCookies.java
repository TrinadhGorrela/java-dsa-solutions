/**
 * 455. Assign Cookies
 * Difficulty: Easy | Tags: Array, Two Pointers, Greedy, Sorting, Quicksort
 * https://leetcode.com/problems/assign-cookies/
 *
 * Pattern: Sort + Greedy Cookie-to-Child Matching
 *
 * Key insight: After sorting both arrays, a single forward scan matches the
 * smallest sufficient cookie to each child in order—any cookie too small for
 * the current child is also too small for all remaining children.
 *
 * Time Complexity: O(N log N + M log M) - Sorting both arrays dominates; the
 * merge scan is O(N+M)
 *
 * Space Complexity: O(1) - Three integers (two pointers + count) beyond the
 * sort
 *
 * Edge Cases Handled: No cookies (0 satisfied), no children (0 satisfied), all
 * cookies too small, all cookies large enough (min(g) matched), oversized
 * cookies leftover unused
 */
class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        int left = 0;
        int right = 0;
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (left < g.length && right < s.length) {
            if (g[left] <= s[right]) {
                count++;
                left++;
                right++;
            } else {
                right++;
            }
        }
        return count;
    }
}
