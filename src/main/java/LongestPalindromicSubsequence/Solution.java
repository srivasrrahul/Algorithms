package LongestPalindromicSubsequence;

import LCS.Cache;

/**
 * Created by rasrivastava on 4/28/15.
 */
public class Solution {

    static String longestPalindromicSubSequence(String s) {
        LCS.Main lcs = new LCS.Main();
        Cache cache = new Cache();
        return lcs.lcs(s,new StringBuilder(s).reverse().toString(),0,0,cache);
    }



    public static void main(String args[]) {
        System.out.println(longestPalindromicSubSequence("ACGTGTCAAAATCG"));
    }
}
