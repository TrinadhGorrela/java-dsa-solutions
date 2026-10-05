/**
 * 2807. Insert Greatest Common Divisors in Linked List
 * Difficulty: Medium | Tags: Linked List, Math, Number Theory
 * https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/
 *
 * Pattern: Linked List Insertion + Euclidean Algorithm (GCD)
 *
 * Key insight: The GCD of two numbers equals GCD(b, a mod b), so Euclid's
 * algorithm shrinks the remainder until it reaches 0, giving the answer in
 * O(log min(a,b)). Threading that value between every adjacent pair via pointer
 * rewiring completes the insertion in one pass.
 *
 * Time Complexity: O(N log M) - The Euclidean algorithm runs in O(log M) per
 * adjacent pair (M = max node value), applied over N - 1 pairs.
 *
 * Space Complexity: O(1) - Only a few pointers; the inserted nodes are the
 * required output, no auxiliary collection.
 *
 * Edge Cases Handled: Single-node list (loop body is skipped and the original
 * head is returned), adjacent equal values (GCD equals the value itself), and
 * coprime neighbors (GCD = 1). Does not guard against a null head, which the
 * constraints rule out.
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
class InsertGreatestCommonDivisorsInLinkedList {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode prev = head;
        ListNode next = head.next;

        while (next != null) {
            ListNode node = new ListNode(helper(prev.val, next.val));
            prev.next = node;
            node.next = next;
            prev = next;
            next = next.next;
        }

        return head;
    }

    private int helper(int s, int t) {

        while (t != 0) {
            int temp = t;
            t = s % t;
            s = temp;
        }

        return s;
    }
}
