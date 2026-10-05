/**
 * 860. Lemonade Change
 * Difficulty: Easy | Tags: Array, Greedy
 * https://leetcode.com/problems/lemonade-change/
 *
 * Pattern: Fixed-Denomination Greedy Change-Making
 *
 * Key insight: Only $5 and $10 bills accumulate (used as change); a $20 payment
 * is best served by one $10 + one $5 (preserves more $5s for future), falling
 * back to three $5s — this greedy preference is optimal because $10 bills are
 * useless as change for $5-only scenarios.
 *
 * Time Complexity: O(N) - Single pass; constant work per bill
 *
 * Space Complexity: O(1) - Two counters (fiveCount, tenCount)
 *
 * Edge Cases Handled: first customer with $10 or $20 (no change possible,
 * returns false), sequence requiring $10+$5 path for $20 (preserves fiveCount),
 * customer paying $20 when only three $5s available (last-resort path)
 */
class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int fiveCount = 0;
        int tenCount = 0;
        for (int bill : bills) {
            if (bill == 5) {
                fiveCount++;
            } else if (bill == 10) {
                tenCount++;
                if (fiveCount == 0) {
                    return false;
                }
                fiveCount--;
            } else {
                if (tenCount != 0 && fiveCount != 0) {
                    tenCount--;
                    fiveCount--;
                } else if (fiveCount >= 3) {
                    fiveCount -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
