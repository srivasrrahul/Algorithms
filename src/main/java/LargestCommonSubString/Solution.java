package LargestCommonSubString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

class ReturnValue {
    private int low;
    private int high;

    public ReturnValue(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    int getLength() {
        return getHigh()-getLow()+1;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //super.clone();
        return new ReturnValue(getLow(),getHigh());
    }
}

class Tuple {
    private int x;
    private int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (x != tuple.x) return false;
        if (y != tuple.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
public class Solution {
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {


            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static HashMap<Tuple,ReturnValue> cache = new HashMap<>();
    static ReturnValue largestCommonSubString(String x,int i,String y,int j) throws CloneNotSupportedException {
        if (i == x.length() || j == y.length()) {
            return null;
        }

        System.out.println(i + " " + j);
        Tuple currentPair = new Tuple(i,j);
        if (cache.containsKey(currentPair)) {
            return cache.get(currentPair);
        }

        //Take current
        ReturnValue combo1 = largestCommonSubString(x,i+1,y,j);
        ReturnValue combo2 = largestCommonSubString(x,i,y,j+1);
        ReturnValue combo3 = largestCommonSubString(x,i+1,y,j+1);

        if (x.charAt(i) == y.charAt(j)) {
            if (combo3 != null && combo3.getLow() == i+1) {
                combo3 = (ReturnValue)combo3.clone();
                combo3.setLow(i);
            }else {
                if (combo3 == null) {
                    combo3 = new ReturnValue(i,i);
                }
            }
        }

        int maxLength = 0;
        ReturnValue returnValue = null;
        if (combo1 != null) {
            maxLength = combo1.getLength();
            returnValue = combo1;
        }

        if (combo2 != null && combo2.getLength() > maxLength) {
            maxLength = combo2.getLength();
            returnValue = combo2;
        }

        if (combo3 != null && combo3.getLength() > maxLength) {
            maxLength = combo3.getLength();
            returnValue = combo3;
        }

        cache.put(currentPair,returnValue);


        return returnValue;


    }

    public static void main(String args[]) throws CloneNotSupportedException {

        ReturnValue returnValue = largestCommonSubString("aasgdhasdhasbcdef", 0, "hbcdefghij", 0);
        System.out.println(returnValue.getLow());
        System.out.println(returnValue.getHigh());
        System.out.println("aasgdhasdhasbcdef".substring(returnValue.getLow(),returnValue.getHigh()+1));

    }
}
