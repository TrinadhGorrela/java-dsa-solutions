/**
 * 680. Valid Palindrome II
 * Difficulty: Easy | Tags: Two Pointers, String, Greedy
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * Pattern: Two Pointers (Inward Scan with One-Skip Backtracking)
 *
 * Key insight: With at most one deletion allowed, the first mismatched pair
 * forces the skipped character to be either the left one or the right one, so
 * retrying those two substrings is exhaustive. Everything outside the mismatch
 * already matched, so only the inner region needs rechecking.
 *
 * Time Complexity: O(N) - At most two inward passes; each pointer only moves
 * toward the center.
 *
 * Space Complexity: O(1) - Only index variables are used; comparison happens
 * directly on the string.
 *
 * Edge Cases Handled: empty string and single character (loop never runs,
 * returns true); already-palindromic input; palindrome achievable by removing
 * the left or the right mismatched character; mismatches near the ends;
 * two-character strings where either deletion works; unrecoverable mismatches
 * such as "abc" (returns false).
 */
class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        boolean isValid = true;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                int tempLeft = left + 1;
                int tempRight = right;

                while (tempLeft < tempRight) {
                    if (s.charAt(tempLeft) != s.charAt(tempRight)) {
                        tempLeft = left;
                        tempRight = right - 1;

                        while (tempLeft < tempRight) {
                            if (s.charAt(tempLeft) != s.charAt(tempRight)) {
                                isValid = false;
                                break;
                            }

                            tempLeft++;
                            tempRight--;
                        }

                        break;
                    }

                    tempLeft++;
                    tempRight--;
                }

                break;
            }

            left++;
            right--;
        }

        return isValid;
    }
}
