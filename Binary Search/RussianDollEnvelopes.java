/**
 * 354. Russian Doll Envelopes
 * Difficulty: Hard | Tags: Array, Binary Search, Dynamic Programming, Sorting,
 * Longest Increasing Subsequence
 * https://leetcode.com/problems/russian-doll-envelopes/
 *
 * Pattern: Sorting + Binary Search (Patience Sorting LIS)
 *
 * Key insight: Sort by width ascending and, on equal widths, by height
 * descending so envelopes of the same width can never chain; the answer is then
 * the longest strictly increasing subsequence of heights, maintained in O(log
 * N) per element via binary search over a tails array.
 *
 * Time Complexity: O(N log N) - Sorting takes O(N log N) and each of the N
 * heights is placed by binary search into the tails array.
 *
 * Space Complexity: O(N) - The tails array holds up to one candidate per
 * envelope.
 *
 * Edge Cases Handled: empty input (returns 0), single envelope (returns 1),
 * duplicate widths (descending-height tie-break prevents nesting), equal
 * heights (strictly increasing check rejects them), all-same width or all-same
 * height (returns 1); assumes positive dimensions per constraints.
 */
class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }

            return a[0] - b[0];
        });

        int[] tails = new int[envelopes.length];
        int size = 0;

        for (int[] envelope : envelopes) {
            int height = envelope[1];

            int in = findInsertionIndex(tails, 0, size, height);

            if (in == size) {
                tails[size] = height;
                size++;
            } else {
                tails[in] = height;
            }

        }

        return size;
    }

    private int findInsertionIndex(int[] tails, int left, int right, int target) {

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (tails[mid] == target) {
                return mid;
            } else if (tails[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
