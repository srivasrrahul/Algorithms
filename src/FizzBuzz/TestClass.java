package FizzBuzz;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TestClass {

    void printRange(int N) {
        for (int i = 1;i<=N;++i) {
            if (i < 3) {
                System.out.println(i);
                continue;
            }

            if (i == 3) {
                System.out.println("Fizz");
                continue;
            }

            if (i == 4) {
                System.out.println(i);
                continue;
            }

            if (i == 5) {
                System.out.println("Buzz");
                continue;
            }

            int x = i % 3;
            int y = i % 5;

            if (x == 0 && y == 0) {
                System.out.println("FizzBuzz");
                continue;
            }

            if (x == 0) {
                System.out.println("Fizz");
                continue;
            }

            if (y == 0) {
                System.out.println("Buzz");
                continue;
            }

            System.out.println(i);


        }
    }

    public static void main(String[] args) throws IOException {
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int testCases = Integer.parseInt(line);
        line = br.readLine();

        String data[] = line.split(" ");
        for (int i = 0;i<data.length;++i) {
            testClass.printRange(Integer.parseInt(data[i]));
        }
    }
}
