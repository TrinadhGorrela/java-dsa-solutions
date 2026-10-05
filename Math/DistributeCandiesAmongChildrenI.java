/**
 * 2928. Distribute Candies Among Children I
 * Difficulty: Easy | Tags: Math, Combinatorics, Enumeration
 * https://leetcode.com/problems/distribute-candies-among-children-i/
 *
 * Pattern: Two-Independent-Variable Enumeration (Complement Inference)
 *
 * Key insight: Only the first two children need explicit loops from 0 to limit;
 * the third child's share is derived as n - i - j, so a valid distribution is
 * counted when that remainder is non-negative and at most limit.
 *
 * Time Complexity: O(limit^2) - Two nested loops over [0, limit], independent
 * of n
 *
 * Space Complexity: O(1) - Only the res counter and loop indices
 *
 * Edge Cases Handled: remaining candies for the third child negative (invalid,
 * skipped) or exceeding limit (invalid), total candies n larger than 3 * limit
 * (zero valid distributions), limit = 0 forcing all shares to 0
 */
class DistributeCandiesAmongChildrenI {
    public int distributeCandies(int n, int limit) {
        int res = 0;
        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= limit; j++) {
                if (n - i - j <= limit && n - i - j >= 0) {
                    res++;
                }
            }
        }
        return res;
    }
}
