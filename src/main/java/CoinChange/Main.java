package CoinChange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    static HashMap<BigDecimal,String> coinValStr = new HashMap<>();
    static {

//        'PENNY': .01,
//                'NICKEL': .05,
//                'DIME': .10,
//                'QUARTER': .25,
//                'HALF DOLLAR': .50,
//                'ONE': 1.00,
//                'TWO': 2.00,
//                'FIVE': 5.00,
//                'TEN': 10.00,
//                'TWENTY': 20.00,
//                'FIFTY': 50.00,
//                'ONE HUNDRED': 100.00
        coinValStr.put(new BigDecimal(0.01).setScale(2,BigDecimal.ROUND_HALF_EVEN),"PENNY");
        coinValStr.put(new BigDecimal(0.05).setScale(2,BigDecimal.ROUND_HALF_EVEN),"NICKEL");
        coinValStr.put(new BigDecimal(0.10).setScale(2,BigDecimal.ROUND_HALF_EVEN),"DIME");
        coinValStr.put(new BigDecimal(0.25).setScale(2,BigDecimal.ROUND_HALF_EVEN),"QUARTER");
        coinValStr.put(new BigDecimal(0.50).setScale(2,BigDecimal.ROUND_HALF_EVEN),"HALF DOLLAR");
        coinValStr.put(new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_HALF_EVEN),"ONE");
        coinValStr.put(new BigDecimal(2.00).setScale(2,BigDecimal.ROUND_HALF_EVEN),"TWO");
        coinValStr.put(new BigDecimal(5.00).setScale(2,BigDecimal.ROUND_HALF_EVEN),"FIVE");
        coinValStr.put(new BigDecimal(10.00).setScale(2,BigDecimal.ROUND_HALF_EVEN),"TEN");
        coinValStr.put(new BigDecimal(20.00).setScale(2,BigDecimal.ROUND_HALF_EVEN),"TWENTY");
        coinValStr.put(new BigDecimal(50.00).setScale(2,BigDecimal.ROUND_HALF_EVEN),"FIFTY");
        coinValStr.put(new BigDecimal(100.00).setScale(2,BigDecimal.ROUND_HALF_EVEN),"ONE HUNDRED");


    }

    ArrayList<BigDecimal> coinChange(BigDecimal changeAmount,HashMap<BigDecimal,ArrayList<BigDecimal>> cache) {

        changeAmount = changeAmount.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        //System.out.println(changeAmount);
        if (changeAmount.signum() == 0) {
            return new ArrayList<>();
        }

        if (coinValStr.containsKey(changeAmount)) {
            ArrayList<BigDecimal> arrayLst = new ArrayList<>();
            arrayLst.add(changeAmount);
            return arrayLst;

        }

        if (cache.containsKey(changeAmount)) {
            return cache.get(changeAmount);
        }

        ArrayList<BigDecimal> leastAmount = null;
        int maxSize  = Integer.MAX_VALUE;
        BigDecimal amountSelected = null;
        for (BigDecimal d : coinValStr.keySet()) {
            if (changeAmount.compareTo(d) >=0 ) {
                ArrayList<BigDecimal> change = coinChange(changeAmount.subtract(d),cache);
                //System.out.println("Change size " + change.size());
                if (change.size() < maxSize) {
                    maxSize = change.size();
                    leastAmount = (ArrayList<BigDecimal>) change.clone();
                    amountSelected = d;

                }
            }
        }

        leastAmount.add(amountSelected);
        cache.put(changeAmount,leastAmount);
        return leastAmount;
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                String x [] = line.split(";");
                //System.out.println(line);
                Double d1 = Double.parseDouble(x[0]);
                Double d2 = Double.parseDouble(x[1]);


                BigDecimal b1 = new BigDecimal(d1);
                BigDecimal b2 = new BigDecimal(d2);

                BigDecimal diff = b2.subtract(b1);
                diff = diff.setScale(2,BigDecimal.ROUND_HALF_EVEN);

                //System.out.println("Diff " + diff.doubleValue());
                if (diff.compareTo(BigDecimal.ZERO) == 0) {
                    System.out.println("ZERO");
                    continue;
                }

                if (diff.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("ERROR");
                    continue;
                }

                HashMap<BigDecimal,ArrayList<BigDecimal>> cache = new HashMap<>();
                ArrayList<BigDecimal> change = coinChange(diff,cache);
                Collections.sort(change, Collections.reverseOrder());
                int i = 0;
                for (BigDecimal d : change) {

                    System.out.print(coinValStr.get(d) + (i==change.size()-1?"":","));
                    ++i;
                }

                System.out.println();



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
