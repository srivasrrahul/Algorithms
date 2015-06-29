package Misc;


class Node {
    private int element;
    private Node next;

    public Node(int element) {
        this.element = element;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class ReturnValue {
    private Node startNode;
    private Node currentNode;

    public ReturnValue(Node startNode, Node currentNode) {
        this.startNode = startNode;
        this.currentNode = currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getCurrentNode() {
        return currentNode;
    }
}

public class Misc {
    static ReturnValue reverseList(Node node) {
        if (node.getNext() == null) {
            return new ReturnValue(node,node); //This is start element of updated list
        }

        ReturnValue returnValue = reverseList(node.getNext());

        returnValue.getCurrentNode().setNext(node);
        returnValue.setCurrentNode(node);
        return returnValue;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.setNext(new Node(2));
        node.getNext().setNext(new Node(3));
        node.getNext().getNext().setNext(new Node(4));

        ReturnValue returnValue = reverseList(node);

        node.setNext(null);
        Node startNode = returnValue.getStartNode();
        int i = 0;
        while (startNode != null) {
            System.out.println(startNode.getElement());
            startNode = startNode.getNext();
            ++i;
        }

    }
}
