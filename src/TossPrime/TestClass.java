package TossPrime;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TestClass {

    boolean isPossibleSort(LinkedList<Integer> source,LinkedList<Integer> destination) {
        if (source.size() == 0) {
            return true;
        }

        if (destination.size() == 0) {
            LinkedList<Integer> sourceClone1 = (LinkedList<Integer>)source.clone();
            LinkedList<Integer> destinationClone1 = (LinkedList<Integer>)destination.clone();

            int s1 = sourceClone1.getFirst();
            int s2 = sourceClone1.getLast();

            sourceClone1.removeFirst();
            destinationClone1.add(s1);

            boolean rc = isPossibleSort(sourceClone1,destinationClone1);
            if (rc == true) {
                return true;
            }

            LinkedList<Integer> sourceClone2 = (LinkedList<Integer>)source.clone();
            LinkedList<Integer> destinationClone2 = (LinkedList<Integer>)destination.clone();
            sourceClone2.removeLast();
            destinationClone2.add(s2);

            return isPossibleSort(sourceClone2,destinationClone2);

        }

        int sourceFirst = source.getFirst();
        int sourceLast = source.getLast();

        int desintaionFirst = destination.getFirst();
        int destinationLast = destination.getLast();

        if (sourceFirst < desintaionFirst) {
            LinkedList<Integer> sourceClone = (LinkedList<Integer>)source.clone();
            LinkedList<Integer> destinationClone = new LinkedList<>();

            destinationClone.add(0,sourceFirst);
            destinationClone.addLast(destinationLast);
            sourceClone.removeFirst();

            boolean rc = isPossibleSort(sourceClone,destinationClone);
            if (rc == true) {
                return true;
            }


        }

        if (sourceFirst > destinationLast) {
            LinkedList<Integer> sourceClone = (LinkedList<Integer>)source.clone();
            LinkedList<Integer> destinationClone = new LinkedList<>();

            destinationClone.addLast(sourceFirst);
            destinationClone.addFirst(desintaionFirst);
            sourceClone.removeFirst();

            boolean rc = isPossibleSort(sourceClone,destinationClone);
            if (rc == true) {
                return true;
            }
        }

        if (sourceLast < desintaionFirst) {
            LinkedList<Integer> sourceClone = (LinkedList<Integer>)source.clone();
            LinkedList<Integer> destinationClone = new LinkedList<>();

            destinationClone.addFirst(sourceLast);
            destinationClone.addLast(destinationLast);
            sourceClone.removeLast();

            boolean rc = isPossibleSort(sourceClone,destinationClone);
            if (rc == true) {
                return true;
            }
        }


        if (sourceLast > destinationLast) {
            LinkedList<Integer> sourceClone = (LinkedList<Integer>)source.clone();
            LinkedList<Integer> destinationClone = new LinkedList<>();

            destinationClone.addLast(sourceLast);
            destinationClone.addFirst(desintaionFirst);
            sourceClone.removeLast();

            boolean rc = isPossibleSort(sourceClone,destinationClone);
            if (rc == true) {
                return true;
            }
        }


        return false;

    }

    String printDest(LinkedList<Integer> x) {
        StringBuilder stringBuilder = new StringBuilder();
        if (x.size() >=2) {
            stringBuilder.append(x.getFirst());
            stringBuilder.append(" ");
            stringBuilder.append(x.getLast());
        }
        return stringBuilder.toString();
    }

    class CacheEntry {
        private int sourceIndexStart;
        private int sourceIndexEnd;


        public CacheEntry(int sourceIndexStart, int sourceIndexEnd) {
            this.sourceIndexStart = sourceIndexStart;
            this.sourceIndexEnd = sourceIndexEnd;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CacheEntry cache = (CacheEntry) o;

            if (sourceIndexEnd != cache.sourceIndexEnd) return false;
            if (sourceIndexStart != cache.sourceIndexStart) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = sourceIndexStart;
            result = 31 * result + sourceIndexEnd;

            return result;
        }
    }
    boolean isPossibleSortArr(ArrayList<Integer> source,LinkedList<Integer> destination,int startFirst,int startEnd,HashMap<CacheEntry,Boolean> cache) {
        //System.out.println("Start first " + startFirst + " End is " + startEnd + " " + (printDest(destination)));
        if (startFirst > startEnd) {
            return true;
        }

        if (destination.size() == 0) {

            LinkedList<Integer> destinationClone1 = new LinkedList<>();

            int s1 = source.get(startFirst);
            int s2 = source.get(startEnd);

            destinationClone1.add(s1);

            boolean rc = isPossibleSortArr(source, destinationClone1, startFirst + 1, startEnd,cache);
            if (rc == true) {
                return true;
            }


            LinkedList<Integer> destinationClone2 = new LinkedList<>();

            destinationClone2.add(s2);

            return isPossibleSortArr(source, destinationClone2, startFirst, startEnd - 1,cache);

        }


        int sourceStart = source.get(startFirst);
        int sourceEnd = source.get(startEnd);

        int desintaionFirst = destination.getFirst();
        int destinationLast = destination.getLast();

        if (sourceStart > desintaionFirst && sourceStart < destinationLast) {
            return false;
        }

        if (sourceEnd > desintaionFirst && sourceEnd < destinationLast) {
            return false;
        }

        CacheEntry cacheEntry = new CacheEntry(startFirst,startEnd);
        if (cache.containsKey(cacheEntry)) {
            //System.out.println("Cache hit");
            return cache.get(cacheEntry);
        }

        if (sourceStart < desintaionFirst) {

            LinkedList<Integer> destinationClone = new LinkedList<>();

            destinationClone.addFirst(sourceStart);
            destinationClone.addLast(destinationLast);

            boolean rc = isPossibleSortArr(source, destinationClone, startFirst + 1, startEnd,cache);
            if (rc == true) {
                cache.put(cacheEntry,true);
                return true;
            }


        }else {

            if (sourceStart > destinationLast) {


                LinkedList<Integer> destinationClone = new LinkedList<>();

                destinationClone.addLast(sourceStart);
                destinationClone.addFirst(desintaionFirst);

                boolean rc = isPossibleSortArr(source, destinationClone, startFirst + 1, startEnd, cache);
                if (rc == true) {
                    cache.put(cacheEntry, true);
                    return true;
                }
            }
        }


        if (sourceEnd < desintaionFirst) {

            LinkedList<Integer> destinationClone = new LinkedList<>();

            destinationClone.addFirst(sourceEnd);
            destinationClone.addLast(destinationLast);


            boolean rc = isPossibleSortArr(source, destinationClone, startFirst, startEnd - 1,cache);
            if (rc == true) {
                cache.put(cacheEntry,true);
                return true;
            }
        }else {


            if (sourceEnd > destinationLast) {
                LinkedList<Integer> destinationClone = new LinkedList<>();

                destinationClone.addLast(sourceEnd);
                destinationClone.addFirst(desintaionFirst);

                boolean rc = isPossibleSortArr(source, destinationClone, startFirst, startEnd - 1, cache);
                if (rc == true) {
                    cache.put(cacheEntry, true);
                    return true;
                }
            }
        }


        //System.out.println("Its a failure for " + startFirst + " " + startEnd);

        cache.put(cacheEntry,false);
        return false;

    }


    public static void main(String[] args) throws IOException {
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int testCases = Integer.parseInt(line);
        for (int i = 0;i<testCases;++i) {
            //System.out.println("Inside index " + i);
            ArrayList<Integer> source = new ArrayList<>();
            LinkedList<Integer> destination = new LinkedList<>();
            line = br.readLine();
            int t = Integer.parseInt(line);
            line = br.readLine();
            String data[] = line.split(" ");
            for (int j = 0 ;j < data.length && j <t;++j) {
                //System.out.println("Addint j" + j);
                source.add(Integer.parseInt(data[j]));
            }

            //System.out.println("Starting now");
            HashMap<CacheEntry,Boolean> cacheEntryBooleanHashMap = new HashMap<>();
            System.out.println("Case #" + (i+1) + ": " +
                    (testClass.isPossibleSortArr(source, destination,
                            0,
                            source.size()-1,cacheEntryBooleanHashMap)?"yes":"no"));

        }


    }

}
