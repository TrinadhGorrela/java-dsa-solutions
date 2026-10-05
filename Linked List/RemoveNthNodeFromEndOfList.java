/**
 * 19. Remove Nth Node From End of List
 * Difficulty: Medium | Tags: Linked List, Two Pointers
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Pattern: Two-Pointer Offset (Dummy Head)
 *
 * Key insight: Place both slow and fast on a dummy node, advance fast n steps,
 * then move both together until fast reaches the last node—slow now sits
 * exactly before the n-th-from-end node, enabling a clean unlink via slow.next
 * = slow.next.next.
 *
 * Time Complexity: O(n) - Single pass with two pointers.
 *
 * Space Complexity: O(1) - Dummy node + two pointers.
 *
 * Edge Cases Handled: removing the head (n == length; dummy bypasses it),
 * removing the tail (n = 1), single-node list, list of two nodes removing the
 * head
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
class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}
