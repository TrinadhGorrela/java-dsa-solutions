/**
 * 2816. Double a Number Represented as a Linked List
 * Difficulty: Medium | Tags: Linked List, Math, Stack
 * https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/
 *
 * Pattern: Stack + Digit Doubling with Carry (Least-Significant First)
 *
 * Key insight: Doubling can overflow the most-significant digit and prepend a
 * new head node, so digits must be processed from the least-significant end.
 * Pushing onto a stack reverses the list without mutating it, and since each
 * doubled digit is at most 9*2+1=19, a single carry bit is always sufficient.
 *
 * Time Complexity: O(N) - Three linear passes: push all values onto the stack,
 * pop to double each digit, and reverse the result list.
 *
 * Space Complexity: O(N) - The stack holds one entry per node in the list.
 *
 * Edge Cases Handled: carry propagating past the head creating a new leading
 * node (e.g. 999 -> 1998), no carry anywhere, mid-list carries from doubled
 * digits >= 10, single-node lists, and null/empty input (returns null).
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
class DoubleANumberRepresentedAsALinkedList {
    public ListNode doubleIt(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        while (head != null) {
            int last = head.val;
            stack.push(last);
            head = head.next;
        }

        int carry = 0;
        ListNode res = new ListNode();
        ListNode dummy = res;

        while (!stack.isEmpty()) {
            int sum = (stack.pop() * 2) + carry;

            if (sum >= 10) {
                carry = 1;
                dummy.next = new ListNode(sum % 10);
            } else {
                carry = 0;
                dummy.next = new ListNode(sum);
            }
            dummy = dummy.next;
        }

        if (carry == 1) {
            dummy.next = new ListNode(carry);
        }

        ListNode curr = res.next;
        ListNode prev = null;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
