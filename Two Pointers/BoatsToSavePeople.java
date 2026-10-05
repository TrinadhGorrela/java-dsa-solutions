/**
 * 881. Boats to Save People
 * Difficulty: Medium | Tags: Array, Two Pointers, Greedy, Sorting, Timsort
 * https://leetcode.com/problems/boats-to-save-people/
 *
 * Pattern: Sort + Greedy Two-Pointer Pairing
 *
 * Key insight: Greedily pair the heaviest person with the lightest person who
 * fits (maximizing boat utilization); if no one fits alongside, the heaviest
 * rides alone—sorting makes this a single-pass two-pointer scan.
 *
 * Time Complexity: O(N log N) - Sorting dominates; the two-pointer scan is O(N)
 *
 * Space Complexity: O(1) - Three integers (left, right, boat count) beyond the
 * sort
 *
 * Edge Cases Handled: Heaviest person exceeds limit alone (boats still
 * counted), everyone fits in pairs (N/2 boats), single person, all people at
 * the same weight, lightest + heaviest exactly equals limit
 */
class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        int left = 0;
        int right = people.length - 1;
        int res = 0;
        Arrays.sort(people);
        while (left <= right) {
            int sum = people[left] + people[right];
            if (sum > limit) {
                res++;
                right--;
            } else {
                res++;
                left++;
                right--;
            } 
        }
        return res;
    }
}
