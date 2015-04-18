package BitCount;

import java.io.BufferedReader;
import java.io.FileReader;


public class Main {
    long countBits(int n) {
        int count = 0;
        while (n != 0) {
            n = n&n-1;
            ++count;
        }

        return count;
    }

    void readFile(String fileName) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                Integer in = Integer.parseInt(line);
                System.out.println(countBits(Integer.parseInt(line)));
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
