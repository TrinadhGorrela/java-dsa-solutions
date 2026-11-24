/**
 * 2410. Maximum Matching of Players With Trainers
 * Difficulty: Medium | Tags: Array, Two Pointers, Greedy, Sorting
 * https://leetcode.com/problems/maximum-matching-of-players-with-trainers/
 *
 * Pattern: Two Pointers (Sorted Greedy Matching)
 *
 * Key insight: After sorting both arrays, the smallest unmatched player is
 * offered the smallest available trainer; if that trainer cannot fit him it
 * cannot fit any later, stronger player either, so it can be discarded. This
 * exchange argument makes each successful pairing locally forced and globally
 * optimal.
 *
 * Time Complexity: O(M log M + N log N) - Sorting players (M) and trainers (N)
 * dominates; the two-pointer scan itself is O(M + N).
 *
 * Space Complexity: O(log M + log N) - Only the recursion stack of primitive
 * dual-pivot quicksort; no auxiliary collections are allocated.
 *
 * Edge Cases Handled: Empty players or trainers (returns 0), unequal pool sizes
 * (loop stops at the shorter array), trainers too small for even the smallest
 * remaining player (skipped), duplicate abilities, all players fitting, and
 * single-element input.
 */
class MaximumMatchingOfPlayersWithTrainers {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int s = 0;
        int t = 0;
        int res = 0;

        while (s < players.length && t < trainers.length) {
            if (players[s] <= trainers[t]) {
                res++;
                s++;
            }

            t++;
        }

        return res;
    }
}
