package SegdewickAlgo;

//import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Rahul on 11/11/15.
 */
//class Syns {
//    private HashSet<String> syns = new HashSet<>();
//    Syns() {
//
//    }
//    void addString(String syn) {
//        syns.add(syn);
//    }
//
//    boolean hasSyn(String syn) {
//        return syns.contains(syn);
//    }
//    HashSet<String> getAllSyns() {
//        return syns;
//    }
//
//}
//
//class ReturnValue {
//    private int distance;
//    private int parent;
//
//    public ReturnValue(int distance, int parent) {
//        this.distance = distance;
//        this.parent = parent;
//    }
//
//    public int getDistance() {
//        return distance;
//    }
//
//    public int getParent() {
//        return parent;
//    }
//}
//
//class HyperNyms {
//    private HashSet<Integer> hyperNyms = new HashSet<>();
//    HyperNyms() {
//
//    }
//
//    void addInt(int id) {
//        hyperNyms.add(id);
//    }
//
//    boolean hashHyperNym(int id) {
//        return hyperNyms.contains(id);
//    }
//
//    HashSet<Integer> getAllHyperNyms() {
//        return hyperNyms;
//    }
//
//
//}
public class WordNet {

//    private HashMap<Integer,Syns> synsHashMap = new HashMap<>();
//    private HashMap<String,ArrayList<Integer>> synsIds = new HashMap<>();
//    private HashMap<Integer,HyperNyms> hyperNymsHashMap = new HashMap<>();
//    void parseSynSet(String synsetFileName) {
//        In in = new In(synsetFileName);
//        while (!in.isEmpty()) {
//            String line = in.readLine();
//            String val [] = line.split(",");
////            if (val[1].equals("1830s")) {
////                System.out.println("test " + val[1]);
////            }
//            //System.out.println(val[1]);
//            int id = Integer.parseInt(val[0]);
//            String syns[] = val[1].split(" ");
//            Syns synsVal = new Syns();
//            for (String syn : syns) {
////                if (val[1].equals("1830s")) {
////                    System.out.println("test1 " + syn);
////                }
//                synsVal.addString(syn);
//                if (synsIds.containsKey(syn)) {
//                    //System.out.println("Problems for " + syn);
//                    synsIds.get(syn).add(id);
//                }else {
//                    ArrayList<Integer> arrayList = new ArrayList<>();
//                    arrayList.add(id);
//                    synsIds.put(syn,arrayList);
//                }
//
//            }
//
//            synsHashMap.put(id,synsVal);
//
//        }
//    }
//
//    void parseHyperNyms(String hypernyms) {
//        In in = new In(hypernyms);
//        while (!in.isEmpty()) {
//            String line = in.readLine();
//            String vals [] = line.split(",");
//            HyperNyms hyperNyms = new HyperNyms();
//            int index = 0;
//            int hyperNymStartVal = 0;
//            for (String val : vals) {
//                int id = Integer.parseInt(val);
//                if (index == 0) {
//                    hyperNymStartVal = id;
//                    ++index;
//                    continue;
//                }else {
//                    hyperNyms.addInt(id);
//                }
//            }
//
//            hyperNymsHashMap.put(hyperNymStartVal,hyperNyms);
//        }
//    }
//    // constructor takes the name of the two input files
//    public WordNet(String synsets, String hypernyms) {
//        parseHyperNyms(hypernyms);
//        parseSynSet(synsets);
//    }
//
//    // returns all WordNet nouns
//    public Iterable<String> nouns() {
//        HashSet<String> allNouns = new HashSet<>();
//        for (int id :  synsHashMap.keySet()) {
//            allNouns.addAll(synsHashMap.get(id).getAllSyns());
//        }
//
//        return allNouns;
//    }
//
//    // is the word a WordNet noun?
//    public boolean isNoun(String word) {
//        for (int id : synsHashMap.keySet()) {
//            if (synsHashMap.get(id).hasSyn(word)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    HyperNyms getHyperNyms(int id) {
//        return hyperNymsHashMap.get(id);
//    }
//
//
//
//    void grabAllPaths(int id,int distance,HashMap<Integer,Integer> pointDistance) {
//        HyperNyms hyperNyms = getHyperNyms(id);
//        if (hyperNyms.getAllHyperNyms().size() == 0) {
//            return;
//        }
//
//        for (int parent : hyperNyms.getAllHyperNyms()) {
////            for (String p : synsHashMap.get(parent).getAllSyns()) {
////                System.out.println(p + " ");
////            }
//
//            if (pointDistance.containsKey(parent)) {
//                if (pointDistance.get(parent) > distance) {
//
//                    pointDistance.put(parent,distance);
//                    grabAllPaths(parent,distance+1,pointDistance);
//                }else {
//                    //Old one has less distance
//                    int oldDistance = pointDistance.get(parent);
//                    //grabAllPaths(parent,oldDistance+1,pointDistance);
//                }
//            }else {
//
//                pointDistance.put(parent,distance);
//                grabAllPaths(parent,distance+1,pointDistance);
//            }
//
//
//        }
//
//
//    }
//
//
//    ReturnValue distanceInternal(int idA,int idB) {
//
//        //System.out.println(idA + " " + idB);
//        HashMap<Integer,Integer> distance1 = new HashMap<>();
//        distance1.put(idA,0); //Self distance is 0
//        grabAllPaths(idA,1,distance1);
//
//        //System.out.println("For B");
//        HashMap<Integer,Integer> distance2 = new HashMap<>();
//        distance1.put(idB,0); //Self distance is 0
//        grabAllPaths(idB,1,distance2);
//
//        //Now we have two paths iterate to common points and see its distance
//        int minDistance = Integer.MAX_VALUE;
//        int lca = -1;
//        for (int x : distance1.keySet()) {
//            if (distance2.containsKey(x)) {
//                //Both exists
//                //System.out.println("Both exists");
//                int commonDistance = distance1.get(x) + distance2.get(x);
//                if (commonDistance < minDistance) {
//                    minDistance = commonDistance;
//                    lca = x;
//                }
//
//            }
//        }
//
//        if (minDistance == Integer.MAX_VALUE) {
//            return null;
//        }
//
//        return new ReturnValue(minDistance,lca);
//    }
//    // distance between nounA and nounB (defined below)
//    public int distance(String nounA, String nounB) {
//        if (nounA.equals(nounB)) {
//            return 0;
//        }
//
//        if (!synsIds.containsKey(nounA) || !synsIds.containsKey(nounB)) {
//            throw new IllegalArgumentException();
//        }
//
//
//        ArrayList<Integer>  idALst = synsIds.get(nounA);
//        ArrayList<Integer> idBLst = synsIds.get(nounB);
//        int minDistance = Integer.MAX_VALUE;
//        for (int idA: idALst) {
//            for (int idB : idBLst) {
//                int distance = distanceInternal(idA,idB).getDistance();
//                if (distance < minDistance) {
//                    minDistance = distance;
//                }
//            }
//        }
//
//        return minDistance;
//
//    }
//
//    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
//    // in a shortest ancestral path (defined below)
//    public String sap(String nounA, String nounB) {
//
//        if (nounA.equals(nounB)) {
//            return nounA;
//        }
//
//        if (!synsIds.containsKey(nounA) || !synsIds.containsKey(nounB)) {
//            throw new IllegalArgumentException();
//        }
//
//
//        ArrayList<Integer>  idALst = synsIds.get(nounA);
//        ArrayList<Integer> idBLst = synsIds.get(nounB);
//        int minDistance = Integer.MAX_VALUE;
//        int minLcaIndex = -1;
//        for (int idA: idALst) {
//            for (int idB : idBLst) {
//                ReturnValue returnValue = distanceInternal(idA,idB);
//                int distance = returnValue.getDistance();
//                int lcaIndex = returnValue.getParent();
//                if (distance < minDistance) {
//                    minDistance = distance;
//                    minLcaIndex = lcaIndex;
//                }
//            }
//        }
//
//        return synsHashMap.get(minLcaIndex).getAllSyns().iterator().next();
//        //return null;
////        if (nounA.equals(nounB)) {
////            return nounA;
////        }
////
////        if (!synsIds.containsKey(nounA) || !synsIds.containsKey(nounB)) {
////            throw new IllegalArgumentException();
////        }
////
////        int idA = synsIds.get(nounA);
////        int idB = synsIds.get(nounB);
////        if (idA == idB) {
////            return synsHashMap.get(idA).getAllSyns().iterator().next();
////        }
////
////        System.out.println(idA + " " + idB);
////        HashMap<Integer,Integer> distance1 = new HashMap<>();
////        distance1.put(idA,0); //Self distance is 0
////        grabAllPaths(idA,1,distance1);
////
////        System.out.println("For B");
////        HashMap<Integer,Integer> distance2 = new HashMap<>();
////        distance1.put(idB,0); //Self distance is 0
////        grabAllPaths(idB,1,distance2);
////
////        //Now we have two paths iterate to common points and see its distance
////        int minDistance = Integer.MAX_VALUE;
////        int minParent = Integer.MAX_VALUE;
////        for (int x : distance1.keySet()) {
////            if (distance2.containsKey(x)) {
////                //Both exists
////                //System.out.println("Both exists");
////                int commonDistance = distance1.get(x) + distance2.get(x);
////                if (commonDistance < minDistance) {
////                    minDistance = commonDistance;
////                    minParent = x;
////                }
////
////            }
////        }
////
////
////        return synsHashMap.get(minParent).getAllSyns().iterator().next();
//    }
//    // do unit testing of this class
//    public static void main(String[] args) {
//        System.out.println(args[0] + "  " + args[1]);
//        WordNet wordNet = new WordNet(args[0],args[1]);
//        System.out.println(wordNet.distance("1830s","1830s"));
//        System.out.println(wordNet.sap("AIDS","cancer"));
//    }
}

