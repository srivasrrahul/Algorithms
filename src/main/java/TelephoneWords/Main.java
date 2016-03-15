package TelephoneWords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    HashMap<String,TreeSet<String>> cache = new HashMap<>();

    TreeSet<String> getCurrent(String x) {
        TreeSet<String> possibilities = new TreeSet<>();
        switch (x) {
            case "0":
                possibilities.add("0");
                break;
            case "1":
                possibilities.add("1");
                break;
            case "2":
                possibilities.add("a");
                possibilities.add("b");
                possibilities.add("c");
                break;
            case "3":
                possibilities.add("d");
                possibilities.add("e");
                possibilities.add("f");
                break;
            case "4":
                possibilities.add("g");
                possibilities.add("h");
                possibilities.add("i");
                break;
            case "5":
                possibilities.add("j");
                possibilities.add("k");
                possibilities.add("l");
                break;
            case "6":
                possibilities.add("m");
                possibilities.add("n");
                possibilities.add("o");
                break;
            case "7":
                possibilities.add("p");
                possibilities.add("q");
                possibilities.add("r");
                possibilities.add("s");
                break;
            case "8":

                possibilities.add("t");
                possibilities.add("u");
                possibilities.add("v");
                break;
            case "9":
                possibilities.add("w");
                possibilities.add("x");
                possibilities.add("y");
                possibilities.add("z");
                break;

        }

        return possibilities;
    }
    TreeSet<String> getPossible(String x,int i) {
        if (i == x.length()-1) {
            return getCurrent(String.valueOf(x.charAt(i)));
        }


        TreeSet<String> nextSet = getPossible(x,i+1);
        TreeSet<String> result = new TreeSet<>();
        TreeSet<String> currentSet = getCurrent(String.valueOf(x.charAt(i)));
        for (String current : currentSet) {
            for (String next : nextSet) {
                result.add(current.concat(next));
            }
        }

        return result;

    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                TreeSet<String> combinations = getPossible(line,0);
                int i = 0;
                for (String data : combinations) {
                    System.out.print(data + ((i == combinations.size() - 1) ? "" : ","));
                    ++i;
                }

                System.out.println();

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
