package GraphAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rasrivastava on 6/23/15.
 */
class GraphAlgo {

    static void dfs(Graph graph,ArrayList<Boolean> visited,int startVertex) {
        visited.set(startVertex,true);
        Iterable<Integer> adjLst = graph.adj(startVertex);
        for (Integer v : adjLst) {
            if (visited.get(v) == true) {
                //skip this data
            }else {
                visited.set(v,true);
                dfs(graph,visited,v);
            }
        }

    }

    static void dfsDirected(DirectedGraph graph,ArrayList<Boolean> visited,int startVertex) {
        visited.set(startVertex,true);
        Iterable<Integer> adjLst = graph.adj(startVertex);
        for (Integer v : adjLst) {
            if (visited.get(v) == true) {
                //skip this data
            }else {
                visited.set(v,true);
                dfsDirected(graph, visited, v);
            }
        }

    }

    static ArrayList<Boolean> dfsDirected(DirectedGraph graph,int startVertex) {
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0;i<graph.getVertices();++i) {
            visited.add(false);
        }

        return visited;
    }



    static ArrayList<Boolean> bfsInterface(Graph graph,int startVertex) {
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0;i<graph.getVertices();++i) {
            visited.add(false);
        }

        bfs(graph,visited,startVertex);
        return visited;
    }
    static void bfs(Graph graph,ArrayList<Boolean> visited,int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.set(startVertex,true);
        while (queue.size() > 0) {
            int u = queue.remove();
            Iterable<Integer> iterable = graph.adj(u);
            for (Integer v : iterable) {
                if (visited.get(v) == false) {
                    visited.set(v,true);
                    queue.add(v);
                }
            }
        }
    }


}
