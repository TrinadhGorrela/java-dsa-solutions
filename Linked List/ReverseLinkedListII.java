/**
 * 92. Reverse Linked List II
 * Difficulty: Medium | Tags: Linked List
 * https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * Pattern: Dummy-Headed Sub-List Reversal
 *
 * Key insight: Use a dummy node so left=1 (head reversal) needs no special
 * case; walk a pointer to the node just before `left`, then reverse exactly
 * (right - left + 1) nodes in-place by repeated front-insertion, and re-stitch
 * the segment's two ends to its neighbors.
 *
 * Time Complexity: O(n) - Single pass to position + reversal of the segment.
 *
 * Space Complexity: O(1) - In-place pointer rewiring only.
 *
 * Edge Cases Handled: left = 1 (head is part of reversal; dummy handles it),
 * left == right (no-op), reversal spans the entire list, reversal at the tail
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
class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode leftList = dummy;
        ListNode curr = dummy.next;
        for (int i = 0; i < left - 1; i++) {
            leftList = leftList.next;
            curr = curr.next;
        }

        ListNode subList = curr;

        ListNode prev = null;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        leftList.next = prev;
        subList.next = curr;
        return dummy.next;
    }
}
