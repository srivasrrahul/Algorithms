package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Rahul on 11/20/15.
 */
public class LazyPrimMST {
    private ArrayList<Edge> mst = new ArrayList<>();
    public LazyPrimMST(EdgeWeightedUndirectedGraph edgeWeightedUndirectedGraph) {
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (Edge edge : edgeWeightedUndirectedGraph.getEdges()) {
            edges.add(edge);
        }

        HashSet<Integer> verticesReached = new HashSet<>();



        while (edges.isEmpty() == false) {
            Edge edge = edges.remove();
            int u = edge.getSourceIndex();
            int v = edge.getDestinationIndex();

            if (verticesReached.contains(u) && verticesReached.contains(v)) {
                System.out.println("Both are in tree " + u + " " + v);

            }else {
                System.out.println("Adding egde " + edge.getSourceIndex() + " " + edge.getDestinationIndex());
                mst.add(edge);
                if (!verticesReached.contains(u)) {
                    verticesReached.add(u);
                }

                if (!verticesReached.contains(v)) {
                    verticesReached.add(v);
                }
                //edges.remove();

            }
        }

    }

    Iterable<Edge> getMst() {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedUndirectedGraph edgeWeightedUndirectedGraph = new EdgeWeightedUndirectedGraph(8);

        edgeWeightedUndirectedGraph.addEdge(0,7,0.16);
        edgeWeightedUndirectedGraph.addEdge(2,3,0.17);
        edgeWeightedUndirectedGraph.addEdge(1,7,0.19);
        edgeWeightedUndirectedGraph.addEdge(0,2,0.26);
        edgeWeightedUndirectedGraph.addEdge(5,7,0.28);
        edgeWeightedUndirectedGraph.addEdge(1,3,0.29);
        edgeWeightedUndirectedGraph.addEdge(1,5,0.32);
        edgeWeightedUndirectedGraph.addEdge(2,7,0.34);
        edgeWeightedUndirectedGraph.addEdge(4,5,0.35);
        edgeWeightedUndirectedGraph.addEdge(1,2,0.36);
        edgeWeightedUndirectedGraph.addEdge(4,7,0.37);
        edgeWeightedUndirectedGraph.addEdge(0,4,0.38);
        edgeWeightedUndirectedGraph.addEdge(2,6,0.40);
        edgeWeightedUndirectedGraph.addEdge(3,6,0.52);
        edgeWeightedUndirectedGraph.addEdge(0,6,0.58);
        edgeWeightedUndirectedGraph.addEdge(4,6,0.58);

        LazyPrimMST lazyPrimMST = new LazyPrimMST(edgeWeightedUndirectedGraph);
        for (Edge edge: lazyPrimMST.getMst()) {
            System.out.println(edge.getSourceIndex() + "," + edge.getDestinationIndex() );
        }
    }
}
