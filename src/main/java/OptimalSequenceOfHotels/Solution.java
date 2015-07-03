package OptimalSequenceOfHotels;


import java.util.HashMap;

public class Solution {
    static int distance(int [] hotels,int i,int j) {
        int x  = hotels[i]-hotels[j];
        return (200-x)*(200-x);
    }

    static int findOptimalSequence(int hotels[],int i) {

        System.out.println(i);
        if (i == 0) {
            return hotels[0];
        }

        int minCost = Integer.MAX_VALUE;
        for (int j = i-1;j>=0;--j) {
            int cost = findOptimalSequence(hotels,j) + distance(hotels,i,j);
            if (cost < minCost) {
                minCost = cost;
            }
        }

        return minCost;
    }

    static HashMap<Integer,Integer> values = new HashMap<>();

    static int findOptimalSequenceMemoized(int hotels[],int i) {

        if (i == 0) {
            return hotels[0];
        }


        if (values.containsKey(i)) {
            return values.get(i);
        }

        System.out.println(i);
        int minCost = Integer.MAX_VALUE;
        for (int j = i-1;j>=0;--j) {
            int cost = findOptimalSequenceMemoized(hotels, j) + distance(hotels,i,j);
            if (cost < minCost) {
                minCost = cost;
            }
        }

        values.put(i,minCost);
        return minCost;
    }

    public static void main(String[] args) {
        int hotels[] = {0,200,300,400,600};
        System.out.println(findOptimalSequenceMemoized(hotels, 4));
    }
}
