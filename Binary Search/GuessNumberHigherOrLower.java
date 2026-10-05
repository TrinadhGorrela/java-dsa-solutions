/**
 * 374. Guess Number Higher or Lower
 * Difficulty: Easy | Tags: Binary Search, Interactive
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 *
 * Pattern: Interactive Binary Search
 *
 * Key insight: The guess() API returns -1 (too high), 1 (too low), or 0
 * (found); use these as the comparison to halve [left, right] each iteration
 * until the exact number is narrowed down.
 *
 * Time Complexity: O(log n) - Interval halves each guess.
 *
 * Space Complexity: O(1) - Three integers: left, right, mid.
 *
 * Edge Cases Handled: n = 1 (returned immediately), picked number at boundary
 * (1 or n), picked number is the initial midpoint
 */
/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class GuessNumberHigherOrLower extends GuessGame {
    public int guessNumber(int n) {
        int left = 0;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int guessResult = guess(mid);

            if (guessResult == 0) {
                return mid;
            } else if (guessResult == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return 0;
    }
}
