/**
 * 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
 * Difficulty: Medium | Tags: Linked List
 * https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-be
 * tween-critical-points/
 *
 * Pattern: Single-Pass Linked List Scan
 *
 * Key insight: A critical point is a local extremum (strictly greater or
 * strictly less than both neighbors); track the first critical point's position
 * and update min-distance between consecutive critical points and max-distance
 * from the first to the latest.
 *
 * Time Complexity: O(n) - Single traversal with prev/curr/next.
 *
 * Space Complexity: O(1) - Position counters and min/max variables.
 *
 * Edge Cases Handled: fewer than 2 critical points (returns [-1, -1]), first
 * critical point at index 1, adjacent critical points (min = 1), all equal
 * values (no critical points)
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev = head;
        ListNode curr = head.next;

        int position = 1;
        int first = 0;
        int prevPos = 0;
        int currPos = 0;
        int nodes = 0;

        int min = Integer.MAX_VALUE;
        int max = 0;

        while (curr.next != null) {
            ListNode next = curr.next;
            if ((prev.val < curr.val && curr.val > next.val) || (prev.val > curr.val && curr.val < next.val)) {
                if (first == 0) {
                    first = position;
                    prevPos = position;
                    currPos = position;
                } else {
                    prevPos = currPos;
                    currPos = position;

                    max = currPos - first;
                    min = Math.min(min, currPos - prevPos);
                }
                nodes++;
            }

            prev = curr;
            curr = next;
            position++;
        }

        if (nodes < 2) {
            return new int[] { -1, -1 };
        }

        return new int[] { min, max };
    }
}
