package DistinctSubsequences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

class SubString {
    private HashSet<String> strings = new HashSet<>();
    void addSubString(String x) {
        for (String val : strings) {
            if (val.contains(x)) {
                return;
            }else {
                if (x.contains(val)) {
                    strings.remove(val);
                    strings.add(x);
                    return;

                }
            }
        }

        strings.add(x);
    }


    public HashSet<String> getStrings() {
        return strings;
    }
}
/**
 * Created by Rahul on 4/28/15.
 */
//ABCDEFGH => f(A) =
public class Main {
    SubString validSubString(String x) {
        ArrayList<String> substrings = new ArrayList<>();
        for (int i = 0;i<x.length();++i) {
            String subString = x.substring(i);
            System.out.println("Adding " + subString);
            substrings.add(subString);
        }
        SubString subString = new SubString();

        Collections.sort(substrings);
        for (int i = 0;i<substrings.size()-1;++i) {
            String v1 = substrings.get(i);
            String v2 = substrings.get(i+1);
            int j = 0;
            for (;j< v1.length() && j < v2.length();++j) {
                if (v1.charAt(j) != v2.charAt(j)) {
                    break;
                }
            }

            if (j != 0) {
                String v3 = v1.substring(0,j);
                subString.addSubString(v3);
            }else {
                //subString.addSubString(v1);
                //subString.addSubString(v2);
            }
        }

        return subString;
    }
//    ArrayList<String> getAllSubsequences(String line,int index) {
//        //System.out.println(line + " " + index);
//        //System.out.println(index);
//        if (index == line.length()-1) {
//
//            ArrayList<String> arrayList = new ArrayList<>();
//            arrayList.add(String.valueOf(line.charAt(index)));
//            return arrayList;
//        }
//
//        ArrayList<String> arrayList = new ArrayList<>();
//        String current = String.valueOf(line.charAt(index));
//        for (int i = index+1;i<line.length();++i) {
//            ArrayList<String> subseq = getAllSubsequences(line,i);
//            arrayList.addAll(subseq);
//            for (String t : subseq) {
//                String updatedStr = current + t;
//                arrayList.add(updatedStr);
//            }
//        }
//
//        arrayList.add(current);
//        return arrayList;
//
//    }


    HashSet<String> getAllSubsequencesHash(String line,int index,HashMap<String,HashSet<String>> cache) {
        //System.out.println(line + " " + index);

        if (index == line.length()-1) {

            HashSet<String> hashSet = new HashSet<>();
            hashSet.add(String.valueOf(line.charAt(index)));
            return hashSet;
        }

        String pending = line.substring(index);
        System.out.println(pending);
        if (cache.containsKey(pending)) {
            System.out.println("cache hit for index " + index);
            return cache.get(index);
        }

        HashSet<String> hashSet = new HashSet<>();
        String current = String.valueOf(line.charAt(index));
        HashSet<String> subseq = getAllSubsequencesHash(line, index+1,cache);

        for (String t : subseq) {
            String updatedStr = current + t;
            hashSet.add(updatedStr);
            hashSet.add(t);
        }


        hashSet.add(current);
        cache.put(pending,hashSet);
        return hashSet;

    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            int n = Integer.parseInt(line);


            int i = 0;
            while (i < n && (line = br.readLine()) != null ) {
                HashMap<String,HashSet<String>> cache = new HashMap<>();
                SubString subString = validSubString(line);
                for (String s : subString.getStrings()) {
                    System.out.println(s);
                }
                //HashSet<String> val = getAllSubsequencesHash(line, 0,cache);
//                HashSet<String> data = new HashSet<>();
//                for (String x : val) {
//
//                    //System.out.println("Val " + x);
//                    data.add(x);
//                }
//
//                data.add("");
//
//                //data.remove(line);
//
//                System.out.println(data.size());
                //val.add("");
               // System.out.println(val.size()+1);
                ++i;



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
