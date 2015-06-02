package Euler1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    int getSumOfDigits(int x) {
        String val = String.valueOf(x);
        int sum = 0;
        for (Character character : val.toCharArray()) {
            sum += character - '0';
        }

        return sum;
    }

    boolean ifDivisibleBy5(int n) {
        String val = String.valueOf(n);
        char ch = val.charAt(val.length()-1);
        return ch == '5' || ch == '0';
    }
    int getSumOfMultiples(int N) {
        int sum = 0;
        for (int i = 1;i<N;++i) {
            int x = getSumOfDigits(i) % 3;
            if (x == 0) {
                sum += i;
                continue;
            }


            if (ifDivisibleBy5(i)) {
                sum += i;
            }
        }

        return sum;
    }

    int getSumOfMultiplesApproach2(int N) {
        int sum = 0;

        for (int i = 3;i<N;i +=3) {
            sum +=i;

        }

        for (int i = 5,k=1;i<N;i +=5) {
            if (k == 3) {
                k=1;
            }else {
                sum += i;
                ++k;
            }
        }

        return sum;
    }

    int getSumOfMultiplesApproach3(int N) {
        int sum = 0;

        for (int i = 3;i<N;i +=3) {
            sum +=i;

        }

        for (int i = 5,k=1;i<N;i +=5) {
            if (k == 3) {
                k=1;
            }else {
                sum += i;
                ++k;
            }
        }

        return sum;
    }

    void handleLine(String line) {

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int testCases = Integer.parseInt(line);
        Solution solution = new Solution();
        for (int i = 0;i<testCases;++i) {
            line= br.readLine();
            System.out.println(solution.getSumOfMultiplesApproach2(Integer.parseInt(line)));

        }




    }
}
