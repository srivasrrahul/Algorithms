package GuessTriangle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Rahul on 4/28/15.
 */

class Point implements Comparable<Point>{
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Point{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }


    //    @Override
//    public String toString() {
////        final StringBuffer sb = new StringBuffer("Point{");
////        sb.append("x=").append(x);
////        sb.append(", y=").append(y);
////        sb.append('}');
////        return sb.toString();
//        System.out.printf("%.4f %.4f",x,y);
//
//
//    }

    void print() {
        System.out.printf("%.4f %.4f",x,y);
        System.out.println();
    }

    static double distance(Point p1,Point p2) {
        double xDistance = p1.getX() - p2.getX();
        double yDistance = p1.getY() - p2.getY();
        return Math.sqrt(xDistance*xDistance+ yDistance*yDistance);
    }

    static ArrayList<Point> solve(Point a,Point b,Point c) {
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
        Double x2 = (a.getX()+b.getX()-c.getX());
        Double x3 = 2*b.getX() - x2;
        Double x1 = 2*a.getX() - x2;

        Double y2 = (a.getY()+b.getY()-c.getY());
        Double y3 = 2*b.getY() - y2;
        Double y1 = 2*a.getY() - y2;

        ArrayList<Point> points = new ArrayList<>();

//        new Point(x1,y1).print();
//        new Point(x2,y2).print();
//        new Point(x3,y3).print();

        points.add(new Point(x1,y1));
        points.add(new Point(x2,y2));
        points.add(new Point(x3,y3));
        Collections.sort(points);

        return points;





    }



    @Override
    public int compareTo(Point o) {
        int comp = Double.compare(this.getX(),o.getX());

        if (comp == 0) {
            return Double.compare(this.getY(),o.getY());
        }

        return comp;
    }
}


public class TestClass {
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line ;


            ArrayList<Point> points = new ArrayList<>();
            int loop  = 0;
            while (loop < 3 && (line = br.readLine()) != null ) {
                //System.out.println("Inside while loop");
                String [] val = line.split(" ");
                points.add(new Point(Double.parseDouble(val[0]),Double.parseDouble(val[1])));
                ++loop;
            }

            //System.out.println("Outside while loop");
            ArrayList<Point> treeSet = Point.solve(points.get(0),points.get(1),points.get(2));
            for (Point point: treeSet) {
                point.print();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        TestClass m = new TestClass();
        m.readFile(null);
    }
}
