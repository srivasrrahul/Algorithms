package LargestSubMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rahul on 4/28/15.
 */
class Result {

    private int sum;

    public Result( int sum) {

        this.sum = sum;
    }



    public int getSum() {
        return sum;
    }
}
class Pair {
    private int row;
    private int col;

    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (row != pair.row) return false;
        return col == pair.col;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
public class Main {
    ArrayList<Pair> getSubMatricesDimensions(ArrayList<ArrayList<Integer>> matrix,int a,int b) {
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int i = a;i<matrix.size();++i) {
            for (int j = b;j<matrix.get(matrix.size()-1).size();++j) {
                Pair pair = new Pair(i,j);
                pairs.add(pair);
            }
        }

        return pairs;
    }
    Result largestMatrix(ArrayList<ArrayList<Integer>> matrix,int a,int b,HashMap<Pair,Integer> cache) {
        //System.out.println(a + " " + b);
        if (a == matrix.size()-1 && b == matrix.get(matrix.size()-1).size()) {
            return new Result(matrix.get(a).get(b));
        }
        Pair current = new Pair(a,b);
        if (cache.containsKey(current)) {
            //System.out.println("Cache found");
            return new Result(cache.get(current));
        }

        Result result1 = null;
        Result result2 = null;
        if (a+1< matrix.size()) {
            result1 = largestMatrix(matrix, a + 1, b,cache);
        }

        if (b+1 < matrix.get(matrix.size()-1).size()) {
            result2 = largestMatrix(matrix, a, b + 1,cache);
        }

        Result maxResultWithoutCurrent = null;
        if (result1 != null && result2 != null) {
            maxResultWithoutCurrent = result1.getSum() > result2.getSum()? result1:result2;
        }

        if (result1 == null) {
            maxResultWithoutCurrent = result2;
        }

        if (result2 == null) {
            maxResultWithoutCurrent = result1;
        }

        //Now calculate largest sum including me
        ArrayList<Pair> dimensions = getSubMatricesDimensions(matrix,a,b);
        int maxSum = Integer.MIN_VALUE;
        for (Pair pair : dimensions) {
            int tempSum   = 0;
            for (int i = a;i<=pair.getRow();++i) {
                for (int j = b; j <= pair.getCol(); ++j) {
                    tempSum += matrix.get(i).get(j);
                }
            }


            if (tempSum > maxSum) {
                maxSum = tempSum;
            }
        }

        if (maxResultWithoutCurrent != null) {
            if (maxResultWithoutCurrent.getSum() > maxSum) {
                cache.put(new Pair(a,b),maxResultWithoutCurrent.getSum());
                return maxResultWithoutCurrent;
            }else {
                cache.put(new Pair(a,b),maxSum);
                return new Result(maxSum);
            }
        }

        cache.put(new Pair(a,b),maxSum);
        return new Result(maxSum);

    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String x[] = line.split(" ");
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (String val : x) {
                    arrayList.add(Integer.parseInt(val));
                }

                matrix.add(arrayList);

            }

            HashMap<Pair,Integer> cache = new HashMap<>();
            System.out.println(largestMatrix(matrix, 0, 0,cache).getSum());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(args[0]);
    }
}
