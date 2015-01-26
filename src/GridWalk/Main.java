package GridWalk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class CacheEntry {
    public CacheEntry(int x, int y) {
        this.x = x;
        this.y = y;
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

    int getSum(int a) {
        int s = 0;
        while (a > 0) {
            int m = a % 10;

            s += m;
            a = a - m;
            a = (a / 10);
        }

        return s;
    }

    boolean isAccessible() {
        //String xStr = String.valueOf(x);
        //long s = 0;
        //int absX = Math.abs(x);
         return (getSum(Math.abs(x)) + getSum(Math.abs(y))) <= 19;

//        for (Character character : xStr.toCharArray()) {
//
//            //System.out.println(s);
//            if (character >= '0' && character <= '9') {
//                //System.out.println(character);
//                s += Integer.valueOf(character - '0');
//                if (s > 19) {
//                    return false;
//                }
//            }
//        }
//
//        //System.out.println(s);
//
//        String yStr = String.valueOf(y);
//        for (Character character : yStr.toCharArray()) {
//            if (character >= '0' && character <= '9') {
//                //System.out.println(character);
//                s += Integer.valueOf(character  - '0');
//                if (s > 19) {
//                    return false;
//                }
//            }
//        }

        //System.out.println("S is " + s);
        //return s<=19;

    }

    @Override
    public String toString() {
        return "CacheEntry{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    private int x;
    private int y;
}

public class Main {
    void accessible(int x,int y,HashSet<CacheEntry> cycleDetector) {

        CacheEntry cacheEntry = new CacheEntry(x,y);


        if (cycleDetector.contains(cacheEntry)) {
            return;
        }


        if (false == cacheEntry.isAccessible())  {
            //System.out.println("not accessible");
            return;
        }

        cycleDetector.add(cacheEntry);
        accessible(x-1,y,cycleDetector);
        accessible(x+1,y,cycleDetector);
        accessible(x,y-1,cycleDetector);
        accessible(x,y+1,cycleDetector);

        return;

    }

    void accessible(int x,int y) {

        HashSet<CacheEntry> hashSet = new HashSet<>();
        hashSet.add(new CacheEntry(0,0));
        int i = 0;


        ArrayList<LinkedList<CacheEntry>> visited = new ArrayList<>();
        visited.add(new LinkedList<CacheEntry>());
        visited.get(0).add(new CacheEntry(0,0));


        while (visited.get(i).size() > 0) {
            visited.add(new LinkedList<CacheEntry>());

            for (CacheEntry u : visited.get(i)) {
                //if (false == hashSet.contains(u)) {

                    CacheEntry cacheEntryUp = new CacheEntry(u.getX() - 1, u.getY());
                    if (cacheEntryUp.isAccessible() && !hashSet.contains(cacheEntryUp)) {
                        hashSet.add(cacheEntryUp);
                        visited.get(i + 1).add(cacheEntryUp);
                    }

                    CacheEntry cacheEntryDown = new CacheEntry(u.getX() + 1, u.getY());
                    if (cacheEntryDown.isAccessible() && !hashSet.contains(cacheEntryDown)) {
                        hashSet.add(cacheEntryDown);
                        visited.get(i + 1).add(cacheEntryDown);
                    }

                    CacheEntry cacheEntryLeft = new CacheEntry(u.getX(), u.getY() - 1);
                    if (cacheEntryLeft.isAccessible() && !hashSet.contains(cacheEntryLeft)) {
                        hashSet.add(cacheEntryLeft);
                        visited.get(i + 1).add(cacheEntryLeft);
                    }

                    CacheEntry cacheEntryRight = new CacheEntry(u.getX(), u.getY() + 1);
                    if (cacheEntryRight.isAccessible() && !hashSet.contains(cacheEntryRight)) {
                        hashSet.add(cacheEntryRight);
                        visited.get(i + 1).add(cacheEntryRight);
                    }
                //}
            }

            i++;

        }


        System.out.println(hashSet.size());



    }

    public static void main(String args[]) {
        Main m = new Main();
        //HashMap<CacheEntry,Long> cacheEntryIntegerHashMap = new HashMap<>();
        //HashSet<CacheEntry> cycleDetector = new HashSet<>();
        m.accessible(0, 0);
        //System.out.println(cycleDetector.size());
    }
}
