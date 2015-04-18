package UglyNumbers;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Main {
    static HashMap<Long,Boolean> uglyNumbers = new HashMap<>();
    static Pattern pattern = Pattern.compile("(?=-)|(?<=-)|(?=\\+)|(?<=\\+)");
    boolean isUgly(long x) {

        //Make it cache based
        if (x == 0) {

            return true;
        }

        if (uglyNumbers.containsKey(x)) {

            return uglyNumbers.get(x);
        }

        if (x % 2 == 0) {
            uglyNumbers.put(x,true);
            return true;
        }

        if (x % 3 == 0) {
            uglyNumbers.put(x,true);
            return true;
        }

        if (x % 5 == 0) {
            uglyNumbers.put(x,true);
            return true;
        }

        if (x % 7 == 0) {
            uglyNumbers.put(x,true);
            return true;
        }

        uglyNumbers.put(x,false);
        return false;
    }

    long eval(String [] arrayList,int index,long computedValue) {

        if (index == arrayList.length) {
            return computedValue;
        }

        //System.out.println(index + " => " + arrayList[index] + " , " + computedValue);

        long rc = 0;
        switch (arrayList[index]) {
            case "+":
                rc =  computedValue + eval(arrayList,index+1,0);
                //System.out.println(index + " Case + => Returning => " + rc);
                return rc;
            case "-":
                rc =  computedValue - Long.parseLong(arrayList[index + 1]) + eval(arrayList, index + 2, 0);
                //System.out.println(index + " Case - => Returning => " + rc);
                return rc;

            default:
                rc = eval(arrayList,index+1, Long.parseLong(arrayList[index]));
                //System.out.println(index + " Case number => Returning => " + rc);
                return rc;
        }
    }

    ArrayList<String> findUgly(String digits,int index) {
        if (index == digits.length()-1) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(String.valueOf(digits.charAt(index)));
            return arrayList;
        }

        Character currentChar = digits.charAt(index);



        ArrayList<String> answers = new ArrayList<>();
        ArrayList<String> pending = findUgly(digits,index+1);

        //Add + to all thos String
        for (String result : pending) {
            answers.add(String.valueOf(currentChar) + "+" + result);
        }
        //Add - here
        for (String result : pending) {
            answers.add(String.valueOf(currentChar) + "-" + result);
        }

        //Add current directly
        for (String result : pending) {
            answers.add(String.valueOf(currentChar) + result);
        }

        return answers;

    }

    void handleLine(String x) {
        //System.out.println(x);
        ArrayList<String> combinations = findUgly(x,0);
        int countUgly = 0;
        for (String string: combinations) {
            //String parsedString[] = string.split("(?=-)|(?<=-)|(?=\\+)|(?<=\\+)");
            String parsedString[] =  pattern.split(string);
            long temp = eval(parsedString,0,0);
            if (isUgly(temp)) {
                ++countUgly;
            }

        }

        System.out.println(countUgly);

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
        //main.print();
        main.readFile(args[0]);
    }

//    public static void main(String args[]) {
//        Main m = new Main();
//        ArrayList<String> arrayList = new ArrayList<>();
//        Collections.addAll(arrayList,"123","+","4","-","56");
//        //System.out.println(m.eval(arrayList, 0, 0));
//
//        int countUgly = 0;
//        ArrayList<String> combinations = m.findUgly("12345",0);
//        for (String string: combinations) {
//            //System.out.println(string);
//            String x[] = string.split("(?=-)|(?<=-)|(?=\\+)|(?<=\\+)");
//            int temp = m.eval(x,0,0);
//            //System.out.println(temp);
//            if (m.isUgly(temp)) {
//                ++countUgly;
//            }
//
//        }
//
//        System.out.println(countUgly);
//
//    }



}
