package SequenceTransformation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

class Pair {
    int m_X;
    int m_Y;
    Pair() {

    }

    void clear() {

    }

    Pair(int a,int b) {
        m_X = a;
        m_Y = b;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Pair) {
            return m_X == ((Pair) o).m_X &&
                    m_Y == ((Pair) o).m_Y;
        }

        return false;
    }

    @Override
    public int hashCode() {
        Integer m = Integer.valueOf(m_X);
        Integer n = Integer.valueOf(m_Y);

        return m + n;
    }
}

public class Main {

    HashMap<Pair,Boolean> m_HashMap = new HashMap<>();
    void clear() {
        m_HashMap.clear();
    }
    boolean isPresent(int sourceIndex,int destIndex) {
        Pair p = new Pair(sourceIndex,destIndex);
        if (m_HashMap.containsKey(p)) {
            return true;
        }

        return false;
    }

    boolean find(int sourceIndex,int destIndex) {
        Pair p = new Pair(sourceIndex,destIndex);
        return m_HashMap.get(p);
    }

    void updateCache(int sourceIndx,int destIndex,boolean res) {
        Pair p = new Pair(sourceIndx,destIndex);
        m_HashMap.put(p,res);
    }

    boolean flipOver(String str,int destIndex) {
        if (destIndex >= str.length()-1) {
            return false;
        }

        return str.charAt(destIndex) == str.charAt(destIndex+1);


    }


    boolean sequencePossible(String sourceString,String destinationString,int sourceIndex,int destIndex) {
        if (sourceIndex >= sourceString.length() && destIndex >= destinationString.length()) {
            return true;
        }

        if (sourceIndex >= sourceString.length()) {
            return false;
        }

        if (destIndex >= destinationString.length()) {
            return false;
        }

        char sourceChar = sourceString.charAt(sourceIndex);
        char destChar = destinationString.charAt(destIndex);
        //System.out.println("Condition " + sourceIndex + " " + destIndex + " " + sourceString.charAt(sourceIndex) + " " + destinationString.charAt(destIndex));

        if (isPresent(sourceIndex,destIndex)) {
            return find(sourceIndex,destIndex);
        }

        if (sourceChar == '0') {

            if (destChar == 'A') {
                //Consume 0 and A
                boolean res = sequencePossible(sourceString,destinationString,sourceIndex+1,destIndex+1);
                if (res == false) {
                    if (false == flipOver(destinationString,destIndex)) {
                        //System.out.println("Flipped over");
                        updateCache(sourceIndex,destIndex,false);
                        return false;
                    }
                    res =  sequencePossible(sourceString,destinationString,sourceIndex,destIndex+1);
                    updateCache(sourceIndex,destIndex,res);
                    return res;
                }

                updateCache(sourceIndex,destIndex,res);
                return res; //true
            }else {
                //System.out.println("returning false");
                updateCache(sourceIndex,destIndex,false);
                return false;
            }
        }else {
            if (sourceChar == '1') {
                if (destChar == 'A') {
                    boolean res = sequencePossible(sourceString,destinationString,sourceIndex+1,destIndex+1);

                    if (res == false) {
                        if (flipOver(destinationString,destIndex) == false) {
                            //System.out.println("Flipped over");
                            updateCache(sourceIndex,destIndex,false);
                            return false;
                        }

                        res =  sequencePossible(sourceString,destinationString,sourceIndex,destIndex+1);
                        updateCache(sourceIndex,destIndex,res);
                        return res;
                    }

                    updateCache(sourceIndex,destIndex,res);
                    return res;
                }else { //destChar == 'B'
                    boolean res = sequencePossible(sourceString,destinationString,sourceIndex+1,destIndex+1);
                    if (res == false) {
                        if (flipOver(destinationString,destIndex) == false) {
                            updateCache(sourceIndex,destIndex,false);
                            return false;
                        }

                        res =  sequencePossible(sourceString,destinationString,sourceIndex,destIndex+1);
                        updateCache(sourceIndex,destIndex,res);
                        return res;
                    }

                    updateCache(sourceIndex,destIndex,res);
                    return res;
                }
            }
        }


        return false;
    }

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int i = 0;

            while ((line = br.readLine()) != null) {
                String arr[]  = line.split(" ");
                System.out.println(sequencePossible(arr[0],arr[1],0,0)?"Yes":"No");
                clear();

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        //System.out.println(sequencePossible("1010","AAAAABBBBAAAA",0,0));
        Main m = new Main();
        m.readFile(args[0]);
    }




}
