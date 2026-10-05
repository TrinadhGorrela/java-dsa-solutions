/**
 * 784. Letter Case Permutation
 * Difficulty: Medium | Tags: String, Backtracking, Bit Manipulation
 * https://leetcode.com/problems/letter-case-permutation/
 *
 * Pattern: Binary Branching on Letter Positions with Pass-Through on Digits
 *
 * Key insight: Each alphabetic position doubles the result count (upper or
 * lower case), while digits are appended unchanged without branching; the total
 * output is 2^L strings where L is the letter count, computed by a single DFS
 * pass.
 *
 * Time Complexity: O(2^L * N) where L = number of letters, N = string length -
 * 2^L leaf strings each of length N
 *
 * Space Complexity: O(N) recursion depth + O(2^L * N) for the output list
 *
 * Edge Cases Handled: all-digit input (returns the input itself as a
 * single-element list), all-letter input (maximum branching), empty string
 * (returns empty list), mixed digits and letters, single character input
 */
class LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, res, new String(), 0);
        return res;
    }

    public static void backtrack(String s, List<String> res, String temp, int index) {
        if (index == s.length()) {
            res.add(temp);
            return;
        }

        if (Character.isLetter(s.charAt(index))) {
            backtrack(s, res, temp + Character.toLowerCase(s.charAt(index)), index + 1);
            backtrack(s, res, temp + Character.toUpperCase(s.charAt(index)), index + 1);
        } else {
            backtrack(s, res, temp + s.charAt(index), index + 1);
        }

    }
}
