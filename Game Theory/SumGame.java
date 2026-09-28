/**
 * 1927. Sum Game
 * Difficulty: Medium | Tags: Math, String, Greedy, Game Theory
 * https://leetcode.com/problems/sum-game/
 *
 * Pattern: Game Theory — Parity + Sum-Balance Analysis
 * Key insight: If the total number of '?' is odd, Alice moves last and wins trivially. With even '?', Bob can mirror
 * Alice's moves on the opposite half. Alice wins only when the half-sums differ by a non-multiple of 9 times the
 * per-half '?' count difference, because each '?' pair swings the sum by exactly 9.
 *
 * Time Complexity: O(n) - Single linear scan computing left/right sums and '?' counts.
 * Space Complexity: O(1) - Four integer counters (leftSum, rightSum, leftCount, rightCount).
 *
 * Edge Cases Handled: odd '?' count (Alice wins immediately), balanced sums and '?' counts (Bob wins), zero '?' in the
 * string, difference not divisible by 9 (Alice wins)
 */
class SumGame {
    public boolean sumGame(String num) {
        int leftSum = 0;
        int rightSum = 0;
        int leftCount = 0;
        int rightCount = 0;

        for (int i = 0; i < num.length() / 2; i++) {
            if (num.charAt(i) == '?') {
                leftCount++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        for (int i = num.length() / 2; i < num.length(); i++) {
            if (num.charAt(i) == '?') {
                rightCount++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        if ((leftCount + rightCount) % 2 != 0) {
            return true;
        }

        if (leftSum == rightSum && leftCount == rightCount) {
            return false;
        }

        int diff = Math.abs(leftCount - rightCount);

        if (leftCount > rightCount) {
            leftSum += (diff / 2) * 9;
        } else {
            rightSum += (diff / 2) * 9;
        }

        if (leftSum == rightSum) {
            return false;
        }

        return true;
    }
}
