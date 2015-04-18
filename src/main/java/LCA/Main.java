package LCA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

public class Main {
    LinkedList<Node> findPath(Node currentNode,int val) {
        //Assume all element are found
        //System.out.println(currentNode.getValue());
        if (currentNode.getValue() == val) {
            LinkedList<Node> nodes = new LinkedList<>();
            nodes.add(currentNode);
            return nodes;
        }

        LinkedList<Node> retValue = null;
        if (currentNode.getValue() < val) {
            retValue =  findPath(currentNode.getRight(),val);
        }else {
            retValue =  findPath(currentNode.getLeft(),val);
        }

        retValue.add(0,currentNode);
        return retValue;
    }

    Node findCommonParent(ArrayList<Node> path1,ArrayList<Node> path2) {

        int i = 0;
        int j = 0;

        for (;i<path1.size() && j<path2.size();i++,++j) {
            if (path1.get(i) == path2.get(j)) {
                continue;
            }

            return path1.get(i-1);
        }


        if (i == path1.size()) {
            return path1.get(i-1);
        }

        if (j == path2.size()) {
            return path2.get(j-1);
        }

        return null;

    }

    Node findLCA(Node rootNode,int v1,int v2) {
        //System.out.println("For path1");
        LinkedList<Node> path1 = findPath(rootNode,v1);
        //System.out.println("For path2");
        LinkedList<Node> path2 = findPath(rootNode,v2);
        ArrayList<Node> path1Arr = new ArrayList<>(path1);
        ArrayList<Node> path2Arr = new ArrayList<>(path2);

        return findCommonParent(path1Arr,path2Arr);

    }

    public Main() {
        rootNode = new Node(30);
        rootNode.setRight(new Node(52));
        rootNode.setLeft(new Node(8));
        rootNode.getLeft().setLeft(new Node(3));
        rootNode.getLeft().setRight(new Node(20));
        rootNode.getLeft().getRight().setLeft(new Node(10));
        rootNode.getLeft().getRight().setRight(new Node(29));
    }
//    public static void main(String[] args) {
//        Main m = new Main();
//        Node rootNode = new Node(30);
//        rootNode.setRight(new Node(52));
//        rootNode.setLeft(new Node(8));
//        rootNode.getLeft().setLeft(new Node(3));
//        rootNode.getLeft().setRight(new Node(20));
//        rootNode.getLeft().getRight().setLeft(new Node(10));
//        rootNode.getLeft().getRight().setRight(new Node(29));
//
//        Node x = m.findLCA(rootNode, 3, 29);
//        System.out.println(x.getValue());
//
//    }

    void handleLine(String line) {
        String arr[] = line.split(" ");
        //System.out.println(line);
        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[1]);

        Node lca = findLCA(rootNode, x, y);
        if (lca == null) {
            System.out.println("issue with lca");
        }else {
            System.out.println(lca.getValue());
        }



    }
    void readFile(String fileName) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
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

    private Node rootNode;
}
