package FirstNonRepeatedChar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    char findFirst(String string) {
        HashSet<Character> observedChars = new HashSet<>();
        HashMap<Character,Integer> nonRepeatedChars = new HashMap<>();
        TreeSet<Integer> indexOfNonRepeatedChars = new TreeSet<>();
        for (int i = 0;i<string.length();++i) {
            char x = string.charAt(i);
            if (observedChars.contains(x)) {
                int lastIndex = nonRepeatedChars.get(x);
                indexOfNonRepeatedChars.remove(lastIndex);
            }else {
                observedChars.add(x);
                nonRepeatedChars.put(x, i);
                indexOfNonRepeatedChars.add(i);
            }
        }

        if (indexOfNonRepeatedChars.size() > 0) {
            return string.charAt(indexOfNonRepeatedChars.first());
        }else {
            return '\0';
        }

    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                System.out.println(findFirst(line));

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
