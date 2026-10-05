/**
 * 2181. Merge Nodes in Between Zeros
 * Difficulty: Medium | Tags: Linked List, Simulation
 * https://leetcode.com/problems/merge-nodes-in-between-zeros/
 *
 * Pattern: Single Pass Linked List Traversal (Running Sum Simulation)
 *
 * Key insight: Each 0, except the skipped head, closes a running-sum window, so
 * a single pass that adds values and resets the sum to 0 on every 0 produces
 * every merged node in order. This holds because each segment is bracketed by
 * zeros and accumulated independently.
 *
 * Time Complexity: O(N) - One traversal of the list, visiting each node exactly
 * once.
 *
 * Space Complexity: O(1) - Only a dummy node and an integer accumulator; the
 * result list is required output, not auxiliary storage.
 *
 * Edge Cases Handled: List of two zeros (yields a single 0 node), consecutive
 * zeros (produce a 0-valued node), a single segment between the leading and
 * trailing zero, and the trailing zero (finalizes the last segment). Relies on
 * the constraint that the list begins with a zero, since head is skipped
 * without checking its value.
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
class MergeNodesInBetweenZeros {
    public ListNode mergeNodes(ListNode head) {
        ListNode res = new ListNode();
        ListNode dummy = res;

        int sum = 0;
        head = head.next;

        while (head != null) {

            sum += head.val;

            if (head.val == 0) {
                dummy.next = new ListNode(sum);
                dummy = dummy.next;
                sum = 0;
            }

            head = head.next;
        }

        return res.next;
    }
}
