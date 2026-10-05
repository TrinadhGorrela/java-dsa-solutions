/**
 * 526. Beautiful Arrangement
 * Difficulty: Medium | Tags: Array, Dynamic Programming, Backtracking, Bit
 * Manipulation, Bitmask
 * https://leetcode.com/problems/beautiful-arrangement/
 *
 * Pattern: Permutation Backtracking with Divisibility Pruning
 *
 * Key insight: At each position p (1-indexed), only try placing numbers i where
 * i % p == 0 or p % i == 0; this mutual-divisibility constraint prunes entire
 * subtrees early, making the search much faster than brute-force permutation
 * enumeration.
 *
 * Time Complexity: O(n!) worst case, but practical runtime is far lower due to
 * divisibility pruning eliminating most branches
 *
 * Space Complexity: O(n) recursion depth + O(n) boolean array for tracking used
 * numbers
 *
 * Edge Cases Handled: n = 1 (single arrangement [1]), positions where no unused
 * number satisfies the divisibility condition (backtrack immediately), all
 * numbers equal to 1 (only one valid arrangement)
 */
class BeautifulArrangement {
    public int countArrangement(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        int count = 0;
        count = backtrack(nums, new ArrayList<>());

        return count;
    }

    public static int backtrack(int[] nums, List<Integer> temp) {
        if (temp.size() == nums.length) {
            return 1;
        }

        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (temp.contains(nums[i])) {
                continue;
            }

            if (nums[i] % (temp.size() + 1) != 0 && (temp.size() + 1) % nums[i] != 0) {
                continue;
            }

            temp.add(nums[i]);
            count += backtrack(nums, temp);
            temp.remove(temp.size() - 1);
        }
        return count;
    }
}
