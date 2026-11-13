/**
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 * Difficulty: Medium | Tags: Hash Table, String, Greedy, Sorting
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies
 * -unique/
 *
 * Pattern: Greedy + Hash Map (Frequency Counting with Decrement-to-Unique)
 *
 * Key insight: Frequencies only need to become distinct, and the only way to
 * fix a collision is to decrease a frequency. Decrementing each duplicate to
 * the next unused value costs the fewest deletions and can never make another
 * character's frequency harder to fix, so the greedy choice is optimal.
 *
 * Time Complexity: O(N) - Counting costs O(N), and every deletion strictly
 * lowers a frequency, so the total work of all while-loop decrements is bounded
 * by the N characters.
 *
 * Space Complexity: O(min(N, 26)) - The frequency map and seen-set hold at most
 * one entry per distinct character, i.e. at most the 26 lowercase letters.
 *
 * Edge Cases Handled: Already-unique frequencies (zero deletions); all
 * characters identical; empty string returns 0; collisions that force a
 * frequency all the way down to 0; chains of duplicate counts; multiple
 * characters may legitimately end at frequency 0.
 */
class MinimumDeletionsToMakeCharacterFrequenciesUnique {
    public int minDeletions(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int del = 0;

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        Set<Integer> seen = new HashSet<>();

        for (char c : freq.keySet()) {
            int count = freq.get(c);

            while (seen.contains(count) && count > 0) {
                count--;
                del++;
            }

            seen.add(count);
        }

        return del;
    }
}
