package IntervalTrees;

import GeometricAlgo.IntervalSingleDimension;

import java.util.Stack;

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
    int countChildren() {
        int count = 0;
        if (leftExists()) {
            ++count;
        }

        if (isRightExists()) {
            ++count;
        }

        return count;
    }

    static void swapContents(Node source,Node dest) {
        int lowTemp = source.getLow();
        int rightTemp = source.getHigh();
        source.low = dest.low;
        source.high = dest.high;

        dest.low = lowTemp;
        dest.high = rightTemp;

    }
}
public class IntervalSearchTree {
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


    IntervalSingleDimension intersects(int low,int high) {
        if (root == null) {
            return null;
        }

        IntervalSingleDimension askedInterval = new IntervalSingleDimension(low,high);
        Node current = root;
        while (current != null) {
            IntervalSingleDimension intervalSingleDimension = new IntervalSingleDimension(current.getLow(),
                    current.getHigh());
            if (intervalSingleDimension.intersects(askedInterval)) {
                return intervalSingleDimension;
            }

            if (current.leftExists()) {
                if (current.getLeft().getMaxEndPoint() < askedInterval.getLow()) {
                    current = current.getRight();
                }else {
                    current = current.getLeft();
                }
            }else {
                current = current.getRight();
            }
        }

        return null;
    }

    static Node findSmallest(Node currentNode) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.leftExists() == false) {
            return currentNode;
        }

        return findSmallest(currentNode.getLeft());


    }

    static Node findSuccessor(Node currentNode) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.isRightExists()) {
            return findSmallest(currentNode.getLeft());
        }

        return null;
    }

    static void deleteNode(Node parentNode,Node currentNode) {
        if (currentNode == null) {
            return;
        }

        //Current one is leaf
        if (currentNode.isLeaf()) {
            if (currentNode == parentNode.getLeft()) {
                parentNode.setLeft(null);
                parentNode.setMaxEndPoint(parentNode.getMaxEndPoint());
                return;
            }else {
                parentNode.setRight(null);
                parentNode.setMaxEndPoint(parentNode.getMaxEndPoint());
                return;
            }
        }

        int leafs = currentNode.countChildren();
        //only one next
        if (leafs == 1) {
            Node next = (currentNode.leftExists()?currentNode.getLeft():currentNode.getRight());
            if (currentNode == parentNode.getLeft()) {
                parentNode.setLeft(next);
                parentNode.setMaxEndPoint(parentNode.getMaxEndPoint());
                return;
            }else {
                parentNode.setRight(next);
                parentNode.setMaxEndPoint(parentNode.getMaxEndPoint());
                return;
            }

        }

//        //two next
//        Node successor = findSuccessor(currentNode);
//        Node.swapContents(currentNode,successor);

        Node succ = currentNode.getRight();
        Node succParent = currentNode;
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.add(succParent);
        while (succ.leftExists()) {

            succParent = succ;
            succ = succ.getLeft();
            nodeStack.add(succParent);
        }

        Node.swapContents(currentNode,succ);
        deleteNode(succParent,succ);
        while (!nodeStack.empty()) {
            Node node = nodeStack.pop();
            int maxEndpoint = getMaxEndpoint(node);
            node.setMaxEndPoint(maxEndpoint);
        }


    }

    static void deleteRoot(Node root,Node currentNode) {
        if (currentNode == null) {
            return;
        }

        //Current one is leaf
        if (currentNode.isLeaf()) {
            root = null;
        }

        int leafs = currentNode.countChildren();
        //only one next
        if (leafs == 1) {
            Node next = (currentNode.leftExists()?currentNode.getLeft():currentNode.getRight());
            root = next;

        }

//        //two next
//        Node successor = findSuccessor(currentNode);
//        Node.swapContents(currentNode,successor);

        Node succ = currentNode.getRight();
        Node succParent = currentNode;
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.add(succParent);
        while (succ.leftExists()) {

            succParent = succ;
            succ = succ.getLeft();
            nodeStack.add(succParent);
        }

        Node.swapContents(currentNode,succ);
        deleteNode(succParent,succ);
        while (!nodeStack.empty()) {
            Node node = nodeStack.pop();
            int maxEndpoint = getMaxEndpoint(node);
            node.setMaxEndPoint(maxEndpoint);
        }


    }

    private static int getMaxEndpoint(Node node) {
        int maxEndPoint = node.getHigh();
        if (node.getLeft() != null) {
            if (maxEndPoint < node.getLeft().getMaxEndPoint()) {
                maxEndPoint = node.getLeft().getMaxEndPoint();
            }
        }

        if (node.getRight() != null) {
            if (maxEndPoint < node.getRight().getMaxEndPoint()) {
                maxEndPoint = node.getRight().getMaxEndPoint();
            }
        }

        return maxEndPoint;
    }

    public static void main(String[] args) {
        IntervalSearchTree intervalSearchTree = new IntervalSearchTree();
        intervalSearchTree.addInterval(10,16);
        intervalSearchTree.addInterval(17,30);
        intervalSearchTree.addInterval(9,13);
        intervalSearchTree.addInterval(5,10);
        intervalSearchTree.addInterval(14, 17);
        System.out.println(intervalSearchTree.intersects(2,6));
    }
}
