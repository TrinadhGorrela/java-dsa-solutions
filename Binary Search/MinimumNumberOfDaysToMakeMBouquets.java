/**
 * 1482. Minimum Number of Days to Make m Bouquets
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
 *
 * Pattern: Binary Search on Answer
 *
 * Key insight: Search over possible bloom days [min(bloomDay), max(bloomDay)];
 * for each candidate day, walk the array counting consecutive bloomed
 * flowers—every run of k yields one bouquet, and leftover bloomed flowers in a
 * run are discarded.
 *
 * Time Complexity: O(n log R) - n flowers, R = max(bloomDay) - min(bloomDay);
 * feasibility is O(n).
 *
 * Space Complexity: O(1) - Only bloom counter and bouquet counter.
 *
 * Edge Cases Handled: m*k > n returns -1 immediately, all flowers bloom on same
 * day, partial runs shorter than k contribute zero bouquets, long arithmetic
 * avoids overflow on m*k
 */
class MinimumNumberOfDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        int left = bloomDay[0];
        int right = bloomDay[0];
        int res = -1;

        if ((long) m * k > bloomDay.length) {
            return -1;
        }

        for (int i : bloomDay) {
            if (left > i) {
                left = i;
            }

            if (right < i) {
                right = i;
            }
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (days(bloomDay, mid, k) >= m) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static int days(int[] bloomDay, int dayThreshold, int k) {
        int consecutiveBlooms = 0;
        int bouquetCount = 0;
        for (int i : nums) {
            if (i <= dayThreshold) {
                consecutiveBlooms++;
            } else {
                bouquetCount += consecutiveBlooms / k;
                consecutiveBlooms = 0;
            }
        }
        bouquetCount += consecutiveBlooms / k;
        return bouquetCount;
    }
}
