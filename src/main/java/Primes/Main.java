package Primes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.HashSet;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }

        BigInteger number = BigInteger.valueOf(n);
        if (false == number.isProbablePrime(2)) {
            return false;
        }

        int sqrt = (int)Math.sqrt(n)+1;
        for (int i = 2;i<=sqrt;++i) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
    void updateNotPrime(HashSet<Integer> notPrimes,int x,int upperLimit) {
        for (int j=1;;++j) {
            int val = x * j;
            if (val > upperLimit) {
                break;
            }

            notPrimes.add(val);
        }
    }
    int countPrimes(int low,int high) {
        HashSet<Integer> primeEncountered = new HashSet<>();

        HashSet<Integer> notPrimes = new HashSet<>();
        for (int i = low;i<=high;++i) {
            if (notPrimes.contains(i) == false && isPrime(i) == true) {
                primeEncountered.add(i);
                updateNotPrime(notPrimes,i,high);
            }
        }

        return primeEncountered.size();
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                String x[] = line.split(",");
                int low = Integer.parseInt(x[0]);
                int high = Integer.parseInt(x[1]);
                System.out.println(countPrimes(low,high));

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
