package MatrixMultiply;

import java.util.ArrayList;
import java.util.HashMap;

class Tuple {
    private int x;
    private int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
class MatrixMetaData {
    private int rows;
    private int cols;

    public MatrixMetaData(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}

class Result {
    private int multiplications;
    private MatrixMetaData matrixMetaData;

    public Result(int multiplications, MatrixMetaData matrixMetaData) {
        this.multiplications = multiplications;
        this.matrixMetaData = matrixMetaData;
    }

    public int getMultiplications() {
        return multiplications;
    }

    public void setMultiplications(int multiplications) {
        this.multiplications = multiplications;
    }

    public MatrixMetaData getMatrixMetaData() {
        return matrixMetaData;
    }

    public void setMatrixMetaData(MatrixMetaData matrixMetaData) {
        this.matrixMetaData = matrixMetaData;
    }
}
public class Solution {
    static int matrixMultiplications(ArrayList<MatrixMetaData> metaDataArrayList,int x,int y) {

        System.out.println(x + "  " + y);
        if (x >= y) {
            return 0;
        }
        int minMatrixMultiplication  = Integer.MAX_VALUE;
        for (int i = x;i<y;++i) {

            int val = matrixMultiplications(metaDataArrayList,x,i) + matrixMultiplications(metaDataArrayList,i+1,y) +
                    metaDataArrayList.get(x).getRows()*metaDataArrayList.get(i).getCols()*metaDataArrayList.get(y).getCols();
            if (val < minMatrixMultiplication) {
                minMatrixMultiplication = val;
            }

        }

        return minMatrixMultiplication;




    }

    static int matrixMultiplicationsDp(ArrayList<MatrixMetaData> metaDataArrayList,int x,int y,HashMap<Tuple,Integer> cache) {


        if (x >= y) {
            return 0;
        }
        Tuple cacheKey = new Tuple(x,y);
        int minMatrixMultiplication  = Integer.MAX_VALUE;
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }
        System.out.println(x + "  " + y);

        for (int i = x;i<y;++i) {

            int val = matrixMultiplicationsDp(metaDataArrayList, x, i,cache) + matrixMultiplicationsDp(metaDataArrayList, i + 1, y,cache) +
                    metaDataArrayList.get(x).getRows()*metaDataArrayList.get(i).getCols()*metaDataArrayList.get(y).getCols();
            if (val < minMatrixMultiplication) {
                minMatrixMultiplication = val;
            }

        }

        cache.put(cacheKey,minMatrixMultiplication);
        return minMatrixMultiplication;




    }







    public static void main(String args[]) {

        ArrayList<MatrixMetaData> matrixMetaDatas = new ArrayList<>();
        matrixMetaDatas.add(new MatrixMetaData(50,20));
        matrixMetaDatas.add(new MatrixMetaData(20,1));
        matrixMetaDatas.add(new MatrixMetaData(1,10));
        matrixMetaDatas.add(new MatrixMetaData(10,100));

        HashMap<Tuple,Integer> cache = new HashMap<>();

        System.out.println(matrixMultiplicationsDp(matrixMetaDatas,0,3,cache));

    }
}
