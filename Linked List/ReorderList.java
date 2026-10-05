/**
 * 143. Reorder List
 * Difficulty: Medium | Tags: Linked List, Two Pointers, Stack, Recursion
 * https://leetcode.com/problems/reorder-list/
 *
 * Pattern: Two Pointers (Slow/Fast) + In-Place Reversal + Merge
 *
 * Key insight: Find the midpoint with slow/fast pointers, reverse the second
 * half in-place, then interleave-merge the two halves. Because the second half
 * is detached at the midpoint, no extra nodes are needed.
 *
 * Time Complexity: O(N) - Three linear passes: find midpoint, reverse second
 * half, merge.
 *
 * Space Complexity: O(1) - All operations are in-place; only a handful of
 * pointers used.
 *
 * Edge Cases Handled: single node (loop bodies never execute, head returned
 * as-is), two nodes (midpoint split produces one reversed node, merge
 * interleaves correctly), odd-length list (slow lands on true middle, right
 * half has one fewer node).
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
class ReorderList {
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode curr = slow.next;
        slow.next = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        ListNode back = prev;

        while (back != null) {
            ListNode frontNext = head.next;
            ListNode backNext = back.next;

            head.next = back;
            back.next = frontNext;

            head = frontNext;
            back = backNext;
        }
    }
}
