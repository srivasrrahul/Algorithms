package CycleDetection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Node {
    private int id;

    Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (id != node.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

class Graph {
    Map<Node,Integer> nodeMap = new HashMap<>();
    List<Node> lst = new ArrayList<>();
    Graph() {

    }

    void printLst(int index) {
        for (int i = index;i<lst.size();++i) {
            System.out.print(lst.get(i).getId());
            if (!(i == lst.size() -1) ) { //Not last
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    boolean add(int n) {
        Node node = new Node(n);


        if (nodeMap.containsKey(node)) {
            printLst(nodeMap.get(node));
            return true;
        }else {
            lst.add(node);
            nodeMap.put(node,lst.size()-1);
            return false;
        }
    }


}
public class Main {
    void handleLine(String line) {
        Graph graph = new Graph();
        String [] strings = line.split(" ");
        for (String string : strings) {
            int n  = Integer.parseInt(string);
            if (graph.add(n)) {
                break;
            }
        }
    }
    void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
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
