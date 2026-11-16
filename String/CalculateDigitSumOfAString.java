/**
 * 2243. Calculate Digit Sum of a String
 * Difficulty: Easy | Tags: String, Simulation
 * https://leetcode.com/problems/calculate-digit-sum-of-a-string/
 *
 * Pattern: Iterative Simulation (Chunking & Digit Summation)
 *
 * Key insight: Repeatedly partitioning the string into groups of size k and
 * replacing each group by its digit sum deterministically shrinks the string
 * until its length is within k, so directly simulating the rounds produces the
 * result.
 *
 * Time Complexity: O(N^2) - Multiple passes with string concatenation/substring
 * creation over the evolving string.
 *
 * Space Complexity: O(N) - Stores intermediate strings built during each round.
 *
 * Edge Cases Handled: Input length already <= k, final group shorter than k,
 * multi-digit group sums.
 */
class CalculateDigitSumOfAString {
    public String digitSum(String s, int k) {

        while (s.length() > k) {
            String t = "";

            for (int i = 0; i < s.length(); i += k) {
                t += helper(s.substring(i, Math.min(i + k, s.length())));
            }

            s = t;
        }

        return s;
    }

    private int helper(String n) {
        int sum = 0;

        for (int i = 0; i < n.length(); i++) {
            sum += Integer.parseInt(n.charAt(i) + "");
        }

        return sum;
    }

}
