package Probability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by rasrivastava on 6/22/15.
 */
public class Shuffle {
    static int nextInt(Random random,int min,int max) {
        return random.nextInt(max-min+1) + min;
    }
    static void shuffle(ArrayList<Integer> arrayList) {
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0;i<arrayList.size()-1;++i) {
            int x = nextInt(random,i,arrayList.size()-1);
            Collections.swap(arrayList,i,x);

        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        shuffle(arrayList);
        System.out.println(arrayList);
    }
}
