/**
 * 203. Remove Linked List Elements
 * Difficulty: Easy | Tags: Linked List, Recursion
 * https://leetcode.com/problems/remove-linked-list-elements/
 *
 * Pattern: Dummy-Headed Linear Scan
 *
 * Key insight: A dummy predecessor node eliminates the special case of removing
 * the head; walk with prev and curr—when curr.val == target, bypass it
 * (prev.next = curr.next), otherwise advance prev.
 *
 * Time Complexity: O(n) - Single traversal of the list.
 *
 * Space Complexity: O(1) - Dummy node + two pointers.
 *
 * Edge Cases Handled: null list, head matches target (dummy bypasses it), all
 * nodes match (result is null), target absent (list unchanged), consecutive
 * matches
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
class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode curr = dummy;
        ListNode prev = dummy;
        while (curr != null) {
            ListNode next = curr.next;
            if (curr.val == val) {
                prev.next = next;
                curr = next;
            } else {
                prev = curr;
                curr = next;
            }

        }
        return dummy.next;
    }
}
