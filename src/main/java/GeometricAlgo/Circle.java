package GeometricAlgo;

/**
 * Created by rasrivastava on 6/23/15.
 */
public class Circle {
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

class CircleAlgorithm {
    double distance(Point p1,Point p2) {
        double xDistance = p1.getX() - p2.getX();
        double yDistance = p1.getY() - p2.getY();
        return Math.sqrt(xDistance*xDistance+ yDistance*yDistance);
    }
    //Intersect not touch
    boolean intersect(Circle c1,Circle c2) {
        double dist = distance(c1.getCentre(),c2.getCentre());
        return Double.compare(dist,(c1.getRadius()+c2.getRadius())) > 0;
    }


}
