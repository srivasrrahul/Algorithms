package LCS;

import java.util.HashMap;

/**
 * Created by rasrivastava on 7/3/15.
 */
public class Cache {
    private HashMap<CacheEntry,String> hashMap = new HashMap<>();
    void updateEntry(CacheEntry cacheEntry,String l) {
        hashMap.put(cacheEntry,l);
    }

    boolean isPresent(int x,int y) {
        return hashMap.containsKey(new CacheEntry(x,y));
    }

    String getValue(int x,int y) {
        return hashMap.get(new CacheEntry(x,y));
    }



}
