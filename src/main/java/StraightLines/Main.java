package StraightLines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Rahul on 4/28/15.
 */
class Point {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != null ? !x.equals(point.x) : point.x != null) return false;
        return !(y != null ? !y.equals(point.y) : point.y != null);

    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    private Double x;
    private Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    static Double ccw(Point a,Point b,Point c) {
        Double val = (b.getX()-c.getX())*(c.getY()-a.getY())-(b.getX()-a.getY())*(c.getX()-a.getX());
        return val;
    }
}

class Line {
    //ax + by = 1
    private Double a;
    private Double b;

    @Override
    public String toString() {
        return "Line{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (a != null ? !a.equals(line.a) : line.a != null) return false;
        return !(b != null ? !b.equals(line.b) : line.b != null);

    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }

    boolean isSlopeValid(Point p1,Point p2) {
        return Double.compare(p1.getX(),p2.getX()) != 0;
    }

    Double getSlope(Point p1,Point p2) {
        return (p2.getY() - p1.getY())/(p2.getX()-p1.getX());
    }

    boolean isSlopeZero(Point p1,Point p2) {
        return Double.compare(p1.getY(),p2.getY()) == 0;
    }

    Double getIntercept(Double slope,Point p1) {
        Double c = p1.getY() - (slope * p1.getX());
        return c;
    }
    public Line(Point p1,Point p2) {
           if (false == isSlopeValid(p1,p2)) {
               //Parallel to Y Axis
               a = 0.0;
               b = p1.getX();
           }else {
               if (isSlopeZero(p1,p2)) {
                   a = p1.getY();
                   b = 0.0;
               }else {
                   //System.out.println("Normal");
                   Double slope = getSlope(p1,p2);
                   Double intercept = getIntercept(slope,p1);
                   //System.out.println(slope + " " + intercept);
                   a = slope/((-1) *intercept);
                   b = 1/slope;
               }


           }
    }
}
public class Main {
    int handlePoints(HashSet<Point> points) {
        HashMap<Line,HashSet<Point>> lines = new HashMap<>();
        for (Point point : points) {
            for (Point point1 : points) {
                if (point == point1) {
                    continue;
                }


                Line line = new Line(point,point1);
                //System.out.println(point.toString());
                //System.out.println(point1.toString());
                //System.out.println(line.toString());
                if (lines.containsKey(line)) {
                    lines.get(line).add(point);
                    lines.get(line).add(point1);
                }else {
                    HashSet<Point> pointHashSet = new HashSet<>();
                    pointHashSet.add(point);
                    pointHashSet.add(point1);
                    lines.put(line,pointHashSet);
                }
            }
        }

        int index = 0;
        for (Line line : lines.keySet()) {
            if (lines.get(line).size() >= 3) {
                ++index;
            }
        }
        return index;
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                String points[] = line.split("\\|");
                HashSet<Point> pointHashSet = new HashSet<>();
                for (String p : points) {
                    if (p.charAt(0) == ' ') {
                        p = p.substring(1);
                    }
                    String coord[] = p.split(" ");
                    //System.out.println(coord[0] + " " + coord[1]);
                    Point point = new Point(Double.parseDouble(coord[0].trim()),Double.parseDouble(coord[1].trim()));
                    pointHashSet.add(point);
                }

                System.out.println(handlePoints(pointHashSet));

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(args[0]);
    }
}
