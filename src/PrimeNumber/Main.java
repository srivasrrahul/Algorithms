package PrimeNumber;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    int lastMaxVal = 2;
    List<Integer> lst = new LinkedList<>();

    Main() {
        lst.add(2);
    }

    boolean isPrime(long n) {
        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        double limit = Math.sqrt(new Long(n).doubleValue());
        long l = new Double(limit).longValue()+1;
        for (int i = 2;i<=l;++i) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    void printLimits(long n) {
        boolean primeOccured = false;
        for (long i = 2;i<n;++i) {
            if (isPrime(i)) {
                if (primeOccured) {
                    System.out.print(",");
                }
                System.out.print(i);
                primeOccured = true;
            }
        }

        System.out.println();
    }

    void handleLine(String line) {
        Long d = Long.parseLong(line);

        printLimits(d);
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
