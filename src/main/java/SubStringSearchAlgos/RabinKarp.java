package SubStringSearchAlgos;

import java.math.BigInteger;
import java.util.Random;


public class RabinKarp {
    //Assuming ascii strings of lower case chars
    private int base = 26 ; //default for chars
    private int lengthOfPattern;
    private int basePowerLength;
    private long largePrime;
    private long storedHash;
    long evalHornersRule(String pattern,int x,int y) {
        long val = 0;
        for (int i = x;i<=y;++i) {
            val = (val*base + (pattern.charAt(i) - 'a')) % largePrime;

        }

        return val;
    }

    void calculateData() {
        basePowerLength = (int)Math.pow(base,lengthOfPattern-1);
    }
    long getLargePrime() {
        BigInteger bigInteger = BigInteger.probablePrime(32,new Random(System.currentTimeMillis()));
        largePrime =  bigInteger.longValue();
        System.out.println("Large prime is " + bigInteger);
        return largePrime;
    }

    public RabinKarp(String pattern) {
        lengthOfPattern = pattern.length();
        getLargePrime();
        calculateData();
        long val = evalHornersRule(pattern,0,pattern.length()-1);

        storedHash = val % largePrime;
    }

    //Monte Carlo version
    int search(String text) {
        //Needs to be fixed
        long val = 0;
        long textHash = evalHornersRule(text,0,lengthOfPattern-1);
        if (textHash == storedHash) {
            return 0;
        }

        for (int i = lengthOfPattern;i<text.length();++i) {
            textHash = (textHash + largePrime - (basePowerLength*(text.charAt(i-lengthOfPattern) - 'a')) % largePrime) % largePrime;
            textHash = (textHash*base + (text.charAt(i) - 'a')) % largePrime;
            if (textHash == storedHash) {
                return i-lengthOfPattern+1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        RabinKarp rabinKarp = new RabinKarp("test");
        System.out.println(rabinKarp.search("hellotestworld"));
    }


}
