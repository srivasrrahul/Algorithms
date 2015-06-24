package GraphAlgorithms;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DirectedGraph {
    private ArrayList<Node> vertexList = new ArrayList<>();


    public DirectedGraph(int v) {
        for (int i = 0;i<v;++i) {
            vertexList.add(new Node(i));
        }
    }

    public DirectedGraph(String fileName) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            line = bufferedReader.readLine();
            int vertices = Integer.parseInt(line);

            for (int i = 0;i<vertices;++i) {
                vertexList.add(new Node(i));
            }

            line = bufferedReader.readLine();
            int edges = Integer.parseInt(line);
            for (int i = 0;i<edges;++i) {
                line = bufferedReader.readLine();
                String [] edge = line.split(" ");
                int x = Integer.parseInt(edge[0]);
                int y = Integer.parseInt(edge[1]);
                //System.out.println("Added x y" + x + "  "  + y);
                addEdge(x,y);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    void addEdge(int x,int y) {
        for (Integer u : vertexList.get(x).getNodeLinkedList()) {
            if (u == y) {
                //Already added
                return;
            }
        }

        vertexList.get(x).getNodeLinkedList().add(y);


    }

    Iterable<Integer> adj(int x) {
        return new AdjacencyList(vertexList.get(x).getNodeLinkedList());
    }


    void printGraph() {
        for (Node node : vertexList) {
            for (Integer y : vertexList.get(node.getVal()).getNodeLinkedList()) {
                System.out.println("Edge " + node.getVal() + " " + y);
            }
        }
    }

    int getVertices() {
        return vertexList.size();
    }

    public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph(args[0]);
        directedGraph.printGraph();
        DAGAlgos.dfsDirected(directedGraph,0);


    }
}
