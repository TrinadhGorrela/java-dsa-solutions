/**
 * 17. Letter Combinations of a Phone Number
 * Difficulty: Medium | Tags: Hash Table, String, Backtracking
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Pattern: Iterative BFS Layer Expansion (No Recursion)
 *
 * Key insight: Maintain a running list of partial strings; for each new digit,
 * replace the list with all current strings concatenated with each letter
 * mapped to that digit, achieving the same result as recursive backtracking in
 * a flat loop.
 *
 * Time Complexity: O(4^N) where N = digits.length - each digit contributes at
 * most 4 letters, and total combinations = product of per-digit letter counts
 *
 * Space Complexity: O(4^N * N) - stores all complete combinations, each of
 * length N
 *
 * Edge Cases Handled: empty digits string (returns empty list immediately),
 * single digit (4 or 3 results), digits 7 or 9 mapped to 4-letter keys,
 * repeated identical digits (e.g., "22" produces
 * ["aa","ab","ac","ba","bb","bc","ca","cb","cc"])
 */
class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> res = new ArrayList<>();
        res.add("");

        for (int i = 0; i < digits.length(); i++) {
            List<String> temp = new ArrayList<>();
            String curr = map.get(digits.charAt(i));
            for (int j = 0; j < res.size(); j++) {
                for (int k = 0; k < curr.length(); k++) {
                    temp.add(res.get(j) + curr.charAt(k));
                }
            }
            res = temp;
        }
        return res;
    }
}
