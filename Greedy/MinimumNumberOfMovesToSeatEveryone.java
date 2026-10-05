/**
 * 2037. Minimum Number of Moves to Seat Everyone
 * Difficulty: Easy | Tags: Array, Greedy, Sorting, Counting Sort
 * https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/
 *
 * Pattern: Sort-and-Pair Optimal Matching
 *
 * Key insight: By the rearrangement inequality, pairing the i-th smallest
 * student with the i-th smallest seat minimizes the sum of absolute
 * displacements — sorting both arrays and taking elementwise differences is
 * both necessary and sufficient.
 *
 * Time Complexity: O(N log N) - Two sorts dominate; the difference scan is O(N)
 *
 * Space Complexity: O(1) - In-place sorts and scalar accumulator
 *
 * Edge Cases Handled: all students already in their seats (total moves = 0),
 * single student, identical seat positions, students and seats in completely
 * reversed order
 */
class MinimumNumberOfMovesToSeatEveryone {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);

        int totalMoves = 0;
        for (int i = 0; i < seats.length; i++) {
            totalMoves += Math.abs(seats[i] - students[i]);
        }

        return totalMoves;
    }
}
