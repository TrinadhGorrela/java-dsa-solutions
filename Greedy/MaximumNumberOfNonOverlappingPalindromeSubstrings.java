/**
 * 2472. Maximum Number of Non-overlapping Palindrome Substrings
 * Difficulty: Hard | Tags: Two Pointers, String, Dynamic Programming, Greedy
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-su
 * bstrings/
 *
 * Pattern: Greedy (Shortest-First) + Two Pointers
 *
 * Key insight: At each position, greedily select the shortest valid palindrome
 * (length k or k+1). Because consuming fewer characters per pick leaves maximum
 * room for subsequent non-overlapping palindromes, the earliest-shortest
 * strategy provably maximizes the total count.
 *
 * Time Complexity: O(n²) - Outer loop scans O(n) positions; inner loop tries
 * candidate lengths with greedy early break, and each two-pointer palindrome
 * check is O(n) worst case
 *
 * Space Complexity: O(1) - Only a fixed number of integer variables; palindrome
 * check is in-place
 *
 * Edge Cases Handled: null input (LeetCode-guaranteed non-null), string shorter
 * than k (loop body never executes, returns 0), k equals string length (single
 * candidate checked), no palindromes of length >= k exist (returns 0), all
 * identical characters (every substring is a palindrome; greedy picks length-k
 * substrings), k = 1 (single characters trivially pass), single-element string
 */
class MaximumNumberOfNonOverlappingPalindromeSubstrings {
    public int maxPalindromes(String s, int k) {
        int start = 0;
        int res = 0;

        for (int right = k - 1; right < s.length(); right++) {
            for (int len = k; len <= right - start + 1; len++) {
                int left = right - len + 1;

                if (isPal(s, left, right)) {
                    res++;
                    start = right + 1;
                    break;
                }

            }
        }

        return res;
    }

    private static boolean isPal(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
