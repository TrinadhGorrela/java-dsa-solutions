/**
 * 237. Delete Node in a Linked List
 * Difficulty: Medium | Tags: Linked List
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 *
 * Pattern: Value Overwrite (Copy-and-Skip Deletion)
 *
 * Key insight: Since we only have the target node (no predecessor), we copy the
 * next node's value into it and bypass the next node. The problem guarantees
 * the target is never the tail, so next always exists.
 *
 * Time Complexity: O(1) - Constant-time: two assignments plus one pointer
 * redirect.
 *
 * Space Complexity: O(1) - In-place mutation, no auxiliary structures.
 *
 * Edge Cases Handled: node.next is guaranteed non-null by LeetCode (node is
 * never the tail).
 * Note: would throw NullPointerException if called on the last node, which the
 * constraints forbid.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
        ListNode curr = node;
        ListNode next = curr.next;

        curr.val = next.val;
        curr.next = next.next;
    }
}
