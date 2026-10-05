/**
 * 2. Add Two Numbers
 * Difficulty: Medium | Tags: Linked List, Math, Recursion
 * https://leetcode.com/problems/add-two-numbers/
 *
 * Pattern: Linked List Addition with Carry
 *
 * Key insight: Walk both lists in parallel, summing corresponding digits plus a
 * running carry; build the result list node-by-node. When one list ends,
 * continue draining the other. A final carry of 1 after both lists exhaust
 * appends one extra node.
 *
 * Time Complexity: O(max(m, n)) - Traverses the longer list once.
 *
 * Space Complexity: O(max(m, n)) - Output list is at most max(m,n) + 1 nodes.
 *
 * Edge Cases Handled: unequal list lengths, digit sum ≥ 10 (carry propagates),
 * final carry producing an extra leading node, one or both lists empty
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
class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode res = new ListNode();
        ListNode curr = res;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            if (sum <= 9) {
                res.next = new ListNode(sum);
                carry = 0;
            } else {
                carry = sum / 10;
                res.next = new ListNode(sum % 10);
            }
            l1 = l1.next;
            l2 = l2.next;
            res = res.next;
        }

        while (l1 != null) {
            int sum = l1.val + carry;
            if (sum <= 9) {
                res.next = new ListNode(sum);
                carry = 0;
            } else {
                carry = sum / 10;
                res.next = new ListNode(sum % 10);
            }
            l1 = l1.next;
            res = res.next;
        }

        while (l2 != null) {
            int sum = l2.val + carry;
            if (sum <= 9) {
                res.next = new ListNode(sum);
                carry = 0;
            } else {
                carry = sum / 10;
                res.next = new ListNode(sum % 10);
            }
            l2 = l2.next;
            res = res.next;
        }

        if (carry != 0) {
            res.next = new ListNode(carry);
        }
        return curr.next;
    }
}
