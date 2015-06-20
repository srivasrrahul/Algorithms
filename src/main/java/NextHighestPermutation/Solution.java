package NextHighestPermutation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    void generateNextPermutation(ArrayList<Integer> arrayList) {

        TreeMap<Integer,Integer> valIndex = new TreeMap<>();
        valIndex.put(arrayList.get(arrayList.size()-1),arrayList.size()-1);
        for (int i = arrayList.size()-2;i>=0;--i) {
            if (arrayList.get(i) < arrayList.get(i+1)) {
                //Get next highest element in tree
                Map.Entry<Integer,Integer> entry = valIndex.higherEntry(arrayList.get(i));
                if (entry != null) {

                    Collections.swap(arrayList, i, entry.getValue());
                    Collections.sort(arrayList.subList(i + 1, arrayList.size()));
                }
                break;
            }else {
                valIndex.put(arrayList.get(i),i);
            }
        }
    }


    void generatePreviousPermutation(ArrayList<Integer> arrayList) {
        TreeMap<Integer,Integer> valIndex = new TreeMap<>();
        valIndex.put(arrayList.get(arrayList.size()-1),arrayList.size()-1);
        for (int i = arrayList.size()-2;i>=0;--i) {
            if (arrayList.get(i) > arrayList.get(i+1)) {
                //Get next highest element in tree
                Map.Entry<Integer,Integer> entry = valIndex.lowerEntry(arrayList.get(i));
                if (entry != null) {

                    Collections.swap(arrayList, i, entry.getValue());
                    Collections.sort(arrayList.subList(i + 1, arrayList.size()),Collections.reverseOrder());
                }
                break;
            }else {
                valIndex.put(arrayList.get(i),i);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(1);

        for (int i = 0 ;i<24;++i) {
            solution.generatePreviousPermutation(arrayList);
            System.out.println(arrayList);
        }


    }
}
