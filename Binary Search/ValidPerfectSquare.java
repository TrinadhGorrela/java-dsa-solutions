/**
 * 367. Valid Perfect Square
 * Difficulty: Easy | Tags: Math, Binary Search
 * https://leetcode.com/problems/valid-perfect-square/
 *
 * Pattern: Binary Search on Integer Square Root
 *
 * Key insight: Search [1, num] for a mid where (long) mid * mid == num; cast to
 * long before multiplying to prevent integer overflow on large inputs.
 *
 * Time Complexity: O(log num) - Interval halves each step.
 *
 * Space Complexity: O(1) - Three integers: left, right, mid (long for square).
 *
 * Edge Cases Handled: num < 0 (returns false), num = 0 or 1 (immediate),
 * overflow avoided via long cast, non-perfect square returns false
 */
class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {

        if (num < 0)
            return false;

        int left = 1;
        int right = num;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sq = (long) mid * mid;
            if (sq == num)
                return true;
            else if (sq < num)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;

    }
}
