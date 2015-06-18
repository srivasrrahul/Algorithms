package Unsorted;


import java.util.ArrayList;

public class Solution {
    public int firstMissingPositive(ArrayList<Integer> A) {
        int min = 1;
        int max = A.size();
        for (int i = 0;i<A.size();++i) {
            if (A.get(i) == i+1) {
                //Easy case
                continue;
            }

            if (A.get(i) <min && A.get(i) > max) {
                //bad case
                continue;
            }

            if (A.get(i) >= min && A.get(i) <= max) {
                int val = A.get(i);
                int y = A.get(val);
                
            }
        }

        return 0;
    }
}
