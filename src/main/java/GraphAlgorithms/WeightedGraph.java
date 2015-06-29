package GraphAlgorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by rasrivastava on 6/24/15.
 */
class WeightedEdge {
    private int nodeIndex;
    private int weight;

    public WeightedEdge(int nodeIndex, int weight) {
        this.nodeIndex = nodeIndex;
        this.weight = weight;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public int getWeight() {
        return weight;
    }
}

class WeightedNode {
    private LinkedList<WeightedEdge> weightedEdges = new LinkedList<>();

    void add(WeightedEdge weightedEdge) {
        weightedEdges.add(weightedEdge);
    }

    public LinkedList<WeightedEdge> getWeightedEdges() {
        return weightedEdges;
    }
}

class AdjacencyWeightedList implements Iterable<WeightedEdge> {

    private  LinkedList<WeightedEdge> weightedEdges;

    public AdjacencyWeightedList(LinkedList<WeightedEdge> weightedEdges) {
        this.weightedEdges = weightedEdges;
    }

    @Override
    public Iterator<WeightedEdge> iterator() {
        return weightedEdges.iterator();
    }
}


public class WeightedGraph {
    private ArrayList<WeightedNode> weightedNodeArrayList = new ArrayList<>();
    public WeightedGraph(int nodes) {
        createNodes(nodes);
    }



    final void createNodes(int nodes) {
        for (int i = 0;i<nodes;++i) {
            weightedNodeArrayList.add(new WeightedNode());
        }
    }

    int getVertices() {
        return weightedNodeArrayList.size();
    }
    void addEdge(int x,int y,int weight) {
        WeightedEdge weightedEdge = new WeightedEdge(y,weight);
        weightedNodeArrayList.get(x).add(weightedEdge);
    }

    Iterable<WeightedEdge> getAdj(int index) {
        return new AdjacencyWeightedList(weightedNodeArrayList.get(index).getWeightedEdges());
    }




}
