package UnionFind;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by rasrivastava on 8/1/15.
 */
public class UnionFind {
    public UnionFind(int N) {
        arr = new int[N];
        for (int i = 0;i<arr.length;++i) {
            arr[i] = i;
        }

        count = N;

        sz = new int[N];
        for (int i = 0;i<sz.length;++i) {
            sz[i] = 1;
        }
    }

    void union_naive(int p,int q) {
        if (p == q) {
            return;
        }

        int pid = find(p);
        int qid = find(q);

        if (pid == qid) {
            return;
        }

        for (int i = 0;i<arr.length;++i) {
            if (arr[i] == qid) {
                arr[i] = pid;
            }
        }

        --count;



    }

    void union(int p,int q) {
        union_tree(p, q);

    }

    int find(int p) {
        return find_tree(p);
    }

    void union_tree(int p,int q) {
        int pRoot = find_tree(p);
        int qRoot = find_tree(q);
        if (pRoot == qRoot) {
            return;
        }

        //arr[pRoot] = qRoot;
        if (sz[pRoot] < sz[qRoot]) {
            arr[pRoot] = qRoot;
            sz[pRoot] += sz[pRoot];
        }else {
            arr[qRoot] = pRoot;
            sz[qRoot] += sz[pRoot];
        }


        --count;


    }
    int find_tree(int p) {
        while (p != arr[p]) {
            p = arr[p];
        }

        return p;
    }

    boolean connected(int p,int q) {
        return find(p) == find(q);
    }

    public int getCount() {
        return count;
    }


    private int arr[];
    private int sz[];
    private int count;



    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();
            int n = Integer.parseInt(line);
            UnionFind unionFind = new UnionFind(n);
            while ((line = br.readLine()) != null) {
                String [] vals = line.split(" ");
                int p = Integer.parseInt(vals[0]);
                int q = Integer.parseInt(vals[1]);
                unionFind.union(p,q);
            }

            System.out.println(unionFind.getCount());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
