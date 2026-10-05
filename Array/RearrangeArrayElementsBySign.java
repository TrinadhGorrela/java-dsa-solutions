/**
 * 2149. Rearrange Array Elements by Sign
 * Difficulty: Medium | Tags: Array, Two Pointers, Simulation
 * https://leetcode.com/problems/rearrange-array-elements-by-sign/
 *
 * Pattern: Two-Pass Partition + Interleave
 *
 * Key insight: Two linear passes (collect, then scatter) preserve relative
 * order within each sign group; positives land at even indices and negatives at
 * odd indices by construction, with no need for additional sorting.
 *
 * Time Complexity: O(N) - First pass fills pos/neg lists, second pass writes
 * result; both O(N)
 *
 * Space Complexity: O(N) - Two ArrayLists plus the result array, all
 * proportional to N
 *
 * Edge Cases Handled: Equal counts of positives and negatives (guaranteed by
 * contract), large N (ArrayList dynamic resizing), single-element array,
 * alternating signs already in place
 */
class RearrangeArrayElementsBySign {
    public int[] rearrangeArray(int[] nums) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                pos.add(nums[i]);
            } else {
                neg.add(nums[i]);
            }
        }

        int positivePlaced = 0;
        int negativePlaced = 0;
        int resultIndex = 0;
        int posIndex = 0;
        int negIndex = 0;

        while (positivePlaced < pos.size() && negativePlaced < neg.size()) {
            if (resultIndex % 2 == 0) {
                nums[resultIndex] = pos.get(posIndex++);
                positivePlaced++;
            } else {
                nums[resultIndex] = neg.get(negIndex++);
                negativePlaced++;
            }
            resultIndex++;
        }

        while (positivePlaced < pos.size()) {
            nums[resultIndex++] = pos.get(posIndex++);
            positivePlaced++;
        }

        while (negativePlaced < neg.size()) {
            nums[resultIndex++] = neg.get(negIndex++);
            negativePlaced++;
        }
        return nums;
    }
}
