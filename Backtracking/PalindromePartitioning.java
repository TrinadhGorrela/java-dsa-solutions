/**
 * 131. Palindrome Partitioning
 * Difficulty: Medium | Tags: String, Dynamic Programming, Backtracking
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * Pattern: Backtracking (DFS) + Palindrome Check
 *
 * Key insight: At each position we greedily try every substring that ends at
 * the current start as a palindrome; only palindromic prefixes advance the
 * recursion, so every path in the recursion tree is a valid partition. Because
 * recursion walks start-to-end and only splits on verified palindromes, it
 * never explores non-palindromic splits.
 *
 * Time Complexity: O(N * 2^N) - In the worst case every substring is a
 * palindrome, producing an exponential number of partitions, each with an O(N)
 * palindrome check.
 *
 * Space Complexity: O(N) - Depth of the recursion stack plus the running temp
 * partition list, excluding the output.
 *
 * Edge Cases Handled: empty string (returns an empty result list without
 * recursing); single character (a palindrome, yields one partition); strings
 * with no valid palindromic partitions; all-identical characters (maximizes the
 * number of partitions).
 */
class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();

        if (s.length() == 0) {
            return res;
        }

        helper(res, new ArrayList<>(), s, 0);
        return res;
    }

    private void helper(List<List<String>> res, List<String> temp, String s, int st) {
        if (st == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = st; i < s.length(); i++) {
            if (isPalindrome(s, st, i)) {
                temp.add(s.substring(st, i + 1));
                helper(res, temp, s, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
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
