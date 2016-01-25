package Squares;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

/**
 * Created by rasrivastava on 4/28/15.
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

    Point incrementRight() {
        return  new Point(x+1,y);
    }

    Point incrementUp() {
        return new Point(x,y+1);
    }

    Point decrementRight() {
        return new Point(x-1,y);
    }

    Point decrememtDown() {
        return new Point(x,y-1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

class ConnectedPoints {
    private Point p1;
    private Point p2;

    public ConnectedPoints(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectedPoints that = (ConnectedPoints) o;

        if (!p1.equals(that.p1)) return false;
        return p2.equals(that.p2);

    }

    @Override
    public int hashCode() {
        int result = p1.hashCode();
        result = 31 * result + p2.hashCode();
        return result;
    }
}
public class Main {
    boolean isPointValid(Point p,int squareSize) {
        if (p.getX() < 0 || p.getY() < 0 || p.getX() >= squareSize || p.getY() >= squareSize) {
            return false;
        }

        return true;
    }
    //Checks x,y has square of size with x,y being leftmost and bottommost point
    boolean squareExists(int x,int y,int size,int squareSize,HashSet<ConnectedPoints> paths) {
        //Check valid point
        if (x < 0 || y < 0 || x >= squareSize || y>=squareSize) {
            //Out of bounds
            return false;
        }

        Point currentPoint = new Point(x,y);
        Point updatedPoint = new Point(x,y);
        //Check on right path
        for (int i = 0;i<size;++i) {
            updatedPoint = currentPoint.incrementRight();
            ConnectedPoints connectedPoints = new ConnectedPoints(currentPoint,updatedPoint);
            ConnectedPoints connectedPointsRev = new ConnectedPoints(updatedPoint,currentPoint);
            if (isPointValid(updatedPoint,squareSize) && (paths.contains(connectedPoints) || paths.contains(connectedPointsRev))) {
                currentPoint = updatedPoint;

            }else {
                return false;
            }

        }

        //Check on above path
        for (int i = 0;i<size;++i) {
            updatedPoint = currentPoint.incrementUp();
            ConnectedPoints connectedPoints = new ConnectedPoints(currentPoint,updatedPoint);
            ConnectedPoints connectedPointsRev = new ConnectedPoints(updatedPoint,currentPoint);
            if (isPointValid(updatedPoint,squareSize) && (paths.contains(connectedPoints) || paths.contains(connectedPointsRev))) {
                currentPoint = updatedPoint;

            }else {
                return false;
            }


        }

        //Check on left path
        for (int i = 0;i<size;++i) {
            updatedPoint = currentPoint.decrementRight();
            ConnectedPoints connectedPoints = new ConnectedPoints(currentPoint,updatedPoint);
            ConnectedPoints connectedPointsRev = new ConnectedPoints(updatedPoint,currentPoint);
            if (isPointValid(updatedPoint,squareSize) && (paths.contains(connectedPoints) || paths.contains(connectedPointsRev))) {
                currentPoint = updatedPoint;
            }else {
                return false;
            }


        }
        //Check on down path
        for (int i = 0;i<size;++i) {
            updatedPoint = currentPoint.decrememtDown();
            ConnectedPoints connectedPoints = new ConnectedPoints(currentPoint,updatedPoint);
            ConnectedPoints connectedPointsRev = new ConnectedPoints(updatedPoint,currentPoint);
            if (isPointValid(updatedPoint,squareSize) && (paths.contains(connectedPoints) || paths.contains(connectedPointsRev))) {
                currentPoint = updatedPoint;

            }else {
                return false;
            }


        }

        return true;


    }
    int countSquares(int squareSize,HashSet<ConnectedPoints> paths) {
        int count = 0;
        for (int i = 0;i<squareSize;++i) {
            for (int j = 0;j<squareSize;++i) {
                for (int k = 0;k<squareSize;k++) {

                }

            }
        }

        return 0;
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
