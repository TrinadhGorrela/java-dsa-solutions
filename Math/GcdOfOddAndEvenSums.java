/**
 * 3658. GCD of Odd and Even Sums
 * Difficulty: Easy | Tags: Math, Number Theory
 * https://leetcode.com/problems/gcd-of-odd-and-even-sums/
 *
 * Pattern: Closed-Form Series Sums + Euclidean GCD
 *
 * Key insight: The sum of even numbers up to n equals n*(n+1) and the sum of
 * odd numbers equals n*n (given 1-indexed n as used in this problem); the GCD
 * of these two closed-form results is computed via repeated Euclidean division.
 *
 * Time Complexity: O(log(min(a, b))) - Euclidean algorithm on the two sums
 *
 * Space Complexity: O(1) - Only sumEven, sumOdd, and the loop variables in gcd
 *
 * Edge Cases Handled: n = 1 (sums 2 and 1 are coprime, GCD 1), n = 0 (both sums
 * 0), the Euclidean loop correctly terminating when the second argument hits 0
 */
class GcdOfOddAndEvenSums {
    public int gcdOfOddEvenSums(int n) {
        int sumEven = n * (n + 1);
        int sumOdd = n * n;
        return gcd(sumEven, sumOdd);
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
