/**
 * 3723. Maximize Sum of Squares of Digits
 * Difficulty: Medium | Tags: Math, Greedy
 * https://leetcode.com/problems/maximize-sum-of-squares-of-digits/
 *
 * Pattern: Greedy (Largest-Digit-First Construction)
 *
 * Key insight: with a fixed digit count and digit sum, the number is maximized
 * lexicographically by placing the largest legal digit (9) in every leftmost
 * position, because any digit stolen from an earlier position for a later one
 * only shifts value rightward and shrinks the number; leftover sum after all
 * positions are filled means the target is unreachable.
 *
 * Time Complexity: O(num) - one pass appending a digit per position, plus an
 * O(1) final leftover check
 *
 * Space Complexity: O(num) - output StringBuilder holding the num-digit result
 *
 * Edge Cases Handled: impossible targets (sum > 9 * num) detected by the
 * leftover sum check and returned as "", sum = 0 (emits num zeros, sum never
 * triggers the failure path), sum = 9 * num (all nines), remainder < 9 (padded
 * with trailing zeros), num = 0 (loop never runs and the method returns ""
 * whether sum is 0 or not); only safe for non-negative num/sum (negative inputs
 * would misbehave without validation)
 */
class MaximizeSumOfSquaresOfDigits {
    public String maxSumOfSquares(int num, int sum) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < num; i++) {
            if (sum - 9 > 0) {
                res.append(9);
                sum -= 9;
            } else {
                res.append(sum);
                sum -= sum;
            }
        }

        if (sum != 0) {
            return "";
        }

        return res.toString();
    }
}
