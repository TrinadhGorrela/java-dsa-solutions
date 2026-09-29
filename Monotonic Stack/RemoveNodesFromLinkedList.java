/**
 * 2487. Remove Nodes From Linked List
 * Difficulty: Medium | Tags: Linked List, Stack, Recursion, Monotonic Stack
 * https://leetcode.com/problems/remove-nodes-from-linked-list/
 *
 * Pattern: Two Reversals + Greedy Monotonic Stack (Reversed List Reused as the Stack)
 * Key insight: A node survives exactly when nothing strictly greater appears after it, so the survivors are precisely the suffix maxima of the list. Reversing the list once turns "something greater to my right" into "something greater to my left", reducing the problem to a left-to-right running-maximum filter in which each node is kept only if it is at least the running best; a second reversal restores the original order.
 *
 * Time Complexity: O(N) - Two in-place reversals plus one left-to-right scan, each visiting every node once.
 * Space Complexity: O(1) - Fully iterative with no recursion and no auxiliary collection; only pointer references and one constant-size sentinel node.
 *
 * Edge Cases Handled: a null head, where both reversals and the scan degenerate cleanly and the method returns null; a single-element list; the original head itself being removed, with the sentinel absorbing the new first node so head.next yields the correct start; equal values retained, since removal requires a strictly greater node, not merely an equal one; a strictly decreasing list collapsing to just the original tail; a strictly increasing list where every node survives; surviving nodes detached (next = null) so no removed node stays reachable; the input list is relinked and mutated in place. Not handled: negative values, because the running maximum is seeded at 0 and would discard a leading run of negatives (safe under the 1 <= val <= 10^5 bound).
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
class RemoveNodesFromLinkedList {
    public ListNode removeNodes(ListNode head) {
        ListNode dummy = reverse(head);
        ListNode res = new ListNode();
        head = res;
        int val = 0;

        while (dummy != null) {
            ListNode next = dummy.next;
            dummy.next = null;

            if (val <= dummy.val) {
                res.next = dummy;
                res = res.next;
                val = dummy.val;
            }

            dummy = next;
        }

        return reverse(head.next);
    }

    private ListNode reverse(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
