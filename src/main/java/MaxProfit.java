import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by rasrivastava on 3/7/15.
 */
public class MaxProfit {


    static int maxProfitInternal(int cost_per_cut, int metal_price, int[] lengths,int index,int rodSize,HashMap<Integer,Integer> hashMap) {
        //System.out.println(index);
        if (index == lengths.length) {
            //Reached end
            return 0;
        }

        if (hashMap.containsKey(index)) {
            //System.out.println("Cache hit");
            return hashMap.get(index);
        }

        int currentRodLength = lengths[index];
        if (rodSize > currentRodLength) {
            //Skip current
            int res =  maxProfitInternal(cost_per_cut,metal_price,lengths,index+1,rodSize,hashMap);
            hashMap.put(index,res);
            return res;
        }

        if (rodSize == currentRodLength) {
            //Maximize it
            int res =  (metal_price * rodSize) + maxProfitInternal(cost_per_cut,metal_price,lengths,index+1,rodSize,hashMap);
            hashMap.put(index,res);
            return res;
        }



        int pendingProfit = maxProfitInternal(cost_per_cut,metal_price,lengths,index+1,rodSize,hashMap);


        //How many pieces we want to cut from here
        int maxPieces = currentRodLength / rodSize;
        int pendingSize = currentRodLength % rodSize;
        if (pendingSize != 0) {
            //We need to
            int maxOutput = 0;
            for (int i = 0; i <= maxPieces; ++i) {
                int sampleProfit = metal_price * rodSize * i - (i * cost_per_cut);
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
            x =  (metal_price * rodSize  * units)  - cuts*cost_per_cut;

            if (x > 0) {
                int res =  x + pendingProfit;
                hashMap.put(index,res);
                return res;
            }else {
                hashMap.put(index,pendingProfit);
                return pendingProfit;
            }

//            for (int i = 0; i < maxPieces; ++i) {
//                int sampleProfit = metal_price * rodSize * i - (i * cost_per_cut);
//            }

        }

    }

    static int maxProfit(int cost_per_cut, int metal_price, int[] lengths) {
        Arrays.sort(lengths);
        int maxProfit = 0;

        for (int currentRodSize = 1;currentRodSize<=lengths[lengths.length-1];++currentRodSize) {
            HashMap<Integer,Integer> hashMap = new HashMap<>();
            int currentProfit  = maxProfitInternal(cost_per_cut,metal_price,lengths,0,currentRodSize,hashMap);
            //System.out.println("Max profit is " + currentProfit + "rodsize " + currentRodSize);
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }

        return maxProfit;
    }
    public static void main(String[] args) throws IOException {

        MaxProfit m = new MaxProfit();
        //System.out.println(primacity.checkPrimacityOfNumbers(1000000, 1000000, 1));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String line = br.readLine();
        int [] lengths = new int[]{26,103,59};
        System.out.println(m.maxProfit(100,10,lengths));

    }
}
