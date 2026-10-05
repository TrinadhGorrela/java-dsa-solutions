/**
 * 875. Koko Eating Bananas
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/koko-eating-bananas/
 *
 * Pattern: Binary Search on Answer
 *
 * Key insight: Search speeds in [1, max(pile)]; for each candidate speed k, sum
 * ceil(pile[i] / k) across all piles to get total hours—if ≤ h the speed is
 * feasible, binary search for the minimum feasible k.
 *
 * Time Complexity: O(n log M) - n piles, M = max(pile); each feasibility check
 * is O(n).
 *
 * Space Complexity: O(1) - Only speed bounds and running hour total (long).
 *
 * Edge Cases Handled: single pile, speed evenly divides all piles, h equals
 * number of piles (speed must be max pile), long prevents overflow on hour
 * accumulation
 */
class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;

        for (int i : piles) {
            if (right < i) {
                right = i;
            }
        }
        int res = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long hoursNeeded = computeHours(piles, mid);
            if (hoursNeeded <= h) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static long computeHours(int[] piles, int speed) {
        long totalHours = 0;
        for (int i = 0; i < piles.length; i++) {
            int fullHours = piles[i] / speed;
            if (piles[i] % speed == 0) {
                totalHours += fullHours;
            } else {
                totalHours += fullHours + 1;
            }
        }
        return totalHours;
    }
}
