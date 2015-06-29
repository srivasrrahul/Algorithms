package GraphAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rasrivastava on 6/23/15.
 */
public class DAGAlgos {
    public ArrayList<Integer> topologicalSort(Graph graph) {
        return null;
    }

    static List<Integer> dfsDirected(DirectedGraph graph,ArrayList<Boolean> visited,int startVertex) {
        visited.set(startVertex,true);
        Iterable<Integer> adjLst = graph.adj(startVertex);
        List<Integer> returnValue = new LinkedList<>();
        for (Integer v : adjLst) {
            if (visited.get(v) == true) {
                //skip this data
            }else {
                visited.set(v,true);
                List<Integer> localValue = dfsDirected(graph, visited, v);
                returnValue.addAll(localValue);
            }
        }

        returnValue.add(0,startVertex);
        return returnValue;

    }

    static ArrayList<Boolean> dfsDirected(DirectedGraph graph,int startVertex) {
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0;i<graph.getVertices();++i) {
            visited.add(false);
        }

        List<Integer> lst = dfsDirected(graph,visited,0);
        System.out.println(lst);
        return visited;
    }

    static int shortestPathsDP(WeightedGraph dag,int source,int dest) {
        if (source == dest) {
            return 0;
        }
        System.out.println(dest);
        //Iterate through all nodes and check which one of them point to dest
        int minPath = Integer.MAX_VALUE;
        int vertices = dag.getVertices();
        for (int i = 0;i<vertices;++i) {
            Iterable<WeightedEdge> weightedEdges = dag.getAdj(i);
            for (WeightedEdge weightedEdge : weightedEdges) {
                if (weightedEdge.getNodeIndex() == dest) {
                    int localMinPath = shortestPathsDP(dag,source,i) ;
                    if (localMinPath == Integer.MAX_VALUE) {
                        //No path
                        continue;
                    }
                    localMinPath += weightedEdge.getWeight() ;
                    if (localMinPath < minPath) {
                        minPath = localMinPath;
                    }
                }
            }
        }


        //System.out.println("Value returned for dest "+ dest + " = " + minPath);
        return minPath;
    }


    static int shortestPathsDPWithEdgeCount(WeightedGraph dag,int source,int dest,int numberOfEdges) {
        if (source == dest) {
            return 0;
        }

        if (numberOfEdges == 0) {
            //No pending path exists
            return Integer.MAX_VALUE;
        }

        System.out.println(dest);
        //Iterate through all nodes and check which one of them point to dest
        int minPath = Integer.MAX_VALUE;
        int vertices = dag.getVertices();
        for (int i = 0;i<vertices;++i) {
            Iterable<WeightedEdge> weightedEdges = dag.getAdj(i);
            for (WeightedEdge weightedEdge : weightedEdges) {
                if (weightedEdge.getNodeIndex() == dest) {
                    int localMinPath = shortestPathsDPWithEdgeCount(dag,source,i,numberOfEdges-1) ;
                    if (localMinPath == Integer.MAX_VALUE) {
                        //No path
                        continue;
                    }
                    localMinPath += weightedEdge.getWeight() ;
                    if (localMinPath < minPath) {
                        minPath = localMinPath;
                    }
                }
            }
        }


        //System.out.println("Value returned for dest "+ dest + " = " + minPath);
        return minPath;
    }

//    HashMap<Integer,Integer> shortestPathSingleSourceAllPairs(WeightedGraph dag,int source) {
//        HashMap<Integer,Integer> vertexToDistance = new HashMap<>();
//        for (int i = 0;i<dag.getVertices();++i) {
//            vertexToDistance.put(i,Integer.MAX_VALUE);
//        }
//
//        vertexToDistance.put(source,0);
//
//
//    }




    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph(6);
        weightedGraph.addEdge(0,1,1);
        weightedGraph.addEdge(0,5,2);
        weightedGraph.addEdge(1,2,6);
        weightedGraph.addEdge(2,3,2);
        weightedGraph.addEdge(2,4,1);
        weightedGraph.addEdge(4,3,1);
        weightedGraph.addEdge(5,1,4);
        weightedGraph.addEdge(5,4,30);

        System.out.println(shortestPathsDPWithEdgeCount(weightedGraph, 0, 4, 2));
    }


}
