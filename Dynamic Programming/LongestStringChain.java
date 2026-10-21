/**
 * 1048. Longest String Chain
 * Difficulty: Medium | Tags: Array, Hash Table, Two Pointers, String, Dynamic
 * Programming, Sorting
 * https://leetcode.com/problems/longest-string-chain/
 *
 * Pattern: Sorting + Dynamic Programming (Longest String Chain with Two-Pointer
 * Subsequence Check)
 *
 * Key insight: sorting by length guarantees every possible predecessor is
 * already processed, and a word can only be preceded by a word exactly one
 * character shorter that appears as a subsequence of it; the two-pointer scan
 * confirms that subsequence relation in a single pass, so dp[i] is one plus the
 * best chain ending at a valid predecessor.
 *
 * Time Complexity: O(N^2 * L) - Every pair of words is tested and each test
 * scans up to L characters, with N = words.length and L = longest word length;
 * the length sort adds O(N log N)
 *
 * Space Complexity: O(N) - One chain length per word in dp, plus the O(N)
 * temporary buffer that Java's sort uses for object arrays
 *
 * Edge Cases Handled: single-word input (returns 1), words of equal length
 * never chain (the length check demands exactly one fewer character), character
 * order is preserved (the predecessor must be a subsequence, not merely an
 * anagram), duplicate words stay independent chain elements (equal lengths
 * cannot link), the global best is tracked separately from the per-word dp so
 * the longest chain is returned, and an already length-sorted input is left
 * untouched by the sort. Note: an empty word array returns 1 rather than 0.
 */
class LongestStringChain {
    public int longestStrChain(String[] words) {
        int[] dp = new int[words.length];
        Arrays.fill(dp, 1);
        int max = 1;

        Arrays.sort(words, (a, b) -> a.length() - b.length());

        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private boolean isPredecessor(String word1, String word2) {
        if (word1.length() + 1 != word2.length()) {
            return false;
        }

        int shortPtr = 0;
        int longPtr = 0;

        while (shortPtr < word1.length() && longPtr < word2.length()) {
            if (word1.charAt(shortPtr) == word2.charAt(longPtr)) {
                shortPtr++;
                longPtr++;
            } else {
                longPtr++;
            }
        }

        return shortPtr == word1.length();
    }
}
