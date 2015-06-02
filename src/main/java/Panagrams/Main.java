package Panagrams;

import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

    void handleLine(String line) {
        char temp[] = new char[26];
        for (int i = 0;i<line.length();++i) {
            char x = line.charAt(i);
            x = Character.toLowerCase(x);
            if (Character.isAlphabetic(x)) {
                int z = x - 'a';
                temp[z] = 1;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i<temp.length;++i) {
            if (temp[i] == 0) {
                stringBuilder.append((char)('a' + i));
            }
        }

        System.out.println(stringBuilder.length() != 0?stringBuilder.toString():"NULL");
    }

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                handleLine(line);

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
