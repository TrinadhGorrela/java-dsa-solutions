/**
 * 3934. Smallest Unique Subarray
 * Difficulty: Hard | Tags: Array, Hash Table, Binary Search, Rolling Hash,
 * Suffix Array, Hash Function
 * https://leetcode.com/problems/smallest-unique-subarray/
 *
 * Pattern: Binary Search on Answer + Rolling Hash (Polynomial Hash)
 *
 * Key insight: Uniqueness is monotonic in length - extending a unique subarray
 * by one element stays unique, because a duplicate extension would imply a
 * duplicate of the original. Binary search therefore finds the minimal length,
 * while a rolling hash tests each candidate in linear time.
 *
 * Time Complexity: O(N log N) - O(log N) binary-search steps, each running an
 * O(N) rolling-hash pass over all windows of the candidate length
 *
 * Space Complexity: O(N) - The hash-frequency map holds up to N distinct window
 * hashes in the worst case
 *
 * Edge Cases Handled: empty input (loop skipped, returns Integer.MAX_VALUE),
 * single element, all-same input (only the full array is unique, returns N), no
 * unique window below full length (returns N); assumes non-negative values and
 * no modular hash collisions
 */
class SmallestUniqueSubarray {
    private static final long b = 1_000_003;

    private static final long mod = 1_000_000_007;

    public int smallestUniqueSubarray(int[] nums) {
        int left = 1;
        int right = nums.length;
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (hasUniqueSubarray(nums, mid)) {
                right = mid - 1;
                min = Math.min(min, mid);
            } else {
                left = mid + 1;
            }
        }

        return min;
    }

    private boolean hasUniqueSubarray(int[] nums, int mid) {
        Map<Long, Integer> map = new HashMap<>();
        long hash = 0;
        long power = 1;

        for (int i = 0; i < mid; i++) {
            hash = (((hash * b) + nums[i]) % mod) % mod;

            if (i < mid - 1) {
                power = (power * b) % mod;
            }

        }

        map.put(hash, 1);

        for (int i = mid; i < nums.length; i++) {
            hash = ((((hash - (nums[i - mid] * power)) % mod) % mod + mod) % mod) % mod;
            hash = ((hash * b + nums[i]) % mod) % mod;

            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }

        for (Long key : map.keySet()) {
            if (map.get(key) == 1) {
                return true;
            }
        }

        return false;
    }
}
