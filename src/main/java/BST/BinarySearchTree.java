package BST;

/**
 * Created by rasrivastava on 3/3/16.
 */
class Node {
    private int data;
    private Node left;
    private Node right;
    private int order;

    public Node(int data) {
        this.data = data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    boolean isLeaf() {
        return left == null && right == null;
    }

    boolean isRightExists() {
        return right != null;
    }

    boolean isLeftExists() {
        return left != null;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
public class BinarySearchTree {
    private Node root;
    void insert(int data) {
        if (root == null) {
            root = new Node(data);
        }else {
            insertInternal(root,data);
        }
    }

    static void updateOrder(Node node) {
        int order = 1;
        if (node.isLeftExists()) {
            order += node.getLeft().getOrder();
        }

        if (node.isRightExists()) {
            order += node.getRight().getOrder();
        }

        node.setOrder(order);
    }
    static void insertInternal(Node node,int data) {
        if (node.getData() < data) {
            if (node.isRightExists() == false) {
                Node newNode = new Node(data);
                node.setRight(newNode);
            }else {
                insertInternal(node.getRight(),data);

            }
        }else {
            if (node.isLeftExists() == false) {
                Node newNode = new Node(data);
                node.setLeft(newNode);
            }else {
                insertInternal(node.getLeft(),data);


            }
        }

        updateOrder(node);
    }


    static Node getRankInternal(Node node,int rank) {
        int currentRank = 0;
        if (node.isLeftExists()) {
            currentRank += node.getLeft().getOrder()+1;
        }

        if (currentRank == rank) {
            return node;
        }

        if (rank < currentRank) {
            return getRankInternal(node.getLeft(), rank);
        }

        return getRankInternal(node.getRight(), rank - currentRank);
    }



    Node getRank(int rank) {
        return getRankInternal(root,rank);
    }

    static int getCurrentRankWithSubTree(Node x) {
        if (x.isLeftExists()) {
            return x.getLeft().getOrder()+1;
        } else {
            return 0;
        }
    }

    static int getRankOfNode(Node currentNode,Node searchedNode,int previousNodes) {
        //System.out.println(currentNode.getData());
        if (currentNode == searchedNode) {
            return previousNodes + getCurrentRankWithSubTree(currentNode);
        }

        if (currentNode.getData() > searchedNode.getData()) {
            return getRankOfNode(currentNode.getLeft(), searchedNode, previousNodes);
        }else {
            return getRankOfNode(currentNode.getRight(), searchedNode, previousNodes + getCurrentRankWithSubTree(currentNode));
        }


    }

    int getRankOfNodeExternal(Node searchedNode) {
        return getRankOfNode(root, searchedNode, 0);
    }

    Node findNode(Node x,int data) {
        //System.out.println(x.getData());
        if (x.getData() == data) {
            return x;
        }

        if (x.getData() > data) {
            return findNode(x.getLeft(), data);
        }

        return findNode(x.getRight(), data);
    }

    int size(int lowerKey,int higherKey) {
        Node higher = findNode(root, higherKey);
        Node lower = findNode(root, lowerKey);
        System.out.println(higher.getData());
        System.out.println(lower.getData());

        System.out.println(getRankOfNodeExternal(higher));
        System.out.println(getRankOfNodeExternal(lower));

        return getRankOfNodeExternal(higher) - getRankOfNodeExternal(lower);
    }

    static void traverseInternal(Node node) {
        if (node == null) {
            return;
        }

        traverseInternal(node.getLeft());
        System.out.println(node.getData());
        traverseInternal(node.getRight());
    }

    void traverse() {
        traverseInternal(root);
    }



    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(100);
        binarySearchTree.insert(50);
        binarySearchTree.insert(25);
        binarySearchTree.insert(200);
        binarySearchTree.insert(175);
        //binarySearchTree.traverse();

//        System.out.println();
//        System.out.println(binarySearchTree.getRank(0).getData());
//
//        Node x = binarySearchTree.getRank(2);
//
//
//        System.out.println(binarySearchTree.getRankOfNodeExternal(x));

        System.out.println(binarySearchTree.size(25,200));

    }
}
