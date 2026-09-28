/**
 * 2029. Stone Game IX
 * Difficulty: Medium | Tags: Array, Math, Greedy, Minimax, Counting, Game Theory, Nim Game, Zero-Sum Game
 * https://leetcode.com/problems/stone-game-ix/
 *
 * Pattern: Mod-3 Residue Game Theory
 * Key insight: After reducing stones mod 3, the game becomes a turn-based alternation between residues 1 and 2 (0s are
 * neutral). Alice wins when: (a) even count of 0s and both 1s and 2s exist, or (b) odd count of 0s and |count1 -
 * count2| > 2 — derived from analyzing losing positions in the mod-3 sequence.
 *
 * Time Complexity: O(N) - Single pass to reduce mod-3, single pass to count residues
 * Space Complexity: O(1) - Fixed-size count[3] array
 *
 * Edge Cases Handled: all stones ≡ 0 (no valid moves possible, returns false), only one residue type besides 0 (Alice
 * cannot complete a pair), |count1 - count2| ≤ 2 with odd 0s (Bob can force a loss)
 */
class StoneGameIx {
    public boolean stoneGameIX(int[] stones) {

        for (int i = 0; i < stones.length; i++) {
            stones[i] = stones[i] % 3;
        }

        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }

        if (count[0] % 2 == 0 && count[1] >= 1 && count[2] >= 1) {
            return true;
        } else if (count[0] % 2 != 0 && Math.abs(count[1] - count[2]) > 2) {
            return true;
        }

        return false;
    }
}
