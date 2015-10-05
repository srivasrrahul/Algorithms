package CountingHeads;

import java.util.HashMap;

/**
 * Created by rasrivastava on 7/14/15.
 */

class Tuple {
    private int x;
    private int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (x != tuple.x) return false;
        if (y != tuple.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
public class Solution {
    HashMap<Tuple,Double> probCache = new HashMap<>();
    double getProbability(double probability[],int i,int k) {
        if (i < k-1) {
            return 0;
        }

        if (k < 0) {
            return 0;
        }

        if (i < 0) {
            return 0;
        }

        if (i == 0) {
            if (k == 1) {
                return probability[i];
            }
            if (k == 0) {
                return (1-probability[i]);
            }else {
                System.out.println("error condition " + k);
            }


        }

        Tuple current = new Tuple(i,k);
        System.out.println(i + " " + k);
        if (probCache.containsKey(current)) {
            return probCache.get(current);
        }

        double d1 = getProbability(probability,i-1,k) * (1.0-probability[i]);
        double d2 = getProbability(probability,i-1,k-1) * probability[i];

        probCache.put(current,d1+d2);
        return d1+d2;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        double [] probability = new double[]{0.5,0.5,0.5,0.5};
        System.out.println(solution.getProbability(probability,probability.length-1,4));
    }
}
