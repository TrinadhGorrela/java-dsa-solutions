/**
 * 2095. Delete the Middle Node of a Linked List
 * Difficulty: Medium | Tags: Linked List, Two Pointers
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 *
 * Pattern: Two Pointers (Slow/Fast) + Sentinel Dummy
 *
 * Key insight: Because fast advances exactly twice as fast as slow, slow lands
 * on the middle node the moment fast falls off the end; the dummy pointer
 * trails slow by one node and therefore always marks the middle's predecessor,
 * letting the node be relinked around in a single assignment.
 *
 * Time Complexity: O(N) - One pass; fast covers the whole list in about N/2
 * iterations
 *
 * Space Complexity: O(1) - Only constant extra pointers are used
 *
 * Edge Cases Handled: single node (returns null); two-node list (the head is
 * the second middle and is unlinked via the sentinel); even-length lists (the
 * second of the two middle nodes is deleted per the problem definition,
 * verified for lengths 2 and 4). Does not guard a null head - head.next would
 * throw NPE - but the constraints guarantee a non-empty list.
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
class DeleteTheMiddleNodeOfALinkedList {
    public ListNode deleteMiddle(ListNode head) {

        if (head.next == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            dummy = dummy.next;
            slow = slow.next;
            fast = fast.next.next;
        }

        dummy.next = slow.next;

        return head;
    }
}
