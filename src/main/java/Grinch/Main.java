package Grinch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by Rahul on 4/28/15.
 */
class WeightedNode {

    private int nodeSourceIndex;
    private int nodeDestIndex;
    private int pathWeight;

    public WeightedNode(int nodeSourceIndex, int nodeDestIndex, int pathWeight) {
        this.nodeSourceIndex = nodeSourceIndex;
        this.nodeDestIndex = nodeDestIndex;
        this.pathWeight = pathWeight;
    }

    public int getNodeSourceIndex() {
        return nodeSourceIndex;
    }

    public int getNodeDestIndex() {
        return nodeDestIndex;
    }

    public int getPathWeight() {
        return pathWeight;
    }

    @Override
    public String toString() {
        return "WeightedNode{" +
                "nodeSourceIndex=" + nodeSourceIndex +
                ", nodeDestIndex=" + nodeDestIndex +
                ", pathWeight=" + pathWeight +
                '}';
    }
}

class Graph {
    private HashMap<Integer,ArrayList<WeightedNode>> nodes = new HashMap<Integer,ArrayList<WeightedNode>>();;



    Graph() {

    }

    Iterable<Integer> getAllVertices() {
        return nodes.keySet();
    }

    void addWeightedNode(WeightedNode weightedNode) {
        int source = weightedNode.getNodeSourceIndex();
        if (nodes.containsKey(source)) {
            nodes.get(source).add(weightedNode);
        }else {
            ArrayList<WeightedNode> weightedNodes = new ArrayList<>();
            weightedNodes.add(weightedNode);
            nodes.put(source,weightedNodes);
        }

        if (false == nodes.containsKey(weightedNode.getNodeDestIndex())) {
            nodes.put(weightedNode.getNodeDestIndex(),new ArrayList<>());
        }
    }
    void addNeighbour(int index,WeightedNode weightedNode) {
        nodes.get(index).add(weightedNode);
    }

    ArrayList<WeightedNode> getNeighbours(int index) {
        return nodes.get(index);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer u : nodes.keySet()) {
            stringBuilder.append(nodes.get(u).toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
class PQKey implements Comparable<PQKey>{
    private int index;

    public int getIndex() {
        return index;
    }

    public int getWeight() {
        return weight;
    }

    private int weight;

    public PQKey(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }



    @Override
    public int compareTo(PQKey o) {
        return Integer.compare(weight,o.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PQKey pqKey = (PQKey) o;

        return index == pqKey.index;

    }

    @Override
    public int hashCode() {
        return index;
    }
}
class ShortestPath {
    ShortestPath(int source,Graph graph) {
        for (Integer vertexId : graph.getAllVertices()) {
            //adding inside vertex
            //System.out.println("Adding vertex " + vertexId);
            weights.put(vertexId,Integer.MAX_VALUE);
        }


        if (false == weights.containsKey(source)) {
            throw new IllegalArgumentException("No such vertex exists " + source);
        }
        weights.put(source,0);
        PriorityQueue<PQKey> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new PQKey(source,0));
        while (!priorityQueue.isEmpty()) {
            PQKey pqKey = priorityQueue.remove();
            //System.out.println("Removed from PQ " + pqKey.getIndex());
            dijkstra(pqKey.getIndex(),graph,priorityQueue);
        }
    }

    int distTo(int destination) {
        if (weights == null) {
            //System.out.println("weights are null");
        }

        //System.out.println(weights.containsKey(destination));
        return weights.get(destination);
    }

    void dijkstra(int source,Graph graph,PriorityQueue<PQKey> priorityQueue) {

        //System.out.println("For source " + source);
        ArrayList<WeightedNode> weightedNodes = graph.getNeighbours(source);
        for (WeightedNode weightedNode : weightedNodes) {
            int v = weightedNode.getNodeDestIndex();
            if (weights.get(v) > (weights.get(source) + weightedNode.getPathWeight())) {
                weights.put(v, weights.get(source) + weightedNode.getPathWeight());
                PQKey pqKey = new PQKey(v,weights.get(source) + weightedNode.getPathWeight());
                priorityQueue.remove(pqKey);
                priorityQueue.add(pqKey);
            }
        }
    }




    private HashMap<Integer,Integer> weights = new HashMap<>();

}
public class Main {
    void hadleLine(String x) {
        //System.out.println(x);
        String y[] = x.split("\\|");
        //System.out.println(y[1]);
        String z[] = y[0].split(",");

        Graph graph = new Graph();
        for (String path : z) {
            //System.out.println("Path is " + path);
            String w[] = path.split(" ");
            if (w.length == 3) {
                String s1 = w[0].trim();
                String s2 = w[1].trim();
                String p = w[2].trim();
                //System.out.println(s1 + " " + s2 + " " + p);
                WeightedNode weightedNode = new WeightedNode(Integer.parseInt(s1), Integer.parseInt(s2), Integer.parseInt(p));
                WeightedNode weightedNode1 = new WeightedNode(Integer.parseInt(s2), Integer.parseInt(s1), Integer.parseInt(p));
                graph.addWeightedNode(weightedNode);
                graph.addWeightedNode(weightedNode1);
            }else {
                String s1 = w[1].trim();
                String s2 = w[2].trim();
                String p = w[3].trim();
                //System.out.println(s1 + " " + s2 + " " + p);
                WeightedNode weightedNode = new WeightedNode(Integer.parseInt(s1), Integer.parseInt(s2), Integer.parseInt(p));
                WeightedNode weightedNode1 = new WeightedNode(Integer.parseInt(s2), Integer.parseInt(s1), Integer.parseInt(p));
                graph.addWeightedNode(weightedNode);
                graph.addWeightedNode(weightedNode1);
                //System.out.println(weightedNode);
            }

        }

        //System.out.println(graph.toString());
        String shortestPaths[] = y[1].split(" ");
        //System.out.println(y[1]);
//        System.out.println(shortestPaths[1] + " " + shortestPaths[2]);
        int u = Integer.parseInt(shortestPaths[1].trim());
        int v = Integer.parseInt(shortestPaths[2].trim());

        try {
            ShortestPath shortestPath = new ShortestPath(u, graph);
            int d = shortestPath.distTo(v);
            if (d == Integer.MAX_VALUE) {
                System.out.println("False");
            } else {

                System.out.println(d);
            }
        }catch (Exception e) {
            //System.out.println("Exception + False " + e.getMessage());
            System.out.println("False");
        }


    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {

                hadleLine(line);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(args[0]);
    }
}
