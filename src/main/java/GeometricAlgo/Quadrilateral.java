package GeometricAlgo;


import java.util.ArrayList;

public class Quadrilateral {

    private ArrayList<Point> pointArrayList;

    public Quadrilateral(ArrayList<Point> pointArrayList) {
        this.pointArrayList = pointArrayList;
    }

    public ArrayList<Point> getPointArrayList() {
        return pointArrayList;
    }

    public void setPointArrayList(ArrayList<Point> pointArrayList) {
        this.pointArrayList = pointArrayList;
    }

    double area() {
        //Divide into two triangles
        double area1 = GeometricAlgos.ccw(pointArrayList.get(0),pointArrayList.get(1),pointArrayList.get(2));
        double area2 = GeometricAlgos.ccw(pointArrayList.get(0),pointArrayList.get(2),pointArrayList.get(3));
        return Math.abs(area1) + Math.abs(area2);
    }

    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0,0));
        points.add(new Point(0,1));
        points.add(new Point(1,1));
        points.add(new Point(1,0));
        Quadrilateral quadrilateral = new Quadrilateral(points);
        System.out.println(quadrilateral.area());
    }
}
