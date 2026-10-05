/**
 * 328. Odd Even Linked List
 * Difficulty: Medium | Tags: Linked List
 * https://leetcode.com/problems/odd-even-linked-list/
 *
 * Pattern: Two-Pointer Decomposition (Odd/Even Split via Dummy Heads)
 *
 * Key insight: A single pass with an alternating boolean partition node into
 * two separate chains (odd-indexed and even-indexed), then stitch the odd
 * chain's tail to the even chain's head — the alternation guarantees no node is
 * skipped.
 *
 * Time Complexity: O(N) - Single pass through all N nodes
 *
 * Space Complexity: O(N) - Creates a new ListNode copy for each original node
 *
 * Edge Cases Handled: null/empty list (returns null), single node (odd chain
 * returns it alone), two-node list (odd→even, even.next = null), already
 * odd-even grouped input
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
class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        ListNode oddDummy = new ListNode();
        ListNode evenDummy = new ListNode();

        ListNode odd = oddDummy;
        ListNode even = evenDummy;

        boolean isOdd = true;

        while (head != null) {
            if (isOdd) {
                oddDummy.next = new ListNode(head.val);
                oddDummy = oddDummy.next;
            } else {
                evenDummy.next = new ListNode(head.val);
                evenDummy = evenDummy.next;
            }

            isOdd = !isOdd;
            head = head.next;
        }

        oddDummy.next = even.next;
        return odd.next;
    }
}
