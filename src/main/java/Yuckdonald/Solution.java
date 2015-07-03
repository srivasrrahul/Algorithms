package Yuckdonald;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Result {
    private int maxProfit;
    private LinkedList<Integer> indexes;

    public Result(int maxProfit) {
        this.maxProfit = maxProfit;
    }

    public Result(int maxProfit, LinkedList<Integer> indexes) {
        this.maxProfit = maxProfit;
        this.indexes = indexes;
    }

    public int getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(int maxProfit) {
        this.maxProfit = maxProfit;
    }

    public LinkedList<Integer> getIndexes() {
        return indexes;
    }

    public void setIndexes(LinkedList<Integer> indexes) {
        this.indexes = indexes;
    }
}
public class Solution {
    private static HashMap<Integer,Result> data = new HashMap<>();
    static public Result maxProfit(int distance[],int profit[],int i,int minDistance) {
        if (i == 0) {
            LinkedList<Integer> lst = new LinkedList<>();
            lst.add(i);
            Result res = new Result(profit[0],lst);
            data.put(i,res);
            return res;
        }

        //System.out.println(i);

        Result oldResult = maxProfit(distance,profit,i-1,minDistance);

        int lastRestaurantIndex = oldResult.getIndexes().getLast();
        int distanceBetweenCurrentAndLast = distance[i] - distance[lastRestaurantIndex];

        if (distanceBetweenCurrentAndLast >= minDistance) {
            //Just add old
            oldResult.getIndexes().add(i);
            oldResult.setMaxProfit(oldResult.getMaxProfit()+profit[i]);
            data.put(i,oldResult);
            return oldResult;
        }

        //If distance is less
        //Two choices
        //Remove last and add current
        //take last only
        //then its either addition of existing one to current by removing elements
        Result previousProfitAssumingCurrent = null;
        for (int j = i-1;j>=0;--j) {
            if (distance[i] - distance[j] >=minDistance) {
                previousProfitAssumingCurrent =  maxProfit(distance,profit,j,minDistance);
                break;
            }else {
                //Skip j
            }
        }


        previousProfitAssumingCurrent.getIndexes().add(i);
        previousProfitAssumingCurrent.setMaxProfit(previousProfitAssumingCurrent.getMaxProfit()+ profit[i]);

        data.put(i,previousProfitAssumingCurrent);
        return previousProfitAssumingCurrent;

    }

    public static void main(String[] args) {

        int profit[] = new int[]{1,1,20,21,10};
        int distance[] = new int[]{0,5,15,17,19};
        maxProfit(distance,profit,4,5).getMaxProfit();
        for (Map.Entry<Integer,Result> entry : data.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getMaxProfit());
            System.out.println("=====");
            for (Integer index : entry.getValue().getIndexes()) {
                System.out.println(index);
            }

            System.out.println("=====");
        }
    }
}
