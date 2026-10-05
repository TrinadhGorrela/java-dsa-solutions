/**
 * 1386. Cinema Seat Allocation
 * Difficulty: Medium | Tags: Array, Hash Table, Greedy, Bit Manipulation
 * https://leetcode.com/problems/cinema-seat-allocation/
 *
 * Pattern: Hash Map Row-Sparse Greedy
 *
 * Key insight: Rows without reservations always accommodate 2 families; for
 * reserved rows, the three candidate blocks (seats 2-5, 4-7, 6-9) are checked
 * with booleans — left and right are independent, but middle is only viable
 * when both left and right are blocked.
 *
 * Time Complexity: O(N) - One pass over reservedSeats to build the map, one
 * pass over reserved rows; unreserved rows contribute a single multiply
 *
 * Space Complexity: O(R) - HashMap stores one boolean[11] per reserved row (R =
 * number of distinct reserved rows)
 *
 * Edge Cases Handled: no reservations at all (returns 2n), single seat reserved
 * in a row, middle block overlapping both left and right reservations, n = 1
 */
class CinemaSeatAllocation {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, boolean[]> map = new HashMap<>();
        for (int[] row : reservedSeats) {
            int r = row[0];
            int col = row[1];
            map.putIfAbsent(row[0], new boolean[11]);
            boolean[] rowSeats = map.get(r);
            rowSeats[col] = true;
        }

        int res = 0;
        for (int i : map.keySet()) {
            boolean[] rowSeats = map.get(i);

            boolean left = !rowSeats[2] && !rowSeats[3] && !rowSeats[4] && !rowSeats[5];
            boolean right = !rowSeats[6] && !rowSeats[7] && !rowSeats[8] && !rowSeats[9];
            boolean middle = !rowSeats[4] && !rowSeats[5] && !rowSeats[6] && !rowSeats[7];

            if (left) {
                res++;
            }

            if (right) {
                res++;
            }

            if (middle && !left && !right) {
                res++;
            }
        }

        if (map.size() < n) {
            int rem = n - map.size();
            res += rem * 2;
        }

        return res;
    }
}
