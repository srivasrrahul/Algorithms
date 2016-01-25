package DecimalToBinary;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    String decToBinary(Integer x) {
        if (x == 0) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (x != 0) {
            int y = x / 2;
            int z = x % 2;
            stringBuilder.append(z);
            x = y;

        }

        return stringBuilder.reverse().toString();
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                System.out.println(decToBinary(Integer.parseInt(line)));

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
