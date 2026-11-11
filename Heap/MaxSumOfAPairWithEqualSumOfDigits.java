/**
 * 2342. Max Sum of a Pair With Equal Sum of Digits
 * Difficulty: Medium | Tags: Array, Hash Table, Sorting, Heap (Priority Queue)
 * https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
 *
 * Pattern: Hash Map Bucketing by Digit Sum + Sorting
 *
 * Key insight: Only the two largest numbers sharing a digit sum can form that
 * bucket's best pair, so bucket every number by its digit sum and take the top
 * two from each — turning an O(N^2) pairwise comparison into a single grouping
 * pass plus per-bucket sorting.
 *
 * Time Complexity: O(N log N) - digit-sum extraction is O(digits) per number
 * (at most 10 for values up to 10^9), and the per-bucket sorts sum to at most
 * one full sort of all N elements.
 *
 * Space Complexity: O(N) - the hash map stores every input number in its
 * digit-sum bucket.
 *
 * Edge Cases Handled: no two numbers sharing a digit sum (returns -1), buckets
 * with fewer than two elements (skipped via the size >= 2 check), and
 * duplicates within a bucket (sorted normally; equal values pair fine). The res
 * == 0 "no pair" sentinel is only safe because nums[i] >= 1 per constraints —
 * zero or negative inputs would be misreported.
 */
class MaxSumOfAPairWithEqualSumOfDigits {
    public int maximumSum(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int sum = helper(nums[i]);

            if (!map.containsKey(sum)) {
                map.put(sum, new ArrayList<>());
            }

            map.get(sum).add(nums[i]);
        }

        int res = 0;

        for (int i : map.keySet()) {
            List<Integer> temp = map.get(i);

            if (temp.size() >= 2) {
                Collections.sort(temp);
                res = Math.max(res, temp.get(temp.size() - 1) + temp.get(temp.size() - 2));
            }
        }

        return res == 0 ? -1 : res;
    }

    private int helper(int n) {
        int sum = 0;

        while (n != 0) {
            int last = n % 10;
            sum += last;
            n /= 10;
        }

        return sum;
    }
}
