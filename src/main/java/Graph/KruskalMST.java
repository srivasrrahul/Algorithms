package Graph;

import UnionFind.UnionFind;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rasrivastava on 11/20/15.
 */
public class KruskalMST {
    private ArrayList<Edge> mst = new ArrayList<>();

    public KruskalMST(EdgeWeightedUndirectedGraph edgeWeightedUndirectedGraph) {
        ArrayList<Edge> edges = edgeWeightedUndirectedGraph.getEdges();
        Collections.sort(edges);
        //UnionFind unionFind = new UnionFind(edgeWeightedUndirectedGraph.getVerticesCount());
        UnionFind unionFind = new UnionFind(edgeWeightedUndirectedGraph.getVerticesCount());

        for (Edge e : edges) {
            System.out.println(e.getWeight());
            if (mst.size() >= edgeWeightedUndirectedGraph.getVerticesCount()-1) {
                break;
            }

            int u = e.getSourceIndex();
            int v = e.getDestinationIndex();


            if (unionFind.connected(u,v) == false) {
                System.out.println("mergeing " + u + " " + v);
                //Not from same set
                unionFind.union(u,v);
                mst.add(e);
            }

        }
    }

    public Iterable<Edge> getMst() {
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


        KruskalMST kruskalMST = new KruskalMST(edgeWeightedUndirectedGraph);
        for (Edge edge: kruskalMST.getMst()) {
            System.out.println(edge.getSourceIndex() + "," + edge.getDestinationIndex() );
        }



    }
}
