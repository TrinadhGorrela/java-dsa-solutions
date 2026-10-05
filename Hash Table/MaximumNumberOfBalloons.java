/**
 * 1189. Maximum Number of Balloons
 * Difficulty: Easy | Tags: Hash Table, String, Counting
 * https://leetcode.com/problems/maximum-number-of-balloons/
 *
 * Pattern: Character Frequency Counting
 *
 * Key insight: Count every character in the text; "balloon" needs b=1, a=1,
 * l=2, o=2, n=1—so the answer is min(count[b], count[a], count[l]/2,
 * count[o]/2, count[n]).
 *
 * Time Complexity: O(n) - One pass to build frequency map, O(1) to compute min.
 *
 * Space Complexity: O(1) - At most 26 entries in the map.
 *
 * Edge Cases Handled: missing any required letter (returns 0), odd count of 'l'
 * or 'o' (integer division floors correctly), text contains no letters from
 * "balloon"
 */
class MaximumNumberOfBalloons {
    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            freq.put(text.charAt(i), freq.getOrDefault(text.charAt(i), 0) + 1);
        }

        int b = freq.getOrDefault('b', 0);
        int a = freq.getOrDefault('a', 0);
        int l = freq.getOrDefault('l', 0) / 2;
        int o = freq.getOrDefault('o', 0) / 2;
        int n = freq.getOrDefault('n', 0);

        return Math.min(b, Math.min(a, Math.min(l, Math.min(o, n))));
    }
}
