/**
 * 69. Sqrt(x)
 * Difficulty: Easy | Tags: Math, Binary Search, Newton's Method
 * https://leetcode.com/problems/sqrtx/
 *
 * Pattern: Binary Search on Integer Square Root
 *
 * Key insight: Search [1, x] for the largest mid where mid ≤ x / mid
 * (equivalent to mid² ≤ x but avoids overflow); record the last satisfying mid
 * as the floor square root.
 *
 * Time Complexity: O(log x) - Interval halves each step.
 *
 * Space Complexity: O(1) - Three integers: start, end, result.
 *
 * Edge Cases Handled: x = 0 (early return), x = 1, perfect square (exact mid
 * found), non-perfect square (returns floor), overflow prevented by using x/mid
 * instead of mid*mid
 */
class Sqrtx {
    public int mySqrt(int x) {
        if (x == 0)
            return 0;

        int start = 1;
        int end = x;
        int result = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid <= x / mid) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }
}
