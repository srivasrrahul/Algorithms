package LuckyNumbers;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by rasrivastava on 4/28/15.
 */
public class Main {
    int getCombinations(int n) {
        if (n > 0) {
            int pendingCombo = getCombinations(n-1);
            int count = 0;
            for (int i = 0;i<pendingCombo;++i) {
                //Add numbers to begin in both sets
                for (int j = 0;j<=9;++j) {
                    ++count;
                }

                //Add numbers to end in both sets
                for (int j = 0;j<=9;++j) {
                    ++count;
                }

                //Add numbers to begin in first  and end in set
                for (int j = 0;j<=9;++j) {
                    ++count;
                }
            }

            return count;
        }
        else {

            return 10;
        }
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                int x = Integer.parseInt(line);
                System.out.println(getCombinations(1));

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
