/**
 * 1344. Angle Between Hands of a Clock
 * Difficulty: Medium | Tags: Math
 * https://leetcode.com/problems/angle-between-hands-of-a-clock/
 *
 * Pattern: Clock Angular Position Formula
 *
 * Key insight: The hour hand sweeps 30° per hour, the minute hand 6° per minute
 * (a relative 5.5°/min); compute the absolute degree difference and fold it
 * into the circular range by returning min(diff, 360 - diff).
 *
 * Time Complexity: O(1) - Constant arithmetic, no iteration
 *
 * Space Complexity: O(1) - Only hour/minute angle variables
 *
 * Edge Cases Handled: hour = 12 (silently folded into the hour * 30 formula as
 * 360°, becoming 0° or a full wrap), hands exactly overlapping (angle 0), angle
 * exactly 180 (kept as 180 by the min comparison)
 */
class AngleBetweenHandsOfAClock {
    public double angleClock(int hour, int minutes) {
        hour = hour * 30;
        double minuteAngle = (double) minutes * 11 / 2;
        double angle = Math.abs(hour - minuteAngle);
        return angle < 360 - angle ? angle : 360 - angle;
    }
}
