package WeightIndependentSets;

import java.util.ArrayList;

/**
 * Created by rasrivastava on 1/7/16.
 */
public class Solution {
    int maxWeight(ArrayList<Integer> arrayList,int n) {
        if (n == 3) {
            int w1 = arrayList.get(0) + arrayList.get(2);
            System.out.println("Inside 3 " + w1 );
            return w1;
        }

        int subWeight = maxWeight(arrayList,n-1);
        int w1 = arrayList.get(n) + arrayList.get(n-2);
        System.out.println("Max Weight for " + n  + " " + w1);
        return Math.max(w1,subWeight);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(4);
        arrayList.add(3);
        arrayList.add(5);
        System.out.println(solution.maxWeight(arrayList,arrayList.size()-1));
    }
}
