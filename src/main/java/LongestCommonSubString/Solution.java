package LongestCommonSubString;

import java.util.HashMap;

class Tuple {
    private int x;
    private int y;

    public Tuple(int x, int y) {
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
    HashMap<Tuple,String> cache = new HashMap<>();
    String longestCommonString(String s1,String s2,int x,int y) {
        if (x >= s1.length() || y >= s2.length()) {
            return "";
        }

        //
        Tuple current = new Tuple(x,y);

        if (cache.containsKey(current)) {
            return cache.get(current);
        }

        System.out.println(x + "  " + y);
        String maxString = "";
        String option1 = longestCommonString(s1,s2,x+1,y);
        maxString = option1;
        String option2 = longestCommonString(s1,s2,x,y+1);
        if (option2.length() > maxString.length()) {
            maxString = option2;
        }

        String option3 = longestCommonString(s1,s2,x+1,y+1);
        if (s1.charAt(x) == s2.charAt(y)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s1.charAt(x));
            stringBuilder.append(option3);
            option3 = stringBuilder.toString();
        }

        if (option3.length() > maxString.length()) {
            maxString = option3;
        }



        cache.put(current,maxString);
        return maxString;



    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonString("abcde","bcde",0,0));
    }
}
