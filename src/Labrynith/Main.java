package Labrynith;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

class Node {
    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public char getVal() {
        return val;
    }

    public void setVal(char val) {
        this.val = val;
    }

    public Node(boolean discovered, char val) {
        this.discovered = discovered;
        this.val = val;
    }


    public Tuple getTuple() {
        return tuple;
    }

    public void setTuple(Tuple tuple) {
        this.tuple = tuple;
    }

    void setParent(Node u) {
        parent = u;
    }

    Node getParent() {
        return parent;
    }

    private boolean discovered;
    private char val;
    private Tuple tuple;
    private Node parent;
}

class Labrynith {
    private ArrayList<ArrayList<Node>> nodeLst = new ArrayList<>();

    void parseLine(String line) {
        int x = nodeLst.size();
        int y = 0;
        ArrayList<Node> newNodeLst = new ArrayList<>();
        for (Character character : line.toCharArray()) {
            Node node = new Node(false,character);
            node.setTuple(new Tuple(x,y));
            newNodeLst.add(node);
            ++y;
        }

        nodeLst.add(newNodeLst);
    }

    Node getVal(Tuple tuple) {
        return nodeLst.get(tuple.getX()).get(tuple.getY());
    }

    List<Node> getNeighbours(Tuple tuple) {

        if (tuple.getX() < 0 || tuple.getX() >= nodeLst.size()) {
            return null;
        }

        if (tuple.getY() < 0 || tuple.getY() >= nodeLst.get(0).size()) {
            return null;
        }

        List<Node> arrLst = new ArrayList<>();
        //Get upper neighbour
        if (tuple.getX() != 0) {
            Node node = nodeLst.get(tuple.getX()-1).get(tuple.getY());
            if (node.getVal() != '*') {
                arrLst.add(node);
            }
        }

        //Get Below neighbour
        if (tuple.getX() < nodeLst.size()-1) {
            Node node = nodeLst.get(tuple.getX()+1).get(tuple.getY());
            if (node.getVal() != '*') {
                arrLst.add(node);
            }
        }

        //Get Left Neighbour
        if (tuple.getY() != 0) {
            Node node = nodeLst.get(tuple.getX()).get(tuple.getY()-1);
            if (node.getVal() != '*') {
                arrLst.add(node);
            }
        }

        //Get Right Neighbour
        if (tuple.getY() < nodeLst.get(0).size()-1) {
            Node node = nodeLst.get(tuple.getX()).get(tuple.getY()+1);
            if (node.getVal() != '*') {
                arrLst.add(node);
            }
        }

        return arrLst;


    }

    void init() {
        int x = 0;
        int y = nodeLst.get(0).size()/2;
        startIndex = new Tuple(x,y);

        x = nodeLst.size()-1;
        endIndex = new Tuple(x,y);
    }

    boolean isEndIndex(Tuple tuple) {
        return tuple.equals(endIndex);
    }


    Tuple getStartIndex() {
        return startIndex;
    }

    void updateEdges(ArrayList<ArrayList<Node>> nodes) {
        for (ArrayList<Node> arrayList : nodes) {
            for (Node node : arrayList) {
                node.setVal('+');
                break;
            }
        }
    }

    void updatedEdges() {
        Node u = getVal(endIndex);
        Node parent = u.getParent();
        while (u != null) {
            u.setVal('+');
            u = parent;
            if (u != null) {
                parent = u.getParent();
            }

        }




    }

    void print() {
        for (ArrayList<Node> rowLst : nodeLst) {
            for (Node node : rowLst) {
                System.out.print(node.getVal());
            }

            System.out.println();
        }
    }

    private Tuple startIndex;
    private Tuple endIndex;
}



class Tuple {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (x != tuple.x) return false;
        if (y != tuple.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int x;
    private int y;
}

class Edge {
    private Node u;
    private Node v;

    public Edge(Node u, Node v) {
        this.u = u;
        this.v = v;
    }

    public Node getU() {
        return u;
    }

    public void setU(Node u) {
        this.u = u;
    }

    public Node getV() {
        return v;
    }

    public void setV(Node v) {
        this.v = v;
    }
}
public class Main {

    ArrayList<ArrayList<Node>> shortestPath(Labrynith labrynith) {
        Tuple startIndex = labrynith.getStartIndex();
        Node startNode = labrynith.getVal(startIndex);
        startNode.setDiscovered(true);

        ArrayList<ArrayList<Node>> layers = new ArrayList<>();
        //ArrayList<Edge> bfsTree = new ArrayList<>();

        layers.add(new ArrayList<Node>());
        layers.get(0).add(startNode);
        int i = 0;

        ArrayList<Node> path = new ArrayList<>();
        path.add(startNode);
        while (layers.get(i).size() > 0) {
            layers.add(new ArrayList<Node>());

            for (Node u : layers.get(i)) {

                for (Node v : labrynith.getNeighbours(u.getTuple())) {
                    //System.out.println("Inside get Neighbours + " + v.getTuple().getX() + " , " + v.getTuple().getY() + "=" + v.getVal() + "=");
                    if (labrynith.isEndIndex(v.getTuple())) {
                        //Found end
                        //System.out.println(layers.size());
                        v.setDiscovered(true);
                        //bfsTree.add(new Edge(u, v));
                        layers.get(i + 1).add(v);
                        v.setParent(u);
                        return layers;
                    }
                    if (v.isDiscovered() == false) {
                        v.setDiscovered(true);
                        //bfsTree.add(new Edge(u, v));
                        v.setParent(u);
                        layers.get(i + 1).add(v);
                    }
                }

            }
            //Increment next layer
            i++;
        }

        return null;
    }




    void readFile(String fileName) {
        Labrynith laybryinith = new Labrynith();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                laybryinith.parseLine(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        laybryinith.init();
        ArrayList<ArrayList<Node>> nodes = shortestPath(laybryinith);
        //System.out.println(edges.size());
        laybryinith.updatedEdges();
        laybryinith.print();



    }

    public static void main(String args[]) {
       Main main = new Main();
        main.readFile(args[0]);
    }

}
