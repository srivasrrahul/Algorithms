package GeometricAlgo;

import java.util.*;

class IntervalPoint implements Comparable<IntervalPoint> {
    private int p;

    public IntervalPoint(int p) {
        this.p = p;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntervalPoint that = (IntervalPoint) o;

        if (p != that.p) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return p;
    }



    @Override
    public int compareTo(IntervalPoint o) {
        return Integer.compare(p,o.getP());
    }
}


class Interval {
    private IntervalPoint low;
    private IntervalPoint high;

    public Interval(IntervalPoint low, IntervalPoint high) {
        this.low = low;
        this.high = high;
    }

    public IntervalPoint getLow() {
        return low;
    }

    public void setLow(IntervalPoint low) {
        this.low = low;
    }

    public IntervalPoint getHigh() {
        return high;
    }

    public void setHigh(IntervalPoint high) {
        this.high = high;
    }
}

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

class PolarAngleComparator implements Comparator<Point> {

    private Point origin;
    PolarAngleComparator(Point origin) {
        this.origin = origin;
    }

    @Override
    public int compare(Point o1, Point o2) {
        GeometricAlgos geometricAlgos = new GeometricAlgos();

        //origin,o1,o2 > 0 then o1 is on left side of o2 wrt origin
        double val =  geometricAlgos.consecutiveLineSegementsTurningAngle(origin,o1,o2);
        if (val < 0) {
            return 1;
        }else {
            if (val > 0) {
                return -1;
            }else {
                return 0;
            }
        }
    }
}

public class GeometricAlgos {

    boolean isOnRight(Point p1,Point p2) {
        double val=  p1.getX()*p2.getY() - p1.getY()*p2.getX() ;
        //System.out.println(val);
        return val > 0;
    }

    double consecutiveLineSegementsTurningAngle(Point p0,Point p1,Point p2) {
        //(p1-p0)*(p2-p0) > 0 p1 is clockwise and angle is positive
        //
        return (p1.getX() - p0.getX())*(p2.getY()-p0.getY())-(p2.getX()-p0.getX())*(p1.getY()-p0.getY());
    }


    static double ccw(Point a ,Point b,Point c) {
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

    static double distance(Point p1,Point p2) {
        double xDistance = p1.getX() - p2.getX();
        double yDistance = p1.getY() - p2.getY();
        return Math.sqrt(xDistance*xDistance+ yDistance*yDistance);
    }

    static double distanceFromPointToLine(Point a,Point p1,Point p2) {
        double numerator = (a.getX()-p1.getX())*(p2.getX()-p1.getX()) +
                (a.getY() - p1.getY()) * (p2.getY() - p1.getY());

        double denominator = distance(p1,p2);

        return numerator/denominator;
    }


    //Check if p0 intersects with p1 and p2
    static boolean rightHorizontolRayIntersect(Point p0,Point p1,Point p2) {
        if (p0.getY() >= p1.getY() && p0.getY() <= p2.getY()) {
            return true;
        }

        if (p0.getY() >= p2.getY() && p0.getY() <= p1.getY()) {
            return true;
        }

        return false;
    }

    static  void testOpenedInterval() {
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(new IntervalPoint(9),new IntervalPoint(17)));
        intervals.add(new Interval(new IntervalPoint(12),new IntervalPoint(22)));
        intervals.add(new Interval(new IntervalPoint(13),new IntervalPoint(14)));
        intervals.add(new Interval(new IntervalPoint(10),new IntervalPoint(15)));

        int [] maxInterval = findMaxInterval(intervals);
        System.out.println(maxInterval[0]);
        System.out.println(maxInterval[1]);

    }

    static void testOnRight() {
        GeometricAlgos geometricAlgos = new GeometricAlgos();
        System.out.println(geometricAlgos.isOnRight(new Point(1, 0), new Point(1, 1)));
    }

    static void testDistanceFromPointToLine() {
        System.out.println(distanceFromPointToLine(new Point(0, 0), new Point(1, 1), new Point(1, 0)));
    }

    static void testPolarAngles() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0,1));
        points.add(new Point(1,1));
        points.add(new Point(1,0));
        points.add(new Point(-1,1));

        Collections.sort(points,new PolarAngleComparator(new Point(0,0)));

        for (Point point : points) {
            System.out.println(point.getX() + "," + point.getY());
        }
    }

    static void testRightRayIntersect() {
        System.out.println(rightHorizontolRayIntersect(new Point(1, 1), new Point(0, 2), new Point(2, 0)));
    }
    public static void main(String[] args) {
        Point p1 = new Point(0,0);


        Point p2 = new Point(1,1);


        Point p3 = new Point(1,0);

        Point p4 = new Point(0,1);

        Line l1 = new Line(p1,p2);

        Line l2 = new Line(p3,p4);

        GeometricAlgos geometricAlgos = new GeometricAlgos();
        //System.out.println(geometricAlgos.isLineIntersect(l1, l2));

        ArrayList<Point> arrayList = new ArrayList<>();
        arrayList.add(new Point(-2,-2));
        arrayList.add(new Point(0,0));
        arrayList.add(new Point(1,1));
        arrayList.add(new Point(2,2));
        arrayList.add(new Point(3,3));
        arrayList.add(new Point(4,4));
        arrayList.add(new Point(-1,-1));

        //System.out.println(geometricAlgos.isCollinear(arrayList,0,arrayList.size()-1));

        //testOpenedInterval();
        //testOnRight();

        //testPolarAngles();
        //testRightRayIntersect();

        testDistanceFromPointToLine();

    }


    static int [] findMaxInterval(ArrayList<Interval> intervals) {
        HashMap<IntervalPoint,Interval> intervalPointLowPoint = new HashMap<>();
        HashMap<IntervalPoint,Interval> intervalPointHighPoint = new HashMap<>();
        ArrayList<IntervalPoint> intervalPointArrayList = new ArrayList<>();
        for (Interval interval : intervals) {
            intervalPointLowPoint.put(interval.getLow(),interval);
            intervalPointHighPoint.put(interval.getHigh(),interval);
            intervalPointArrayList.add(interval.getLow());
            intervalPointArrayList.add(interval.getHigh());
        }

        Collections.sort(intervalPointArrayList);

        int maxIntervalSize = 0;
        int [] maxInterval = new int[2];
        int lastOpenedPoint = 0;

        HashSet<IntervalPoint> openedIntervals = new HashSet<>();
        for (IntervalPoint intervalPoint : intervalPointArrayList) {
            if (intervalPointLowPoint.containsKey(intervalPoint)) {
                //An interval is opened update last opened point
                openedIntervals.add(intervalPoint);
                lastOpenedPoint = intervalPoint.getP();
            }else {
                //An interval is closed
                int overlapIntervalSize = openedIntervals.size();
                if (overlapIntervalSize > maxIntervalSize) {
                    maxIntervalSize = overlapIntervalSize;
                    maxInterval[0] = lastOpenedPoint;
                    maxInterval[1] = intervalPoint.getP();

                }

                openedIntervals.remove(intervalPoint);



            }
        }


        return maxInterval;
    }





}
