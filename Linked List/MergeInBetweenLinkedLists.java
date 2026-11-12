/**
 * 1669. Merge In Between Linked Lists
 * Difficulty: Medium | Tags: Linked List
 * https://leetcode.com/problems/merge-in-between-linked-lists/
 *
 * Pattern: Linked List Traversal (Pointer Rewiring)
 *
 * Key insight: Walk a position counter to node a-1 to splice list2 into list1,
 * then walk list2 to its tail and reconnect it to the node after b. No list
 * lengths are needed in advance because the position counter identifies the
 * removal boundary on the fly.
 *
 * Time Complexity: O(N + M) - Traverses list1 up to index b and list2 once,
 * where N and M are their respective lengths.
 *
 * Space Complexity: O(1) - Only a few pointer variables and a counter are used;
 * no extra nodes or collections.
 *
 * Edge Cases Handled: a == 1 (slice begins at the head boundary), single-node
 * list2, contiguous removal range where b = a, tail reattachment at any valid
 * b; a == 0 is NOT handled (position counter starts at 1), matching the
 * problem's a >= 1 constraint
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
class MergeInBetweenLinkedLists {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummy = list1;
        ListNode tail = null;
        int len = 0;

        while (dummy != null) {
            len++;

            if (len == a) {
                tail = dummy.next;
                dummy.next = list2;
                break;
            }

            dummy = dummy.next;
        }

        while (dummy.next != null) {
            dummy = dummy.next;
        }

        while (tail != null) {
            if (len == b + 1) {
                dummy.next = tail;
            }

            len++;
            tail = tail.next;
        }

        return list1;
    }
}
