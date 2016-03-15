package DistinctSubsequence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
    int distinct(String haystack,String needle,int x,int y) {
        if (x >= haystack.length()) {
            return 0;
        }

        if (needle.length()-1 == y) {
            String current = haystack.substring(x);
            int count = 0;
            char charTobeFound = needle.charAt(y);
            for (Character ch : current.toCharArray()) {
                if (ch == charTobeFound) {
                    ++count;
                }
            }

            return count;
        }



        if (haystack.length() < needle.length()) {
            return 0;
        }



        char currentCharinHayStack = haystack.charAt(x);
        char currentCharinNeedle = needle.charAt(y);

        int value = 0;
        if (currentCharinHayStack == currentCharinNeedle) {
            value = distinct(haystack,needle,x+1,y+1);
        }

        int value1 = distinct(haystack,needle,x+1,y);
        return value+value1;
    }

    int distinctMemoized(String haystack,String needle,int x,int y,HashMap<Pair,Integer> cache) {
        if (x >= haystack.length()) {
            return 0;
        }

        if (haystack.length() < needle.length()) {
            return 0;
        }

        Pair pair = new Pair(x,y);
        if (cache.containsKey(pair)) {
            return cache.get(pair);
        }

        if (needle.length()-1 == y) {
            String current = haystack.substring(x);
            int count = 0;
            char charTobeFound = needle.charAt(y);
            for (Character ch : current.toCharArray()) {
                if (ch == charTobeFound) {
                    ++count;
                }
            }

            return count;
        }







        char currentCharinHayStack = haystack.charAt(x);
        char currentCharinNeedle = needle.charAt(y);

        int value = 0;
        if (currentCharinHayStack == currentCharinNeedle) {
            value = distinctMemoized(haystack, needle, x + 1, y + 1,cache);
        }

        int value1 = distinctMemoized(haystack, needle, x + 1, y,cache);
        cache.put(pair,value+value1);
        return value+value1;
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                String val[] = line.split(",");
                HashMap<Pair,Integer> cache = new HashMap<>();
                System.out.println(distinctMemoized(val[0],val[1],0,0,cache));

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
