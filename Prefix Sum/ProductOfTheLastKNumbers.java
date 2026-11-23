/**
 * 1352. Product of the Last K Numbers
 * Difficulty: Medium | Tags: Array, Math, Design, Data Stream, Prefix Sum
 * https://leetcode.com/problems/product-of-the-last-k-numbers/
 *
 * Pattern: Naive Backward Scan (Reverse Multiplication of the Last k Elements)
 *
 * Key insight: getProduct walks backward from the tail and multiplies exactly
 * the k most recently added values, so the product is computed directly from
 * the stored stream. This is a plain O(k) scan rather than the usual prefix
 * product trick; anything outside the last k slots is ignored, including zeros.
 *
 * Time Complexity: O(1) amortized per add (ArrayList append), O(k) per
 * getProduct (k backward reads and multiplications)
 *
 * Space Complexity: O(N) - Every appended value is stored, where N is the total
 * number of add calls
 *
 * Edge Cases Handled: k = 1 (last value), k equal to the stream size (whole
 * stream), zero inside the window (product 0) and outside it (excluded by the
 * backward scan), k = 0 (loop skipped, returns 1), empty stream or k larger
 * than stream size throws IndexOutOfBoundsException, products may overflow int
 */
class ProductOfTheLastKNumbers {
    List<Integer> stream;

    public ProductOfTheLastKNumbers() {
        this.stream = new ArrayList<>();
    }

    public void add(int num) {
        stream.add(num);
    }

    public int getProduct(int k) {
        int res = 1;
        int idx = stream.size() - 1;

        for (int i = 0; i < k; i++) {
            res *= stream.get(idx--);
        }

        return res;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
