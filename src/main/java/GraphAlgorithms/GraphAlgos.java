package GraphAlgorithms;

import java.util.HashMap;
import java.util.HashSet;

class Triplet {
    private int x;
    private int y;
    private int z;

    public Triplet(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triplet triplet = (Triplet) o;

        if (x != triplet.x) return false;
        if (y != triplet.y) return false;
        if (z != triplet.z) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}

class TSPResult {
    private HashSet<Integer> vertexes;
    private int nodeIndex;

    public TSPResult(HashSet<Integer> vertexes, int nodeIndex) {
        this.vertexes = vertexes;
        this.nodeIndex = nodeIndex;
    }

    public HashSet<Integer> getVertexes() {
        return vertexes;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TSPResult tspResult = (TSPResult) o;

        if (nodeIndex != tspResult.nodeIndex) return false;
        if (vertexes != null ? !vertexes.equals(tspResult.vertexes) : tspResult.vertexes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vertexes != null ? vertexes.hashCode() : 0;
        result = 31 * result + nodeIndex;
        return result;
    }
}
public class GraphAlgos {
    static HashMap<Triplet,Integer> floydWarshall(WeightedGraph weightedGraph) {
        HashMap<Triplet,Integer> distances = new HashMap<>();
        for (int i = 0;i<weightedGraph.getVertices();++i) {
            for (int j = 0;j<weightedGraph.getVertices();++j) {
                distances.put(new Triplet(i,j,0),Integer.MAX_VALUE);
            }
        }

        for (int i = 0;i<weightedGraph.getVertices();++i) {
            Iterable<WeightedEdge> weightedEdges = weightedGraph.getAdj(i);
            for (WeightedEdge weightedEdge : weightedEdges) {
                distances.put(new Triplet(i,weightedEdge.getNodeIndex(),0),weightedEdge.getWeight());
            }
        }

        for (int k = 1;k<weightedGraph.getVertices();++k) {
            for (int i = 0;i<weightedGraph.getVertices();++i) {
                for (int j = 0;j<weightedGraph.getVertices();++j) {
                    Triplet dist = new Triplet(i,j,k);
                    int d1 = distances.get(new Triplet(i,j,k-1));
                    System.out.println(i + " " + j + " " + k);
                    if (distances.get(new Triplet(i,k,k-1)) == Integer.MAX_VALUE || distances.get(new Triplet(k,j,k-1)) == Integer.MAX_VALUE) {

                        distances.put(dist,d1);
                    }else {
                        int d2 = distances.get(new Triplet(i, k, k - 1)) + distances.get(new Triplet(k, j, k - 1));
                        distances.put(dist, Math.min(d1, d2));
                    }
                }
            }
        }

        return distances;
    }



    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph(6);
        weightedGraph.addEdge(0,1,1);
        weightedGraph.addEdge(0,5,2);
        weightedGraph.addEdge(1,2,6);
        weightedGraph.addEdge(2,3,2);
        weightedGraph.addEdge(2,4,1);
        weightedGraph.addEdge(4,3,1);
        weightedGraph.addEdge(5,1,4);
        weightedGraph.addEdge(5,4,3);

        HashMap<Triplet,Integer> distances = floydWarshall(weightedGraph);
        System.out.println("Result");
        for (int i = 0;i<weightedGraph.getVertices();++i) {
            for (int j = 0;j<weightedGraph.getVertices();++j) {
                System.out.println(i + " => " + j + " " + distances.get(new Triplet(i,j,weightedGraph.getVertices()-1)));
            }
        }
    }
}
