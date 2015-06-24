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

}
