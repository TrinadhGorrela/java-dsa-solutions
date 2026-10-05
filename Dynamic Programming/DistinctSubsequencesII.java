/**
 * 940. Distinct Subsequences II
 * Difficulty: Hard | Tags: String, Dynamic Programming
 * https://leetcode.com/problems/distinct-subsequences-ii/
 *
 * Pattern: Dynamic Programming (Counting with Last-Occurrence Dedup)
 *
 * Key insight: The number of distinct subsequences ending with character c is
 * maintained, and when c recurs, the newly generated subsequences that
 * duplicated the previous set ending with c must be subtracted. By storing
 * end[index] (subsequences already finishing with this letter) and subtracting
 * it, we avoid double-counting identical subsequences that end with the same
 * character.
 *
 * Time Complexity: O(N) - One pass over the string with O(1) per character
 * (constant 26-letter alphabet).
 *
 * Space Complexity: O(1) - A fixed 26-element array tracks the per-letter
 * subsequence counts.
 *
 * Edge Cases Handled: single-character string (yields exactly 1 subsequence);
 * repeated/all-same characters (deduplication via end[index] keeps the count at
 * N not 2^N); large counts overflowing int (modulo 1_000_000_007 with + MOD to
 * keep subtraction non-negative); empty string (loop never runs, returns 0).
 */
class DistinctSubsequencesII {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;

        int[] end = new int[26];
        int total = 0;

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            int oldTotal = total;
            int newSubsequences = (oldTotal + 1 - end[index] + MOD) % MOD;
            total = (total + newSubsequences) % MOD;
            end[index] = (end[index] + newSubsequences) % MOD;
        }

        return total;
    }
}
