package ClosestPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Rahul on 4/28/15.
 */
class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Double getDistance(Point p1) {
        int x = p1.getX()-getX();
        int y = p1.getY()-getY();
        return Math.sqrt(x*x+y*y);
    }
}

class RetValue {
    private Double distance;
    private Point point1;
    private Point point2;

    public RetValue(Double distance, Point point1, Point point2) {
        this.distance = distance;
        this.point1 = point1;
        this.point2 = point2;
    }

    public Double getDistance() {
        return distance;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }
}
class XAxisComparator implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {
        return Integer.compare(o1.getX(),o1.getY());
    }
}

class YAxisComparator implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {
        return Integer.compare(o1.getY(),o2.getY());
    }
}
public class Main {

    RetValue minDistance(ArrayList<Point> points) {
        if (points.size() == 2) {
            return new RetValue(points.get(0).getDistance(points.get(1)),points.get(0),points.get(1));
        }

        ArrayList<Point> copyPoints = new ArrayList<>();
        for (Point point : points) {
            copyPoints.add(point);
        }
        Collections.sort(copyPoints, new XAxisComparator());
//        Collections.sort(points, new YAxisComparator());
        int mid = copyPoints.size() / 2;
        int xMid = copyPoints.get(mid).getX();
        ArrayList<Point> lst1 = (ArrayList<Point>)copyPoints.subList(0,mid);
        ArrayList<Point> lst2 = (ArrayList<Point>)copyPoints.subList(mid,points.size());
        RetValue retValue1 = minDistance(lst1);
        RetValue retValue2 = minDistance(lst2);

        Double minDistance = retValue1.getDistance().compareTo(retValue2.getDistance()) > 0?retValue2.getDistance():retValue1.getDistance();
        int minDistanceInt = minDistance.intValue()+1;
        int minDistanceLowerBound = xMid - minDistanceInt;
        int minDistanceUpperBound = xMid + minDistanceInt;
        ArrayList<Point> pointsInRange = new ArrayList<>();
        for (Point point : points) {
            if (point.getX() >= minDistanceLowerBound && point.getY() <= minDistanceUpperBound) {
                pointsInRange.add(point);
            }
        }

        Collections.sort(pointsInRange,new YAxisComparator());
        Double closesetDistance = null;
        Point pLow = null,pHigh= null;
        for (int i = 0;i<pointsInRange.size();++i) {
            Point point = pointsInRange.get(i);

            Double dist1  = Double.MAX_VALUE;

            Point p1 = null;
            if (i+1 < pointsInRange.size()) {
                p1 = pointsInRange.get(i+1);
                dist1 = point.getDistance(p1);
                if (dist1.compareTo(closesetDistance) < 0) {
                    closesetDistance = dist1;
                    pLow = point;
                    pHigh = p1;
                }
            }

            Double dist2  = Double.MAX_VALUE;
            Point p2 = null;
            if (i+2 < pointsInRange.size()) {
                p2 = pointsInRange.get(i+2);
                dist2 = point.getDistance(p2);
                if (dist2.compareTo(closesetDistance) < 0) {
                    closesetDistance = dist2;
                    pLow = point;
                    pHigh = p2;
                }
            }

            Double dist3  = Double.MAX_VALUE;
            Point p3 = null;
            if (i+3 < pointsInRange.size()) {
                p3 = pointsInRange.get(i+3);
                dist3 = point.getDistance(p3);
                if (dist3.compareTo(closesetDistance) < 0) {
                    closesetDistance = dist3;
                    pLow = point;
                    pHigh = p3;
                }
            }


            Double dist4  = Double.MAX_VALUE;
            Point p4 = null;
            if (i+4 < pointsInRange.size()) {
                p4 = pointsInRange.get(i+4);
                dist4 = point.getDistance(p4);
                if (dist4.compareTo(closesetDistance) < 0) {
                    closesetDistance = dist4;
                    pLow = point;
                    pHigh = p4;
                }
            }

            Double dist5  = Double.MAX_VALUE;
            Point p5 = null;
            if (i+5 < pointsInRange.size()) {
                p5 = pointsInRange.get(i+5);
                dist5 = point.getDistance(p5);
                if (dist5.compareTo(closesetDistance) < 0) {
                    closesetDistance = dist5;
                    pLow = point;
                    pHigh = p5;
                }
            }

            Double dist6  = Double.MAX_VALUE;
            Point p6 = null;
            if (i+6 < pointsInRange.size()) {
                p6 = pointsInRange.get(i+6);
                dist6 = point.getDistance(p6);
                if (dist6.compareTo(closesetDistance) < 0) {
                    closesetDistance = dist6;
                    pLow = point;
                    pHigh = p6;
                }
            }

            Double dist7  = Double.MAX_VALUE;
            Point p7 = null;
            if (i+7 < pointsInRange.size()) {
                p7 = pointsInRange.get(i+7);
                dist7 = point.getDistance(p7);
                if (dist3.compareTo(closesetDistance) < 0) {
                    closesetDistance = dist7;
                    pLow = point;
                    pHigh = p7;
                }
            }
        }

        ArrayList<Double> distances = new ArrayList<>();
        distances.add(retValue1.getDistance());
        distances.add(retValue2.getDistance());
        distances.add(closesetDistance);

        Collections.sort(distances);

        if (distances.get(0) == retValue1.getDistance()) {
            return retValue1;
        }

        if (distances.get(0) == retValue2.getDistance()) {
            return retValue2;
        }

        return new RetValue(closesetDistance,pLow,pHigh);







    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {


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
