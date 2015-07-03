package LongestContiguosSubsequence;

import java.util.Arrays;

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
    static void scan(int a[],int maxSumEndingAtCurrent[],
                      int i) {


        if (i == a.length) {
            return;
        }

        if (a[i] + maxSumEndingAtCurrent[i-1] > a[i]) {
            maxSumEndingAtCurrent[i] = a[i] + maxSumEndingAtCurrent[i-1];
        }else {
            maxSumEndingAtCurrent[i] = a[i];
        }

        scan(a,maxSumEndingAtCurrent,i+1);


    }

    public static void main(String[] args) {
        int a[] = new int[] {5,15,-20,10,-5,40,10,-20,21};
        int maxSumAtCurrent[] = new int[a.length];
        maxSumAtCurrent[0] = a[0];
        scan(a,maxSumAtCurrent,1);
        System.out.println(Arrays.toString(maxSumAtCurrent));


    }
}
