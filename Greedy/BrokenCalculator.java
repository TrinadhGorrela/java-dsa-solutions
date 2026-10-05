/**
 * 991. Broken Calculator
 * Difficulty: Medium | Tags: Math, Greedy
 * https://leetcode.com/problems/broken-calculator/
 *
 * Pattern: Reverse Greedy (Invert Operations)
 *
 * Key insight: Forward multiply-by-2 is cheap but reverse divide-by-2 halves
 * fast; increment has no inverse advantage, so work backward from target —
 * halve when even, increment when odd — then bridge the remaining gap with
 * subtraction.
 *
 * Time Complexity: O(log target) - Each step halves or nearly halves target;
 * final subtraction is O(1)
 *
 * Space Complexity: O(1) - Two integer variables (count, target)
 *
 * Edge Cases Handled: target == startValue (zero steps), odd target values
 * (increment to make even then halve), target < startValue (reverse loop never
 * runs, only subtraction remains), power-of-two target
 */
class BrokenCalculator {
    public int brokenCalc(int startValue, int target) {
        int count = 0;
        while (target > startValue) {
            if (target % 2 == 0) {
                target /= 2;
            } else {
                target++;
            }
            count++;
        }

        count += startValue - target;
        return count;
    }
}
