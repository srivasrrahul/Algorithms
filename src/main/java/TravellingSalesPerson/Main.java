package TravellingSalesPerson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Rahul on 4/28/15.
 */
class WeightedEdge {
    private int weight;
    private int source;
    private int dest;

    public int getSource() {
        return source;
    }

    public int getDest() {
        return dest;
    }

    public WeightedEdge(int weight, int source, int dest) {
        this.weight = weight;
        //source < dest
        if (source < dest) {
            this.source = source;
            this.dest = dest;
        }else {
            this.dest = source;
            this.source = dest;
        }
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeightedEdge that = (WeightedEdge) o;


        if (source != that.source) return false;
        return dest == that.dest;

    }

    @Override
    public int hashCode() {

        int result = source;
        result = 31 * result + dest;
        return result;
    }
}

class Graph {
    HashMap<Integer,HashSet<WeightedEdge>> vertices = new HashMap<>();
    void addEdge(WeightedEdge weightedEdge) {
        if (vertices.containsKey(weightedEdge.getSource())) {
            vertices.get(weightedEdge.getSource()).add(weightedEdge);
        }else {
            HashSet<WeightedEdge> weightedEdges = new HashSet<>();
            weightedEdges.add(weightedEdge);
            vertices.put(weightedEdge.getSource(),weightedEdges);
        }

        if (vertices.containsKey(weightedEdge.getDest())) {
            vertices.get(weightedEdge.getDest()).add(weightedEdge);
        }else {
            HashSet<WeightedEdge> weightedEdges = new HashSet<>();
            weightedEdges.add(weightedEdge);
            vertices.put(weightedEdge.getDest(),weightedEdges);
        }
    }

    Iterable<WeightedEdge> getNeighbours(int vertex) {
        return vertices.get(vertex);
    }

    TreeSet<Integer> getVertices() {
        TreeSet<Integer> verticesArr = new TreeSet<>();
        for (int x : vertices.keySet()) {
            verticesArr.add(x);
        }

        return verticesArr;
    }

}

class TSP {
    ArrayList<Integer> getPendingVertices(TreeSet<Integer> vertices,int current) {
        ArrayList<Integer> pendingVertices = new ArrayList<>();
        for (int v : vertices) {
            if (v < current) {
                pendingVertices.add(v);
            }else {
                break;
            }
        }

        return pendingVertices;
    }

    HashSet<HashSet<Integer>> getSubSets(ArrayList<Integer> vertices,int i) {
        if (i == vertices.size()-1) {
            HashSet<Integer> combinations = new HashSet<>();
            combinations.add(vertices.get(i));
            HashSet<HashSet<Integer>> retValue = new HashSet<>();
            retValue.add(combinations);
            retValue.add(new HashSet<>());
            return retValue;
        }

        HashSet<HashSet<Integer>> subsets = getSubSets(vertices,i+1);
        HashSet<HashSet<Integer>> retValue = new HashSet<>();
        for (HashSet<Integer> subset : subsets) {
            HashSet<Integer> extendedValue = (HashSet<Integer>) subset.clone();
            extendedValue.add(vertices.get(i));
        }

        retValue.addAll(subsets);
        return retValue;


    }
    int getCurrentPathLength(HashSet<WeightedEdge> pathTillNow) {
        int pathLength = 0;
        for (WeightedEdge weightedEdge : pathTillNow) {
            pathLength += weightedEdge.getWeight();
        }

        return pathLength;
    }

    int pathExistsThenWeight(int current,int u,Graph graph) {
        for (WeightedEdge weightedEdge : graph.getNeighbours(current)) {
            if (weightedEdge.getSource() == u || weightedEdge.getSource() == u) {
                return weightedEdge.getWeight();
            }
        }

        return Integer.MAX_VALUE;
    }

    int pathExists(int current,int u,int v,Graph graph) {
        int p1 = pathExistsThenWeight(current,u,graph);
        int p2 = pathExistsThenWeight(current,v,graph);
        if (p1 != Integer.MAX_VALUE || p2 != Integer.MAX_VALUE) {
            return p1+p2;
        }

        return Integer.MAX_VALUE;
    }

    int updateEdges(HashSet<Integer> subset,int current,HashSet<WeightedEdge> pathTillNow,Graph graph) {
        if (subset.size() == 0) {
            return Integer.MAX_VALUE;
        }

        int currentPathLength = getCurrentPathLength(pathTillNow);
        if (subset.size() == 1) {
            int v = subset.iterator().next();
            for (WeightedEdge weightedEdge : graph.getNeighbours(current)) {
                if (weightedEdge.getDest() == v || weightedEdge.getSource() == v) {
                    //path exists
                    return currentPathLength + weightedEdge.getWeight();
                }
            }

            return Integer.MAX_VALUE;
        }

        //For more than 1
        for (int x : subset) {
            int path = currentPathLength;

            for (WeightedEdge weightedEdge : pathTillNow) {
                if (weightedEdge.getSource() == x || weightedEdge.getDest() == x) {
                    int alternate = (weightedEdge.getSource() == x) ? weightedEdge.getDest() : weightedEdge.getSource();

                    int pathLength = pathExists(current, x, alternate, graph);
                    if (pathLength != Integer.MAX_VALUE) {

                    }

                }
            }

        }

        return 0;
    }


    int calculate(Graph graph) {
        TreeSet<Integer> vertices  = graph.getVertices();
        for (int vertex : vertices) {

        }

        return 0;
    }

}
public class Main {

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {


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
