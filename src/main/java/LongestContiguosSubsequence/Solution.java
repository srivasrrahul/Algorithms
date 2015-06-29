package LongestContiguosSubsequence;

import java.util.TreeMap;

class Result {
    private int i;
    private int j;
    private int sumOfIndexes;

    public Result(int i, int j, int sumOfIndexes) {
        this.i = i;
        this.j = j;
        this.sumOfIndexes = sumOfIndexes;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getSumOfIndexes() {
        return sumOfIndexes;
    }

    public void setSumOfIndexes(int sumOfIndexes) {
        this.sumOfIndexes = sumOfIndexes;
    }
}

//Not optimal algorithm
public class Solution {
    //HashMap should be changed to index
    static int maxSum(int a[],
                      int i,int maxLastLow,int maxLastHigh,int lastSum,TreeMap<Integer,Integer> sumFromPositiveBetweens) {

        return 0;






    }

    public static void main(String[] args) {
        int a[] = new int[] {5,15,-30,10,-5,40,10};


    }
}
