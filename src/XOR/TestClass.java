package XOR;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class TestClass {

        BigInteger modulus = BigInteger.valueOf(1000000000000007L);


        void  doXor(String s1, String s2)  {

            //long t1 = System.currentTimeMillis();

            //System.out.println("s1 length "+ s1.length());
            BigInteger solution = BigInteger.ZERO.setBit(s1.length()).subtract(BigInteger.ONE);
            for (int i = 0,j=s1.length()-1;i<s1.length();++i,--j) {
                if (s1.charAt(i) == s2.charAt(i)) {
                    solution = solution.flipBit(j);
                }
            }

            System.out.println(solution.mod(modulus));


        }





    public static void main(String[] args) throws IOException {
        //long t1 = System.currentTimeMillis();
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int testCases = Integer.parseInt(line);
        //testClass.init();
        //BigInteger modulus = BigInteger.valueOf(1000000007);
        for (int i = 0;i<testCases;++i) {
            //long t1 = System.currentTimeMillis();
            String s1 = br.readLine();
            String s2 = br.readLine();

            testClass.doXor(s1, s2);

        }


    }
}
