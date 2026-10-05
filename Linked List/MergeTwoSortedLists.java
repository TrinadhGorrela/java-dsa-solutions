/**
 * 21. Merge Two Sorted Lists
 * Difficulty: Easy | Tags: Linked List, Recursion
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Pattern: Iterative Merge with Dummy Head
 *
 * Key insight: A dummy node provides a clean tail pointer; compare the two list
 * heads, attach the smaller node, advance that list, and repeat. When one list
 * exhausts, link the remainder of the other—zero node allocation beyond the
 * output.
 *
 * Time Complexity: O(m + n) - Each node is visited exactly once.
 *
 * Space Complexity: O(1) - Dummy node + one tail pointer; output is in-place.
 *
 * Edge Cases Handled: one or both lists empty, unequal lengths (remaining tail
 * appended), equal values (list1's node kept first), both single-node lists
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
class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        }

        if (list2 != null) {
            current.next = list2;
        }

        return dummy.next;
    }
}
