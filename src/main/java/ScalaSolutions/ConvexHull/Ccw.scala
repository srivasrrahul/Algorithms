package ScalaSolutions.ConvexHull

/**
 * Created by rasrivastava on 10/20/16.
 */
class Point(val x : Double,val y : Double) {

}
//ax ay 1
//bx by 1
//cx cy 1
class Ccw(val a : Point,val b : Point,val c : Point) {
  val ccwValue : Double = (b.x-a.x)*(c.y-a.y)- (b.y-a.y)*(c.x-a.x)
  def isCounterClockWise() : Boolean = {
    ccwValue >= 0
  }

  def isCollinear() : Boolean  = {
    ccwValue.compareTo(0.0) == 0
  }
}

object CcwTest extends App {
  val a = new Point (1,0)
  val b = new Point(0.5,0.5)
  val c = new Point(0,0)
  println(new Ccw(a,b,c).isCounterClockWise())

}
