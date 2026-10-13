/**
 * 700148. Insertion Sort
 * Difficulty: Easy | Tags: Sorting
 * https://www.geeksforgeeks.org/problems/insertion-sort/1
 *
 * Pattern: Sorting (Insertion Sort)
 *
 * Key insight: Grows a sorted prefix by inserting each new element into its
 * correct place, shifting larger elements one step right; because the prefix is
 * already sorted, the inner loop can stop at the first element that is not
 * greater, so nearly sorted input costs almost nothing.
 *
 * Time Complexity: O(N^2) - Worst case (reverse-sorted) shifts O(N) elements
 * per insertion; O(N) best case when the input is already sorted.
 *
 * Space Complexity: O(1) - In-place shifting using a single temporary for the
 * current element.
 *
 * Edge Cases Handled: empty array and single element (loop body is skipped),
 * already-sorted input (inner loop breaks immediately), reverse-sorted worst
 * case, duplicate values (stable order preserved), negative numbers; null input
 * is not guarded and throws a NullPointerException.
 */
class InsertionSort {
    public void insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > curr) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = curr;
        }
    }
}
