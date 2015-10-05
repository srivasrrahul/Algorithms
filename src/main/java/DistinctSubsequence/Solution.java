package DistinctSubsequence;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by rasrivastava on 4/28/15.
 */
public class Solution {
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {


            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    int distinctSubsequence(String x1,String x2) {
        if (x2.length() == 0 || x1.length() == 0) {
            return 0;
        }

        int d1 = 0;
        if (x1.charAt(0) == x2.charAt(0)) {
            d1 =  distinctSubsequence(x1.substring(1),x2.substring(1)) + 1;
        }

        int d2 =  distinctSubsequence(x1.substring(1),x2);

        int d3 =  distinctSubsequence(x1,x2.substring(1));

        return d1+d2;

    }

    public static void main(String args[]) {

        Solution m = new Solution();
        System.out.println("rabbit".substring(1));
        System.out.println(m.distinctSubsequence("rabbbit", "rabbit"));
    }
}
