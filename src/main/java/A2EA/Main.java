package A2EA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Rahul on 4/28/15.
 */
class Pair {
    private int n;
    private int p;

    public Pair(int n, int p) {
        this.n = n;
        this.p = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (n != pair.n) return false;
        return p == pair.p;

    }

    @Override
    public int hashCode() {
        int result = n;
        result = 31 * result + p;
        return result;
    }
}
public class Main {

    private double CONST_PR = 1/6.0;
    double getProbability(int n,int p,HashMap<Pair,Double> cache) {
        //System.out.println(n + " " + p);

        if (p > (6*n)) {
            return 0;
        }

        if (n == 1) {
            //System.out.println("here");
            if (p <= 6 && p >= 1) {
                //System.out.println("here1");
                return CONST_PR;
            }else {
                return 0;
            }
        }

        if (p <= 0) {
            return 0;
        }

        Pair pair = new Pair(n,p);
        if (cache.containsKey(pair)) {
            //System.out.println("cache hit");
            return cache.get(pair);
        }
        double p1 = CONST_PR*getProbability(n-1,p-1,cache);
        double p2 = CONST_PR*getProbability(n-1,p-2,cache);
        double p3 = CONST_PR*getProbability(n-1,p-3,cache);
        double p4 = CONST_PR*getProbability(n-1,p-4,cache);
        double p5 = CONST_PR*getProbability(n-1,p-5,cache);
        double p6 = CONST_PR*getProbability(n-1,p-6,cache);
        double pr = p1+p2+p3+p4+p5+p6;
        cache.put(pair,pr);
        return pr;
    }

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;

            int n = Integer.parseInt(br.readLine());
            int i = 0;
            while (i < n && (line = br.readLine()) != null) {
                String [] val = line.split(" ");
                int N = Integer.parseInt(val[0]);
                int P = Integer.parseInt(val[1]);

                HashMap<Pair,Double> cache = new HashMap<>();
                double pr = getProbability(N, P,cache);
                //System.out.println(pr);
                pr = pr*100;
                System.out.println((int)pr);
                ++i;

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(null);
    }
}
