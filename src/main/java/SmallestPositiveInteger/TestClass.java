package SmallestPositiveInteger;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class TestClass {

    boolean findNumber(int x,int y) {
        String stringRep = String.valueOf(x);
        long product = 1;
        for (int i = 0; i<stringRep.length();++i) {
            if (stringRep.charAt(0) == '0') {
                product = 0;
                break;
            }

            product *= (stringRep.charAt(i) - '0');
        }

        long mod = product % 1000000007;
        return (mod == y);

    }

    boolean isPrimeNumber(int x) {
        if (x == 2) {
            return true;
        }

        if ((x % 2) == 0) {
            return false;
        }

        int upperLimit = (int)Math.sqrt(x) + 1;
        for (int i = 3;i<=upperLimit;i+=2) {
            if ((x % i) == 0) {
                return false;
            }
        }

        return true;
    }

    String formNumber(int x) {

        String string = String.valueOf(x);
        for (int i = 9;i>=2;--i) {
             if ((x % i) == 0) {
                 return String.valueOf(i) + formNumber(x / i);
             }
        }

        return "";


    }

    int getSmallestNumber(int x) {
        //Its not a prime
        String string = String.valueOf(x);
        if (string.contains("0")) {
            return 0;
        }

        String num = formNumber(x);
        char [] arr = num.toCharArray();
        Arrays.sort(arr);
        String sortedValue = String.valueOf(arr);
        return Integer.parseInt(sortedValue);
    }

    public static void main(String[] args) throws IOException {
        //long t1 = System.currentTimeMillis();
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int x = Integer.parseInt(line);
        BigInteger bigInteger = BigInteger.valueOf(x);
        if (bigInteger.isProbablePrime(8)) {
            if (testClass.isPrimeNumber(x)) {
                System.out.println();
                return;
            }
        }
//        for (int i = 1;i<1000000007;++i) {
//            boolean rc  = testClass.findNumber(i,x);
//            if (rc == true) {
//                System.out.println(i);
//                break;
//            }
//        }

        System.out.println(testClass.getSmallestNumber(x));


    }
}
