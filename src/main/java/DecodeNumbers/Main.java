package DecodeNumbers;



/**
 * Created by Rahul on 11/17/14.
 * Code is licensed solely to Rahul
 */

import java.io.BufferedReader;
import java.io.FileReader;
public class Main {
    int decode(String str,int index) {
        if (index >= str.length()-1) {
            return 1;
        }

        int m = decode(str,index+1);

        int n  = 0;

        if (index < str.length()-1) {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(str.charAt(index));
            strBuilder.append(str.charAt(index+1));

            String y = strBuilder.toString();
            int z = Integer.parseInt(y);

            if (z <= 26) {
                n = decode(str,index+2);

            }
        }

        return m+n;
    }

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int i = 0;

            while ((line = br.readLine()) != null) {


                System.out.println(decode(line,0));


            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main m = new Main();
        m.readFile(args[0]);
        //System.out.println(m.decode("1",0));
    }
}
