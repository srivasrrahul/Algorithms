package Package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



class Item implements Comparable{
    @Override
    public int compareTo(Object o) {
        return Integer.compare(getIndex(),((Item)o).getIndex());
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Item(int index, double weight, double cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }


    private int index;
    private double weight;
    private double cost;


}

public class Main {

    double getCost(ArrayList<Item> items) {
        if (null == items) {
            return 0.0;
        }

       double total = 0.0;

        for (Item item : items) {
            total += item.getCost();
        }

        return total;
    }

    double getWeight(ArrayList<Item> items) {
        if (null == items) {
            return 0.0;
        }

        double total = 0.0;
        for (Item item : items) {
            total += item.getWeight();
        }

        return total;
    }

    class CacheEntry {

        public CacheEntry(int index, double totalWeightTillNow) {
            this.index = index;
            this.totalWeightTillNow = totalWeightTillNow;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CacheEntry cache = (CacheEntry) o;

            if (index != cache.index) return false;
            if (Double.compare(cache.totalWeightTillNow, totalWeightTillNow) != 0) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = index;
            temp = Double.doubleToLongBits(totalWeightTillNow);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public double getTotalWeightTillNow() {
            return totalWeightTillNow;
        }

        public void setTotalWeightTillNow(double totalWeightTillNow) {
            this.totalWeightTillNow = totalWeightTillNow;
        }

        private int index;
        private double totalWeightTillNow;

    }
    ArrayList<Item> getMaxCost(ArrayList<Item> items,double totalWeight,double totalWeightTillNow,int index,
                               HashMap<CacheEntry,ArrayList<Item>> cacheArrayListHashMap) {


        if (index == items.size()) {
            //Nothing left
            return new ArrayList<>();
        }

        CacheEntry cacheEntry = new CacheEntry(index,totalWeightTillNow);
        if (cacheArrayListHashMap.containsKey(cacheEntry)) {
            return cacheArrayListHashMap.get(cacheEntry);
        }

        //Take current as well
        double currentWeight = items.get(index).getWeight();
        ArrayList<Item> pendingItems1 = null;

        if (Double.compare(totalWeightTillNow+currentWeight,totalWeight) < 0) {
            pendingItems1 = getMaxCost(items,totalWeight,totalWeightTillNow+currentWeight,index+1,cacheArrayListHashMap);
            if (null == pendingItems1) {
                pendingItems1 = new ArrayList<>();
            }

            pendingItems1.add(items.get(index));
        }

        //Now don't take current
        ArrayList<Item> pendingItems2 = getMaxCost(items,totalWeight,totalWeightTillNow,index+1,cacheArrayListHashMap);

        double cost1 = getCost(pendingItems1);

        double cost2 = getCost(pendingItems2);


        if (Double.compare(cost1,cost2) == 0) {
            //Both are equal
            double w1 = getWeight(pendingItems1);
            double w2 = getWeight(pendingItems2);


            ArrayList<Item> itemRetValue =  Double.compare(w1,w2) <= 0? pendingItems1:pendingItems2;
            cacheArrayListHashMap.put(cacheEntry,itemRetValue);
            return itemRetValue;
        }


        ArrayList<Item>  itemRetValue1 = Double.compare(cost1, cost2) > 0?pendingItems1:pendingItems2;
        cacheArrayListHashMap.put(cacheEntry,itemRetValue1);
        return itemRetValue1;




    }

    void handleLine(String line) {
        String x[] = line.split(":");
        double totalWeight = Double.parseDouble(x[0]);
        x[1] = x[1].replace(" ",";");
        x[1] = x[1].replace("$","");
        String items[] = x[1].split(";");
        ArrayList<Item> itemsLst = new ArrayList<>();
        for (int i = 0;i<items.length;) {
            if (items[i].length() == 0) {
                ++i;
                continue;
            }

            items[i] = items[i].replace("(","");
            items[i] = items[i].replace(")","");
            String val[] = items[i].split(",");
            Item item = new Item(Integer.parseInt(val[0]),Double.parseDouble(val[1]),Double.parseDouble(val[2]));
            itemsLst.add(item);
            //System.out.println(items[i]);
            ++i;
        }



        HashMap<CacheEntry,ArrayList<Item>> cacheArrayListHashMap  = new HashMap<>();
        ArrayList<Item> answer = getMaxCost(itemsLst,totalWeight,0,0,cacheArrayListHashMap);

        if (null != answer && answer.size() > 0) {
            Collections.sort(answer);
            int i = 0;
            for (Item item : answer) {
                System.out.print(item.getIndex() + (i+1 == answer.size()?"":","));
                ++i;
            }
        }else {
            System.out.print("-");
        }

        System.out.println();



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
        //main.print();
        main.readFile(args[0]);
    }
}
