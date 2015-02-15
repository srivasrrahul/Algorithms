package Comrades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    private int n;



    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int state = 1;//0 for sleep,1 for wakeup
    private List<Node> reportee = new ArrayList<>();
    private Node parent;
    private int subTreeAlive = 0;

    public HashSet<Node> getSubTree() {
        return subTree;
    }

    private HashSet<Node> subTree = new HashSet<>();


    HashSet<Node> init() {
        System.out.println("Initilaizing " + n);
        for (Node reporteeNode : reportee) {
            HashSet<Node> subTree = reporteeNode.init();
            this.subTree.addAll(subTree);
        }

        subTree.add(this);
        //System.out.println("Returning " + n + " " + subTree.size());
        return subTree;

    }



    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node(int n) {
        this.n = n;
    }

    public List<Node> getReportee() {
        return reportee;
    }

    public void addReportee(Node reportee) {
        this.reportee.add(reportee);
    }

    public int getSubTreeAlive() {
        return subTreeAlive;
    }

    public void setSubTreeAlive(int subTreeAlive) {
        this.subTreeAlive = subTreeAlive;
    }
}
class TestClass {
    private HashMap<Integer,Node> hashMap = new HashMap<>();
    private Node commander;
    void addRelationShip(int identifier,int immediateSupervisor) {
        System.out.println("Adding relationship");
        if (immediateSupervisor == 0) {
            commander = hashMap.get(identifier);
            return;
        }

        Node sourceNode = hashMap.get(identifier);
        Node supervisorNode = hashMap.get(immediateSupervisor);
        supervisorNode.addReportee(sourceNode);
        sourceNode.setParent(supervisorNode);
    }

    void init(int N) {
        for (int i = 0;i<N;++i) {
            Node node = new Node(i+1);
            hashMap.put(i+1,node);
        }
    }

    LinkedList<Node> copyList(List<Node> sourceLst) {
        LinkedList<Node> lst = new LinkedList<>();
        for (Node node : sourceLst) {
            lst.add(node);
        }

        return lst;
    }
    void type1Order(int immediateSuprvisor) {
        System.out.println("Inside type 10 order");
        Node node = hashMap.get(immediateSuprvisor);
        HashSet<Node> reportees = node.getSubTree();
        for (Node reportee : reportees) {
            if (reportee == node) {
                continue;
            }
            reportee.setState(1);
        }

    }

    void type20Order(int immediateSuprvisor) {

        System.out.println("Inside type 20 order");
        Node node = hashMap.get(immediateSuprvisor);
        HashSet<Node> reportees = node.getSubTree();
        for (Node reportee : reportees) {
            if (reportee == node) {
                continue;
            }
            reportee.setState(0);
        }
    }

    void type30Order(int immediateSuprvisor) {

        System.out.println("Inside type 30 order");
        Node node = hashMap.get(immediateSuprvisor);
        HashSet<Node> reportees = node.getSubTree();
        int awake = 0;
        for (Node reportee : reportees) {
            if (reportee == node) {
                continue;
            }

            if (reportee.getState() == 1) {
                ++awake;
            }
        }
        System.out.println(awake);
    }

    public static void main(String[] args) throws IOException {
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            testClass.init(N);
        }

        line = br.readLine();
        String parentData[] = line.split(" ");
        for (int i = 0;i<parentData.length;++i) {
            testClass.addRelationShip(i+1,Integer.parseInt(parentData[i]));
        }

        testClass.commander.init(); //Initialize tree and subtree sizes
        line = br.readLine();
        int numberOfCommands = Integer.parseInt(line);
        for (int i = 0;i<numberOfCommands;++i) {
            line = br.readLine();
            String [] cmd = line.split(" ");
            switch (Integer.parseInt(cmd[0])) {
                case 1:
                    testClass.type1Order(Integer.parseInt(cmd[1]));
                    break;
                case 2:
                    testClass.type20Order(Integer.parseInt(cmd[1]));
                    break;
                case 3:
                    testClass.type30Order(Integer.parseInt(cmd[1]));
                    break;

            }
        }

    }


}
