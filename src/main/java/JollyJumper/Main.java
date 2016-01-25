package JollyJumper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    boolean isJolly(ArrayList<Integer> arrayList,int n) {
        BitSet bitSet = new BitSet(n-1);
        for (int i = 1;i < n;++i) {
            int diff = arrayList.get(i) - arrayList.get(i-1);

            diff = Math.abs(diff);
            //System.out.println(diff);
            if ((diff -1) >= 0 && (diff-1) < n-1) {
                bitSet.set(diff - 1);
            }

        }
        //System.out.println("cardinal " + bitSet.cardinality() + " " + (n-1));

        return (bitSet.cardinality() == (n-1));




    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                ArrayList<Integer> integers = new ArrayList<>();
                String intArr[] = line.split(" ");
                int n = Integer.parseInt(intArr[0]);
                int i = 0;
                for (String str: intArr) {
                    //System.out.println(str);
                    if (i == 0) {
                        ++i;
                        continue;
                    }
                    integers.add(Integer.parseInt(str));
                }

                System.out.println(isJolly(integers,n)?"Jolly":"Not jolly");

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
