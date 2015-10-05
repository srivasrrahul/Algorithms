package Trip;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class CacheEntry {
    private int x;
    private int y;


    CacheEntry(int x,int y) {
        this.x = x;
        this.y = y;
    }



    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheEntry that = (CacheEntry) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}



class Cache {
    private HashMap<CacheEntry,Set<String>> hashMap = new HashMap<>();
    void updateEntry(CacheEntry cacheEntry,Set<String> l) {
        hashMap.put(cacheEntry,l);
    }

    boolean isPresent(int x,int y) {
        return hashMap.containsKey(new CacheEntry(x,y));
    }

    Set<String> getValue(int x,int y) {
        return hashMap.get(new CacheEntry(x,y));
    }

}



public class Main {

    public Set<String> lcs(String string1,String string2,int x,int y, Cache cache) {
        if (x == string1.length() || y== string2.length()) {
            return new HashSet<>();
        }

        if (cache.isPresent(x,y)) {
            return cache.getValue(x,y);
        }

        CacheEntry cacheEntry = new CacheEntry(x,y);
        char ch1 = string1.charAt(x);
        char ch2 = string2.charAt(y);

        Set<String> arr[] = new HashSet[3];
        arr[0] = new HashSet<>();
        arr[1] = new HashSet<>();
        arr[2] = new HashSet<>();

        if (ch1 == ch2) {
            Set<String> temp = lcs(string1,string2,x+1,y+1,cache);
            //arr[0] = Character.toString(ch1) + arr[0];
            for (String val : temp) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(ch1);
                stringBuilder.append(val);
                arr[0].add(stringBuilder.toString());
            }

            if (arr[0].size() == 0) {
                arr[0].add(String.valueOf(ch1));
            }
        }

        arr[1] = lcs(string1,string2,x+1,y,cache);
        arr[2] = lcs(string1,string2,x,y+1,cache);


        ArrayList<String> updatedValues = new ArrayList<>();
        updatedValues.addAll(arr[0]);
        updatedValues.addAll(arr[1]);
        updatedValues.addAll(arr[2]);

        Collections.sort(updatedValues, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });

        Collections.reverse(updatedValues);

        if (updatedValues.size() > 0) {
            int maxLength = updatedValues.get(0).length();
            HashSet<String> returnValue = new HashSet<>();
            for (String val : updatedValues) {
                if (val.length() < maxLength) {
                    break;
                }

                returnValue.add(val);

            }

            cache.updateEntry(cacheEntry,returnValue);
            return returnValue;
        }else {
            HashSet<String> returnValue = new HashSet<>();
            cache.updateEntry(cacheEntry,returnValue);
            return returnValue; //Empty
        }


    }

    Set<String> getTrip(String x1,String x2,int i,int j) {
        Cache cache = new Cache();
        return lcs(x1,x2,i,j,cache);

    }


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String lineStr = bufferedReader.readLine();
        int val = Integer.parseInt(lineStr);
        for (int i = 0;i<val;++i) {
            String x = bufferedReader.readLine();
            String y = bufferedReader.readLine();
            Set<String> retValue = main.getTrip(x,y,0,0);
            ArrayList<String> sortedLst = new ArrayList<>();
            for (String v : retValue) {
                sortedLst.add(v);
                //System.out.println(v);
            }

            Collections.sort(sortedLst);

            for (String u : sortedLst) {
                System.out.println(u);
            }
        }



//        TreeSet<String> val = main.getTrip("abcabcaa","acbacba",0,0);
//        TreeSet<String> maxValues = new TreeSet<>();
//
//        Iterator<String> iterator = val.descendingIterator();
//        while (iterator.hasNext()) {
//            String value = iterator.next();
//            if (maxValues.size() == 0) {
//                maxValues.add(value);
//            }else {
//                if (maxValues.iterator().next().length() > value.length()) {
//                    //break;
//                }else {
//                    maxValues.add(value);
//                }
//            }
//        }
//
//        for (String v : maxValues) {
//            System.out.println(v);
//        }

    }
}
