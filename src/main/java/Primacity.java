import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;


public class Primacity {

    HashSet<Integer> primeSet = new HashSet<>();
    LinkedList<Integer> primeLst = new LinkedList();
    HashMap<Integer,Integer> primeMultiples = new HashMap<>();
    void formPrimeMultiples(int max,int min) {
        for (Integer c : primeLst) {
            int val = c + c;
            while (val < min) {
                val = val + c;
            }
            //System.out.println("For prime " + c);
            while (val <= max && val >=min) {

                if (primeMultiples.containsKey(val)) {
                    //System.out.println("Adding val + " + val + " " + primeMultiples.get(val)+1 );
                    primeMultiples.put(val,primeMultiples.get(val)+1);
                }else {
                    //System.out.println("Adding val + " + val + " " + 1 );
                    primeMultiples.put(val,1);
                }

                val = val + c;
            }
        }
    }
    boolean isPrime(int n) {
        //System.out.println("Inside prime " + n);

        if (n == 2) {
            return true;
        }

        if ((n % 2) == 0) {
            return false;
        }

        BigInteger bigInteger = BigInteger.valueOf(n);
        if (bigInteger.isProbablePrime(8)) {
            int x = (int)Math.sqrt(n);
            for (int i = 2;i<=x;++i) {
                if ((n % i) == 0) {

                    return false;
                }
            }


            return true;
        }

        return false;
    }
    int primacity(int n) {
        //System.out.println("Inside primacity " + n);
        if (primeSet.contains(n)) {
            //System.out.println("n is prime " + n);
            return 1;
        }

        int count = 0;
        for (Integer x : primeLst) {
            if (x < n) {
                if ((n % x) == 0) {
                    ++count;
                }
            }else {
                break;
            }
        }

        return count;

    }


    int findPrimacity(int a,int b,int k) {
        //System.out.println(a + " " + b  + " " + k);
        //primeLst.clear();
        for (int i = (primeLst.size()>0)?(primeLst.getLast()+1):2;i<=b;++i) {
            boolean rc = isPrime(i);
            if (rc) {
                //System.out.println("Adding to prime list " + i);
                primeSet.add(i);
                primeLst.add(i);
            }
            //System.out.println(isPrime(i) + " " + i);
        }

        primeMultiples.clear();

        formPrimeMultiples(b,a);
        int count = 0;
        for (Integer key : primeMultiples.keySet()) {
            if (primeMultiples.get(key) == k) {
                ++count;
            }
        }



        return count;
    }


    public static void main(String[] args) throws IOException {
        Primacity primacity = new Primacity();
        //System.out.println(primacity.checkPrimacityOfNumbers(1000000, 1000000, 1));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int testCases = Integer.parseInt(line);
        for (int i = 0;i<testCases;++i) {
            line = br.readLine();
            String []data = line.split(" ");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);
            int k = Integer.parseInt(data[2]);
            System.out.println("Case #" + (i + 1) + ":" + " " + primacity.findPrimacity(a, b, k));
        }


    }


}
