package Triangle;

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
}
public class TestClass {
    static double ccw(Point a ,Point b,Point c) {
        double val = (b.getX()-a.getX())*(c.getY()-a.getY())-(b.getY()-a.getY())*(c.getX()-a.getX());
        return val;
    }

    boolean isInsideTriangle(Point p1, Point p2, Point p3, Point x) {
        Double area = Math.abs(ccw(p1, p2, p3));
        Double a1 = Math.abs(ccw(x, p1, p2));
        Double a2 = Math.abs(ccw(x, p2, p3));
        Double a3 = Math.abs(ccw(x, p3, p1));

        //System.out.println(area + " " + a1 + " " + a2 + " " + a3);
        return area.equals(a1 + a2 + a3);


    }

    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();

            int n = Integer.parseInt(line);

            for (int k = 0;k<n;++k) {
                line = br.readLine();
                String [] val = line.split(" ");
                int i = 0;
                Point points[] = new Point[4];
                int j = 0;
                while (i < val.length) {
                    int x = Integer.parseInt(val[i++]);
                    int y = Integer.parseInt(val[i++]);
                    Point point = new Point(x,y);
                    points[j++] = point;

                }

                if (isInsideTriangle(points[0],points[1],points[2],points[3])) {
                    System.out.println("INSIDE");
                }else {
                    System.out.println("OUTSIDE");
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
