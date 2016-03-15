package ColumnNames;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    String columnName(int columnName,String tillNow) {
        //System.out.println("val = " + columnName);
        int x = columnName-1;
        int m = x % 26;
        int c = m + 'A';
        String val = Character.toString((char)c);
        //System.out.println(val);
        tillNow = val + tillNow;
        if (columnName >= 26) {
            return columnName(x / 26,tillNow);
        }else {
            return tillNow;
        }

    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                int x = Integer.valueOf(line);
                System.out.println(columnName(x,""));

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
