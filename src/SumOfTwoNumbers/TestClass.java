package SumOfTwoNumbers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {
    public static void main(String[] args) throws IOException {
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int testCases = Integer.parseInt(line);
        for (int i = 0;i<testCases;++i) {
            line = br.readLine();
            String data[] = line.split(" ");
            long x = Integer.parseInt(data[0]);
            long y = Integer.parseInt(data[1]);
            System.out.println(x+y);
        }



    }
}
