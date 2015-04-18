package Coins;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Main {
    private HashMap<Integer,Integer> m_HashMap = new HashMap<>();
    int count(int amount) {
        if (amount == 1 || amount == 3 || amount == 5) {
            return 1;
        }

        if (m_HashMap.containsKey(amount)) {
            return m_HashMap.get(amount);
        }

        int f1 = Integer.MAX_VALUE;
        int f2 = Integer.MAX_VALUE;
        int f3 = Integer.MAX_VALUE;

        if (amount > 1) {
            f1 = 1 + count(amount-1);
        }

        if (amount > 3) {
            f2 = 1 + count(amount-3);
        }

        if (amount > 5) {
            f3 = 1 + count(amount-5);
        }

        int x =  Math.min(f1,Math.min(f2,f3));
        m_HashMap.put(amount,x);
        return x;
    }

    void readFile(String fileName) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                //Integer in = Integer.parseInt(line);
                System.out.println(count(Integer.parseInt(line)));
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main m = new Main();
        //System.out.println(m.count(20));
        m.readFile(args[0]);
    }
}
