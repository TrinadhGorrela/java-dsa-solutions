/**
 * 135. Candy
 * Difficulty: Hard | Tags: Array, Greedy
 * https://leetcode.com/problems/candy/
 *
 * Pattern: Two-Pass Greedy with Max Merge
 *
 * Key insight: A single pass can only enforce one neighbor constraint at a
 * time; two passes (left-to-right for ascending runs, right-to-left for
 * descending runs) independently set lower bounds, and the elementwise max
 * satisfies both simultaneously.
 *
 * Time Complexity: O(N) - Three linear passes: forward, backward, and sum
 *
 * Space Complexity: O(N) - Candies array of length N
 *
 * Edge Cases Handled: single child (returns 1), flat ratings (all get 1),
 * strict mountain shape (peak gets max of both slopes), equal adjacent ratings
 * (no forced increment in either direction)
 */
class Candy {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candies[i] = Math.max(candies[i], candies[i - 1] + 1);
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i + 1] < ratings[i]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int total = 0;
        for (int i = 0; i < ratings.length; i++) {
            total += candies[i];
        }

        return total;
    }
}
