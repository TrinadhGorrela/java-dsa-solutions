/**
 * 1834. Single-Threaded CPU
 * Difficulty: Medium | Tags: Array, Sorting, Heap (Priority Queue)
 * https://leetcode.com/problems/single-threaded-cpu/
 *
 * Pattern: Min-Heap + Sorting (Two Priority Queues)
 *
 * Key insight: One min-heap orders tasks by enqueue time and releases all tasks
 * available at the current time into a second heap keyed by (processing time,
 * index). When no task is available the CPU jumps straight to the next enqueue
 * time, so idle gaps are skipped in one step instead of being simulated.
 *
 * Time Complexity: O(N log N) - Every task is inserted into and removed from
 * the heaps once, each operation costing O(log N).
 *
 * Space Complexity: O(N) - Two heaps hold up to N task records and the result
 * array is size N.
 *
 * Edge Cases Handled: single task, idle gaps before and between tasks (time
 * jumps to the next arrival), ties in processing time broken by the smaller
 * original index, enqueue time 0, large cumulative runtime (accumulated in a
 * long); empty input is NOT handled (task.peek() would throw)
 */
class SingleThreadedCpu {
    public int[] getOrder(int[][] tasks) {
        Queue<int[]> task = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Queue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[2], b[2]);
            }

            return Integer.compare(a[1], b[1]);
        });
        int[] res = new int[tasks.length];

        for (int i = 0; i < tasks.length; i++) {
            task.add(new int[] { tasks[i][0], tasks[i][1], i });
        }

        long time = task.peek()[0];
        int in = 0;

        while (in < tasks.length) {
            while (!task.isEmpty() && task.peek()[0] <= time) {
                available.add(task.poll());
            }

            if (available.isEmpty()) {
                time = task.peek()[0];
                continue;
            }

            int[] temp = available.poll();
            res[in++] = temp[2];
            time += temp[1];
        }

        return res;
    }
}
