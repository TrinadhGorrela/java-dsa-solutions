/**
 * 700143. Bubble Sort
 * Difficulty: Easy | Tags: Sorting
 * https://www.geeksforgeeks.org/problems/bubble-sort/1
 *
 * Pattern: Sorting (Bubble Sort with Early Exit)
 *
 * Key insight: Each pass bubbles the largest remaining element to the end of
 * the unsorted region by swapping adjacent out-of-order pairs; because a pass
 * that performs no swaps proves the array is sorted, the early-exit flag lets
 * nearly sorted input finish in a single pass.
 *
 * Time Complexity: O(N^2) - Two nested passes compare and swap each pair; drops
 * to O(N) on already-sorted input thanks to the early-exit break.
 *
 * Space Complexity: O(1) - Sorts fully in place using only constant extra
 * variables (the swap temp and the swap flag).
 *
 * Edge Cases Handled: empty array and single element (loop bounds prevent
 * out-of-range access), already-sorted input (exits after one no-swap pass),
 * all-equal or duplicate elements (no swaps triggered), negative numbers; null
 * input is not guarded and would throw a NullPointerException.
 */
class BubbleSort {
    public void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSwap = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSwap = true;
                }
            }
            if (!isSwap) {
                break;
            }
        }

    }
}
