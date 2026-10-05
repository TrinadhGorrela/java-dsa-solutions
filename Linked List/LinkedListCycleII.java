/**
 * 142. Linked List Cycle II
 * Difficulty: Medium | Tags: Hash Table, Linked List, Two Pointers, Floyd's
 * Cycle Finding Algorithm
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * Pattern: Floyd's Two-Phase Cycle Detection
 *
 * Key insight: Phase 1: slow/fast meet inside the cycle. Phase 2: reset slow to
 * head and advance both one step at a time—they meet at the cycle entry because
 * the distance from head to entry equals the distance from the meeting point
 * back to entry (mod cycle length).
 *
 * Time Complexity: O(n) - Each phase is at most O(n).
 *
 * Space Complexity: O(1) - Two pointers only.
 *
 * Edge Cases Handled: no cycle (fast reaches null), cycle at the head (entry
 * found immediately in phase 2), single-node self-loop, cycle does not include
 * the head
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
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
