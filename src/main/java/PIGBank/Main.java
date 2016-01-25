package PIGBank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Rahul on 10/10/15.
 */

class Tuple {
    private int x;
    private int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (x != tuple.x) return false;
        return y == tuple.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
public class Main {

    private HashMap<Tuple,Integer> cache = new HashMap<>();
    int minMoney(TreeMap<Integer,Integer> moneyAgainstWeight,int emptyWeight,int maxWeight,int currentWeight,int currentValue) {

        if (currentWeight >= maxWeight) {
            //Can't add any more weight
            return currentValue;
        }

        Tuple currentTuple = new Tuple(currentWeight,currentValue);
        if (cache.containsKey(currentTuple)) {
            return cache.get(currentTuple);
        }

        int minMoneyRetValue = Integer.MAX_VALUE;
        for (Integer amount : moneyAgainstWeight.keySet()) {
            int weight = moneyAgainstWeight.get(amount);
            if (currentWeight + weight <= maxWeight) {
                int tempRetValue  = minMoney(moneyAgainstWeight,emptyWeight,maxWeight,currentWeight+weight,currentValue + amount);
                if (tempRetValue < minMoneyRetValue) {
                    minMoneyRetValue = tempRetValue;
                }
            }else {
                break;
            }
        }
        cache.put(currentTuple,minMoneyRetValue);
        return minMoneyRetValue;
    }

    public static void main(String[] args) throws IOException {
//        TreeMap<Integer,Integer> moneyAgainstWeight = new TreeMap<>();
//        moneyAgainstWeight.put(10,3);
//        moneyAgainstWeight.put(20,4);
//        minMoney(moneyAgainstWeight,1,6,1,0);


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String lineStr = bufferedReader.readLine();
        int val = Integer.parseInt(lineStr);
        for (int i = 0;i<val;++i) {

            String minMax = bufferedReader.readLine();
            String[] minMaxArr = minMax.split(" ");
            int minWeight = Integer.parseInt(minMaxArr[0]);
            int maxWeight = Integer.parseInt(minMaxArr[1]);

            TreeMap<Integer, Integer> valWeights = new TreeMap<>();
            String v = bufferedReader.readLine();
            int count = Integer.parseInt(v);

            for (int j = 0; j < count; ++j) {
                String valWeight = bufferedReader.readLine();
                String[] valWeightArr = valWeight.split(" ");
                valWeights.put(Integer.parseInt(valWeightArr[0]), Integer.parseInt(valWeightArr[1]));

            }

            Main main = new Main();
            int result = main.minMoney(valWeights, minWeight, maxWeight, minWeight, 0);
            if (result == Integer.MAX_VALUE) {
                System.out.println("This is impossible.");
            } else {
                System.out.println("The minimum amount of money in the piggy-bank is " + result + ".");
            }


        }




    }

}
