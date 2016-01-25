package Graph;

import java.util.ArrayList;

/**
 * Created by rasrivastava on 11/20/15.
 */
public class EdgeWeightedUndirectedGraph {
    ArrayList<Edge> vertices[];
    ArrayList<Edge> edges = new ArrayList<>();
    public EdgeWeightedUndirectedGraph(int verticesCount) {
        vertices = new ArrayList[verticesCount];
        for (int i = 0;i<verticesCount;++i) {
            vertices[i] = new ArrayList<>();

        }
    }

//    void addEdge(int u,int v) {
//        //System.out.println(u + " " + v);
//        Edge edge = new Edge(u,v);
//        vertices[u].add(edge);
//        vertices[v].add(edge);
//        edges.add(edge);
//    }

    void addEdge(int u,int v,double weight) {
        Edge edge = new Edge(u,v,weight);
        vertices[u].add(edge);
        vertices[v].add(edge);
        edges.add(edge);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    int getVerticesCount() {
        return vertices.length;
    }

    ArrayList<Edge> getEdge(int u) {
        return vertices[u];
    }
}
