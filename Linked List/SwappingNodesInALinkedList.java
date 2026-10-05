/**
 * 1721. Swapping Nodes in a Linked List
 * Difficulty: Medium | Tags: Linked List, Two Pointers
 * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
 *
 * Pattern: Two-Pass Traversal (Symmetric Index Pairing)
 *
 * Key insight: The k-th node from the head and the k-th node from the tail are
 * mirror positions, and in 1-based counting the mirror of position k is exactly
 * position len - k + 1. That index is unknowable until the list is fully
 * measured, so the code counts first and then re-walks once, capturing both
 * nodes by position and exchanging only their .val fields so the node
 * identities, next pointers, and the returned head all stay untouched.
 *
 * Time Complexity: O(N) - Two sequential linear traversals of the N-node list:
 * one to count length, one to locate the two positions.
 *
 * Space Complexity: O(1) - Only a fixed set of scalar counters and node
 * references; the two placeholder new ListNode() slots are constant-sized.
 *
 * Edge Cases Handled: single-element list (k == 1 makes both positions the same
 * node, so the value swap degenerates to a self no-op); k == 1 swapping head
 * with tail; the exact middle node of an odd-length list swapping with itself;
 * duplicate values swapped harmlessly; negative and large int values, since the
 * swap is plain field assignment with no arithmetic; out-of-range k is a silent
 * no-op, because both sentinels keep their default val of 0 and no real node is
 * touched. Not handled: an empty list is never explicitly checked, and the
 * method simply returns the (null) head unchanged.
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
class SwappingNodesInALinkedList {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode lenCalc = head;
        int len = 0;

        while (lenCalc != null) {
            len++;
            lenCalc = lenCalc.next;
        }

        ListNode dummy = head;
        int cnt = 1;

        ListNode firstNode = new ListNode();
        ListNode secondNode = new ListNode();

        while (dummy != null) {
            if (cnt == k) {
                firstNode = dummy;
            }

            if (cnt == (len - k + 1)) {
                secondNode = dummy;
            }

            cnt++;
            dummy = dummy.next;
        }

        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;

        return head;
    }
}
