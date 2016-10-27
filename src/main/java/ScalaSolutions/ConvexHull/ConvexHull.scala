package ScalaSolutions.ConvexHull

import scala.collection.mutable

/**
 * Created by rasrivastava on 10/20/16.
 */
object LowestYCoordinateSortedOrder extends Ordering[Point]{
  override def compare(x: Point, y: Point): Int = {
    x.y compare y.y
  }
}

//Assume everybody is positive
object LowestPolarAngleSortedOrder extends Ordering[Point] {
  override def compare(x: Point, y: Point): Int = {
    getSlope(x) compare getSlope(y)
  }

  def getSlope(point : Point) : Double = {
    Math.atan(point.y/point.x)
  }
}
class ConvexHull(points : Array[Point]) {

  scala.util.Sorting.quickSort(points)(LowestYCoordinateSortedOrder)
  val polarRangeSorted = new Array[Point](points.size-1)
  Array.copy(points,1,polarRangeSorted,0,polarRangeSorted.size)
  scala.util.Sorting.quickSort(polarRangeSorted)(LowestPolarAngleSortedOrder)
  Array.copy(polarRangeSorted,0,points,1,polarRangeSorted.size)

  def getHull() : List[Point] = {
    val stack = new mutable.Stack[Point]()
    stack.push(points(0))
    stack.push(points(1))

    for (i <- 2 to points.size - 1) {
      var top = stack.pop()
      val peekedPoint = stack.head
      while (new Ccw(peekedPoint, top, points(i)).isCounterClockWise() == false) {
        top = stack.pop
      }

      stack.push(top)
      stack.push(points(i))

    }

    stack.toList
  }



}


