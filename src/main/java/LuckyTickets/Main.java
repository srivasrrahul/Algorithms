package LuckyTickets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rahul on 4/28/15.
 */
class Pair {
    private int x;
    private int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (x != pair.x) return false;
        return y == pair.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
public class Main {
    private Map<Pair,BigInteger> cache = new HashMap<>();
    BigInteger getSumWithLength(int sum,int lengthDigits) {
        //System.out.println(sum);
        if (lengthDigits == 1) {
            if (sum <= 9 && sum >=0) {
                return BigInteger.ONE;
            }else {
                return BigInteger.ZERO;
            }
        }

        if (sum < 0) {
            //System.out.println("returning zero");
            return BigInteger.ZERO;
        }



        Pair pair = new Pair(sum,lengthDigits);
        if (cache.containsKey(pair)) {
            return cache.get(pair);
        }
        //for 0 to 9
        BigInteger v0 = getSumWithLength(sum, lengthDigits - 1);
        BigInteger v1 = getSumWithLength(sum - 1, lengthDigits - 1);
        BigInteger v2 = getSumWithLength(sum - 2, lengthDigits - 1);
        BigInteger v3 = getSumWithLength(sum - 3, lengthDigits - 1);
        BigInteger v4 = getSumWithLength(sum - 4, lengthDigits - 1);
        BigInteger v5 = getSumWithLength(sum - 5, lengthDigits - 1);
        BigInteger v6 = getSumWithLength(sum - 6, lengthDigits - 1);
        BigInteger v7 = getSumWithLength(sum - 7, lengthDigits - 1);
        BigInteger v8 = getSumWithLength(sum - 8, lengthDigits - 1);
        BigInteger v9 = getSumWithLength(sum - 9, lengthDigits - 1);
        //BigInteger returnValue =  v0+v1+v2+v3+v4+v5+v6+v7+v8+v9;
        BigInteger returnValue =  v0.add(v1).add(v2).add(v3).add(v4).add(v5).add(v6).add(v7).add(v8).add(v9);
        cache.put(pair,returnValue);
        return returnValue;


    }

    BigInteger getLucky(int n) {
        int m = n/2;
        int maxSum = 9*m;
        //System.out.println(maxSum);
        BigInteger s = BigInteger.ZERO;
        for (int i = 0;i<=maxSum;++i) {
            BigInteger x = getSumWithLength(i,m);
            x = x.multiply(x);

            s = s.add(x);
        }
        return s;
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                int x = Integer.parseInt(line);
                //System.out.println(x);
                System.out.println(getLucky(x));

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(args[0]);
        //System.out.println(m.getLucky(6));
    }
}
