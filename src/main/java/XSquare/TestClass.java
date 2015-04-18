package XSquare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class TestClass {
    //Any single character is a valid subsequence
    boolean commonSubSequence(String s1,String s2) {
        HashSet<Character> hashSet = new HashSet<>();
        for (Character ch : s1.toCharArray()) {
            hashSet.add(ch);
        }

        for (Character character : s2.toCharArray()) {
            if (hashSet.contains(character)) {
                return true;
            }
        }

        return false;
    }
    public static void main(String args[] ) throws Exception {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        TestClass testClass = new TestClass();
        for (int i = 0; i < N; i++) {
            String s1 = br.readLine();
            String s2 = br.readLine();

            System.out.println(testClass.commonSubSequence(s1,s2)?"Yes":"No");
        }


        //System.out.println("Hello World!");
    }
}

