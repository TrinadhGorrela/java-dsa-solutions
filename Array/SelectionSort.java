/**
 * 700147. Selection Sort
 * Difficulty: Easy | Tags: Sorting
 * https://www.geeksforgeeks.org/problems/selection-sort/1
 *
 * Pattern: Sorting (Selection Sort)
 *
 * Key insight: Repeatedly finds the minimum of the unsorted suffix and swaps it
 * into position i, so after k passes the first k positions are final; the
 * number of swaps is fixed at N-1 regardless of input order, which is useful
 * when writes are expensive.
 *
 * Time Complexity: O(N^2) - Always scans the entire remaining suffix for the
 * minimum, independent of input order.
 *
 * Space Complexity: O(1) - In-place with constant extra variables for the
 * minimum index and the swap temp.
 *
 * Edge Cases Handled: empty array and single element (loop bounds are safe),
 * already-sorted input (still O(N^2) but only self-swaps occur), all-equal or
 * duplicate elements, minimum already at index i (self-swap is harmless),
 * negative numbers; null input is not guarded and throws a
 * NullPointerException.
 */
class SelectionSort {
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int in = i;

            for (int j = i; j < arr.length; j++) {
                if (arr[in] > arr[j]) {
                    in = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[in];
            arr[in] = temp;
        }

    }
}
