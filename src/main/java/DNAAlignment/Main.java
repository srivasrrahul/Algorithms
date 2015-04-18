package DNAAlignment;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class CacheEntry {
    private int x;


    public CacheEntry(int x, int y, boolean previousIndel) {
        this.x = x;
        this.y = y;
        this.previousIndel = previousIndel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheEntry that = (CacheEntry) o;

        if (previousIndel != that.previousIndel) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (previousIndel ? 1 : 0);
        return result;
    }

    private int y;
    boolean previousIndel;
    CacheEntry() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isPreviousIndel() {
        return previousIndel;
    }

    public void setPreviousIndel(boolean previousIndel) {
        this.previousIndel = previousIndel;
    }
}

class Cache {
    private HashMap<CacheEntry,Integer> cacheEntryIntegerHashMap = new HashMap<>();
    boolean isPresent(CacheEntry cacheEntry) {
        return cacheEntryIntegerHashMap.containsKey(cacheEntry);
    }

    Integer getValue(CacheEntry cacheEntry) {
        return cacheEntryIntegerHashMap.get(cacheEntry);
    }

    void setValue(CacheEntry cacheEntry,int val) {
        cacheEntryIntegerHashMap.put(cacheEntry,val);
    }


}

public class Main {
    public static final int INDEL = -8;
    int getCurrentIndel(boolean previousIndel) {
        if (previousIndel) {
            return -1;
        }else {
            return -8;
        }
    }
    int getValue(String string1,String string2,int x,int y) {
        if (string1.charAt(x) == string2.charAt(y)) {
            return 3;
        }else {
            return -3;
        }

    }
    int alignment(String string1,String string2,int x,int y,boolean previousIndel) {

        if (x >= string1.length() || y >= string2.length()) {
            return 0;
        }

        if (x >= string1.length()) {
            //Only string2 is pending
            return getCurrentIndel(previousIndel) + alignment(string1,string2,x,y+1,true);
        }

        if (y >= string2.length()) {
            //Only string1 is pending
            return getCurrentIndel(previousIndel) + alignment(string1,string2,x+1,y,true);
        }

        //Get Alignment if both are taken
        int currentValue = getValue(string1,string2,x,y);
        currentValue += alignment(string1,string2,x+1,y+1,false); //At least we managed to pick up current

        //Assume second doesn't exist
        int currentValue1 =getCurrentIndel(previousIndel);
        currentValue1 += alignment(string1,string2,x+1,y,true);

        //Assume first Value doesn't exist
        int currentValue2 = getCurrentIndel(previousIndel);
        currentValue2 += alignment(string1,string2,x,y+1,true);


        return Math.max(Math.max(currentValue, currentValue1), currentValue2);

    }

    int alignmentCached(String string1,String string2,int x,int y,boolean previousIndel,Cache cache) {

        //System.out.println("Inside alignment cached " + x + " , " + y + " , " + previousIndel);
        if (x >= string1.length() && y >= string2.length()) {
            return 0;
        }

        CacheEntry cacheEntry = new CacheEntry(x,y,previousIndel);
        if (cache.isPresent(cacheEntry)) {
            return cache.getValue(cacheEntry);
        }

        if (x >= string1.length()) {
            //Only string2 is pending
            int v1 = getCurrentIndel(previousIndel) + alignmentCached(string1, string2, x+1, y + 1, true, cache);
            cache.setValue(cacheEntry,v1);
            return v1;
        }

        if (y >= string2.length()) {
            //Only string1 is pending
            int v2 =  getCurrentIndel(previousIndel) + alignmentCached(string1, string2, x + 1, y, true, cache);
            cache.setValue(cacheEntry,v2);
            return v2;
        }

        //Get Alignment if both are taken
        int currentValue = getValue(string1,string2,x,y);
        currentValue += alignmentCached(string1, string2, x + 1, y + 1, false, cache); //At least we managed to pick up current

        //Assume second doesn't exist
        int currentValue1 =getCurrentIndel(previousIndel);
        currentValue1 += alignmentCached(string1, string2, x + 1, y, true, cache);

        //Assume first Value doesn't exist
        int currentValue2 = getCurrentIndel(previousIndel);
        currentValue2 += alignmentCached(string1, string2, x, y + 1, true, cache);


        //return Math.max(Math.max(currentValue,currentValue1),currentValue2);
        int finalValue = Math.max(Math.max(currentValue,currentValue1),currentValue2);
        cache.setValue(cacheEntry,finalValue);
        return finalValue;


    }

    void handleLine(String line) {
        String [] arr = line.split("\\|");
        Cache cache = new Cache();
        arr[0] = arr[0].replaceAll("\\s+|[^ACTG]", "");
        arr[1] = arr[1].replaceAll("\\s+|[^ACTG]", "");
        //System.out.println(arr[0]);
        //System.out.println(arr[1]);

        System.out.println(alignmentCached(arr[0],arr[1],0,0,false,cache));
    }
    void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                handleLine(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.readFile(args[0]);
    }

}
