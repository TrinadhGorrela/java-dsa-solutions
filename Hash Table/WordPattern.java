/**
 * 290. Word Pattern
 * Difficulty: Easy | Tags: Hash Table, String
 * https://leetcode.com/problems/word-pattern/
 *
 * Pattern: Dual-Map Bijection Check
 *
 * Key insight: Maintain a char→word HashMap and a word-HashSet simultaneously;
 * the map ensures each pattern character maps to exactly one word, while the
 * set prevents two different pattern characters from sharing the same
 * word—enforcing a true bijection.
 *
 * Time Complexity: O(n) - Split the string into n words, then single pass;
 * map/set ops are O(1) average.
 *
 * Space Complexity: O(n) - Map and set together hold up to n entries.
 *
 * Edge Cases Handled: pattern and word count mismatch (returns false), empty
 * pattern, two pattern chars mapped to the same word (set blocks it), one
 * pattern char mapped to two words (map blocks it)
 */
class WordPattern {
	public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        String[] words = s.split(" ");

        if (pattern.length() != words.length)
            return false;

        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (!map.get(pattern.charAt(i)).equals(words[i])) {
                    return false;
                }
            } else {
                if (set.contains(words[i])) {
                    return false;
                }

                map.put(pattern.charAt(i), words[i]);
                set.add(words[i]);
            }
        }
		
        return true;
    }
}
