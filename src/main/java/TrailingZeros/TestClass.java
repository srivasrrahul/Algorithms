package TrailingZeros;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {
    int getNumberOfTrailingZeroes(int N) {
        if (N == 0) {
            return 0;
        }

        int x = N / 5;
        return x + getNumberOfTrailingZeroes(x);
    }

    public static void main(String[] args) throws IOException {
        //long t1 = System.currentTimeMillis();
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int x = Integer.parseInt(line);
        System.out.println(testClass.getNumberOfTrailingZeroes(x));


    }
}
