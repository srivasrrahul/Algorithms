package GraphAlgorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



class ConnectedComponents {

    private int [] connectedComponents;
    private int count;


    public ConnectedComponents(Graph graph) {

        preprocess(graph);
    }

    boolean isConnected(int u,int v) {
        return connectedComponents[u] == connectedComponents[v];
    }

    int getCountofConnectedComponents() {
        return count;
    }

    int id(int u) {
        return connectedComponents[u];
    }

    void preprocess(Graph graph) {
        connectedComponents = new int[graph.getVertices()];
        int k = 1;
        for (int i = 0;i<graph.getVertices();++i) {

            if (connectedComponents[i] == 0) {

                ArrayList<Boolean> visited = GraphAlgo.bfsInterface(graph, i);

                for (int j = 0;j<graph.getVertices();++j) {
                    if (visited.get(j) == true) {
                        connectedComponents[j] = k;
                    }
                }

                ++k;

            }

        }

        count = k;
    }
}
//Its undirected graph
public class Graph {
    private ArrayList<Node> vertexList = new ArrayList<>();


    public Graph(int v) {
        for (int i = 0;i<v;++i) {
            vertexList.add(new Node(i));
        }
    }

    public Graph(String fileName) {

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
        vertexList.get(y).getNodeLinkedList().add(x);

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
        Graph graph = new Graph(args[0]);
        graph.printGraph();

        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0;i<graph.getVertices();++i) {
            visited.add(false);
        }

//        GraphAlgo.dfs(graph,visited,0);
//
//        for (int i = 0;i<visited.size();++i) {
//            System.out.println(i + " vertex " + visited.get(i));
//        }
//
//        System.out.println("================================");

//        for (int i = 0;i<graph.getVertices();++i) {
//            visited.add(false);
//        }

//        GraphAlgo.bfs(graph, visited, 12);
//
//        for (int i = 0;i<visited.size();++i) {
//            System.out.println(i + " vertex " + visited.get(i));
//        }

        ConnectedComponents connectedComponents = new ConnectedComponents(graph);
        System.out.println(connectedComponents.getCountofConnectedComponents());


    }


}
