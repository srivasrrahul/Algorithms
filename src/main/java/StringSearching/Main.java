package StringSearching;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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
    private HashMap<CacheEntry,Boolean> hashMap = new HashMap<>();
    void updateEntry(CacheEntry cacheEntry,Boolean l) {
        hashMap.put(cacheEntry,l);
    }

    boolean isPresent(int x,int y) {
        return hashMap.containsKey(new CacheEntry(x,y));
    }

    boolean getValue(int x,int y) {
        return hashMap.get(new CacheEntry(x,y));
    }



}
public class Main {
    boolean search(String hayStack,String needle,int x,int y) {
//        if (cache.isPresent(x,y)) {
//            return cache.getValue(x,y);
//        }

        if (x == hayStack.length() && y < needle.length()) {
            //Haystack gone but needle left
            //cache.updateEntry(new CacheEntry(x,y),false);
            return false;
        }

        //All needle consumed
        if (y == needle.length()) {
            //cache.updateEntry(new CacheEntry(x,y),true);
            return true;
        }


        if (x == hayStack.length()-1 && y == needle.length()-1) {
            //cache.updateEntry(new CacheEntry(x,y),true);
            return hayStack.charAt(x) == needle.charAt(y);
        }


        // \* should be intepreted as regular string
        if (hayStack.charAt(x)  == '*' && needle.charAt(y) == '\\' && needle.charAt(y+1) == '*') {
            //System.out.println("Checking *");
            return search(hayStack,needle,x+1,y+2);

        }

        //System.out.println("X => " +  x + " Y => " + y);
        if (needle.charAt(y) == '*') {
            //Use current
            boolean b1 = search(hayStack,needle,x+1,y);
            if (b1 == true) {
                //cache.updateEntry(new CacheEntry(x,y),true);
                return true;
            }
            boolean b2 = search(hayStack,needle,x+1,y+1);
            if (b2 == true) {
                //cache.updateEntry(new CacheEntry(x,y),true);
                return true;
            }else {
                //cache.updateEntry(new CacheEntry(x,y),false);
                return false;
            }
        }else {
            if (hayStack.charAt(x) == needle.charAt(y)) {
                boolean b1 = search(hayStack,needle,x+1,y+1);
                //cache.updateEntry(new CacheEntry(x,y),b1);
                return b1;
                //return search(hayStack,needle,x+1,y+1);
            }else {
                boolean b1 = search(hayStack,needle,x+1,y);
                //cache.updateEntry(new CacheEntry(x,y),b1);
                return b1;

                //return search(hayStack,needle,x,y);
            }
        }
    }

    void handleLine(String line) {
        String arr[] = line.split(",");
        Cache cache = new Cache();
        System.out.println(search(arr[0],arr[1],0,0)?"true":"false");
        //System.out.println(search(arr[0],arr[1],0,0));
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

//    public static void main(String args[]) {
//        Main m = new Main();
//        System.out.println(m.search("Code\\*Eval", "Code\\*Eval", 0,0));
//    }
}
