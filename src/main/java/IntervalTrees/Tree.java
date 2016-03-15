package IntervalTrees;

/**
 * Created by Rahul on 3/7/16.
 */
class Node {
    private int low;
    private int high;

    private int maxEndPoint;

    private Node left;
    private Node right;

    public Node(int low, int high) {
        this.low = low;
        this.high = high;
        this.maxEndPoint = high;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }


    public Node getLeft() {
        return left;
    }

    public int getMaxEndPoint() {
        return maxEndPoint;
    }

    public void setMaxEndPoint(int maxEndPoint) {
        this.maxEndPoint = maxEndPoint;
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

    boolean isLeaf() {
        return left == null && right == null;
    }

    boolean leftExists() {
        return left != null;
    }
    boolean isRightExists() {
        return right != null;
    }

    void updateMaxEndpoint(Node newNode) {
        if (maxEndPoint < newNode.getMaxEndPoint()) {
            maxEndPoint = newNode.getMaxEndPoint();
        }
    }
}
public class Tree {
    private Node root;
    static Node addIntervalIntenal(Node node,int low,int high) {
        if (low > node.getLow()) {
            if (node.isRightExists() == false) {
                Node newNode = new Node(low,high);
                node.setRight(newNode);
                node.updateMaxEndpoint(newNode);
                return newNode;
            }else {
                Node newNode =  addIntervalIntenal(node.getRight(),low,high);
                node.updateMaxEndpoint(newNode);
                return newNode;
            }
        }else {
            if (node.leftExists() == false) {
                Node newNode = new Node(low,high);
                node.setLeft(newNode);
                node.updateMaxEndpoint(newNode);
                return newNode;

            }else {
                Node newNode = addIntervalIntenal(node.getLeft(),low,high);
                node.updateMaxEndpoint(newNode);
                return newNode;
            }
        }
    }

    void addInterval(int low,int high) {
        if (root == null) {
            root = new Node(low,high);
        }else {
            addIntervalIntenal(root,low,high);
        }
    }

    void intersects(int low,int high) {

    }
}
