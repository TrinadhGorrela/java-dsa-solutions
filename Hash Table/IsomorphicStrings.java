/**
 * 205. Isomorphic Strings
 * Difficulty: Easy | Tags: Hash Table, String
 * https://leetcode.com/problems/isomorphic-strings/
 *
 * Pattern: HashMap Bijection Check
 *
 * Key insight: Map each s-character to a t-character; on conflict (same s maps
 * to different t) return false. Also reject if a t-character is already claimed
 * by a different s-character, checked via containsValue, enforcing a true
 * one-to-one bijection.
 *
 * Time Complexity: O(n²) - Single pass is O(n), but containsValue per new
 * mapping is O(n).
 *
 * Space Complexity: O(n) - HashMap stores up to min(|Σ_s|, |Σ_t|) entries.
 *
 * Edge Cases Handled: different-length strings (returns false), empty strings,
 * two s-chars mapping to same t-char (one-to-many blocked by containsValue),
 * single-character strings
 */
class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> iso = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (iso.containsKey(c1)) {
                if (iso.get(c1) != c2) {
                    return false;
                }
            } else {
                if (iso.containsValue(c2)) {
                    return false;
                }
                iso.put(c1, c2);
            }
        }
        return true;
    }
}
