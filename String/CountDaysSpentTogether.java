/**
 * 2409. Count Days Spent Together
 * Difficulty: Easy | Tags: Math, String
 * https://leetcode.com/problems/count-days-spent-together/
 *
 * Pattern: Date-to-Day Conversion + Interval Intersection
 *
 * Key insight: Convert each date to its absolute day-of-year using a fixed
 * month-length table, then the shared interval is [max(arrivals),
 * min(departures)]. Because both ranges are inclusive, the overlap length is
 * min(leave) - max(arrive) + 1, and a negative result means the intervals are
 * disjoint.
 *
 * Time Complexity: O(1) - At most 12 month additions per date, independent of
 * input size.
 *
 * Space Complexity: O(1) - Uses a fixed 12-element month array and a few scalar
 * variables.
 *
 * Edge Cases Handled: non-overlapping intervals (negative overlap clamped to
 * 0), single-day overlap where both arrive and leave on the same date,
 * identical intervals, adjacent intervals sharing exactly one day, dates
 * spanning multiple months, and same-month travel. Note: the fixed February
 * length of 28 only works because the problem guarantees year 2022, so it is
 * not safe for leap years.
 */
class CountDaysSpentTogether {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceArriveDay = convertDateToDays(arriveAlice);
        int aliceLeaveDay = convertDateToDays(leaveAlice);
        int bobArriveDay = convertDateToDays(arriveBob);
        int bobLeaveDay = convertDateToDays(leaveBob);

        int overlap = Math.min(aliceLeaveDay, bobLeaveDay) - Math.max(aliceArriveDay, bobArriveDay) + 1;

        if (overlap < 0) {
            return 0;
        }

        return overlap;
    }

    private int convertDateToDays(String date) {
        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, date.length()));

        int totalDays = 0;
        for (int i = 0; i < month - 1; i++) {
            totalDays += days[i];
        }

        return totalDays + day;
    }
}
