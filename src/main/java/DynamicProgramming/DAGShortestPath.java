package DynamicProgramming;



import Graph.Digraph;

/**
 * Created by rasrivastava on 10/24/15.
 */

class NoSuchPath extends Exception{
    public NoSuchPath(String message) {
        super(message);
    }

    public NoSuchPath(String message, Throwable cause) {
        super(message, cause);
    }
}
public class DAGShortestPath {
    int shortestPath(Graph.Digraph graph, int u,int v) throws NoSuchPath{
        if (u == v) {
            return 0;
        }

        Iterable<Integer> adjList = graph.adj(u);
        int cost = Integer.MAX_VALUE;
        for (int neighbour : adjList) {
            try {
                int nextCost = shortestPath(graph, neighbour, v);
                if (nextCost + 1 < cost) {
                    cost = nextCost+1;
                }
            }catch (NoSuchPath e) {
                //Ignore as only of type no such path is thrown
            }
        }

        if (cost == Integer.MAX_VALUE) {
            throw new NoSuchPath("No Such path from " + u);
        }

        return cost;
    }

    public static void main(String[] args) throws NoSuchPath {
        Digraph dag = new Digraph(5);
        dag.addEdge(0,1);
        dag.addEdge(0,2);
        dag.addEdge(1,2);
        dag.addEdge(1,4);
        dag.addEdge(2,3);
        dag.addEdge(3,4);

        DAGShortestPath dagShortestPath = new DAGShortestPath();
        System.out.println(dagShortestPath.shortestPath(dag,0,4));
    }
}
