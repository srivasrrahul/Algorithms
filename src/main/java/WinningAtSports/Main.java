package WinningAtSports;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;

class CacheKey {
    private int x;
    private int y;

    public CacheKey(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheKey cacheKey = (CacheKey) o;

        if (x != cacheKey.x) return false;
        if (y != cacheKey.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
public class Main {
    BigInteger stressFree(int x,int y,int a,int b,HashMap<CacheKey,BigInteger> cache) {
        if (x == a && y == b) {
            return BigInteger.ONE;
        }

        if (x > a || y > b) {
            return BigInteger.ZERO;
        }

        if (x <= y) {
            //Its not stressfree
            return BigInteger.ZERO;
        }


        CacheKey cacheKey = new CacheKey(x,y);
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }
        BigInteger left = stressFree(x+1,y,a,b,cache);
        BigInteger right = stressFree(x,y+1,a,b,cache);

        BigInteger result = left.add(right);
        cache.put(cacheKey,result);
        return left.add(right);
    }

    BigInteger stressFul(int x,int y,int a,int b,HashMap<CacheKey,BigInteger> cache) {
        if (x == a && y == b) {
            return BigInteger.ONE;
        }

        if (x > a || y > b) {
            return BigInteger.ZERO;
        }

        if (x > y && y < b) {
            //Its not stressful
            //person has won more points if opponent has not reached its maximum
            return BigInteger.ZERO;
        }

        CacheKey cacheKey = new CacheKey(x,y);
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }


        BigInteger left = stressFul(x + 1, y, a, b, cache);
        BigInteger right = stressFul(x,y+1,a,b,cache);

        BigInteger result = left.add(right);
        cache.put(cacheKey,result);
        return left.add(right);

    }




    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int testCases = Integer.parseInt(line);
        BigInteger modValue = BigInteger.valueOf(1000000007);
        for (int i = 0;i<testCases;++i) {
            line = br.readLine();
            String [] data = line.split("-");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);
            //System.out.println(a + " " + b);
            HashMap<CacheKey,BigInteger> cacheKeyBigIntegerHashMap = new HashMap<>();
            HashMap<CacheKey,BigInteger> cacheKeyBigIntegerHashMap1 = new HashMap<>();
            System.out.println("Case #" + (i+1) + ": " + main.stressFree(1,0,a,b,cacheKeyBigIntegerHashMap).mod(modValue) + " " +
                    main.stressFul(0, 0, a, b, cacheKeyBigIntegerHashMap1).mod(modValue));
        }
    }

//    public static void main  (String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String line = br.readLine();
//        long x = Integer.parseInt(line);
//        long y= Integer.parseInt(br.readLine());
//        System.out.println(x+y);
//
//    }


}
