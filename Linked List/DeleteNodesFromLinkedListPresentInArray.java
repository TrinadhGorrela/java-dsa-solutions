/**
 * 3217. Delete Nodes From Linked List Present in Array
 * Difficulty: Medium | Tags: Array, Hash Table, Linked List
 * https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/
 *
 * Pattern: Hash Set + Sentinel Dummy Walk
 *
 * Key insight: Converting nums into a HashSet makes each node check O(1), and
 * the sentinel head turns even deleting the original head into an ordinary
 * splice; a prev pointer trailing the current node links around any node whose
 * value is in the set while traversal continues untouched.
 *
 * Time Complexity: O(N + M) - Building the set costs O(M) for the nums array,
 * then a single O(N) pass over the list
 *
 * Space Complexity: O(M) - Hash set holding the values from nums
 *
 * Edge Cases Handled: deletion of the head node (the sentinel makes it
 * identical to any other splice); every node deleted (returns a null tail, per
 * the single-node case); no deletion (empty nums leaves the list unchanged);
 * duplicate values in nums (a set dedupes them harmlessly)
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
class DeleteNodesFromLinkedListPresentInArray {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        ListNode dummy = new ListNode();

        ListNode prev = dummy;
        ListNode res = prev;
        dummy.next = head;
        dummy = dummy.next;

        while (dummy != null) {
            ListNode next = dummy.next;
            if (set.contains(dummy.val)) {
                prev.next = next;

            } else {
                prev.next = dummy;
                prev = prev.next;
            }

            dummy = dummy.next;

        }
        return res.next;
    }
}
