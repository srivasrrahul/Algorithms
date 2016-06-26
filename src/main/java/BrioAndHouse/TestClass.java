package BrioAndHouse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rahul on 4/28/15.
 */
class Point {
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
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    static double distance(Point p1,Point p2) {
        double xDistance = p1.getX() - p2.getX();
        double yDistance = p1.getY() - p2.getY();
        return Math.sqrt(xDistance*xDistance+ yDistance*yDistance);
    }
}

class Circle {
    private Point point;
    private double radius;

    public Circle(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }


    double area() {
        return 3.14*radius*radius;
    }

    double perimeter() {
        return 2*3.14*radius;
    }

    Point getCentre() {
        return point;
    }

    double getRadius() {
        return radius;
    }

}
class Line {
    private Point p1;
    private Point p2;
    private Double slope;
    private Double intersection;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        slope = getSlope();
        intersection = getIntersection();
    }

    Double getIntersection() {
        return p2.getY()-(slope*p2.getX());
    }

    Double getSlope() {
        return (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Double getDistance(Point x) {
        Double denom = Math.sqrt(slope*slope + 1);
        Double numerator = Math.abs(slope*x.getX()+(-1)*x.getY()+intersection);
        return denom/numerator;

    }
}
public class TestClass {

    static double ccw(Point a ,Point b,Point c) {
        double val = (b.getX()-a.getX())*(c.getY()-a.getY())-(b.getY()-a.getY())*(c.getX()-a.getX());
        return val;
    }

    int findInteresection(Circle circle,Point p1,Point p2) {
        Double ccw = Math.abs(ccw(p1,p2,circle.getCentre()));
        //System.out.println(ccw);

        Double distance = Point.distance(p1,p2);
        //System.out.println(distance);
        Double height = ccw/distance;

        int cmp = Double.compare(height,circle.getRadius());
        return cmp;

    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();


            int n = Integer.parseInt(line);
            for (int i = 0;i<n;++i) {
                line = br.readLine();
                String []val = line.split(" ");
                Point p1 = new Point(Integer.parseInt(val[0]),Integer.parseInt(val[1]));
                Point p2 = new Point(Integer.parseInt(val[2]),Integer.parseInt(val[3]));
                line = br.readLine();
                Circle circle = new Circle(new Point(0,0),Integer.parseInt(line));
                switch (findInteresection(circle,p1,p2)) {
                    case 0:
                        System.out.println("JUST MISSED");
                        break;
                    case 1:
                        System.out.println("SAFE");
                        break;
                    case -1:
                        System.out.println("REPLANNING");
                        break;
                }

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
