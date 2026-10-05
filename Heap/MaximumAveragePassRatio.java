/**
 * 1792. Maximum Average Pass Ratio
 * Difficulty: Medium | Tags: Array, Greedy, Heap (Priority Queue)
 * https://leetcode.com/problems/maximum-average-pass-ratio/
 *
 * Pattern: Greedy Max-Heap on Marginal Gain
 *
 * Key insight: Each extra student should go to the class where adding them
 * yields the largest delta in pass ratio: `(pass+1)/(total+1) - pass/total`. A
 * max-heap on this gain ensures each assignment is locally optimal; reinsert
 * with updated gain after each allocation.
 *
 * Time Complexity: O((N + E) log N) - Build heap O(N), then E heap operations
 * of O(log N) each
 *
 * Space Complexity: O(N) - Heap stores one entry per class
 *
 * Edge Cases Handled: extraStudents = 0 (no heap pops), class with 100% pass
 * rate (gain approaches 0, naturally deprioritized), single class (all students
 * go there), large E with small N (heap churn but correct)
 */
class MaximumAveragePassRatio {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> maxGainQueue = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        for (int[] classInfo : classes) {
            int pass = classInfo[0];
            int total = classInfo[1];
            double gain = (pass + 1.0) / (total + 1) - (double) pass / total;
            maxGainQueue.offer(new double[] { gain, pass, total });
        }

        for (int s = 0; s < extraStudents; s++) {
            double[] top = maxGainQueue.poll();
            double pass = top[1] + 1;
            double total = top[2] + 1;
            double gain = (pass + 1.0) / (total + 1) - (double) pass / total;
            maxGainQueue.offer(new double[] { gain, pass, total });
        }
        double res = 0.0;
        while (!maxGainQueue.isEmpty()) {
            double[] top = maxGainQueue.poll();
            res = res + top[1] / top[2];
        }
        return res / classes.length;
    }
}
