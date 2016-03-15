package Acquire;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Rahul on 4/28/15.
 */
class Plot {
    private int length;
    private int width;

    public Plot(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}

class Group {
    private int maxLength;
    private int maxWidth;

    public int getMaxLength() {
        return maxLength;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

//    ArrayList<Plot> plots = new ArrayList<>();
//    ArrayList<Plot> getAllPlots() {
//        return plots;
//    }

    void addPlot(Plot plot) {
        if (plot.getLength() > maxLength) {
            maxLength = plot.getLength();
        }

        if (plot.getWidth() > maxWidth) {
            maxWidth = plot.getWidth();
        }

        //plots.add(plot);
    }

    int cost() {
        return maxLength*maxWidth;
    }

    Group createCopy() {
        Group group = new Group();
        group.maxLength = maxLength;
        group.maxWidth = maxWidth;
        return group;
    }






}

class Pair<T1,T2> {
    private T1 x;
    private T2 y;

    public Pair(T1 x, T2 y) {
        this.x = x;
        this.y = y;
    }

    public T1 getX() {
        return x;
    }

    public T2 getY() {
        return y;
    }
}


public class Main {
    Group updateGroup(Group group,Plot plot) {
        Group groupClone = group.createCopy();
        groupClone.addPlot(plot);
        return group;
    }
    ArrayList<Group> minCost(ArrayList<Plot> plots,int i) {
        if (i == 0) {
            Plot plot = plots.get(0);
            Group group = new Group();
            group.addPlot(plot);
            ArrayList<Group> groups = new ArrayList<>();
            return groups;
        }

        ArrayList<Group> groups = minCost(plots,i-1);
        Plot currentPlot = plots.get(i);
        Group minCostGroup = null;
        int minCost = Integer.MAX_VALUE;
        int index = 0;
        int lowCostIndex = 0;
        int currentCost = 0;
        for (Group group : groups) {
            currentCost += group.cost();



        }

        for (Group group : groups) {

            Group updateGroup = updateGroup(group,currentPlot);
            if (updateGroup.cost() < minCost) {
                minCost = updateGroup.cost();
                minCostGroup = updateGroup;
                lowCostIndex = index;

            }

            ++index;

        }

        int totalIfNoNew = Integer.MAX_VALUE;
        if (minCostGroup != null) {
            int netAdditionIfNoNew = minCostGroup.cost() - groups.get(lowCostIndex).cost();
            totalIfNoNew = currentCost + minCostGroup.cost() - groups.get(lowCostIndex).cost();
        }

        Group selfGroup = new Group();
        selfGroup.addPlot(currentPlot);

        int totalIfNew  = currentCost + selfGroup.cost();


        if (totalIfNew <= totalIfNoNew) {
            groups.add(selfGroup);
        }else {
            groups.set(lowCostIndex,minCostGroup);
        }

        return groups;

    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            int n = Integer.parseInt(line);


            ArrayList<Plot> plots = new ArrayList<>();
            for (int i = 0;i<n;++i) {
                line = br.readLine();
                String rectangle[] = line.split(" ");
                Plot plot = new Plot(Integer.parseInt(rectangle[0]),Integer.parseInt(rectangle[0]));
                plots.add(plot);
            }

            ArrayList<Group> groups = minCost(plots,plots.size()-1);
            int cost = 0;
            for (int i = 0;i<groups.size();++i) {
                cost += groups.get(i).cost();
            }

            System.out.println(cost);
//            while ((line = br.readLine()) != null) {
//
//
//            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(null);
    }
}
