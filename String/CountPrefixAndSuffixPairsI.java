/**
 * 3042. Count Prefix and Suffix Pairs I
 * Difficulty: Easy | Tags: Array, String, Trie, Rolling Hash, String Matching,
 * Hash Function
 * https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/
 *
 * Pattern: Brute-Force Pair Enumeration (O(N^2) Comparison)
 *
 * Key insight: For each pair (i, j) with i < j, check whether words[i] is
 * simultaneously a prefix and suffix of words[j] by comparing two
 * length-s.length() substrings. This brute-force approach is correct when the
 * input is small (problem guarantees n <= 50), and the early continue when
 * words[i] is longer than words[j] prunes unnecessary comparisons.
 *
 * Time Complexity: O(N^2 * M) where M is the max string length - Each of O(N^2)
 * pairs is checked with two substring operations of cost up to O(M).
 *
 * Space Complexity: O(M) - Each substring operation allocates a temporary
 * string of length up to M.
 *
 * Edge Cases Handled: words[i] longer than words[j] (skipped via early
 * continue); empty string in the array (always prefix/suffix of anything,
 * counted correctly); single-element array (returns 0); words[i] equals
 * words[j] exactly.
 */
class CountPrefixAndSuffixPairsI {
    public int countPrefixSuffixPairs(String[] words) {
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String s = words[i];
                String t = words[j];
                if (s.length() > t.length()) {
                    continue;
                }
                String front = t.substring(0, s.length());
                String back = t.substring(t.length() - s.length(), t.length());
                if (front.equals(s) && back.equals(s)) {
                    res++;
                }
            }
        }
        return res;
    }
}
