package RemoveChars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {

    String remove(String source,String needle) {
        HashSet<Character> needleHash = new HashSet<>();
        for (int i = 0;i<needle.length();++i) {
            needleHash.add(needle.charAt(i));
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i<source.length();++i) {
            if (!needleHash.contains(source.charAt(i))) {
                stringBuilder.append(source.charAt(i));
            }
        }

        return stringBuilder.toString();
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                String [] arr = line.split(",");
                arr[1] = arr[1].trim();
                System.out.println(remove(arr[0],arr[1]));

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
