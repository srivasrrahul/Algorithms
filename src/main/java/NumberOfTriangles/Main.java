package NumberOfTriangles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class Graph {


    Graph(int nodeCount) {
        for (int i = 0;i<nodeCount;++i) {
            neighBourList.add(new HashSet<Integer>());
        }
    }

    HashSet<Integer> getNeighbours(int nodeIndex) {
        return neighBourList.get(nodeIndex);
    }

    void addNeighBour(int sourceIndex,int destinationIndex) {
        neighBourList.get(sourceIndex).add(destinationIndex);
    }

    int getNodeSize() {
        return neighBourList.size();
    }

    private ArrayList<HashSet<Integer>> neighBourList = new ArrayList<>();
}

class GraphTriangle{
    GraphTriangle(int a,int b,int c) {
        hashSet.add(a);
        hashSet.add(b);
        hashSet.add(c);
    }

    private HashSet<Integer> hashSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphTriangle that = (GraphTriangle) o;

        if (!hashSet.equals(that.hashSet)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return hashSet.hashCode();
    }

    @Override
    public String toString() {
        String data = "";
        for (Integer u : hashSet) {
            data += " , ";
            data += u;
        }

        return data;
    }
}

class GraphAlgo {
    void getCount(Graph graph,int sourceIndex,
                 LinkedList<Integer> pathTillNow,
                 int currentIndex,
                 int pathRemaining,
                 HashSet<GraphTriangle> graphTriangles) {

        if (pathRemaining == 1) {
            for (Integer v : graph.getNeighbours(currentIndex)) {
                if (v == sourceIndex) {
                    pathTillNow.add(currentIndex);
                    GraphTriangle graphTriangle = new GraphTriangle(pathTillNow.get(0),
                            pathTillNow.get(1),
                            pathTillNow.get(2));

                    //System.out.println(graphTriangle.toString());
                    //System.out.println(graphTriangle.hashCode());
                    graphTriangles.add(graphTriangle);


                    return;
                }
            }

            return;
        }

        for (Integer v : graph.getNeighbours(currentIndex)) {
            LinkedList<Integer> clonedLst = (LinkedList<Integer>)pathTillNow.clone();
            clonedLst.add(currentIndex);
            getCount(graph, sourceIndex, clonedLst, v, pathRemaining - 1, graphTriangles);
        }
    }

    HashSet<GraphTriangle> getAllTrianges(Graph graph) {
        HashSet<GraphTriangle> hashSet = new HashSet<>();
        for (int i  = 0;i<graph.getNodeSize();++i) {
            LinkedList<Integer> linkedList = new LinkedList<>();
            getCount(graph, i, linkedList, i, 3, hashSet);
        }


        return hashSet;


    }
}
public class Main {

    void handleLine(String line) {
        String [] arr = line.split(";");
        int vertices = Integer.parseInt(arr[0].split(" ")[0]);
        Graph graph = new Graph(vertices);

        arr = arr[1].split(",");
        for (int i = 0;i<arr.length;++i) {
            String edge[] = arr[i].split(" ");
            int sourceIndex = Integer.parseInt(edge[0]);
            int destinationIndex = Integer.parseInt(edge[1]);
            if (sourceIndex != destinationIndex) {
                graph.addNeighBour(sourceIndex, destinationIndex);
                graph.addNeighBour(destinationIndex, sourceIndex);
            }
        }

        GraphAlgo graphAlgo = new GraphAlgo();
        System.out.println(graphAlgo.getAllTrianges(graph).size());

    }
    void readFile(String fileName) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
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
        main.readFile(args[0]);
    }
}
