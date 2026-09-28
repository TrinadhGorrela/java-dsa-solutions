/**
 * 1019. Next Greater Node In Linked List
 * Difficulty: Medium | Tags: Array, Linked List, Stack, Monotonic Stack
 * https://leetcode.com/problems/next-greater-node-in-linked-list/
 *
 * Pattern: Monotonic Stack (Right-to-Left Decreasing Pass)
 * Key insight: Scanning right-to-left with a strictly decreasing monotonic stack means each element pops all smaller-or-equal stack entries then reports the remaining top as its next greater — every value is pushed and popped at most once.
 *
 * Time Complexity: O(N) - One pass to flatten the list + one right-to-left pass where each element is pushed/popped from the stack at most once
 * Space Complexity: O(N) - ArrayList copy of list values + stack + result array, all proportional to N
 *
 * Edge Cases Handled: null/empty list (returns empty array), single node (no greater element → 0), strictly increasing list (every element gets 0), strictly decreasing list (every element sees the previous element's value), duplicate values (<= comparison pops equal values so only strictly greater is kept)
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
class NextGreaterNodeInLinkedList {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        Stack<Integer> stack = new Stack<>();
        int[] res = new int[list.size()];
        int in = list.size() - 1;

        for (int i = list.size() - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= list.get(i)) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                res[in--] = 0;
            } else {
                res[in--] = stack.peek();
            }
            stack.push(list.get(i));
        }

        return res;
    }
}
