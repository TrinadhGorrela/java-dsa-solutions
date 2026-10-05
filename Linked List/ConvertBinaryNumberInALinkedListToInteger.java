/**
 * 1290. Convert Binary Number in a Linked List to Integer
 * Difficulty: Easy | Tags: Linked List, Math
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integ
 * er/
 *
 * Pattern: String Accumulation + Base-2 Parse
 *
 * Key insight: Concatenate each node's bit into a StringBuilder to form the
 * binary string, then use Integer.parseInt with radix 2 to convert to decimal.
 *
 * Time Complexity: O(n) - One pass to build the string, parse is also O(n).
 *
 * Space Complexity: O(n) - StringBuilder holds one character per node.
 *
 * Edge Cases Handled: single node, leading zero bits, maximum 30-bit value
 * (within int range)
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
class ConvertBinaryNumberInALinkedListToInteger {
    public int getDecimalValue(ListNode head) {
        StringBuilder res = new StringBuilder();
        while (head != null) {
            res.append(head.val + "");
            head = head.next;
        }

        return Integer.parseInt(res.toString(), 2);
    }
}
