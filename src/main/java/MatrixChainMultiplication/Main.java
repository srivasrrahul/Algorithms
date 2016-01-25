package MatrixChainMultiplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rahul on 4/28/15.
 */
class Pair {
    private int color;
    private int cost;

    public Pair(int color, int cost) {
        this.color = color;
        this.cost = cost;
    }

    public int getColor() {
        return color;
    }

    public int getCost() {
        return cost;
    }
}
class Join {
    private int x;
    private int y;

    public Join(int x, int y) {
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

        Join join = (Join) o;

        if (x != join.x) return false;
        return y == join.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
public class Main {

    Pair getCost(ArrayList<Integer> integerArrayList,int i,int j,HashMap<Join,Pair> cache) {
        if (i == j) {
            return new Pair(integerArrayList.get(i),0); //if same no smoke and same colour
        }
        Join join = new Join(i,j);

        if (cache.containsKey(join)) {
            return cache.get(join);
        }

        int minSmoke = Integer.MAX_VALUE;
        Pair updatedPair = null;

        for (int k=i;k<j;++k) {

            //System.out.println("For " + i + " " + k + " " + j);
            Pair colorLeft = getCost(integerArrayList, i, k,cache);
            Pair colorRight = getCost(integerArrayList, k + 1, j,cache);
            int resultColor = (colorLeft.getColor() + colorRight.getColor()) % 100;
            int resultSmoke = colorLeft.getColor() * colorRight.getColor() + colorLeft.getCost() + colorRight.getCost();
            if (resultSmoke < minSmoke) {
                minSmoke = resultSmoke;
                updatedPair = new Pair(resultColor,resultSmoke);
            }

        }

        cache.put(join,updatedPair);

        return updatedPair;


    }



    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;


            while ((line = br.readLine()) != null) {
                int count = Integer.parseInt(line);
                line = br.readLine();
                String vals[] = line.split(" ");
                ArrayList<Integer> integers = new ArrayList<>();
                for (String val : vals ) {
                    integers.add(Integer.parseInt(val));
                }

                HashMap<Join,Pair> cache = new HashMap<>();
                System.out.println(getCost(integers,0,integers.size()-1,cache).getCost());

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
