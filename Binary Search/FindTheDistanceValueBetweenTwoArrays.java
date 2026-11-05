/**
 * 1385. Find the Distance Value Between Two Arrays
 * Difficulty: Easy | Tags: Array, Two Pointers, Binary Search, Sorting
 * https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
 *
 * Pattern: Sorting + Binary Search
 *
 * Key insight: Sorting arr2 lets us binary search it for any value within
 * distance d of each arr1[i]. Because the acceptable range [arr1[i] - d,
 * arr1[i] + d] is contiguous in a sorted array, the narrowing search either
 * hits such a value or proves none exists.
 *
 * Time Complexity: O(M log M + N log M) - Sort arr2 of size M once, then binary
 * search it for each of the N elements of arr1.
 *
 * Space Complexity: O(1) auxiliary - arr2 is sorted in place and only scalar
 * pointers/counters are used (ignoring the sort's O(log M) recursion stack).
 *
 * Edge Cases Handled: empty arr1 (returns 0), empty arr2 (every arr1 element
 * counts because nothing is within d), single and duplicate elements, negative
 * numbers, d = 0 (handled via Math.abs); subtraction at int extremes can
 * overflow Math.abs.
 */
class FindTheDistanceValueBetweenTwoArrays {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        Arrays.sort(arr2);

        for (int i = 0; i < arr1.length; i++) {
            int left = 0;
            int right = arr2.length - 1;
            boolean isValid = true;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (Math.abs(arr2[mid] - arr1[i]) <= d) {
                    isValid = false;
                    break;
                } else if (arr2[mid] > arr1[i]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (isValid) {
                count++;
            }
        }

        return count;
    }
}
