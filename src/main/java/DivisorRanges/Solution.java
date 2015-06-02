package DivisorRanges;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

    int divisorRangeMemoized(int n,int [] locations,int k) {

        int multiple = 1;
        //HashSet<Integer> multiples = new HashSet<>();
        int [] solution = new int[n+1];
        int cumulativeRange = 0;
        while (true) {
            int x = multiple * k;
            if (x > n) {
                break;
            }

           // multiples.add(x);
            ++multiple;

            int index = locations[x];
            //System.out.println("Index is " + index + " for val " + multiple);
            int previousRange = 0;
            if (index > 0) {
                previousRange = solution[index-1];
            }

            if (previousRange != 0) {
                solution[index] = previousRange+1;
                cumulativeRange += previousRange+1;
                //System.out.println("Previous range is non 0 " + cumulativeRange + " previous range " + previousRange);
            }else {

                solution[index] = 1;
                cumulativeRange += 1;
                //System.out.println("Previous range is 0 " + cumulativeRange);
            }




        }



        return cumulativeRange;


    }
    int divisorRange(ArrayList<Integer> arrayList,int k) {
        ArrayList<Boolean> divisibleCheck = new ArrayList<>();
        for (int i = 0;i<arrayList.size();++i) {
            int a = arrayList.get(i);
            if ((a % k) == 0) {
                divisibleCheck.add(true);
            }else {
                divisibleCheck.add(false);
            }
        }
        int totalSeries = 0;
        for (int i = 0;i<arrayList.size();++i) {

            int seriesStartingFromX = 0;
            if (divisibleCheck.get(i)) {
                ++seriesStartingFromX;
                for (int j = i+1;j<arrayList.size();++j) {

                    if (divisibleCheck.get(j)) {
                        ++seriesStartingFromX;
                    }
                }
            }

            totalSeries += seriesStartingFromX;

        }

        return totalSeries;
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(4);
//        arrayList.add(3);
//        arrayList.add(5);
//
//        System.out.println(solution.divisorRange(arrayList,2));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);

        line = br.readLine();
        String [] arr = line.split(" ");

        int [] placeHolder = new int[N+1];

        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0;i<arr.length;++i) {
            int x = Integer.parseInt(arr[i]);

            placeHolder[x] = i;
        }



        line = br.readLine();

        for (int i = 0;i<Integer.parseInt(line);++i) {
            int query = Integer.parseInt(br.readLine());
            System.out.println(solution.divisorRangeMemoized(N, placeHolder,query));
        }


    }
}
