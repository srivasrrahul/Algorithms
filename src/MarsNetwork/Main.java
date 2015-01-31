package MarsNetwork;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Point {

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.getGroup().add(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public String toString() {
        return "" +
                "" + x +
                "," + y
                ;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    void setGroup(HashSet<Point> group) {
        hashSet = group;
    }

    HashSet<Point> getGroup() {
        return hashSet;
    }

    private int x;
    private int y;
    private HashSet<Point> hashSet = new HashSet<>();

}

class Edge implements Comparable<Edge>{
    private Point p1;
    private Point p2;
    private Double distance;

    public Edge(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        distance = getDistance();
    }

    Point getP1() {
        return p1;
    }

    Point getP2() {
        return p2;
    }

    Double getDistance() {
        int xDiff = p1.getX() - p2.getX();
        int yDiff = p1.getY() - p2.getY();
        return Math.sqrt(xDiff * xDiff + yDiff*yDiff);
    }

    @Override
    public int compareTo(Edge o) {
        return distance.compareTo(o.getDistance());
    }
}

public class Main {
    ArrayList<Edge> createEdges(ArrayList<Point> pointArrayList) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0;i<pointArrayList.size();++i) {
            for (int j = i+1;j<pointArrayList.size();++j) {


                edges.add(new Edge(pointArrayList.get(i),pointArrayList.get(j)));
            }
        }

        //System.out.println(edges.size());
        return edges;
    }

    void mergeGroup(Point p1,Point p2) {
        HashSet<Point> group = p1.getGroup();
        for (Point targetPoint : p2.getGroup()) {
            targetPoint.setGroup(group);
            group.add(targetPoint);
        }
        p2.setGroup(group);

    }
    double findMinPath(ArrayList<Edge> edges) {
        Collections.sort(edges);
        double totalDistance = 0;

        for (Edge edge : edges) {
            if (edge.getP1().getGroup() != edge.getP2().getGroup()) {
//                System.out.println("  ");
//                System.out.println(edge.getP1());
//                System.out.println(edge.getP2());
                mergeGroup(edge.getP1(),edge.getP2());
                totalDistance += edge.getDistance();

            }

        }


        Double fractionalPart = totalDistance - (int)totalDistance;
        if (Double.compare(fractionalPart,0) == 0) {
            System.out.println((int)totalDistance);
        }else {
            System.out.println(((int)totalDistance) + 1);
        }


        return totalDistance;
    }

    void handleLine(String line) {

        ArrayList<Point> pointArrayList = new ArrayList<>();
        String points[] = line.split(" ");
        //System.out.println(points.length);
        for (String point : points) {
            //System.out.println(point);
            String [] coord = point.split(",");
            pointArrayList.add(new Point(Integer.parseInt(coord[0]),Integer.parseInt(coord[1])));

        }

        ArrayList<Edge> edges = createEdges(pointArrayList);
        findMinPath(edges);
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
