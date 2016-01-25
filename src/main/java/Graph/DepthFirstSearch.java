package Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by rasrivastava on 11/22/15.
 */
public class DepthFirstSearch {
    public DepthFirstSearch(Digraph digraph) {
        for (int i = 0;i<digraph.getV();++i) {
            if (marked.contains(i) == false) {
                dfsInternal(digraph, i);
            }
        }
    }

    private void dfsInternal(Digraph digraph,int u) {
        dfsPath.add(u);
        marked.add(u);
        for (int v : digraph.adj(u)) {
            if (marked.contains(v) == false) {
                dfsInternal(digraph,v);
            }
        }
    }

    public Iterable<Integer> getDfsTree() {
        return dfsPath;
    }

    private HashSet<Integer> marked = new HashSet<>();
    private ArrayList<Integer> dfsPath = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Digraph digraph = Util.constructDigraph(args[0]);
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(digraph);
        for (int  x: depthFirstSearch.getDfsTree()) {
            System.out.println(x);
        }
    }
}
