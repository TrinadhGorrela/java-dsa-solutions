/**
 * 389. Find the Difference
 * Difficulty: Easy | Tags: Hash Table, String, Bit Manipulation, Sorting
 * https://leetcode.com/problems/find-the-difference/
 *
 * Pattern: ASCII Sum Difference
 *
 * Key insight: Sum all ASCII values of t, subtract all ASCII values of s; since
 * t contains exactly one extra character, the remainder is that character's
 * ASCII code—no hash map needed.
 *
 * Time Complexity: O(n) - Two linear passes over the strings.
 *
 * Space Complexity: O(1) - Single integer accumulator.
 *
 * Edge Cases Handled: empty s (t has one character), extra character at any
 * position, repeated characters (sum difference still isolates the single
 * addition)
 */
class FindTheDifference {
    public char findTheDifference(String s, String t) {

        int total = 0;

        for (char c : t.toCharArray()) {
            total += c;
        }

        for (char c : s.toCharArray()) {
            total -= c;
        }

        return (char) total;

    }
}
