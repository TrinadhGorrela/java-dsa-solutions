/**
 * 83. Remove Duplicates from Sorted List
 * Difficulty: Easy | Tags: Linked List
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 *
 * Pattern: Two-Pointer Deduplication
 *
 * Key insight: Because the list is sorted, duplicates appear in consecutive
 * runs; walk prev and curr—when curr.val == prev.val, skip it (prev.next =
 * curr.next), otherwise advance both pointers.
 *
 * Time Complexity: O(n) - Single traversal, each node visited once.
 *
 * Space Complexity: O(1) - Two pointers: prev, curr.
 *
 * Edge Cases Handled: null list, all nodes identical (reduces to single node),
 * no duplicates (list unchanged), duplicates at the tail
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
class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return head;
        }
        
        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null) {
            ListNode next = curr.next;
            if (prev.val == curr.val) {
                prev.next = next;
                curr = curr.next;
            } else {
                prev = curr;
                curr = next;
            }
        }
        return head;
    }
}
