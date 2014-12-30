package DoubleSquares;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


public class Main {

    boolean isPerfectSquare(int x) {


        Double d = Math.sqrt(x);
        int y = d.intValue();
        return x == (y*y);

    }

    int countSumOfSquares(int x) {
        if (x == 0 || x == 1) {
            return 1;
        }

        int count = 0;
        Double d = Math.sqrt(x);
        int y = d.intValue();

        for (int i = 0;i<y;++i) {

            int z = x - (i*i);
            if (isPerfectSquare(z)) {

                ++count;
            }
        }

        return count;
    }

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int i = 0;

            while ((line = br.readLine()) != null) {

                if (i == 0) {
                    ++i;
                    continue;
                }
                Integer n = Integer.parseInt(line);
                System.out.println(countSumOfSquares(n));


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
