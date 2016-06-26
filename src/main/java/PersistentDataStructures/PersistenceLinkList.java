package PersistentDataStructures;

import java.util.HashMap;

/**
 * Created by rasrivastava on 6/10/16.
 */
class Node {
    private  int value;
    private HashMap<Integer,Integer> mods = new HashMap<>();
    private Node next;
    private Node prev;

    public Node(int value) {
        this.value = value;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    Node copyAndCreateNode(int val) {
        Node nodePrime = new Node(value);
        nodePrime.setPrev(prev);
        return nodePrime;
    }

    void modify(int updatedVal) {
        if (mods.size() > 2) {


        }else {
            int version = mods.size();
            mods.put(version+1,updatedVal);
        }
    }
}
public class PersistenceLinkList {
}
