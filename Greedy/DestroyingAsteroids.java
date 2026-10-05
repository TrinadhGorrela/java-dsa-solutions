/**
 * 2126. Destroying Asteroids
 * Difficulty: Medium | Tags: Array, Greedy, Sorting
 * https://leetcode.com/problems/destroying-asteroids/
 *
 * Pattern: Sort-and-Absorb Greedy
 *
 * Key insight: Sorting ensures we always absorb the smallest reachable asteroid
 * first, monotonically increasing mass so larger ones become reachable; if any
 * asteroid exceeds current mass, all subsequent (larger) ones will too —
 * immediate failure.
 *
 * Time Complexity: O(N log N) - Sorting dominates; the absorption scan is O(N)
 *
 * Space Complexity: O(1) - Single long accumulator for mass (long avoids int
 * overflow)
 *
 * Edge Cases Handled: asteroid larger than starting mass (first-or-early fail),
 * all asteroids absorbable (mass grows monotonically), mass sum exceeding int
 * range (long prevents overflow), single asteroid smaller than mass
 */
class DestroyingAsteroids {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long currMass = mass;

        for (int i : asteroids) {
            if (currMass < i) {
                return false;
            }
            currMass += i;
        }
        return true;
    }
}
