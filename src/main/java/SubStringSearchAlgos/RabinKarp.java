package SubStringSearchAlgos;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by rasrivastava on 7/3/15.
 */
public class RabinKarp {
    //Assuming ascii strings of lower case chars
    private long largePrime;
    private long storedHash;
    long evalHornersRule(String pattern,int x,int y) {
        long val = 0;
        for (int i = x;i<=y;++i) {
            val = val*26 + (pattern.charAt(i) - 'a');
            val = val % largePrime;
        }

        return val;
    }

    long getLargePrime() {
        BigInteger bigInteger = BigInteger.probablePrime(64,new Random(System.currentTimeMillis()));
        largePrime =  bigInteger.longValue();
        return largePrime;
    }

    public RabinKarp(String pattern) {
        getLargePrime();
        long val = evalHornersRule(pattern,0,pattern.length()-1);

        storedHash = val % largePrime;
    }

    int search(String text) {
        //Needs to be fixed
        long val = 0;
        for (int i = 0;i<text.length();++i) {
            if (i > 0) {
                val = val - (text.charAt(i-1)-'a');
            }
            val = val * 26 + (text.charAt(i) - 'a');
            val = val % largePrime;
            if (val == storedHash) {
                System.out.println("Matched at index " + i);
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        RabinKarp rabinKarp = new RabinKarp("test");
        System.out.println(rabinKarp.search("hellotestworld"));
    }


}
