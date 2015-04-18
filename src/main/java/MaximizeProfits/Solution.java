package MaximizeProfits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    static int maxProfitInternalForSage(int costPerCut, int metalPrice, int[] lengths,int index,int rodSize,HashMap<Integer,Integer> hashMap) {

        if (index == lengths.length) {
            //Reached end
            return 0;
        }

        if (hashMap.containsKey(index)) {
            return hashMap.get(index);
        }

        int currentRodLength = lengths[index];
        if (rodSize > currentRodLength) {
            int res =  maxProfitInternalForSage(costPerCut, metalPrice, lengths, index + 1, rodSize, hashMap);
            hashMap.put(index,res);
            return res;
        }

        if (rodSize == currentRodLength) {
            //Maximize it
            int res =  (metalPrice * rodSize) + maxProfitInternalForSage(costPerCut, metalPrice, lengths, index + 1, rodSize, hashMap);
            hashMap.put(index,res);
            return res;
        }



        int pendingProfit = maxProfitInternalForSage(costPerCut, metalPrice, lengths, index + 1, rodSize, hashMap);


        //How many pieces we want to cut from here
        int maxPieces = currentRodLength / rodSize;
        int pendingSize = currentRodLength % rodSize;
        if (pendingSize != 0) {
            //We need to
            int maxOutput = 0;
            for (int i = 0; i <= maxPieces; ++i) {
                int sampleProfit = metalPrice * rodSize * i - (i * costPerCut);
                if (sampleProfit > maxOutput) {
                    maxOutput = sampleProfit;
                }
            }

            hashMap.put(index, maxOutput + pendingProfit);
            return maxOutput + pendingProfit;
        }else {
            //One cut gives two
            int x = 0;
            int units = (currentRodLength / rodSize);
            int cuts = units - 1;
            x =  (metalPrice * rodSize  * units)  - cuts*costPerCut;

            if (x > 0) {
                int res =  x + pendingProfit;
                hashMap.put(index,res);
                return res;
            }else {
                hashMap.put(index,pendingProfit);
                return pendingProfit;
            }

        }

    }

    static int maxProfitForSage(int costPerCut, int metalPrice, int[] lengths) {
        Arrays.sort(lengths);
        int maxProfit = 0;

        for (int currentRodSize = 1;currentRodSize<=lengths[lengths.length-1];++currentRodSize) {
            HashMap<Integer,Integer> hashMap = new HashMap<>();
            int currentProfit  = maxProfitInternalForSage(costPerCut, metalPrice, lengths, 0, currentRodSize, hashMap);
            //System.out.println("Max profit is " + currentProfit + "rodsize " + currentRodSize);
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }

        return maxProfit;
    }
    public static void main(String[] args) throws IOException {

        Solution m = new Solution();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int cutCost = Integer.parseInt(line);

        int unitCost = Integer.parseInt(br.readLine());

        int lengthArraySize = Integer.parseInt(br.readLine());

        int [] lengths = new int[lengthArraySize];
        for (int i = 0;i<lengthArraySize;++i) {
            lengths[i] = Integer.parseInt(br.readLine());
        }
        //int [] lengths = new int[]{26,103,59};
        //System.out.println(m.maxProfitForSage(100, 10, lengths));
        System.out.println(m.maxProfitForSage(cutCost, unitCost, lengths));

    }
}
