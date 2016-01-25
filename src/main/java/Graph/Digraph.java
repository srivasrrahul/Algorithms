package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rahul on 10/23/15.
 */
public class Digraph {
    private int v;
    private int e;
    private ArrayList<Set<Integer>> adj = new ArrayList<>();
    public Digraph(int v) {
        this.v = v;
        this.e = 0;

        for (int i = 0;i<v;++i) {
            adj.add(new HashSet<Integer>());
        }
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public  void addEdge(int u,int v) {
        adj.get(u).add(v);
    }

    public Iterable<Integer> adj(int u) {
        return adj.get(u);
    }


}
