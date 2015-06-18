package TrailingZeroes;

/**
 * Created by rasrivastava on 4/28/15.
 */
public class Solution {

    public int trailingZeroes(int A) {
        int zeros = 0;
        int fivePow = 5;
        int res = A / fivePow;
        while (res != 0) {
            zeros += (A/fivePow);
            fivePow = fivePow*5;
            res = A /fivePow;
        }

        return zeros;
    }

    public static void main(String args[]) {

        Solution m = new Solution();
        System.out.println(m.trailingZeroes(9));
    }
}
