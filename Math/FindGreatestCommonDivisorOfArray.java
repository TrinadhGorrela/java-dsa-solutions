/**
 * 1979. Find Greatest Common Divisor of Array
 * Difficulty: Easy | Tags: Array, Math, Number Theory, Euclidean Algorithm,
 * Greatest Common Divisor
 * https://leetcode.com/problems/find-greatest-common-divisor-of-array/
 *
 * Pattern: Min/Max Scan + Euclidean GCD
 *
 * Key insight: gcd(all elements) = gcd(min, max) because the GCD of any set
 * divides both the minimum and maximum. A single pass finds min and max; then
 * the Euclidean algorithm (repeated modulo) computes their GCD in O(log min)
 * time.
 *
 * Time Complexity: O(N + log M) - Linear scan for min/max, then Euclidean
 * algorithm where M = min value
 *
 * Space Complexity: O(1) - Only min, max, and temporary swap variables
 *
 * Edge Cases Handled: single element (gcd with itself), all elements equal, min
 * and max coprime (GCD = 1), values at integer extremes
 */
class FindGreatestCommonDivisorOfArray {
    public int findGCD(int[] nums) {
        int small = Integer.MAX_VALUE;
        int large = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (small > nums[i]) {
                small = nums[i];
            }
            if (large < nums[i]) {
                large = nums[i];
            }
        }
        return gcd(large, small);
    }

    public static int gcd(int s, int t) {
        while (t != 0) {
            int temp = t;
            t = s % t;
            s = temp;
        }
        return s;
    }
}
