/**
 * 3360. Stone Removal Game
 * Difficulty: Easy | Tags: Math, Simulation
 * https://leetcode.com/problems/stone-removal-game/
 *
 * Pattern: Math + Simulation (Alternating Decreasing Removals)
 *
 * Key insight: Each turn removes one fewer stone (10, 9, 8, ...), so at most
 * ten turns occur and only their parity matters. After the loop, `stones` is
 * the next removal size that cannot be paid; because the starting size 10 is
 * even, an odd `stones` implies an odd number of completed turns, meaning the
 * first player (Alice) made the last move.
 *
 * Time Complexity: O(1) - At most 10 loop iterations regardless of n.
 *
 * Space Complexity: O(1) - Only two int variables are used.
 *
 * Edge Cases Handled: n = 0, fewer than 10 stones (first removal impossible), n
 * exactly matching a cumulative removal total, leftover stones insufficient for
 * the next removal, and very large n (still capped at 10 turns)
 */
class StoneRemovalGame {
    public boolean canAliceWin(int n) {
        int stones = 10;

        while (n >= stones) {
            n -= stones;
            stones--;
        }

        return stones % 2 != 0;
    }
}
