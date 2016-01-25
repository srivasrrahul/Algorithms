package AlphaCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rahul on 4/28/15.
 */
class Solution {
    private int leaves = 0;
    private HashMap<Integer,BigInteger> cache = new HashMap<>();
    void encodeMechanism(String string,int i) {


        if (i == string.length()) {
            ++leaves;

            return;
        }

        //System.out.println(string.substring(i));

        //System.out.println(string.substring(i));



        int currentValue = Integer.parseInt(String.valueOf(string.charAt(i)));
        //System.out.println("Current value " + currentValue) ;
        if (currentValue != 0) {
            encodeMechanism(string,i+1);
        }else {
            //cache.put(i,0);
            return;
        }


        if (i < string.length()-1) {
            int y = Integer.parseInt(string.substring(i, i + 2));
            if (y <= 26) {
                encodeMechanism(string,i+2);
            }
        }






    }

    BigInteger getLeavesDP(String string) {
        BigInteger bigIntegerArr[] = new BigInteger[string.length()+1];
        bigIntegerArr[string.length()] = BigInteger.ONE; //Since last cant be 0
        for (int i = string.length()-1;i>=0;--i) {
            //System.out.println(i);
            int current = Integer.parseInt(String.valueOf(string.charAt(i)));
            if (current == 0) {
                bigIntegerArr[i] = BigInteger.ZERO;
                continue;
            }
            if (i < string.length()-1) {
                int prev = Integer.parseInt(string.substring(i,i+2));
                if (prev <= 26) {
                    bigIntegerArr[i] = bigIntegerArr[i+2].add(bigIntegerArr[i+1]);
                }else {
                    bigIntegerArr[i] = bigIntegerArr[i+1];
                }
            }else {
                bigIntegerArr[string.length()-1] = BigInteger.ONE;
            }

        }
        return bigIntegerArr[0];
    }

    BigInteger getLeafs(String string,int i) {


        if (i == string.length()) {


            return BigInteger.ONE; //One leaf
        }

        //System.out.println(string.substring(i));

        //System.out.println(string.substring(i));


        if (cache.containsKey(i)) {
            //System.out.println("cache hit");
            return cache.get(i);
        }

        int currentValue = Integer.parseInt(String.valueOf(string.charAt(i)));
        //System.out.println("Current value " + currentValue) ;
        BigInteger s1 = null;
        if (currentValue != 0) {
            s1 = getLeafs(string,i+1);
        }else {
            cache.put(i,BigInteger.ZERO);
            return BigInteger.ZERO; //No leaves from this substree as its not valid
        }


        BigInteger s2 = null;
        if (i < string.length()-1) {
            int y = Integer.parseInt(string.substring(i, i + 2));
            if (y <= 26) {
                s2 = getLeafs(string, i + 2);
            }
        }


        if (s1 == null && s2 == null) {
            return BigInteger.ZERO;
        }

        if (s2 == null) {
            cache.put(i,s1);
            return s1;
        }

        BigInteger sol = s1.add(s2);
        cache.put(i,sol);
        return sol;




    }

    public int getLeaves() {
        return leaves;
    }
}
public class Main {
//    ArrayList<String> encodeMechanism(String string,int i,HashMap<Integer,ArrayList<String>> cache) {
//
//        if (i == string.length()) {
//            return new ArrayList<>();
//        }
//        if (cache.containsKey(i)) {
//            System.out.println("Cache hit");
//            return cache.get(i);
//        }
//        //System.out.println(string.substring(i));
//
//        String currentValue = String.valueOf(string.charAt(i));
//        //System.out.println("Current value is " + currentValue);
//        if (currentValue.equals("0")) {
//            //0 can't be current
//            return encodeMechanism(string,i+1,cache);
//        }
//
//        if (i == string.length()-1) {
//            ArrayList<String> arrayList =  new ArrayList<>();
//            arrayList.add(string.substring(i));
//            return arrayList;
//        }
//
//
//        ArrayList<String> s1 = encodeMechanism(string,i+1,cache);
//
//        ArrayList<String> updatedS1 = new ArrayList<>();
//        ArrayList<String> s2 = null;
//        ArrayList<String> updatedS2 = new ArrayList<>();
//
//        if (i < string.length()-1) {
//            int y = Integer.parseInt(string.substring(i, i + 2));
//
//            if (y <= 26) {
//                s2 = encodeMechanism(string, i + 2,cache);
//            }
//
//            String prefix = string.substring(i, i + 2);
//            if (s2 != null && s2.size() > 0) {
//                for (String s: s2) {
//                    StringBuilder stringBuilder = new StringBuilder(prefix);
//                    stringBuilder.append(s);
//                    updatedS2.add(stringBuilder.toString());
//                }
//
//                updatedS2 = s2;
//            }else {
//                if (s2 != null) {
//                    //Empty recieved at least add current
//                    updatedS2.add(prefix);
//                }
//            }
//
//
//        }
//
//        for (String s : s1) {
//            StringBuilder stringBuilder = new StringBuilder(currentValue);
//            stringBuilder.append(s);
//            updatedS1.add(stringBuilder.toString());
//        }
//
//
//        updatedS1.addAll(updatedS2);
//        cache.put(i,updatedS1);
//        return updatedS1;
//
//
////        int x = Integer.parseInt(String.valueOf(string.charAt(i)));
////        int y = Integer.parseInt(string.substring(i,i+2));
////        if (y <= 26) {
////            return 2 + encodeMechanism(string,i+2);
////        }else {
////            return 1+ encodeMechanism(string,i+1);
////        }
//
//    }



    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;


            while ((line = br.readLine()) != null) {
                if (line.equals("0")) {
                    break;
                }

                HashMap<Integer,ArrayList<String>> cache = new HashMap<>();
                Solution solution = new Solution();
                //solution.encodeMechanism(line,0);
                //System.out.println(solution.getLeaves());
                System.out.println(solution.getLeavesDP(line));

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {


            Main m = new Main();
            m.readFile(null);

    }
}
