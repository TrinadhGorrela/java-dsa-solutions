/**
 * 369. Plus One Linked List
 * Difficulty: Medium | Tags: Linked List, Math
 * https://leetcode.com/problems/plus-one-linked-list/
 *
 * Pattern: Sentinel Prepend + Rightmost Non-Nine Carry Absorption
 * Key insight: Adding 1 to a decimal number only disturbs the trailing run of 9s - each 9 rolls over to 0 and the carry stops at
 * the rightmost digit smaller than 9. So one forward scan records that rightmost non-nine digit, a single increment absorbs the
 * carry, and a second scan zeroes the 9-run behind it, instead of propagating carry node by node. Prepending a 0 sentinel
 * guarantees such a digit always exists, so an all-9s list overflows into the dummy rather than needing a special case.
 *
 * Time Complexity: O(N) - Two linear passes over the list: one to locate the rightmost digit < 9, one to zero the trailing run.
 * Space Complexity: O(1) - Exactly one extra sentinel ListNode; the original nodes are mutated and reused, so nothing else grows with N.
 *
 * Edge Cases Handled: full overflow when every digit is 9 (999 -> 1000), where the sentinel grows to 1 and is returned as the new
 * head; sentinel left untouched (head.val == 0) and unlinked to avoid a spurious leading zero; trailing nines of any length
 * (1299 -> 1300); no nines at all (1234 -> 1235), where the second pass is a no-op; single-node lists (0 -> 1, 9 -> 10); null/empty
 * input (head == null), where the sentinel alone becomes the result [1]; leading zeroes (0099 -> 0010) since the scan targets the
 * rightmost digit < 9; mutates the input list in place; assumes digits 0-9, so it is not valid for negative or multi-digit values.
 */
// ============================================================================


public class PlusOneLinkedList {
    public ListNode addOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode lastNine = dummy;
        dummy.next = head;
        head = dummy;

        while (dummy != null) {
            if (dummy.val < 9) {
                lastNine = dummy;
            }
            dummy = dummy.next;
        }

        lastNine.val += 1;
        lastNine = lastNine.next;

        while (lastNine != null) {
            lastNine.val = 0;
            lastNine = lastNine.next;
        }

        if (head.val == 0) {
            return head.next;
        }

        return head;
    }

    // Helper method to create a linked list from an array


    // Helper method to print the linked list


}
