/**
 * 299. Bulls and Cows
 * Difficulty: Medium | Tags: Hash Table, String, Counting
 * https://leetcode.com/problems/bulls-and-cows/
 *
 * Pattern: Hash Map Frequency Counting (Two Pass)
 *
 * Key insight: Bulls (exact position matches) are counted first and removed
 * from the secret's frequency map so they are never re-counted as cows later. A
 * cow is any guess digit still present in the remaining secret frequencies but
 * at the wrong position; decrementing the map on each match guarantees every
 * secret digit is paired at most once, preventing double counting.
 *
 * Time Complexity: O(N) - One pass to build the frequency map plus two linear
 * scans over the guess, all backed by O(1) hash map operations.
 *
 * Space Complexity: O(K) - Frequency map; K = distinct characters (bounded by
 * 10 digits per constraints, so effectively O(1)).
 *
 * Edge Cases Handled: all digits match in position (bull phase empties the map,
 * cow phase no-ops); duplicate digits across secret/guess (decrements prevent
 * re-use); empty strings yield "0A0B".
 * Honest caveat: the cow phase indexes secret.charAt(i) with guess.length(), so
 * only safe for equal-length inputs (guess longer than secret would throw
 * IndexOutOfBounds).
 */
class BullsAndCows {
    public String getHint(String secret, String guess) {
        Map<Character, Integer> freq = new HashMap<>();
        int bulls = 0;
        int cows = 0;

        for (char c : secret.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);

            if (secret.charAt(i) == c) {
                bulls++;
                freq.put(c, freq.get(c) - 1);

                if (freq.get(c) == 0) {
                    freq.remove(c);
                }

            }
        }

        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);

            if (freq.containsKey(c) && secret.charAt(i) != c) {
                cows++;

                freq.put(c, freq.get(c) - 1);

                if (freq.get(c) == 0) {
                    freq.remove(c);
                }
            }
        }

        StringBuilder s = new StringBuilder();

        s.append(bulls).append('A').append(cows).append('B');

        return s.toString();
    }
}
