import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Solution {

    class Point{
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
    class Square {
        private Point lowerLeftPoint;
        private Point upperRightPoint;
        private long area;

        public Square(Point lowerLeftPoint, Point upperRightPoint) {
            this.lowerLeftPoint = lowerLeftPoint;
            this.upperRightPoint = upperRightPoint;
            area = getAreaInternal();
        }

        public Point getLowerLeftPoint() {
            return lowerLeftPoint;
        }

        public void setLowerLeftPoint(Point lowerLeftPoint) {
            this.lowerLeftPoint = lowerLeftPoint;
        }

        public Point getUpperRightPoint() {
            return upperRightPoint;
        }

        public void setUpperRightPoint(Point upperRightPoint) {
            this.upperRightPoint = upperRightPoint;
        }

        //x2 => x2-1
        //y2 => y2-1
        Square reduceRightDownLeft() {
            return
                    new Square(new Point(lowerLeftPoint.getX(),lowerLeftPoint.getY()),
                            new Point(upperRightPoint.getX()-1,upperRightPoint.getY()-1));



        }

        //x1 => x1+1
        //y1 => y1+1
        Square reduceLeftUpRight() {
            return new Square(new Point(lowerLeftPoint.getX()+1,lowerLeftPoint.getY()+1),
                    new Point(upperRightPoint.getX(),upperRightPoint.getY()));


        }

        //reduce
        Square reduceLeftDownRight() {
            return new Square(new Point(lowerLeftPoint.getX(),lowerLeftPoint.getY()+1),
                    new Point(upperRightPoint.getX()-1,upperRightPoint.getY()));
        }


        Square reduceLeftDownUp() {
            return new Square(new Point(lowerLeftPoint.getX()+1,lowerLeftPoint.getY()),
                    new Point(upperRightPoint.getX(),upperRightPoint.getY()-1));
        }





        private long getAreaInternal() {
            long diffX = getUpperRightPoint().getX()-getLowerLeftPoint().getX();
            long diffY = getUpperRightPoint().getY()-getLowerLeftPoint().getY();
            return Math.abs(diffX*diffY);
        }

        long getArea() {
            return area;
        }

        @Override
        public String toString() {
            return "Square{" +
                    "lowerLeftPoint=" + lowerLeftPoint.getX() + "," + lowerLeftPoint.getY() + "  " +
                    ", upperRightPoint=" + upperRightPoint.getX() + "," + upperRightPoint.getY() +
                    ", area=" + area +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Square square = (Square) o;

            if (lowerLeftPoint != null ? !lowerLeftPoint.equals(square.lowerLeftPoint) : square.lowerLeftPoint != null)
                return false;
            if (upperRightPoint != null ? !upperRightPoint.equals(square.upperRightPoint) : square.upperRightPoint != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = lowerLeftPoint != null ? lowerLeftPoint.hashCode() : 0;
            result = 31 * result + (upperRightPoint != null ? upperRightPoint.hashCode() : 0);
            return result;
        }
    }

    static boolean pointInsideSquare(Point point,Square square) {
        //All conditions needs to be satisfied
        return point.getX() > square.getLowerLeftPoint().getX() && point.getX() < square.getUpperRightPoint().getX()
                && point.getY() > square.getLowerLeftPoint().getY() && point.getY() < square.getUpperRightPoint().getY();

    }

    Point findLeftMostPoint(ArrayList<Point> points) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Point point : points) {

            if (point.getX() < minX) {
                minX = point.getX();
            }

            if (point.getY() < minY) {
                minY = point.getY();
            }
        }

        int minValue = minX < minY?minX:minY;
        return new Point(minValue-1,minValue-1);
    }

    Point findRightMostPoint(ArrayList<Point> points) {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Point point : points) {
            if (point.getX() > maxX) {
                maxX = point.getX();
            }

            if (point.getY() > maxY) {
                maxY = point.getY();
            }
        }

        int maxValue = maxX > maxY?maxX:maxY;
        return new Point(maxValue+1,maxValue+1);
    }

    static Square minAreaInternal(ArrayList<Point> points,int k,Square square,HashMap<Square,Square> visisted) {

        //System.out.println("Inside square + " + square.toString());
        if (visisted.containsKey(square)) {
            //System.out.println("Cache hit");
            return visisted.get(square);
        }

        int pointsContained = 0;
        for (Point point : points) {
            if (pointInsideSquare(point, square)) {
                ++pointsContained;
            }
        }

        if (pointsContained < k) {
           // System.out.println("Less points contained ");
            //Path is wrong back track
            return null;
        }

        if (square.getArea() == 9) {
            //Unit square
            return square;
        }

        TreeMap<Long,Square> treeMap = new TreeMap<>();

        Square option5 = minAreaInternal(points,k,square.reduceLeftDownRight(),visisted);
        if (option5 != null) {
            treeMap.put(option5.getArea(),option5);
        }

        Square option6 = minAreaInternal(points,k,square.reduceLeftDownUp(),visisted);
        if (option6 != null) {
            treeMap.put(option6.getArea(),option6);
        }

        Square option1 = minAreaInternal(points,k,square.reduceLeftUpRight(),visisted);
        if (option1 != null) {
            treeMap.put(option1.getArea(), option1);
        }

        Square option2 = minAreaInternal(points,k,square.reduceRightDownLeft(),visisted);
        if (option2 != null) {
            treeMap.put(option2.getArea(),option2);
        }


        if (treeMap.size() == 0) {
            visisted.put(square,square);
            return square;
        }

        visisted.put(square,treeMap.firstEntry().getValue());
        return treeMap.firstEntry().getValue();

    }

    static int minarea(int[] x, int[] y, int k) {
        if (k == 1) {
            //For a single point no point in looping
            return 4;
        }

        Solution minimumArea = new Solution();
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0;i<x.length;++i) {
            points.add(minimumArea.new Point(x[i],y[i]));
        }

        HashMap<Square,Square> hashMap = new HashMap<>();
        Square maxSquare = minimumArea.new Square(minimumArea.findLeftMostPoint(points),minimumArea.findRightMostPoint(points));
        Square minSquare = minAreaInternal(points,k,maxSquare,hashMap);
        //System.out.println(minSquare.getArea() + minSquare.toString());
        return (int)minSquare.getArea();
    }
    public static void main(String[] args) throws IOException {

        Solution m = new Solution();
        //System.out.println(primacity.checkPrimacityOfNumbers(1000000, 1000000, 1));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String line = br.readLine();
        //int [] lengths = new int[]{26,103,59};
        //System.out.println(m.maxProfit(100,10,lengths));
        System.out.println(minarea(new int[]{-200, 200,600}, new int[]{-400, 400,600}, 2));

    }
}
