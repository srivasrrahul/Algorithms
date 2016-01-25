package LargestSumInArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    HashMap<Integer,Integer> getMaxValue(ArrayList<Integer> arrayList) {
        HashMap<Integer,Integer> maxValueIncludingIndexes = new HashMap<>();
        maxValueIncludingIndexes.put(0,arrayList.get(0));
        for (int i = 1;i<arrayList.size();++i) {
            int current = arrayList.get(i);
            int prev = maxValueIncludingIndexes.get(i-1);
            maxValueIncludingIndexes.put(i,Math.max(current+prev,current));
        }

        return maxValueIncludingIndexes;
    }
    int largestSum(ArrayList<Integer> arrayList,int n) {

        if (n == 0) {
            return arrayList.get(0);
        }

        int lastSumWithoutMe  = largestSum(arrayList,n-1);
        int sumIncludingMe = arrayList.get(n);
        int maxSumEncountered = arrayList.get(n);
        int pendingSum  = 0;
        for (int i = n-1 ;i>=0;--i) {
            sumIncludingMe += arrayList.get(i);
            if (sumIncludingMe > maxSumEncountered) {
                maxSumEncountered = sumIncludingMe + pendingSum;
                pendingSum = 0;
            }else {
                pendingSum += arrayList.get(i);
            }
        }

        return Math.max(lastSumWithoutMe,maxSumEncountered);
    }
    int largestSumDp(ArrayList<Integer> arrayList,HashMap<Integer,Integer> maxValueCache,int n) {

        if (n == 0) {
            return arrayList.get(0);
        }

        int lastSumWithoutMe  = largestSumDp(arrayList, maxValueCache,n - 1);
        int sumIncludingMe = maxValueCache.get(n);


        return Math.max(lastSumWithoutMe,sumIncludingMe);
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                String strArr[] = line.split(",");
                for (String x : strArr) {
                    arrayList.add(Integer.parseInt(x));
                }

                HashMap<Integer,Integer> cacheVal = getMaxValue(arrayList);
                System.out.println(largestSumDp(arrayList, cacheVal,arrayList.size()-1));

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(args[0]);
    }
}
