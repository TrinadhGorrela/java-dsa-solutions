/**
 * 278. First Bad Version
 * Difficulty: Easy | Tags: Binary Search, Interactive
 * https://leetcode.com/problems/first-bad-version/
 *
 * Pattern: Binary Search (Lower Bound)
 *
 * Key insight: Versions form a monotonic [good...good, bad...bad] sequence;
 * when mid is bad, set end = mid (it could be the first bad); when mid is good,
 * set start = mid + 1 (first bad is strictly after). Converges when start ==
 * end.
 *
 * Time Complexity: O(log n) - Interval halves each step.
 *
 * Space Complexity: O(1) - Two pointers: start, end.
 *
 * Edge Cases Handled: n = 1, first version is bad (start never advances past
 * 1), last version is first bad, all versions good
 */
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class FirstBadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
