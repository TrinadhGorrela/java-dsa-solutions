/**
 * 2571. Minimum Operations to Reduce an Integer to 0
 * Difficulty: Medium | Tags: Dynamic Programming, Greedy, Bit Manipulation
 * https://leetcode.com/problems/minimum-operations-to-reduce-an-integer-to-0/
 *
 * Pattern: Greedy Bit Manipulation (Carry Propagation)
 *
 * Key insight: Each operation toggles one power of two, and when two
 * consecutive bits are set, adding 1 carries them into a single higher bit —
 * clearing both for the cost of one operation — so counting isolated set bits
 * while folding adjacent pairs minimizes operations.
 *
 * Time Complexity: O(log N) - One shift per bit of n, with O(1) work per bit.
 *
 * Space Complexity: O(1) - Only a counter and the input are used; no auxiliary
 * structures.
 *
 * Edge Cases Handled: n == 0 returns 0, powers of two (a single set bit), runs
 * of consecutive set bits handled via the carry (n += 1), and carry ripple into
 * a new high bit. Only valid for non-negative n, as required by the problem.
 */
class MinimumOperationsToReduceAnIntegerTo0 {
    public int minOperations(int n) {
        int count = 0;

        while (n > 0) {
            if (n % 2 == 1) {
                count++;

                if ((n >> 1) % 2 == 1) {
                    n += 1;
                }
            }

            n >>= 1;
        }

        return count;
    }
}
