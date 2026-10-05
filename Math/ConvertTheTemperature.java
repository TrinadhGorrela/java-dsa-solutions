/**
 * 2469. Convert the Temperature
 * Difficulty: Easy | Tags: Math
 * https://leetcode.com/problems/convert-the-temperature/
 *
 * Pattern: Direct Unit Conversion (Fixed Formulas)
 *
 * Key insight: Apply the two standard conversion formulas — K = C + 273.15 and
 * F = C * 1.80 + 32.00 — and package both results into a two-element array.
 *
 * Time Complexity: O(1) - Two fixed arithmetic expressions
 *
 * Space Complexity: O(1) - A constant-size [2] array allocated for the result
 *
 * Edge Cases Handled: celsius = 0 (K = 273.15, F = 32.00), negative celsius
 * (valid linear mapping), fractional celsius (double arithmetic preserves
 * precision)
 */
class ConvertTheTemperature {
    public double[] convertTemperature(double celsius) {
        double kelvin = celsius + 273.15;
        double fahrenheit = celsius * 1.80 + 32.00;

        double[] result = new double[2];
        result[0] = kelvin;
        result[1] = fahrenheit;
        return result;
    }
}
