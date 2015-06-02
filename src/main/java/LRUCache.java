import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rasrivastava on 4/29/15.
 */

public class LRUCache<KeyType,ValueType> extends LinkedHashMap<KeyType,ValueType>{
    private LinkedHashMap<KeyType,ValueType> linkedHashMap = new LinkedHashMap<>(16, (float) 0.75,true);


    public LRUCache(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor,true);
    }

    public LRUCache(int initialCapacity) {
        super(initialCapacity,(float)0.75,true);
    }

    public LRUCache() {
        super(15,(float)0.75,true);
    }

    protected LRUCache(Map<? extends KeyType, ? extends ValueType> m) {
        super(m);
    }

    public LRUCache(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    private static final int MAX_ITEMS = 4;

    @Override
    protected boolean removeEldestEntry(Map.Entry<KeyType, ValueType> eldest) {
        return size() > MAX_ITEMS;
    }

    public static void main(String[] args) {
        LRUCache<Integer,String> lruCache = new LRUCache<>();
        lruCache.put(1,"One");
        lruCache.put(2,"Two");
        lruCache.put(3,"Three");
        lruCache.put(4,"Four");
        lruCache.get(1);
        lruCache.get(2);
        lruCache.get(4);
        lruCache.put(5,"Four");
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));


    }
}
