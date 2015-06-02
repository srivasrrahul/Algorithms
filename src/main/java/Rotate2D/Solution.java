package Rotate2D;

import java.util.ArrayList;
import java.util.Collections;


public class Solution {

    void swap(ArrayList<ArrayList<Integer>> arrayLists,int x,int y) {
        //arr[x][y] to arr[y][x]
        int temp = arrayLists.get(x).get(y);
        arrayLists.get(x).set(y,arrayLists.get(y).get(x));
        arrayLists.get(y).set(x,temp);
    }
    void transpose(ArrayList<ArrayList<Integer>> arrayLists) {
        int n = arrayLists.size();
        for (int i = 0;i<n;++i) {
            for (int j = i+1;j<n;++j) {
                swap(arrayLists,i,j);
            }
        }
    }

    public void rotate(ArrayList<ArrayList<Integer>> A) {
        transpose(A);
        for (ArrayList<Integer> arrayList: A) {
            Collections.reverse(arrayList);
        }
    }

    void print(ArrayList<ArrayList<Integer>> arrayLists) {
        for (ArrayList<Integer> arrayList : arrayLists) {
            for (Integer x : arrayList) {
                System.out.print(x + " ");
            }

            System.out.println();
        }
    }

    public static void main(String args[]) {

        Solution m = new Solution();
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(4);
        arrayList1.add(5);
        arrayList1.add(6);

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(7);
        arrayList2.add(8);
        arrayList2.add(9);


        arrayLists.add(arrayList);
        arrayLists.add(arrayList1);
        arrayLists.add(arrayList2);

        m.rotate(arrayLists);
        m.print(arrayLists);

    }
}
