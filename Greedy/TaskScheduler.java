/**
 * 621. Task Scheduler
 * Difficulty: Medium | Tags: Array, Hash Table, Greedy, Sorting, Heap (Priority
 * Queue), Counting
 * https://leetcode.com/problems/task-scheduler/
 *
 * Pattern: Greedy Counting (Math Formula)
 *
 * Key insight: The bottleneck is the most frequent task. Placing (maxFreq - 1)
 * chunks of size (n + 1) creates the required cooldown gaps; adding the count
 * of tasks sharing maxFreq fills the final chunk. The answer is the maximum of
 * this formula and the total task count (when there are enough distinct tasks
 * to fill every slot with no idle time).
 *
 * Time Complexity: O(N) - Single pass over the task array to compute
 * frequencies and track max; N = number of tasks
 *
 * Space Complexity: O(1) - Fixed-size frequency array of 26 elements regardless
 * of input size
 *
 * Edge Cases Handled: n = 0 (no cooldown, answer is just task count); single
 * task type; all tasks are the same type; more distinct task types than
 * cooldown slots (no idle needed); empty task array
 */
class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0;
        int maxCount = 0;

        for (char task : tasks) {
            freq[task - 'A']++;

            if (freq[task - 'A'] == maxFreq) {
                maxCount++;
            } else if (freq[task - 'A'] > maxFreq) {
                maxFreq = freq[task - 'A'];
                maxCount = 1;
            }
        }

        int minIntervals = (maxFreq - 1) * (n + 1) + maxCount;

        return Math.max(minIntervals, tasks.length);
    }
}
