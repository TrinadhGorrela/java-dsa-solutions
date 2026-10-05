/**
 * 1011. Capacity To Ship Packages Within D Days
 * Difficulty: Medium | Tags: Array, Binary Search
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 *
 * Pattern: Binary Search on Answer
 *
 * Key insight: The search space for capacity is [max(weights), sum(weights)];
 * for each candidate capacity, a greedy left-to-right scan packs as many
 * packages as possible per day and counts whether days suffice.
 *
 * Time Complexity: O(n log W) - n = number of packages, W = sum of weights;
 * each feasibility check is O(n).
 *
 * Space Complexity: O(1) - Only load accumulator and day counter.
 *
 * Edge Cases Handled: single package (capacity = its weight), all packages fit
 * in one day, capacity must equal heaviest single package minimum, days exactly
 * equal to number of packages
 */
class CapacityToShipPackagesWithinDDays {

    public static boolean isValid(int[] weights, int days, int capacity) {
        int currentLoad = 0;
        int daysNeeded = 1;
        for (int i = 0; i < weights.length; i++) {
            currentLoad += weights[i];
            if (currentLoad > capacity) {
                currentLoad = weights[i];
                daysNeeded++;
            }
        }

        return daysNeeded <= days;
    }

    public int shipWithinDays(int[] weights, int days) {
        int minCapacity = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        for (int i = 0; i < weights.length; i++) {
            left = Math.max(left, weights[i]);
            right += weights[i];
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(weights, days, mid)) {
                minCapacity = Math.min(minCapacity, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return minCapacity;
    }
}
