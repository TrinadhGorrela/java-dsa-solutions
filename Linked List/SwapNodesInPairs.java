/**
 * 24. Swap Nodes in Pairs
 * Difficulty: Medium | Tags: Linked List, Recursion
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Pattern: Iterative Linked List Pairwise Rewiring (Three-Pointer)
 *
 * Key insight: Maintain a trailing 'prev' pointer and advance a 'slow'/'fast'
 * pair through adjacent nodes. At each step, relink the four relevant .next
 * pointers to swap the current pair, then shift all three pointers forward by
 * two positions. Because each link is updated before the pointers advance, no
 * stack or temporary list is needed.
 *
 * Time Complexity: O(N) - Single linear pass; each node is visited exactly once
 *
 * Space Complexity: O(1) - Only a fixed number of pointer variables regardless
 * of list length
 *
 * Edge Cases Handled: null head (empty list); single node (odd-length tail left
 * untouched); two-node list (single swap, no loop iteration); even-length list
 * (all pairs swapped)
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
class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        ListNode prev = head;

        head = head.next;

        while (fast != null && fast.next != null) {
            ListNode next = fast.next;
            fast.next = slow;
            prev.next = fast;
            slow.next = next;

            prev = slow;
            slow = next;
            fast = next.next;
        }

        if (slow != null && fast != null) {
            ListNode next = fast.next;
            fast.next = slow;
            prev.next = fast;
            slow.next = next;
        }

        return head;
    }
}
