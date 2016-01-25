package Graph;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by rasrivastava on 11/21/15.
 */

class Index implements Comparable<Index> {
    private int index;
    private double data;

    public Index(int index, double data) {
        this.index = index;
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public double getData() {
        return data;
    }


    @Override
    public int compareTo(Index o) {
        return Double.compare(data,o.getData());
    }
}
public class Dijkstra {
    private Edge [] edgeTo;
    private double[] distanceTo;
    private PriorityQueue<Index> priorityQueue = new PriorityQueue<>();
    public Dijkstra(EdgeWeightedDigraph digraph,int source) {
        edgeTo = new Edge[digraph.getVerticesCount()];
        distanceTo = new double[digraph.getVerticesCount()];


        for (int i = 0;i<digraph.getVerticesCount();++i) {
            distanceTo[i] = Double.POSITIVE_INFINITY;
        }

        distanceTo[source] = 0.0;
        priorityQueue.add(new Index(source,0.0));
        edgeTo[source] = null;

        while (priorityQueue.isEmpty() == false) {
            Index index = priorityQueue.remove();
            for (Edge edge : digraph.getEdge(index.getIndex())) {
                relax(edge);
            }
        }

    }

    private void relax(Edge edge) {
        if (distanceTo[edge.getDestinationIndex()] > distanceTo[edge.getSourceIndex()] + edge.getWeight()) {
            distanceTo[edge.getDestinationIndex()] = distanceTo[edge.getSourceIndex()] + edge.getWeight();
            edgeTo[edge.getDestinationIndex()] = edge;
            priorityQueue.add(new Index(edge.getDestinationIndex(),distanceTo[edge.getDestinationIndex()]));
        }
    }

    public Edge[] getEdgeTo() {
        return edgeTo;
    }

    public double[] getDistanceTo() {
        return distanceTo;
    }

    double distTo(int v) {
        return distanceTo[v];
    }

    boolean hasPathTo(int v) {
        return Double.compare(distanceTo[v],Double.POSITIVE_INFINITY) != 0;
    }

    Iterable<Edge> pathTo(int v) {
        Stack<Edge> edgeStack = new Stack<>();
        for (Edge edge = edgeTo[v];edge != null ; edge = edgeTo[edge.getSourceIndex()]) {
            edgeStack.push(edge);
        }

        return edgeStack;
    }

    public static void main(String[] args) throws IOException {

        EdgeWeightedDigraph weightedDigraph = Util.constructEdgeWeightedDirectedGraph(args[0]);
        Dijkstra dijkstra = new Dijkstra(weightedDigraph,0);

        for (int i = 1;i<weightedDigraph.getVerticesCount();++i) {
            System.out.println("=====For "+  i + " =======");
            double distance = 0.0;
            for (Edge edge : dijkstra.pathTo(i)) {
                distance += edge.getWeight();
                System.out.println(edge);
            }

            System.out.println("Total distance " + distance);

            System.out.println("================");
        }
    }
}
