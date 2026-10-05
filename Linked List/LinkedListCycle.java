/**
 * 141. Linked List Cycle
 * Difficulty: Easy | Tags: Hash Table, Linked List, Two Pointers, Floyd's Cycle
 * Finding Algorithm
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * Pattern: Floyd's Tortoise and Hare
 *
 * Key insight: Move slow by one and fast by two; if a cycle exists they must
 * eventually meet (fast laps slow). If fast reaches null, no cycle exists.
 *
 * Time Complexity: O(n) - In the worst case slow traverses the full list once.
 *
 * Space Complexity: O(1) - Two pointers: slow, fast.
 *
 * Edge Cases Handled: null list (returns false), single node with no cycle,
 * self-loop (slow == fast.next), two-node cycle
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
}
