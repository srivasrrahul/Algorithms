package CountReality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Rahul on 4/28/15.
 */
public class Main {
    private HashMap<Integer,ArrayList<ArrayList<Integer>>> memoizedData = new HashMap<>();
    ArrayList<ArrayList<Integer>> countMoney(int cents) {
        if (memoizedData.containsKey(cents)) {
            return memoizedData.get(cents);
        }
        //System.out.println(cents);
        if (cents == 1) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(1);
            ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
            lst.add(arrayList);
            return lst;
        }
        if (cents <= 0) {
            return new ArrayList<>();
        }




        //For 1
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        ArrayList<ArrayList<Integer>> lst = countMoney(cents-1);
        if (lst.size() > 0) {
            for (ArrayList<Integer> hashSet : lst) {
                //hashSet.add(1);
                ArrayList<Integer> clonedData = (ArrayList<Integer>) hashSet.clone();
                clonedData.add(1);
                solution.add(clonedData);
            }
        }

        //For 5
        if (cents == 5) {
            ArrayList<Integer> hashSet = new ArrayList<>();
            hashSet.add(5);
            solution.add(hashSet);
        }else {
            if (cents > 5) {
                ArrayList<ArrayList<Integer>> setsForFive = countMoney(cents-5);
                if (setsForFive.size() > 0) {
                    for (ArrayList<Integer> hashSet : setsForFive) {
                        ArrayList<Integer> clonedData = (ArrayList<Integer>) hashSet.clone();
                        clonedData.add(5);
                        solution.add(clonedData);
                    }
                }
            }
        }

        //For 10
        if (cents == 10) {
            ArrayList<Integer> hashSet = new ArrayList<>();
            hashSet.add(10);
            solution.add(hashSet);
        }else {
            if (cents > 10) {
                ArrayList<ArrayList<Integer>> setsForTen = countMoney(cents-10);
                if (setsForTen.size() > 0) {
                    for (ArrayList<Integer> hashSet : setsForTen) {
                        ArrayList<Integer> clonedData = (ArrayList<Integer>) hashSet.clone();
                        clonedData.add(10);
                        solution.add(clonedData);
                    }
                }
            }
        }

        //For 25
        if (cents == 25) {
            ArrayList<Integer> hashSet = new ArrayList<>();
            hashSet.add(25);
            solution.add(hashSet);
        }else {
            if (cents > 25) {
                ArrayList<ArrayList<Integer>> setsForTen = countMoney(cents-25);
                if (setsForTen.size() > 0) {
                    for (ArrayList<Integer> hashSet : setsForTen) {
                        ArrayList<Integer> clonedData = (ArrayList<Integer>) hashSet.clone();
                        clonedData.add(25);
                        solution.add(clonedData);
                    }
                }
            }
        }

        //For 50
        if (cents == 50) {
            ArrayList<Integer> hashSet = new ArrayList<>();
            hashSet.add(50);
            solution.add(hashSet);
        }else {
            if (cents > 50) {
                ArrayList<ArrayList<Integer>> setsForTen = countMoney(cents-50);
                if (setsForTen.size() > 0) {
                    for (ArrayList<Integer> hashSet : setsForTen) {
                        ArrayList<Integer> clonedData = (ArrayList<Integer>) hashSet.clone();
                        clonedData.add(50);
                        solution.add(clonedData);
                    }
                }
            }
        }

        //For 25
//        if (cents == 25) {
//            HashSet<Integer> hashSet = new HashSet<>();
//            hashSet.add(25);
//            solution.add(hashSet);
//        }else {
//            if (cents > 25) {
//                HashSet<HashSet<Integer>> setsForTwentyFive = countMoney(cents-25);
//                if (setsForTwentyFive.size() > 0) {
//                    for (HashSet<Integer> hashSet : setsForTwentyFive) {
//                        HashSet<Integer> clonedData = (HashSet<Integer>) hashSet.clone();
//                        clonedData.add(25);
//                        solution.add(clonedData);
//                    }
//                }
//            }
//        }
//
//        //For 50
//        if (cents == 50) {
//            HashSet<Integer> hashSet = new HashSet<>();
//            hashSet.add(50);
//            solution.add(hashSet);
//        }else {
//            if (cents > 50) {
//                HashSet<HashSet<Integer>> setsForFifty = countMoney(cents-50);
//                if (setsForFifty.size() > 0) {
//                    for (HashSet<Integer> hashSet : setsForFifty) {
//                        HashSet<Integer> clonedData = (HashSet<Integer>) hashSet.clone();
//                        clonedData.add(50);
//                        solution.add(clonedData);
//                    }
//                }
//            }
//        }

        for (ArrayList<Integer> set : solution) {
            Collections.sort(set);
        }
        //System.out.println("====For  " + cents + " ==");
        HashSet<ArrayList<Integer>> hashSets = new HashSet<>();
        for (ArrayList<Integer> set : solution) {
            //HashSet<Integer> hashSet = new HashSet<>();
//            for (Integer integer : set) {
//                System.out.print(integer + " ");
//                //hashSet.add(integer);
//            }
            hashSets.add(set);
            //System.out.println();
        }

        //System.out.println(" Internal Begin");
        solution.clear();
        for (ArrayList<Integer> arrayList : hashSets) {
            //System.out.println(arrayList);
            solution.add(arrayList);
        }
        //System.out.println(" Internal End");


//        solution.clear();
//        System.out.println("====For  " + cents + " ==");
//        for (HashSet<Integer> hashSet : hashSets) {
//            ArrayList<Integer> arrayList = new ArrayList<>(hashSet);
//            System.out.println(arrayList);
//            solution.add(arrayList);
//        }



        //System.out.println("======END========");
        memoizedData.put(cents,solution);
        return solution;

    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                int val = Integer.parseInt(line);
                System.out.println(countMoney(val).size());

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
