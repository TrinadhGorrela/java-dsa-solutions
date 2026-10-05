/**
 * 287. Find the Duplicate Number
 * Difficulty: Medium | Tags: Array, Two Pointers, Binary Search, Bit
 * Manipulation, Pigeonhole Principle, Floyd's Cycle Finding Algorithm
 * https://leetcode.com/problems/find-the-duplicate-number/
 *
 * Pattern: Floyd's Tortoise and Hare Cycle Detection
 *
 * Key insight: Since values are in [1, n], treating nums[i] as "next node"
 * forms a linked list with a cycle — the duplicate is the cycle's entrance,
 * found by moving slow/fast to meet, then resetting one pointer to 0 and
 * advancing both at speed 1.
 *
 * Time Complexity: O(N) - Phase 1 finds meeting point in O(N), phase 2 finds
 * entrance in O(N)
 *
 * Space Complexity: O(1) - Two pointers only; no hash set or array copy needed
 *
 * Edge Cases Handled: duplicate at index 0 (cycle starts at 0), self-loop
 * (nums[i] = i forms a trivial cycle), duplicate appearing 2+ times (still one
 * cycle entrance)
 */
class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
