package MinPathSum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class Tuple {
    private int x;
    private int y;

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean isMax(int matrixSize) {
        return x == matrixSize-1 && y == matrixSize-1;
    }

    Tuple getRight(int matrixSize) {
        if (y == matrixSize-1) {
            return null;
        }

        return new Tuple(x,y+1);
    }

    Tuple getDown(int matrixSize) {
        if (x == matrixSize-1) {
            return null;
        }

        return new Tuple(x+1,y);
    }
}
public class Main {
    long minPathSum(ArrayList<ArrayList<Integer>> matrix,Tuple currentTuple,HashMap<Tuple,Long> cache) {
        if (currentTuple == null) {
            return Long.MAX_VALUE;
        }

        if (cache.containsKey(currentTuple)) {
            return cache.get(currentTuple);
        }

        int n = matrix.size();
        int currentVal = matrix.get(currentTuple.getX()).get(currentTuple.getY());
        if (currentTuple.isMax(n)) {
            return currentVal;
        }



        Tuple sideTuple = currentTuple.getRight(n);
        Tuple downTuple = currentTuple.getDown(n);


        //if (sideTuple != null) {
        long sidePath = minPathSum(matrix, sideTuple, cache);
        //}


        long downPath = minPathSum(matrix,downTuple,cache);

        long lowerPath = sidePath < downPath?(sidePath+currentVal):(downPath+currentVal);
        cache.put(currentTuple,lowerPath);
        return lowerPath;

    }

    void readFile(String fileName) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println("Initial line is " + line);
                int n = Integer.parseInt(line);
                ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
                for (int i = 0;i<n;++i) {
                    arrayList.add(new ArrayList<Integer>());
                    line = bufferedReader.readLine();
                    String [] x = line.split(",");
                    //System.out.println("Line is " + line);
                    for (int j = 0;j<n;++j) {
                        arrayList.get(i).add(Integer.parseInt(x[j]));
                    }
                }

                HashMap<Tuple,Long> cache = new HashMap<>();
                System.out.println(minPathSum(arrayList, new Tuple(0, 0),cache));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.readFile(args[0]);
    }




}
