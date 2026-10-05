/**
 * 187. Repeated DNA Sequences
 * Difficulty: Medium | Tags: Hash Table, String, Bit Manipulation, Sliding
 * Window, Rolling Hash, Hash Function, Z Algorithm, Boyer-Moore String-Search
 * Algorithm
 * https://leetcode.com/problems/repeated-dna-sequences/
 *
 * Pattern: Hash Table + Substring Enumeration (Fixed-Length Sliding Window)
 *
 * Key insight: Every possible 10-character DNA substring is enumerated and
 * counted using a HashMap. Once all substrings are tallied, those with count >
 * 1 are repeated. Because every sliding window of length 10 is checked (using
 * substring), no repeated 10-mers are missed.
 *
 * Time Complexity: O(10 * N) which simplifies to O(N) - Each of the O(N)
 * substrings of length 10 is hashed by copying 10 characters; the HashMap
 * operations are amortized O(1).
 *
 * Space Complexity: O(N) - The HashMap stores up to N - 9 distinct 10-character
 * substrings.
 *
 * Edge Cases Handled: strings shorter than 10 characters (returns empty
 * immediately); overlapping occurrences of the same 10-mer counted correctly
 * via HashMap increment logic; input length exactly 10.
 */
class RepeatedDnaSequences {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();

        if (s.length() < 10) {
            return res;
        }

        map.put(s.substring(0, 10), 1);
        for (int i = 1; i <= s.length() - 10; i++) {
            String temp = s.substring(i, i + 10);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }

        for (String t : map.keySet()) {
            if (map.get(t) > 1) {
                res.add(t);
            }
        }

        return res;
    }
}
