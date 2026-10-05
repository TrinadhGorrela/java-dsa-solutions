/**
 * 2011. Final Value of Variable After Performing Operations
 * Difficulty: Easy | Tags: Array, String, Simulation
 * https://leetcode.com/problems/final-value-of-variable-after-performing-operat
 * ions/
 *
 * Pattern: Linear String Scan with Pattern Match
 *
 * Key insight: Each operation string is exactly one of four forms: "X++",
 * "++X", "X--", "--X". A single equality check per increment form (two checks)
 * and a default decrement branch handles all cases without parsing.
 *
 * Time Complexity: O(N) - One pass over the operations array with O(1) string
 * comparisons per element
 *
 * Space Complexity: O(1) - Only an integer accumulator; no additional data
 * structures
 *
 * Edge Cases Handled: all increments (postfix and prefix mix), all decrements,
 * single operation, alternating increment/decrement forms
 */
class FinalValueOfVariableAfterPerformingOperations {
    public int finalValueAfterOperations(String[] operations) {
        int res = 0;
        for (int i = 0; i < operations.length; i++) {
            if (operations[i].equals("X++") || operations[i].equals("++X")) {
                res++;
            } else {
                res--;
            }
        }
        return res;
    }
}
