package StringSummaization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Solution {
    void handleLine(String line) {
        if (line.isEmpty()) {
            return;
        }
        String arr[] = line.split(",");
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayListOrig = new ArrayList<>();
        HashMap<Integer,Integer> location = new HashMap<>();
        for (int i  = 0;i<arr.length;++i) {
            int x = Integer.parseInt(arr[i]);
            arrayList.add(x);
            location.put(arrayList.get(i),i);
            arrayListOrig.add(x);
        }

        Collections.sort(arrayList);

        for (int i = 0;i<arrayListOrig.size();++i) {
            int x = arrayListOrig.get(i);
            int indexInNewList = Collections.binarySearch(arrayList,x);
            boolean found = false;
            int nextBigValFinal = 0;
            int nextBigValFinalIndex = arrayListOrig.size()-1;

            ArrayList<Integer> updatedLocation = new ArrayList<>();
            for (int j = indexInNewList+1;j<arrayList.size();++j) {
                int nextBigVal = arrayList.get(j);
                int nexBigValIndex = location.get(nextBigVal);
                if (nexBigValIndex > i) {
                    found = true;
                    updatedLocation.add(nexBigValIndex);
                }
            }

            if (found == false) {
                System.out.print(-1);
            }else {
                Collections.sort(updatedLocation);
                System.out.print(arrayList.get(updatedLocation.get(0)));
            }
            if (i != arrayListOrig.size()-1) {
                System.out.print(",");
            }


        }

        System.out.println();


    }

    public static void main(String args[] ) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Solution solution = new Solution();
        solution.handleLine(line);

    }
}