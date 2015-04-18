package Horses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class TestClass {

    class ReturnValue {
        private int armyPersonsSelected;
        private HashSet<Integer> horsesList;



        public ReturnValue(int armyPersonsSelected, HashSet<Integer> horsesList) {
            this.armyPersonsSelected = armyPersonsSelected;
            this.horsesList = horsesList;
        }

        ReturnValue createNewCopy() {
            HashSet<Integer> newHashSet = (HashSet<Integer>)horsesList.clone();
            return new ReturnValue(armyPersonsSelected,newHashSet);
        }

        public int getArmyPersonsSelected() {
            return armyPersonsSelected;
        }

        public void setArmyPersonsSelected(int armyPersonsSelected) {
            this.armyPersonsSelected = armyPersonsSelected;
        }

        public HashSet<Integer> getHorsesList() {
            return horsesList;
        }

        public void setHorsesList(HashSet<Integer> horsesList) {
            this.horsesList = horsesList;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            return "ReturnValue{" +
                    "armyPersonsSelected = " + armyPersonsSelected +
                    ", horsesList=" + horsesList +
                    '}';
        }
    }

    class ReturnValueBitSet {
        private int armyPersonsSelected;
        private BitSet horsesList;



        public ReturnValueBitSet(int armyPersonsSelected, BitSet horsesList) {
            this.armyPersonsSelected = armyPersonsSelected;
            this.horsesList = horsesList;
        }

        ReturnValueBitSet createNewCopy() {
            BitSet newBitSet = (BitSet)horsesList.clone();
            return new ReturnValueBitSet(armyPersonsSelected,newBitSet);
        }

        public int getArmyPersonsSelected() {
            return armyPersonsSelected;
        }

        public void setArmyPersonsSelected(int armyPersonsSelected) {
            this.armyPersonsSelected = armyPersonsSelected;
        }

        public BitSet getHorsesList() {
            return horsesList;
        }

        public void setHorsesList(BitSet horsesList) {
            this.horsesList = horsesList;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            return "ReturnValue{" +
                    "armyPersonsSelected = " + armyPersonsSelected +
                    ", horsesList=" + horsesList +
                    '}';
        }
    }



    HashMap<Integer,Integer> copyHashMap(HashMap<Integer,Integer> sourceHashMap) {
        HashMap<Integer,Integer> newHashMap = new HashMap<>();
        for (Map.Entry<Integer,Integer> entry : sourceHashMap.entrySet()) {
            newHashMap.put(entry.getKey(),entry.getValue());
        }

        return newHashMap;
    }


    class CacheEntry {
        private HashSet<Integer> assignedHorsesPriorToCurrent;
        private int currentIndex;

        public CacheEntry(HashSet<Integer> assignedHorsesPriorToCurrent, int currentIndex) {
            this.assignedHorsesPriorToCurrent = assignedHorsesPriorToCurrent;
            this.currentIndex = currentIndex;
        }

        CacheEntry createNewCopy() {
            HashSet<Integer> hashSetCopy = (HashSet<Integer>)assignedHorsesPriorToCurrent.clone();
            return new CacheEntry(hashSetCopy,currentIndex);

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CacheEntry that = (CacheEntry) o;

            if (currentIndex != that.currentIndex) return false;
            if (assignedHorsesPriorToCurrent != null ? !assignedHorsesPriorToCurrent.equals(that.assignedHorsesPriorToCurrent) : that.assignedHorsesPriorToCurrent != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = assignedHorsesPriorToCurrent != null ? assignedHorsesPriorToCurrent.hashCode() : 0;
            result = 31 * result + currentIndex;
            return result;
        }
    }

    class CacheEntryBitSet {
        private BitSet assignedHorsesPriorToCurrent;
        private int currentIndex;
        private boolean pendingFull = false;

        public CacheEntryBitSet(BitSet assignedHorsesPriorToCurrent, int currentIndex) {
            this.assignedHorsesPriorToCurrent = assignedHorsesPriorToCurrent;
            this.currentIndex = currentIndex;
        }

        CacheEntryBitSet createNewCopy() {
            BitSet bitsetCopy = (BitSet)assignedHorsesPriorToCurrent.clone();
            return new CacheEntryBitSet(bitsetCopy,currentIndex);

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CacheEntryBitSet that = (CacheEntryBitSet) o;

            if (currentIndex != that.currentIndex) return false;
            if (assignedHorsesPriorToCurrent != null ? !assignedHorsesPriorToCurrent.equals(that.assignedHorsesPriorToCurrent) : that.assignedHorsesPriorToCurrent != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = assignedHorsesPriorToCurrent != null ? assignedHorsesPriorToCurrent.hashCode() : 0;
            result = 31 * result + currentIndex;
            return result;
        }

        public boolean isPendingFull() {
            return pendingFull;
        }

        public void setPendingFull(boolean pendingFull) {
            this.pendingFull = pendingFull;
        }
    }

    ReturnValue maxArmyMen(ArrayList<HashSet<Integer>> armyMenChoices,HashSet<Integer> assignedHorsesPriorToCurrent,
                           int currentIndex,HashMap<CacheEntry,ReturnValue> cacheEntryIntegerHashMap) {

        //System.out.println("Inside currentIndex " + currentIndex);
        if (currentIndex >= armyMenChoices.size()) {

            return new ReturnValue(0,new HashSet<Integer>());
        }

        CacheEntry currentCacheEntry = new CacheEntry(assignedHorsesPriorToCurrent,currentIndex);
        if (cacheEntryIntegerHashMap.containsKey(currentCacheEntry)) {
            //System.out.println("Cache taking effect retuning for index " + currentIndex + " value " + cacheEntryIntegerHashMap.get(currentCacheEntry).getArmyPersonsSelected());
            return cacheEntryIntegerHashMap.get(currentCacheEntry).createNewCopy();
        }

        if (currentIndex == armyMenChoices.size()-1) {
            for (Integer horseChoice : armyMenChoices.get(currentIndex)) {
                if (assignedHorsesPriorToCurrent.contains(horseChoice) == false) {
                    //System.out.println("Last temp assigned " + currentIndex + " " + " horse " + horseChoice);
                    HashSet<Integer> hashSet = new HashSet<>();
                    hashSet.add(horseChoice);
                    ReturnValue returnValue = new ReturnValue(1,hashSet);

                    cacheEntryIntegerHashMap.put(currentCacheEntry,returnValue.createNewCopy());
                    return returnValue;
                }
            }

            //System.out.println("No horse pending for last " + currentIndex);
            ReturnValue returnValue = new ReturnValue(0,new HashSet<Integer>());
            return returnValue;
        }

        ReturnValue maxValue = new ReturnValue(0,new HashSet<Integer>());
        int finalHorseChoice = 0;
        for (Integer horseChoice : armyMenChoices.get(currentIndex)) {
            if (assignedHorsesPriorToCurrent.contains(horseChoice) == false) {
                //Copy by value
                //System.out.println("Temp assigning to armymen with index " + currentIndex + " " + " horse " + horseChoice);
                //HashMap<Integer,Integer> selectHashMap = copyHashMap(assignedHorsesPriorToCurrent);
                HashSet<Integer> selectHashSet = (HashSet<Integer>) assignedHorsesPriorToCurrent.clone();
                selectHashSet.add(horseChoice);
                ReturnValue sampleMax = maxArmyMen(armyMenChoices,selectHashSet,currentIndex+1,cacheEntryIntegerHashMap);
                sampleMax.setArmyPersonsSelected(sampleMax.getArmyPersonsSelected()+1);
                //System.out.println("Total person selected with index " + currentIndex + " " + " horseIndex " + horseChoice + " " + sampleMax.getArmyPersonsSelected());

                sampleMax.getHorsesList().add(horseChoice);
                if (sampleMax.getArmyPersonsSelected() > maxValue.getArmyPersonsSelected()) {
                    finalHorseChoice = horseChoice;
                    maxValue = sampleMax;
                }
            }
        }

        //Now dont select current
        ReturnValue sampleMax = maxArmyMen(armyMenChoices,assignedHorsesPriorToCurrent,currentIndex+1,cacheEntryIntegerHashMap);
        if (sampleMax.getArmyPersonsSelected() > maxValue.getArmyPersonsSelected()) {
            //maxValue = sampleMax;
            //System.out.println("Current person not selected with index " + currentIndex + " " + " horseIndex " + finalHorseChoice + " " + maxValue.getArmyPersonsSelected());

            return sampleMax;
        }

        //Now add current

        //System.out.println("Final person selected with index " + currentIndex + " " + " horseIndex " + finalHorseChoice + " " + maxValue.getArmyPersonsSelected());

        cacheEntryIntegerHashMap.put(currentCacheEntry,maxValue.createNewCopy());
        return maxValue;


    }

    BitSet getPendingBits(BitSet s1,BitSet s2,int M) {
        BitSet bitSet = (BitSet)s1.clone();
        bitSet.xor(s2);
        bitSet.and(s1);
        return bitSet;

    }
    ReturnValueBitSet maxArmyMenBitSet(ArrayList<BitSet> armyMenChoices,BitSet assignedHorsesPriorToCurrent,
                           int currentIndex,HashMap<CacheEntryBitSet,ReturnValueBitSet> cacheEntryIntegerHashMap,int M) {

        System.out.println("Inside currentIndex " + currentIndex);
        if (currentIndex >= armyMenChoices.size()) {

            return new ReturnValueBitSet(0,new BitSet(M));
        }

        CacheEntryBitSet currentCacheEntry = new CacheEntryBitSet(assignedHorsesPriorToCurrent,currentIndex);
        if (cacheEntryIntegerHashMap.containsKey(currentCacheEntry)) {
            System.out.println("Cache taking effect retuning for index " + currentIndex + " value " + cacheEntryIntegerHashMap.get(currentCacheEntry).getArmyPersonsSelected());
            return cacheEntryIntegerHashMap.get(currentCacheEntry).createNewCopy();
        }

        if (currentIndex == armyMenChoices.size()-1) {
            BitSet indexChoice = armyMenChoices.get(currentIndex);
            BitSet remainedChoice = getPendingBits(indexChoice,assignedHorsesPriorToCurrent,M);
            if (remainedChoice.isEmpty()) {
                ReturnValueBitSet returnValue = new ReturnValueBitSet(0,new BitSet(M));
                cacheEntryIntegerHashMap.put(currentCacheEntry,returnValue.createNewCopy());
                return returnValue;
            }

            for (int i = remainedChoice.nextSetBit(0); i >= 0; i = remainedChoice.nextSetBit(i+1)) {
                // operate on index i here
                BitSet bitset = new BitSet(M);
                bitset.set(i);
                ReturnValueBitSet returnValue = new ReturnValueBitSet(1,bitset);

                cacheEntryIntegerHashMap.put(currentCacheEntry,returnValue.createNewCopy());
                return returnValue;

            }

//            for (Integer horseChoice : armyMenChoices.get(currentIndex)) {
//                if (assignedHorsesPriorToCurrent.get(horseChoice) == false) {
//                    //System.out.println("Last temp assigned " + currentIndex + " " + " horse " + horseChoice);
//                    BitSet bitset = new BitSet(M);
//                    bitset.set(horseChoice);
//                    ReturnValueBitSet returnValue = new ReturnValueBitSet(1,bitset);
//
//                    cacheEntryIntegerHashMap.put(currentCacheEntry,returnValue.createNewCopy());
//                    return returnValue;
//                }
//            }

            //System.out.println("No horse pending for last " + currentIndex);

        }

        ReturnValueBitSet maxValue = new ReturnValueBitSet(0,new BitSet(M));
        int finalHorseChoice = 0;
        BitSet indexChoice = armyMenChoices.get(currentIndex);
        BitSet remainedChoice = getPendingBits(indexChoice,assignedHorsesPriorToCurrent,M);
        if (false == remainedChoice.isEmpty()) {
            for (int horseChoice = remainedChoice.nextSetBit(0); horseChoice >= 0; horseChoice = remainedChoice.nextSetBit(horseChoice+1)) {
                // operate on index i here
                System.out.println("Temp assigning to armymen with index " + currentIndex + " " + " horse " + horseChoice);
                //HashMap<Integer,Integer> selectHashMap = copyHashMap(assignedHorsesPriorToCurrent);
                BitSet selectBitSet = (BitSet) assignedHorsesPriorToCurrent.clone();
                selectBitSet.set(horseChoice);
                ReturnValueBitSet sampleMax = maxArmyMenBitSet(armyMenChoices, selectBitSet, currentIndex + 1, cacheEntryIntegerHashMap,M);
                sampleMax.setArmyPersonsSelected(sampleMax.getArmyPersonsSelected()+1);
                System.out.println("Total person selected with index " + currentIndex + " " + " horseIndex " + horseChoice + " " + sampleMax.getArmyPersonsSelected());

                sampleMax.getHorsesList().set(horseChoice);
                if (sampleMax.getArmyPersonsSelected() > maxValue.getArmyPersonsSelected()) {
                    finalHorseChoice = horseChoice;
                    maxValue = sampleMax;
                }
                //if keg is full then this is max
                if (sampleMax.getArmyPersonsSelected() == armyMenChoices.size()-currentIndex-1) {
                    System.out.println("This is max break for index" + currentIndex);
                    
                    cacheEntryIntegerHashMap.put(currentCacheEntry,maxValue.createNewCopy());
                    return maxValue;

                }
            }
        }
//        for (Integer horseChoice : armyMenChoices.get(currentIndex)) {
//            if (assignedHorsesPriorToCurrent.get(horseChoice) == false) {
//                //Copy by value
//                System.out.println("Temp assigning to armymen with index " + currentIndex + " " + " horse " + horseChoice);
//                //HashMap<Integer,Integer> selectHashMap = copyHashMap(assignedHorsesPriorToCurrent);
//                BitSet selectBitSet = (BitSet) assignedHorsesPriorToCurrent.clone();
//                selectBitSet.set(horseChoice);
//                ReturnValueBitSet sampleMax = maxArmyMenBitSet(armyMenChoices, selectBitSet, currentIndex + 1, cacheEntryIntegerHashMap,M);
//                sampleMax.setArmyPersonsSelected(sampleMax.getArmyPersonsSelected()+1);
//                //System.out.println("Total person selected with index " + currentIndex + " " + " horseIndex " + horseChoice + " " + sampleMax.getArmyPersonsSelected());
//
//                sampleMax.getHorsesList().set(horseChoice);
//                if (sampleMax.getArmyPersonsSelected() > maxValue.getArmyPersonsSelected()) {
//                    finalHorseChoice = horseChoice;
//                    maxValue = sampleMax;
//                }
//            }
//        }

        //Now dont select current
        ReturnValueBitSet sampleMax = maxArmyMenBitSet(armyMenChoices, assignedHorsesPriorToCurrent, currentIndex + 1, cacheEntryIntegerHashMap,M);
        if (sampleMax.getArmyPersonsSelected() > maxValue.getArmyPersonsSelected()) {
            //maxValue = sampleMax;
            System.out.println("Current person not selected with index " + currentIndex + " " + " horseIndex " + finalHorseChoice + " " + maxValue.getArmyPersonsSelected());
            cacheEntryIntegerHashMap.put(currentCacheEntry,sampleMax.createNewCopy());
            return sampleMax;
        }

        //Now add current

        System.out.println("Final person selected with index " + currentIndex + " " + " horseIndex " + finalHorseChoice + " " + maxValue.getArmyPersonsSelected());


        cacheEntryIntegerHashMap.put(currentCacheEntry,maxValue.createNewCopy());
        return maxValue;


    }

//    public static void main(String[] args) throws IOException {
//        TestClass testClass = new TestClass();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int testCases = Integer.parseInt(br.readLine()); //Its a watse
//        String line;
//
//        for (int i = 0;i<testCases;++i) {
//            ArrayList<HashSet<Integer>> armyMenChoices = new ArrayList<>();
//            line = br.readLine();
//            String [] data = line.split(" ");
//            int armyMen = Integer.parseInt(data[0]);
//            int horse = Integer.parseInt(data[1]);
//
//            for (int j = 0;j<armyMen;++j) {
//                line = br.readLine();
//                data = line.split(" ");
//                HashSet<Integer> horses = new HashSet<>();
//                for (int k = 1;k<data.length;++k) {
//                    horses.add(Integer.parseInt(data[k]));
//                }
//
//                armyMenChoices.add(horses);
//
//            }
//
//            //HashMap horses vs persons
//            HashSet<Integer> hashSet = new HashSet<>();
//            HashMap<CacheEntry,ReturnValue> cacheEntryHashMap = new HashMap<>();
//            System.out.println(testClass.maxArmyMen(armyMenChoices,hashSet,0,cacheEntryHashMap).getHorsesList().size());
//
//        }
//
//
//
//
//
//    }

    public static void main(String[] args) throws IOException {
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine()); //Its a watse
        String line;

        for (int i = 0;i<testCases;++i) {
            ArrayList<BitSet> armyMenChoices = new ArrayList<>();
            line = br.readLine();
            String [] data = line.split(" ");
            int armyMen = Integer.parseInt(data[0]);
            int horse = Integer.parseInt(data[1]);

            for (int j = 0;j<armyMen;++j) {
                line = br.readLine();
                data = line.split(" ");
                BitSet horses = new BitSet(horse+1);
                for (int k = 1;k<data.length;++k) {
                    horses.set(Integer.parseInt(data[k]));
                }

                armyMenChoices.add(horses);

            }

            //HashMap horses vs persons
            BitSet bitSet = new BitSet(horse+1);
            HashMap<CacheEntryBitSet,ReturnValueBitSet> cacheEntryHashMap = new HashMap<>();
            System.out.println(testClass.maxArmyMenBitSet(armyMenChoices, bitSet, 0, cacheEntryHashMap,horse+1).getArmyPersonsSelected());

        }





    }
}
