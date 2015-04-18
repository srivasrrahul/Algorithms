package LetsBegin;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class TestClass {
    private HashMap<Integer,Integer> cache = new HashMap<>();
    int findMin(int x) {
        if (x <= 1) {
            return -1;
        }

        if (x == 2 || x == 3 || x == 5 || x == 7) {
            return 1;
        }

        if (cache.containsKey(x)) {
            return cache.get(x);
        }

        int minCombo = Integer.MAX_VALUE;
        if (x > 7) {
            int f1 = findMin(x - 7);
            if (f1 >=0 && f1+1 < minCombo ){
                minCombo = f1+1;
            }
        }

        if (x > 5) {
            int f1 = findMin(x - 5);
            if (f1 >= 0 && f1+1 < minCombo ){
                minCombo = f1+1;
            }
        }

        if (x > 3) {
            int f1 = findMin(x - 3);
            if (f1 >= 0 && f1+1 < minCombo ){
                minCombo = f1+1;
            }
        }


        if (x > 2) {
            //System.out.println(x);
            int f1 = findMin(x - 2);
            if (f1 >=0 && f1+1 <minCombo) {
                minCombo = f1+1;
            }


        }








        cache.put(x,minCombo);


        return minCombo;


    }

    public static void main(String[] args) throws IOException {
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            System.out.println(testClass.findMin(Integer.parseInt(line)));
        }
    }
}
