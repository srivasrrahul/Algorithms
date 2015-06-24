package KnapSack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Item {
    private int id;
    private int weight;
    private int val;

    public Item(int id, int weight, int val) {
        this.id = id;
        this.weight = weight;
        this.val = val;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", weight=" + weight +
                ", val=" + val +
                '}';
    }
}
public class Solution {

    static ArrayList<Item> cloneItems(ArrayList<Item> source) {
        ArrayList<Item> arrayList = new ArrayList<>();
        for (Item item : source) {
            arrayList.add(item);
        }

        return arrayList;
    }

    int getTotalWeight(Set<Item> items) {
        int w = 0;
        for (Item item : items) {
            w += item.getWeight();
        }

        return w;
    }

    int getTotalVal(ArrayList<Item> items) {
        int w = 0;
        for (Item item : items) {
            w += item.getVal();
        }

        return w;
    }

    HashMap<Integer,ArrayList<Item>> cache = new HashMap<>();
    ArrayList<Item> unlimitedKnapSack(HashSet<Item> itemHashSet,int W) {

        if (W == 0) {
            return new ArrayList<>();
        }

        //System.out.println("Inside stack " + W);
        if (cache.containsKey(W)) {
            return cloneItems(cache.get(W));
        }

        System.out.println("Inside stack " + W);

        int maxVal = 0;
        ArrayList<Item> retValue = new ArrayList<>();
        for (Item item : itemHashSet) {
            if (item.getWeight() <= W) {
                ArrayList<Item> items = unlimitedKnapSack(itemHashSet,W-item.getWeight());
                items = cloneItems(items);
                items.add(item);
                //System.out.println("For W " + W + "  " + items);
                if (getTotalVal(items) > maxVal) {
                    maxVal = getTotalVal(items);
                    retValue = items;
                }
            }
        }

        //System.out.println("Adding for W " + W + " " + retValue);
        cache.put(W,retValue);
        return retValue;
    }

    ArrayList<Item> limitedKnapSack(HashSet<Item> itemHashSet,HashSet<Integer> usedItems,int W) {
        int maxVal = 0;
        ArrayList<Item> retValue = new ArrayList<>();
        for (Item item : itemHashSet) {
            if (!usedItems.contains(item.getId())) {
                if (item.getWeight() <= W) {
                    usedItems.add(item.getId());
                    ArrayList<Item> items = limitedKnapSack(itemHashSet, usedItems,W - item.getWeight());
                    items.add(item);
                    if (getTotalVal(items) > maxVal) {
                        maxVal = getTotalVal(items);
                        retValue = items;
                    }
                    usedItems.remove(item.getId());
                }
            }
        }

        return retValue;
    }



    public static void main(String args[]) {

        Solution m = new Solution();
        HashSet<Item> items = new HashSet<>();
        items.add(new Item(1,6,30));
        items.add(new Item(2,3,14));
        items.add(new Item(3,4,16));
        items.add(new Item(4,2,9));

        HashSet<Integer> usedItems = new HashSet<>();
        //ArrayList<Item> set = m.limitedKnapSack(items,usedItems, 10);
        ArrayList<Item> set = m.unlimitedKnapSack(items, 10);
        for (Item item : set) {
            System.out.println(item.getId());
        }
    }
}
