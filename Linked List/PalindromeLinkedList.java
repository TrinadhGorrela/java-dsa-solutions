/**
 * 234. Palindrome Linked List
 * Difficulty: Easy | Tags: Linked List, Two Pointers, Stack, Recursion
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * Pattern: Two Pointers (Slow/Fast) + In-Place Reversal
 *
 * Key insight: Find the midpoint with slow/fast pointers, reverse the second
 * half in-place, then compare both halves node-by-node — a palindrome reads the
 * same forwards and backwards, so matching the first half against the reversed
 * second half is sufficient.
 *
 * Time Complexity: O(N) - Three linear passes (find midpoint, reverse, compare)
 * each visit every node at most once
 *
 * Space Complexity: O(1) - Only a constant number of pointers are used; the
 * second half is reversed in-place
 *
 * Edge Cases Handled: Single element list (trivially a palindrome); odd-length
 * list (middle node excluded from comparison); even-length list (both halves
 * compared fully); all-identical values; already-palindromic list; only safe
 * for non-null input (null head causes NPE at slow.next = null)
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
class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode curr = slow;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        slow.next = null;

        ListNode back = prev;

        while (head != null && back != null) {
            if (head.val != back.val) {
                return false;
            }

            head = head.next;
            back = back.next;
        }

        return true;
    }
}
