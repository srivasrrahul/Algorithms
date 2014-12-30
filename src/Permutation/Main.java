package Permutation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    List<String> permute(String x) {
       if (x.length() == 1) {
           return new LinkedList<String>(Arrays.asList(x));
       }



       String newString = x.substring(1);
        //System.out.println("new string passed is " + newString);
        char firstVal = x.charAt(0);
       List<String> permutations = permute(newString);
        List<String> updatedPermutations = new LinkedList<>();
        for (String str : permutations) {
            for (int i = 0;i<str.length()+1;++i) {
                StringBuilder stringBuilder = new StringBuilder(str);
                stringBuilder.insert(i,firstVal);
                //System.out.println("updated str " + stringBuilder.toString());
                updatedPermutations.add(stringBuilder.toString());
            }
        }

        return updatedPermutations;
    }

    void handleLine(String line) {
        List<String> lst = permute(line);
        Collections.sort(lst);
        int index = 0;
        for (String string : lst) {
            System.out.print(string + ((index == lst.size()-1)?"":",") );
            ++index;

        }

        System.out.println();

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
        main.readFile(args[0]);
    }
}
