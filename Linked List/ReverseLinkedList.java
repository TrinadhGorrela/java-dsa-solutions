/**
 * 206. Reverse Linked List
 * Difficulty: Easy | Tags: Linked List, Recursion
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * Pattern: Iterative In-Place Reversal
 *
 * Key insight: Maintain prev, curr, next; at each step, save curr.next, point
 * curr.next back to prev, then advance both pointers. After the loop, prev sits
 * at the new head.
 *
 * Time Complexity: O(n) - Single traversal, each pointer moves once.
 *
 * Space Complexity: O(1) - Three pointers: prev, curr, next.
 *
 * Edge Cases Handled: null list (prev stays null), single node (returned
 * unchanged), two nodes
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
class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
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
