/**
 * 86. Partition List
 * Difficulty: Medium | Tags: Linked List, Two Pointers
 * https://leetcode.com/problems/partition-list/
 *
 * Pattern: Two Pointers (Link Partitioning with Two Dummy Heads)
 *
 * Key insight: Because each node is detached from the input and appended to a
 * "small" or "large" chain by comparing its value with x, relative order inside
 * both partitions is preserved; splicing the small chain onto the large chain
 * rebuilds a stable partition with no swaps.
 *
 * Time Complexity: O(N) - Single pass over the list; every node is processed
 * exactly once
 *
 * Space Complexity: O(1) - Only two constant dummy sentinel nodes are
 * allocated; no data nodes are created
 *
 * Edge Cases Handled: null/empty list (loop is skipped, a null tail is
 * returned); all nodes < x or all nodes >= x (one chain stays empty and the
 * join still succeeds); single node; duplicate values (stable ordering is kept)
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
class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode();
        ListNode res = small;
        ListNode large = new ListNode();
        ListNode dummy = large;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = next;
        }
        small.next = dummy.next;
        return res.next;
    }
}
