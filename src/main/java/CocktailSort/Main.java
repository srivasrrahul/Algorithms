package CocktailSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    void cocktailSort(ArrayList<Integer> arrayList,int iteration) {
        //System.out.println(iteration);
        if (iteration == 0) {
            return;
        }

        for (int i = 0;i<arrayList.size()-1;++i) {
            //System.out.println("1");
            int x = arrayList.get(i);
            int y = arrayList.get(i+1);

            if (x > y) {
                arrayList.set(i,y);
                arrayList.set(i+1,x);
            }
        }

        for (int i = arrayList.size()-1;i>0;--i) {
            int y = arrayList.get(i);
            int x = arrayList.get(i-1);

            if (x > y) {
                arrayList.set(i,x);
                arrayList.set(i-1,y);
            }
        }

        cocktailSort(arrayList,iteration-1);
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                String x[] = line.split("\\|");
                //System.out.println(x[0] + "    " + x[1]);
                String arr[] = x[0].split(" ");

                ArrayList<Integer> arrayList = new ArrayList<>();
                for (String string : arr) {
                    arrayList.add(Integer.parseInt(string));
                }

                x[1] = x[1].trim();
                int iteration = Integer.parseInt(x[1]);
                cocktailSort(arrayList,iteration);

                int index = 0;
                for (int val : arrayList) {
                    System.out.print(val + (index == arrayList.size()-1?"":" "));
                    ++index;
                }

                System.out.println();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(args[0]);
    }
}
