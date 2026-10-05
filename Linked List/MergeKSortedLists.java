/**
 * 23. Merge k Sorted Lists
 * Difficulty: Hard | Tags: Linked List, Divide and Conquer, Heap (Priority
 * Queue), Merge Sort, Tournament Sort
 * https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * Pattern: Flatten + Sort (Collect-and-Rebuild)
 *
 * Key insight: Rather than managing a heap of K list pointers, flatten every
 * node's value into a single list, sort it once, and rebuild the linked list —
 * trading optimality of O(N log K) for implementation simplicity.
 *
 * Time Complexity: O(N log N) - Dominated by sorting the flattened list of all
 * N node values across K lists
 *
 * Space Complexity: O(N) - Auxiliary ArrayList stores all node values before
 * rebuilding
 *
 * Edge Cases Handled: null/empty input (lists array empty or all null heads →
 * returns null), single list (trivially sorted), K=0, all identical values,
 * lists of varying lengths
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
class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();

        for (ListNode head : lists) {
            while (head != null) {
                list.add(head.val);
                head = head.next;
            }
        }

        Collections.sort(list);

        ListNode head = new ListNode(Integer.MAX_VALUE);
        ListNode dummy = head;

        for (int i : list) {
            dummy.next = new ListNode(i);
            dummy = dummy.next;
        }

        return head.next;
    }
}
