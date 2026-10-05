/**
 * 219. Contains Duplicate II
 * Difficulty: Easy | Tags: Array, Hash Table, Sliding Window
 * https://leetcode.com/problems/contains-duplicate-ii/
 *
 * Pattern: Sliding Window (Brute Force)
 *
 * Key insight: For each index i, the inner loop checks at most k subsequent
 * positions (j - i <= k), so the worst case is bounded by O(N*k) even though it
 * appears quadratic.
 *
 * Time Complexity: O(N*k) - Outer loop over N elements, inner loop scans up to
 * k positions ahead
 *
 * Space Complexity: O(1) - No auxiliary data structures; only loop indices
 *
 * Edge Cases Handled: k >= n (full array scanned per element), duplicates
 * exactly k apart (boundary match), single element (no inner iteration), no
 * duplicates (full O(N*k) traversal)
 */
class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length && j - i <= k; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
