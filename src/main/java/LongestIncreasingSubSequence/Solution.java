package LongestIncreasingSubSequence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;


class Key {
    private int index;
    private ArrayList<Integer> arrayList;

    public Key(int index, ArrayList<Integer> arrayList) {
        this.index = index;
        this.arrayList = arrayList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if (index != key.index) return false;
        if (arrayList != null ? !arrayList.equals(key.arrayList) : key.arrayList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + (arrayList != null ? arrayList.hashCode() : 0);
        return result;
    }
}

public class Solution {
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {


            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    static HashMap<Key,ArrayList<Integer>> arrayListHashMap = new HashMap<>();
    static ArrayList<Integer> lis(ArrayList<Integer> lst,int index,ArrayList<Integer> lstTillNow) {
        //System.out.println(index);
        if (index == lst.size()) {
            //Max Element empty
            return lstTillNow;
        }

        Key key = new Key(index,lstTillNow);
        if (arrayListHashMap.containsKey(key)) {
            //System.out.println("Cache hit for index " + index + " " + arrayListHashMap.get(key) );
            return arrayListHashMap.get(key);
        }

        System.out.println("hello");

        //Take current One
        int val = lst.get(index);

        ArrayList<Integer> maxTillNow = lstTillNow;
        ArrayList<Integer> takeCurrentOneIfCurrentFits = (ArrayList<Integer>)lstTillNow.clone();
        ArrayList<Integer> solveIfCurrentOneFits = new ArrayList<>();
        if (val > lstTillNow.get(lstTillNow.size()-1)) {
            takeCurrentOneIfCurrentFits.add(val);
            solveIfCurrentOneFits =  lis(lst,index+1,takeCurrentOneIfCurrentFits);
            if (maxTillNow.size() < solveIfCurrentOneFits.size()) {
                maxTillNow = solveIfCurrentOneFits;
            }
        }

        //Skip current one
        ArrayList<Integer> skippedCurrentOne = lis(lst,index+1,lstTillNow);
        if (skippedCurrentOne.size() > maxTillNow.size()) {
            maxTillNow = skippedCurrentOne;
        }

        //Start from current One
        ArrayList<Integer> newTempLst = new ArrayList<>();
        newTempLst.add(val);
        ArrayList<Integer> maxListFromCurrent = lis(lst, index + 1, newTempLst);

        if (maxListFromCurrent.size() > maxTillNow.size()) {
            maxTillNow = maxListFromCurrent;
        }
        if (maxTillNow == null) {
            System.out.println("Error");
        }

        //System.out.println("Adding for index " + index + " " + maxTillNow);
        arrayListHashMap.put(key,maxTillNow);
        return maxTillNow;

    }

    public static void main(String args[]) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(5);
        arrayList.add(2);
        arrayList.add(8);
        arrayList.add(6);
        arrayList.add(3);
        arrayList.add(6);
        arrayList.add(9);
        arrayList.add(7);

        ArrayList<Integer> solution = new ArrayList<>();
        solution.add(arrayList.get(0));

        arrayListHashMap.clear();
        ArrayList<Integer> retValue = lis(arrayList,1,solution);
        System.out.println(retValue);


    }
}
