/**
 * 49. Group Anagrams
 * Difficulty: Medium | Tags: Array, Hash Table, String, Sorting
 * https://leetcode.com/problems/group-anagrams/
 *
 * Pattern: Sorted-String Key Hashing
 *
 * Key insight: Sorting each string's characters produces a canonical form — all
 * anagrams map to the same key, so a single HashMap pass groups them without
 * pairwise comparison.
 *
 * Time Complexity: O(N * L log L) - N strings each sorted in O(L log L) where L
 * is max string length
 *
 * Space Complexity: O(N * L) - HashMap holds all N strings plus their sorted
 * keys of length up to L
 *
 * Edge Cases Handled: empty strings (grouped together as valid anagrams),
 * single-character strings, all strings identical (single group), all strings
 * unique (N groups of size 1)
 */
class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        List<List<String>> result = new ArrayList<>(map.values());
        return result;
    }
}
