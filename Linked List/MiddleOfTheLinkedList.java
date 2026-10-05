/**
 * 876. Middle of the Linked List
 * Difficulty: Easy | Tags: Linked List, Two Pointers
 * https://leetcode.com/problems/middle-of-the-linked-list/
 *
 * Pattern: Fast / Slow Runner
 *
 * Key insight: Advance slow by one and fast by two; when fast reaches the end,
 * slow is at the second middle (for even-length lists) because it traveled
 * exactly half the distance.
 *
 * Time Complexity: O(n) - Slow traverses half the list.
 *
 * Space Complexity: O(1) - Two pointers: slow, fast.
 *
 * Edge Cases Handled: single node (returned as-is), even-length list (returns
 * the second middle), odd-length list
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
class MiddleOfTheLinkedList {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
