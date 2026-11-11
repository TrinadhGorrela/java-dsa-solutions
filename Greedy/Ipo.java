/**
 * 502. IPO
 * Difficulty: Hard | Tags: Array, Greedy, Sorting, Heap (Priority Queue)
 * https://leetcode.com/problems/ipo/
 *
 * Pattern: Two-Heap Greedy (Min-Heap by Capital + Max-Heap by Profit)
 *
 * Key insight: Greedily take the highest-profit affordable project each round.
 * Because the wallet w only ever grows, affordability is monotonic: each
 * project crosses the capital threshold exactly once, so every element is moved
 * between the two heaps at most once.
 *
 * Time Complexity: O((N + K) log N) - Each project is pushed and popped from
 * each heap at most once (N log N), and each of the K selections costs one
 * additional O(log N) poll.
 *
 * Space Complexity: O(N) - The two priority queues together hold all N projects
 * at once.
 *
 * Edge Cases Handled: No affordable project remaining (breaks out before K
 * rounds), K larger than the number of affordable projects, empty
 * profits/capital arrays; int arithmetic on w is safe only within LeetCode's
 * bounds (max achievable capital stays below 2^31).
 */
class Ipo {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        Queue<int[]> projects = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Queue<int[]> availablePro = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

        for (int i = 0; i < capital.length; i++) {
            projects.add(new int[] { capital[i], profits[i] });
        }

        while (k-- != 0) {
            while (!projects.isEmpty() && projects.peek()[0] <= w) {
                availablePro.add(projects.poll());
            }

            if (availablePro.isEmpty()) {
                break;
            }

            w = w + availablePro.peek()[1];

            availablePro.poll();
        }

        return w;
    }
}
