package SmallBST;


class OrderStatisticData {
    private int sizeOfSubTree;

    public OrderStatisticData() {
        this.sizeOfSubTree = 1;
    }

    public int getSizeOfSubTree() {
        return sizeOfSubTree;
    }

    public void setSizeOfSubTree(int sizeOfSubTree) {
        this.sizeOfSubTree = sizeOfSubTree;
    }
}
class Node {
    private int data;
    private Node left;
    private Node right;
    private OrderStatisticData orderStatisticData;


    public Node(int data) {
        this.data = data;
        this.orderStatisticData  = new OrderStatisticData();
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }


    public OrderStatisticData getOrderStatisticData() {
        return orderStatisticData;
    }

    public void setOrderStatisticData(OrderStatisticData orderStatisticData) {
        this.orderStatisticData = orderStatisticData;
    }
}

public class BST {
    Node root;

    public BST() {

    }

    public Node getRoot() {
        return root;
    }

    void visitInOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        visitInOrderTraversal(node.getLeft());
        System.out.println(node.getData() + " " + node.getOrderStatisticData().getSizeOfSubTree());
        visitInOrderTraversal(node.getRight());
    }
    void addDataInternal(Node node,int data) {
        if (data < node.getData()) {
            if (node.getLeft() == null) {
                Node leftNode = new Node(data);
                node.getOrderStatisticData().setSizeOfSubTree(node.getOrderStatisticData().getSizeOfSubTree()+1);
                node.setLeft(leftNode);
            }else {
                addDataInternal(node.getLeft(),data);
            }
        }else {
            if (node.getRight() == null) {
                Node rightNode = new Node(data);
                node.getOrderStatisticData().setSizeOfSubTree(node.getOrderStatisticData().getSizeOfSubTree()+1);
                node.setRight(rightNode);
            }else {
                addDataInternal(node.getRight(),data);
            }
        }
    }

    void addData(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }

        addDataInternal(root,data);
    }

    Node selectWithIndex(Node node,int rank) {
        int currentRank = 1;
        if (node.getLeft() != null) {
            currentRank = node.getLeft().getOrderStatisticData().getSizeOfSubTree()+1;
        }


        if (rank == currentRank) {
            return node;
        }

        if (currentRank > rank) {
            return selectWithIndex(node.getLeft(),rank);
        }else {
            return selectWithIndex(node.getRight(),rank-currentRank);
        }
    }

    public static void main(String[] args) {
        BST bst   = new BST();
        bst.addData(20);
        bst.addData(10);
        bst.addData(30);
        bst.addData(50);
        bst.addData(9);
        bst.visitInOrderTraversal(bst.getRoot());
        System.out.println(bst.selectWithIndex(bst.getRoot(),5).getData());
    }
}
