package CountStairs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

class CacheKey {
    private int x;


    public CacheKey(int x) {
        this.x = x;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheKey cacheKey = (CacheKey) o;

        if (x != cacheKey.x) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return x;
    }
}
public class Main {
    BigInteger countStairs(int n,int currentStairNumber,HashMap<CacheKey,BigInteger> hashMap) {

        if (currentStairNumber == n+1) {
            return BigInteger.ONE;
        }

        if (currentStairNumber > n) {
            return BigInteger.ZERO;
        }

        CacheKey cacheKey = new CacheKey(currentStairNumber);

        if (hashMap.containsKey(cacheKey)) {
            return hashMap.get(cacheKey);
        }

        BigInteger count = countStairs(n,currentStairNumber+1,hashMap);
        BigInteger count1 = countStairs(n,currentStairNumber+2,hashMap);

        //System.out.println("Counters are " + count+count1);
        hashMap.put(cacheKey, count.add(count1));
        return count.add(count1);
    }

    void handleLine(String line) {

        //System.out.println("Line is " + line);
        HashMap<CacheKey,BigInteger> hashMap = new HashMap<>();
        System.out.println(countStairs(Integer.parseInt(line),1,hashMap));
        //System.out.println(line);
    }
    void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                handleLine(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.readFile(args[0]);
    }
}
