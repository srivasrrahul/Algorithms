package Kattis;

/**
 * Created by rasrivastava on 1/2/17.
 */
public class Kata {
    public static String getMiddle(String word) {
        //Code goes here!

        int length = word.length();
        if (length == 2 || length == 1) {
            return word;
        }


        if (length % 2 == 1) {
            int mid = length/2;
            return word.substring(mid,mid+1);
        }else {
            int mid = length/2;
            return word.substring(mid-1,mid+1);
        }
    }

    public static void main(String[] args) {
        System.out.println(getMiddle("t2ef"));
    }
}

