/**
 * 147. Insertion Sort List
 * Difficulty: Medium | Tags: Linked List, Sorting
 * https://leetcode.com/problems/insertion-sort-list/
 *
 * Pattern: Insertion Sort (In-Place Linked List Rewiring)
 *
 * Key insight: Detach each node from the unsorted remainder and splice it into
 * a dummy-headed sorted prefix by scanning for the first node with a value >=
 * it. Because only next pointers are rewired, the list is sorted without
 * copying node values.
 *
 * Time Complexity: O(N^2) - Each node may scan the entire growing sorted prefix
 * (an already-ascending list is the worst case); reverse-sorted input is O(N).
 *
 * Space Complexity: O(1) - Sorting is done in place using a dummy node and a
 * few pointer variables.
 *
 * Edge Cases Handled: null head (returns immediately), single node (loop never
 * runs), duplicate values (strict < comparison keeps equal elements adjacent),
 * already-sorted and reverse-sorted lists
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
class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        ListNode curr = head.next;

        dummy.next = head;
        dummy.next.next = null;

        while (curr != null) {
            ListNode next = curr.next;
            ListNode prev = dummy;

            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }

            ListNode node = prev.next;
            prev.next = curr;
            curr.next = node;

            curr = next;
        }

        return dummy.next;
    }
}
