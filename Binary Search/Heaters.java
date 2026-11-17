/**
 * 475. Heaters
 * Difficulty: Medium | Tags: Array, Two Pointers, Binary Search, Sorting
 * https://leetcode.com/problems/heaters/
 *
 * Pattern: Binary Search on the Answer + Two Pointers (Feasibility Check)
 *
 * Key insight: Coverability is monotonic in the radius: if radius R covers
 * every house, any larger radius does too, so binary search finds the minimum.
 * Both arrays are sorted, so a single forward sweep of houses against heaters
 * decides feasibility in linear time.
 *
 * Time Complexity: O(H log H + K log K + (H + K) log C) - Sorts H houses and K
 * heaters, then runs a linear two-pointer check per binary-search step over
 * coordinate range C.
 *
 * Space Complexity: O(log(H + K)) - Only the recursion stack of Java's
 * primitive-array sort; no auxiliary collections.
 *
 * Edge Cases Handled: Single house or heater, duplicate and unsorted positions,
 * houses beyond the farthest heater; empty arrays throw on index access, and
 * negative coordinates are not handled (the 0-based upper bound would skip the
 * search).
 */
class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int left = 0;
        int right = Math.max(houses[houses.length - 1], heaters[heaters.length - 1]);
        int res = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isValid(houses, heaters, mid)) {
                right = mid - 1;
                res = Math.min(res, mid);
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private boolean isValid(int[] houses, int[] heaters, int radius) {
        int in = 0;

        for (int i = 0; i < heaters.length; i++) {
            int minRange = heaters[i] - radius;
            int maxRange = heaters[i] + radius;

            while (in < houses.length && (houses[in] >= minRange && houses[in] <= maxRange)) {
                in++;
            }
        }

        return in == houses.length;
    }

}
