/**
 * 846. Hand of Straights
 * Difficulty: Medium | Tags: Array, Hash Table, Greedy, Sorting
 * https://leetcode.com/problems/hand-of-straights/
 *
 * Pattern: Greedy + Min-Heap (Always Start a Group at the Smallest Card)
 *
 * Key insight: The smallest card still in hand must begin the next group
 * because no smaller card exists to precede it. So greedily consuming min,
 * min+1, ..., min+groupSize-1 from a min-heap is both necessary and sufficient
 * — if any successor card is missing, no valid grouping exists.
 *
 * Time Complexity: O(N^2) - each of the N removals uses
 * PriorityQueue.contains() and remove(Object), which linearly scan the heap's
 * backing array; a sorted TreeMap/counter approach would achieve O(N log N).
 *
 * Space Complexity: O(N) - the priority queue holds all N cards at once.
 *
 * Edge Cases Handled: hand length not divisible by groupSize (immediate false),
 * a missing consecutive card mid-group (false), duplicate card values (the heap
 * preserves multiplicity, and remove(min + 1) removes exactly one occurrence),
 * groupSize = 1 (inner loop is skipped; true iff divisible), and single-card
 * hands. Assumes groupSize >= 1 per constraints — groupSize = 0 would throw an
 * ArithmeticException on the modulo check.
 */
class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for (int i = 0; i < hand.length; i++) {
            queue.add(hand[i]);
        }

        while (!queue.isEmpty()) {
            int min = queue.poll();
            int size = groupSize - 1;

            while (size-- != 0) {
                if (!queue.contains(min + 1)) {
                    return false;
                }

                queue.remove(min + 1);
                min += 1;
            }
        }

        return true;
    }
}
