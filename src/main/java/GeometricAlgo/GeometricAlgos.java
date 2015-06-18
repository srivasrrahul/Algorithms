package GeometricAlgo;

import java.util.ArrayList;

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
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
}

class Line {
    private Point p1;
    private Point p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line() {

    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }
}
public class GeometricAlgos {
    double ccw(Point a ,Point b,Point c) {
        double val = (b.getX()-c.getX())*(c.getY()-a.getY())-(b.getX()-a.getY())*(c.getX()-a.getX());
        return val;
    }

    boolean isLineIntersect(Line l1,Line l2) {

        if (ccw(l1.getP1(),l1.getP2(),l2.getP1()) * ccw(l1.getP1(),l1.getP2(),l2.getP2()) > 0) {
            return false;
        }

        if (ccw(l2.getP1(),l2.getP2(),l1.getP1()) * ccw(l2.getP1(),l2.getP2(),l1.getP2()) > 0) {
            return false;
        }

        return true;
    }

    double getSlope(Point p1,Point p2) {
        return (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
    }

    //Excluding lines parallel to x axis
    boolean isCollinear(ArrayList<Point> pointArrayList,int low,int high) {
        //All two points are collinear
        System.out.println(low + " " + high);
        if (low+1 == high || low >= high) {
            //System.out.println("Returning true");
            return true;
        }

        int mid = low + (high-low)/2;

        boolean isLeftCollinear = isCollinear(pointArrayList,low,mid);
        if (isLeftCollinear == false) {
            //System.out.println("Returning false " + isLeftCollinear);
            return false;
        }

        boolean isRightCollinear = isCollinear(pointArrayList,mid+1,high);

        if (isRightCollinear == false) {
            //System.out.println("Returning false "  + " " + isRightCollinear);
            return false;
        }

        //Both are collinear
        Point leftPoint = pointArrayList.get(low);
        Point rightPoint = pointArrayList.get(high);
        Point midPoint = pointArrayList.get(mid);

        double leftSlope = getSlope(leftPoint,midPoint);
        double rightSlope = getSlope(leftPoint,rightPoint);
        //System.out.println("Leftslope " + leftSlope + " RightSlope " + rightSlope + " " + low + " " + high);
        return Double.compare(leftSlope,rightSlope) == 0;

    }

    public static void main(String[] args) {
        Point p1 = new Point(0,0);


        Point p2 = new Point(1,1);


        Point p3 = new Point(1,0);

        Point p4 = new Point(0,1);

        Line l1 = new Line(p1,p2);

        Line l2 = new Line(p3,p4);

        GeometricAlgos geometricAlgos = new GeometricAlgos();
        System.out.println(geometricAlgos.isLineIntersect(l1, l2));

        ArrayList<Point> arrayList = new ArrayList<>();
        arrayList.add(new Point(-2,-2));
        arrayList.add(new Point(0,0));
        arrayList.add(new Point(1,1));
        arrayList.add(new Point(2,2));
        arrayList.add(new Point(3,3));
        arrayList.add(new Point(4,4));
        arrayList.add(new Point(-1,-1));

        System.out.println(geometricAlgos.isCollinear(arrayList,0,arrayList.size()-1));

    }




}
